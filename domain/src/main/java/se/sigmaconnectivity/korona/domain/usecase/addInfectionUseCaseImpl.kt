package se.sigmaconnectivity.korona.domain.usecase

import io.reactivex.schedulers.Schedulers
import se.sigmaconnectivity.korona.domain.InfectionsRepository
import se.sigmaconnectivity.korona.domain.executor.PostExecutionThread
import se.sigmaconnectivity.korona.domain.model.InfectionItem

class AddInfectionUseCase(
    private val postExecutionThread: PostExecutionThread,
    private val repository: InfectionsRepository
){

    fun execute(infection: InfectionItem) = repository.add(infection)
        .subscribeOn(Schedulers.io())
        .observeOn(postExecutionThread.scheduler)
}