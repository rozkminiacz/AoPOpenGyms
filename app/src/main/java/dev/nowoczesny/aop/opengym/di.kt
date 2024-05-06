package dev.nowoczesny.aop.opengym

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
            networkService = get()
        )
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

    viewModel {
        PlaceDetailViewModel(
            fetchGymById = get(),
            stateHandle = get()
        )
    }

    viewModel {
        PlaceListViewModel(
            fetchAllGymData = get()
        )
    }
}