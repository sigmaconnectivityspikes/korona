package se.sigmaconnectivity.korona.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.*
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val isPending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer<T> { t ->
            if (isPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T) {
        isPending.set(true)
        super.setValue(t)
    }

    @MainThread
    fun <Y> mapSingle(mapFunction: (T) -> Y): LiveData<Y> {
        val result = MediatorLiveData<Y>()
        result.addSource(this) { x ->
            if (isPending.compareAndSet(true, false)) {
                result.value = mapFunction(x)
            }
        }
        return result
    }
}