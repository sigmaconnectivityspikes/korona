package se.sigmaconnectivity.korona.data.mapper

import se.sigmaconnectivity.korona.domain.model.InfectionItem

private const val HASH_ID_FIELD_KEY = "hashId"

fun InfectionItem.domainToDb() =
    hashMapOf(HASH_ID_FIELD_KEY to hashId)