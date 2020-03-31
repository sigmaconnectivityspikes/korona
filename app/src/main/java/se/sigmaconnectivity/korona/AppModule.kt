package se.sigmaconnectivity.korona

import com.polidea.rxandroidble2.RxBleClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import se.sigmaconnectivity.korona.domain.executor.PostExecutionThread
import se.sigmaconnectivity.korona.domain.usecase.ContactUseCase
import se.sigmaconnectivity.korona.domain.usecase.ContactUseCaseImpl

val appModule = module {
    single { RxBleClient.create(androidApplication()) }
    factory<PostExecutionThread> { PostExecutionThread() }
    factory<ContactUseCase> { ContactUseCaseImpl(get(), get()) }
}