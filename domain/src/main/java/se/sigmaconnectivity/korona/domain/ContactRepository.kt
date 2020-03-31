package se.sigmaconnectivity.korona.domain

import io.reactivex.Completable
import io.reactivex.Single
import se.sigmaconnectivity.korona.domain.entity.Entity

interface ContactRepository {
    fun deleteContact(contact: Entity.Contact): Completable
    fun saveContact(contact: Entity.Contact): Completable
    fun getDevicesCount(): Single<Int>
    fun getContactByHashOrNew(hash: String): Single<Entity.Contact>
}