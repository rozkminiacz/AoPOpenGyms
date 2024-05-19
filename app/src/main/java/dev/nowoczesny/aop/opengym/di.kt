package dev.nowoczesny.aop.opengym

import dev.nowoczesny.aop.opengym.data.DatabaseModule
import dev.nowoczesny.aop.opengym.data.GymDao
import dev.nowoczesny.aop.opengym.data.NetworkModule
import dev.nowoczesny.aop.opengym.data.NetworkService
import dev.nowoczesny.aop.opengym.data.RemoteGymService
import dev.nowoczesny.aop.opengym.domain.FetchAllGymData
import dev.nowoczesny.aop.opengym.domain.FetchGymById
import dev.nowoczesny.aop.opengym.domain.GymService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<NetworkService> { NetworkModule().networkService }

    single<GymService> {
        RemoteGymService(
            networkService = get(),
            gymDao = get()
        )
    }

    single<GymDao> {
        DatabaseModule(applicationContext = get()).db.gymDao()
    }

    single {
        FetchAllGymData(
            service = get()
        )
    }

    single {
        FetchGymById(
            service = get()
        )
    }

    single {
        SearchGymData(gymService = get())
    }

    single { SearchHistory() }

    viewModel {
        PlaceDetailViewModel(
            fetchGymById = get(),
            stateHandle = get()
        )
    }

    viewModel {
        PlaceListViewModel(
            fetchAllGymData = get(),
            searchHistory = get(),
            searchGymData = get()
        )
    }
}