package se.sigmaconnectivity.korona

import com.polidea.rxandroidble2.RxBleClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import se.sigmaconnectivity.korona.domain.executor.PostExecutionThread
import org.koin.androidx.viewmodel.dsl.viewModel
import se.sigmaconnectivity.korona.domain.usecase.AddInfectionUseCase

val appModule = module {
    single { RxBleClient.create(androidApplication()) }
    factory<PostExecutionThread> { PostExecutionThread() }
    viewModel  {MainViewModel(get()) }
    factory { AddInfectionUseCase(get(), get()) }

}