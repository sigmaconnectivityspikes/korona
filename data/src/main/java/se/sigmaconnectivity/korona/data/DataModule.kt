package se.sigmaconnectivity.korona.data

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import se.sigmaconnectivity.korona.domain.InfectionsRepository

val dataModule = module {
    single { Firebase.firestore }
    single<InfectionsRepository> { InfectionRepositoryImpl(get()) }
}