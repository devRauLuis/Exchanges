package com.devaruluis.exchanges.di

import com.devaruluis.exchanges.data.network.CoinApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val COIN_API_URL = "https://coinsapp-pa2.herokuapp.com/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient().newBuilder()
            .addInterceptor(logging).build()
    }

    @Provides
    @Named("coinApiGson")
    fun provideCoinApiGson(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    @Named("coinApiRetrofit")
    fun provideCoinApiRetrofit(
        @Named("coinApiGson") gson: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder().baseUrl(
        COIN_API_URL
    ).addConverterFactory(gson).client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideCoinApiClient(@Named("coinApiRetrofit") retrofit: Retrofit): CoinApiClient {
        return retrofit.create(CoinApiClient::class.java)
    }
}