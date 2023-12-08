package com.example.harrypotter.di

import com.example.harrypotter.apiService.ApiServices
import com.example.harrypotter.apiService.RetrofitUtils.retrofitInstance
import com.example.harrypotter.utils.ConstraintUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("apiHarryPorter")
    fun provideBaseUrl() = ConstraintUtils.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        @Named("apiHarryPorter")
        baseUrl : String,
        okHttpClient : OkHttpClient,
        cache: Cache
    ) : ApiServices = retrofitInstance(baseUrl,okHttpClient, cache).create(ApiServices::class.java)
}