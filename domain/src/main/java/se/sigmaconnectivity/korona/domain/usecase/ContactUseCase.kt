package se.sigmaconnectivity.korona.domain.usecase

import io.reactivex.Completable
import io.reactivex.Single
import se.sigmaconnectivity.korona.domain.entity.Entity

interface ContactUseCase {
    fun saveContact(contact: Entity.Contact): Completable
    fun deleteContact(contact: Entity.Contact): Completable
    fun getContactByHashOrNew(hash: String): Single<Entity.Contact>
    fun getDevicesCount(): Single<Int>
    fun processContactMatch(hash: String, timestamp: Long): Completable
    fun processContactLost(hash: String, timestamp: Long): Completable
}