package se.sigmaconnectivity.korona.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import se.sigmaconnectivity.korona.data.entity.Contact

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDevice(contact: Contact)

    @Delete
    fun delete(contact: Contact)

    @Update
    fun update(contact: Contact)

    @Query("SELECT * FROM CONTACTS_TABLE WHERE hash == (:infectedHash) LIMIT 1")
    fun getContactByHash(infectedHash: String) : Contact?

    @Query("SELECT COUNT(*) FROM CONTACTS_TABLE")
    fun count() : Int
}