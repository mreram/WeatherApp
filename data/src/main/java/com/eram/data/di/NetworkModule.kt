package com.eram.data.di

import android.content.Context
import com.eram.data.BuildConfig
import com.eram.data.R
import com.eram.data.remote.OpenWeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFoursquareService(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): OpenWeatherApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.weather_base_url))
            .addConverterFactory(gsonConverter)
            .client(okHttpClient)
            .build()
        return retrofit.create(OpenWeatherApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

}