package com.example.retrofitcopy.di

import com.example.retrofitcopy.api.CovidApi
import com.example.retrofitcopy.feature.data.countries.CountriesRemoteDataSource
import com.example.retrofitcopy.feature.data.countries.CountriesRepositoryImpl
import com.example.retrofitcopy.feature.data.countries.RetrofitCountriesRemoteDataSource
import com.example.retrofitcopy.feature.data.details.DetailsRemoteDataSource
import com.example.retrofitcopy.feature.data.details.DetailsRepositoryImpl
import com.example.retrofitcopy.feature.data.details.RetrofitDetailsRemoteDataSource
import com.example.retrofitcopy.feature.domain.countries.GetCountriesUseCase
import com.example.retrofitcopy.feature.domain.countries.CountriesRepository
import com.example.retrofitcopy.feature.domain.details.DetailsRepository
import com.example.retrofitcopy.feature.domain.details.GetDetailsUseCase
import com.example.retrofitcopy.feature.presentation.countries.CountriesViewModel
import com.example.retrofitcopy.feature.presentation.details.DetailsViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("x-rapidapi-key", "08bed347a6msh3dd0a268fd9176dp18a720jsn4578e4b02e27")
            .addHeader("x-rapidapi-host", "covid-193.p.rapidapi.com")
            .build()
        return chain.proceed(request)
    }
}

val covidModule = module{

    factory {
        GetCountriesUseCase(
            repository = get()
        )
    }

    single<CountriesRepository>{
        CountriesRepositoryImpl(
            remoteDataSource = get()
        )
    }

    single<CountriesRemoteDataSource>{
        RetrofitCountriesRemoteDataSource(
            covidApi = get()
        )
    }

    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiInterceptor())
            .build()

        Retrofit.Builder()
//            .baseUrl("https://api.covid19api.com/")
            .baseUrl("https://covid-193.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single{
        get<Retrofit>().create<CovidApi>(CovidApi::class.java)
    }

    viewModel<CountriesViewModel> {
        CountriesViewModel(
            getCountriesUseCase = get()
        )
    }

    viewModel<DetailsViewModel> {
        DetailsViewModel(
            getDetailsUseCase = get()
        )
    }

    factory {
        GetDetailsUseCase(
            repository = get()
        )
    }

    single<DetailsRepository> {
        DetailsRepositoryImpl(
            remoteDataSource = get()
        )
    }

    single<DetailsRemoteDataSource>{
        RetrofitDetailsRemoteDataSource(
            covidApi = get()
        )
    }

}