package se.sigmaconnectivity.korona

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import se.sigmaconnectivity.korona.domain.model.InfectionItem
import se.sigmaconnectivity.korona.domain.usecase.AddInfectionUseCase
import timber.log.Timber

class MainViewModel(private val addInfectionUseCase: AddInfectionUseCase) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    fun onHashIdSet(hashId: String) {
        addInfectionUseCase.execute(infection = InfectionItem(hashId = hashId))
            .subscribe({
                Timber.d("Infection added")
            }, {
                Timber.e(it, "Couldn't add infection to db")
            }).addTo(compositeDisposable)
    }
}