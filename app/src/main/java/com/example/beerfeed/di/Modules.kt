package com.example.beerfeed.di

import androidx.room.Room
import com.example.beerfeed.data.local.BeersDatabase
import com.example.beerfeed.data.remote.BeersApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.punkapi.com/v2/"
private const val DB_NAME = "BeersDatabase"

val networkModule = module {

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BASIC }
                    .also { this.addInterceptor(it) }
            }.build()
    }

    fun provideBeersApi(retrofit: Retrofit): BeersApiService =
        retrofit.create(BeersApiService::class.java)

    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { provideBeersApi(get()) }

}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            BeersDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    single { get<BeersDatabase>().beersDao() }

}

