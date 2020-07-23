package com.example.retrofit.di

import com.example.retrofit.api.QuoteApi
import com.example.retrofit.feature.data.QuoteRemoteDataSource
import com.example.retrofit.feature.data.QuoteRemoteDataSourceImpl
import com.example.retrofit.feature.data.QuoteRepositoryImpl
import com.example.retrofit.feature.domain.GetQuoteOfDayUseCase
import com.example.retrofit.feature.domain.QuoteRepository
import com.example.retrofit.feature.presentation.QuoteOfDayViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiInterceptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("api-key", "my_secret_key")
            .build()
        return chain.proceed(request)
    }
}
val quoteModule = module {

    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.forismatic.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single {
        get<Retrofit>().create<QuoteApi>(QuoteApi::class.java)
    }

    single<QuoteRemoteDataSource> {
        QuoteRemoteDataSourceImpl(
            quoteApi = get()
        )
    }

    single<QuoteRepository> {
        QuoteRepositoryImpl(
            remoteDataSource = get()
        )
    }

    factory {
        GetQuoteOfDayUseCase(
            repository = get()
        )
    }

    viewModel {
        QuoteOfDayViewModel(
            getQuoteOfDayUseCase = get()
        )
    }
}