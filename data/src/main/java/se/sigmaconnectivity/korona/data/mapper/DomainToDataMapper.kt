package se.sigmaconnectivity.korona.data.mapper

import se.sigmaconnectivity.korona.data.entity.Contact
import se.sigmaconnectivity.korona.domain.entity.Entity

fun Entity.Contact.domainToData() = Contact(
    hash, lastTimeStamp, contactCounter, totalContactTime
)