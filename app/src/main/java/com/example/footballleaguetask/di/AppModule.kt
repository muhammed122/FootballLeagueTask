package com.example.footballleaguetask.di

import android.content.Context
import androidx.room.Room
import com.example.footballleaguetask.data.repository.RepositoryImpl
import com.example.footballleaguetask.data.source.datasource.RemoteDataSource
import com.example.footballleaguetask.data.source.datasource.RemoteDataSourceImpl
import com.example.footballleaguetask.data.source.local.AppDatabase
import com.example.footballleaguetask.data.source.local.AreaDao
import com.example.footballleaguetask.data.source.remote.ApiService
import com.example.footballleaguetask.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAuthServices(
        retrofit: Retrofit,
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "football_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAreaDao(db: AppDatabase): AreaDao = db.areaDao()

}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindAppModule {
    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        dataSourceImpl: RemoteDataSourceImpl,
    ): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: RepositoryImpl,
    ): Repository
}