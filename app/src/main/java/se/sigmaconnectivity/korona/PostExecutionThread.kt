package se.sigmaconnectivity.korona

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import se.sigmaconnectivity.korona.domain.executor.PostExecutionThread

class PostExecutionThread: PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}