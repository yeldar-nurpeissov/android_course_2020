package com.example.myapplication.di

import com.example.myapplication.api.CovidApi
import com.example.myapplication.feature.data.CountryRemoteDataSource
import com.example.myapplication.feature.data.CountryRemoteDataSourceImpl
import com.example.myapplication.feature.data.CountryRepositoryImpl
import com.example.myapplication.feature.domain.CountryRepository
import com.example.myapplication.feature.domain.GetAllCountriesUseCase
import com.example.myapplication.feature.domain.GetDetailOfCountryUseCase
import com.example.myapplication.feature.presentation.AllCountryViewModel
import com.example.myapplication.feature.presentation.DetailOfCountryViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("api-key", "my_secret_key")
            .build()
        return chain.proceed(request)
    }
}
val countryModule = module {
    val connectTimeout : Long = 40// 20s
    val readTimeout : Long  = 40 // 20s

    fun provideHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(ApiInterceptor())
            .build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single {
        val baseUrl="https://api.covid19api.com/"
        provideRetrofit(get(),baseUrl)
    }

    fun provideCovidApi(retrofit: Retrofit):CovidApi{
        return retrofit.create(CovidApi::class.java)
    }

    single {
        provideCovidApi(get())
    }

    single<CountryRemoteDataSource> {
        CountryRemoteDataSourceImpl(covidApi = get())
    }

    single<CountryRepository> {
        CountryRepositoryImpl(remoteDataSource = get())
    }

    factory {
        GetAllCountriesUseCase(
            repository = get()
        )
    }

    factory {
        GetDetailOfCountryUseCase(
            repository = get()
        )
    }

    viewModel {
        AllCountryViewModel(getAllCountriesUseCase = get())
    }

    viewModel {
        DetailOfCountryViewModel(getDetailOfCountryUseCase = get())
    }
}