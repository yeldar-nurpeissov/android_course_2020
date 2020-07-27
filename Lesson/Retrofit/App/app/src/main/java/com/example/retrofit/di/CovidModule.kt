package com.example.retrofit.di

import com.example.retrofit.api.CovidApi
import com.example.retrofit.feature.covid19.data.CovidRemoteDataSource
import com.example.retrofit.feature.covid19.data.CovidRemoteDataSourceRetrofit
import com.example.retrofit.feature.covid19.data.CovidRepositoryImpl
import com.example.retrofit.feature.covid19.domain.CovidRepository
import com.example.retrofit.feature.covid19.domain.GetCountriesUseCase
import com.example.retrofit.feature.covid19.domain.GetDetailsOfCountryUseCase
import com.example.retrofit.feature.covid19.presentation.countries.CovidCountriesViewModel
import com.example.retrofit.feature.covid19.presentation.detailed.CountryDetailsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val covidModule = module {
    single(named("covid")) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.covid19api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single {
        get<Retrofit>(named("covid")).create(CovidApi::class.java)
    }

    single<CovidRemoteDataSource> {
        CovidRemoteDataSourceRetrofit(
            api = get()
        )
    }

    single<CovidRepository>{
        CovidRepositoryImpl(
            remoteDataSource = get()
        )
    }

    factory {
        GetCountriesUseCase(
            repository = get()
        )
    }

    factory {
        GetDetailsOfCountryUseCase(
            repository = get()
        )
    }

    viewModel {
        CovidCountriesViewModel(
            getCountriesUseCase = get()
        )
    }

    viewModel { (slug: String) ->
        CountryDetailsViewModel(
            getDetailsOfCountryUseCase = get(),
            slug = slug
        )
    }
}