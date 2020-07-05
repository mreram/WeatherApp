package com.eram.data.di

import android.content.Context
import com.eram.data.ErrorHandler
import com.eram.data.RepositoryImpl
import com.eram.data.local.LocalDataSource
import com.eram.data.local.LocalDataSourceImpl
import com.eram.data.local.PreferencesHelper
import com.eram.data.remote.OpenWeatherApiService
import com.eram.data.remote.RemoteDataSource
import com.eram.data.remote.RemoteDataSourceImpl
import com.eram.domain.Repository
import com.eram.domain.exceptions.IErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSourceImpl: RemoteDataSourceImpl,
        localDataSourceImpl: LocalDataSourceImpl
    ): Repository {
        return RepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        preferencesHelper: PreferencesHelper
    ): LocalDataSource {
        return LocalDataSourceImpl(preferencesHelper)
    }

    @Singleton
    @Provides
    fun providePreferencesHelper(@ApplicationContext context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        @ApplicationContext context: Context,
        weatherApiService: OpenWeatherApiService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(context, weatherApiService)
    }

    @Singleton
    @Provides
    fun provideErrorHandler(): IErrorHandler {
        return ErrorHandler()
    }

}