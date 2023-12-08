package com.example.harrypotter.apiService

import android.app.Application
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitUtils {

    @Provides
    @Singleton
    fun createCache(application: Application) : Cache{
        val cacheSize : Long= 5 * 1024 * 1024
        return Cache(File(application.cacheDir, "${application.packageName}.cache"),cacheSize)
    }
    @Provides
    @Singleton
    fun okHttpClient() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(1,TimeUnit.MINUTES)
            .connectTimeout(30,TimeUnit.SECONDS)
            .build()
    }

    fun retrofitInstance(baseUrl : String,okHttpClient : OkHttpClient,cache: Cache) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}