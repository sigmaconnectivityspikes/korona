package se.sigmaconnectivity.korona.data

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import se.sigmaconnectivity.korona.data.mapper.domainToDb
import se.sigmaconnectivity.korona.domain.InfectionsRepository
import se.sigmaconnectivity.korona.domain.model.InfectionItem
import timber.log.Timber

class InfectionRepositoryImpl(private val fireStore: FirebaseFirestore) : InfectionsRepository {
    override fun add(infection: InfectionItem) = Completable.create { emitter ->
        // change Completable.create since it is a RxJava
        fireStore.collection("infections")
            .add(infection.domainToDb()).addOnCompleteListener { task ->
                if (!emitter.isDisposed) {
                    if (task.isSuccessful)
                        emitter.onComplete()
                    else
                        emitter.onError(task.exception!!)
                }
            }
    }
}