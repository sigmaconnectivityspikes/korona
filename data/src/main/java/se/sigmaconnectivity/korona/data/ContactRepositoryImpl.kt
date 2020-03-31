package se.sigmaconnectivity.korona.data

import io.reactivex.Completable
import io.reactivex.Single
import se.sigmaconnectivity.korona.data.db.ContactDao
import se.sigmaconnectivity.korona.data.mapper.dataToDomain
import se.sigmaconnectivity.korona.data.mapper.domainToData
import se.sigmaconnectivity.korona.domain.ContactRepository
import se.sigmaconnectivity.korona.domain.entity.Entity
import timber.log.Timber

class ContactRepositoryImpl(private val contactDao: ContactDao) : ContactRepository {
    override fun getContactByHashOrNew(hash: String): Single<Entity.Contact> {
        return Single.fromCallable {
            contactDao.getContactByHash(hash)?.dataToDomain() ?: Entity.Contact(hash)
        }
    }

    override fun deleteContact(contact: Entity.Contact): Completable {
        return Completable.fromAction { contactDao.delete(contact.domainToData()) }
    }

    override fun saveContact(contact: Entity.Contact): Completable {
        return Completable.fromAction {
            Timber.d("saveDevice")
            contactDao.insertDevice(contact.domainToData())
        }
    }

    override fun getDevicesCount(): Single<Int> {
        return Single.fromCallable { contactDao.count() }
    }
}