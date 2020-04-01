package se.sigmaconnectivity.korona

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import se.sigmaconnectivity.korona.domain.model.InfectionItem
import se.sigmaconnectivity.korona.domain.usecase.AddInfectionUseCase
import se.sigmaconnectivity.korona.livedata.SingleLiveEvent
import timber.log.Timber

class MainViewModel(private val addInfectionUseCase: AddInfectionUseCase) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _scanQrCode = SingleLiveEvent<Unit>()
    val scanQrCode: LiveData<Unit> = _scanQrCode
    private val _scanQrCodeResult = SingleLiveEvent<String>()
    val scanQrCodeResult: LiveData<String> = _scanQrCodeResult

    fun onHashIdSet(hashId: String) {
        addInfectionUseCase.execute(infection = InfectionItem(hashId = hashId))
            .subscribe({
                Timber.d("Infection added")
            }, {
                Timber.e(it, "Couldn't add infection to db")
            }).addTo(compositeDisposable)
    }

    fun onStartScanQrCode() {
        _scanQrCode.setValue(Unit)
    }

    fun onScanQrCodeResult(result: String) {
        _scanQrCodeResult.setValue(result)
    }
}