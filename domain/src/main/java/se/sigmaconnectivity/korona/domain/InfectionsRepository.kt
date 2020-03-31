package se.sigmaconnectivity.korona.domain

import io.reactivex.Completable
import se.sigmaconnectivity.korona.domain.model.InfectionItem

interface InfectionsRepository {
    fun add(infection: InfectionItem): Completable
}