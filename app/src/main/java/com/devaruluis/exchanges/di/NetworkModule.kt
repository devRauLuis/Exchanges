package com.devaruluis.exchanges.di

import com.devaruluis.exchanges.data.network.CoinPaprikaApiClient
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
    private const val COIN_PAPRIKA_URL = "https://api.coinpaprika.com/"

    @Provides
    @Named("coinPaprikaGson")
    fun provideCoinPaprikaGson(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient().newBuilder()
            .addInterceptor(logging).build()
    }

    @Singleton
    @Provides
    @Named("coinPaprikaApiRetrofit")
    fun provideZenQuotesApiRetrofit(
        @Named("coinPaprikaGson") gson: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().baseUrl(COIN_PAPRIKA_URL)
            .addConverterFactory(gson).client(okHttpClient).build()


    @Provides
    @Singleton
    fun provideCoinPaprikaApiClient(@Named("coinPaprikaApiRetrofit") retrofit: Retrofit): CoinPaprikaApiClient {
        return retrofit.create(CoinPaprikaApiClient::class.java)
    }


}