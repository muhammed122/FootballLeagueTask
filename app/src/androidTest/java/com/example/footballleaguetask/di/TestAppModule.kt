package com.example.footballleaguetask.di

import android.content.Context
import androidx.room.Room
import com.example.footballleaguetask.data.repository.FakeRepository
import com.example.footballleaguetask.data.repository.RepositoryImpl
import com.example.footballleaguetask.data.source.datasource.FakeRemoteDataSource
import com.example.footballleaguetask.data.source.datasource.RemoteDataSource
import com.example.footballleaguetask.data.source.local.AppDatabase
import com.example.footballleaguetask.data.source.local.AreaDao
import com.example.footballleaguetask.domain.repository.Repository
import com.example.footballleaguetask.utils.FakeNetworkChecker
import com.example.utils.network.NetworkChecker
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestAppModule {

    @Provides
    @Singleton
    fun provideFakeRemoteDataSource(): FakeRemoteDataSource = FakeRemoteDataSource()

    @Provides
    @Singleton
    fun provideInMemoryDb(@ApplicationContext context: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideAreaDao(db: AppDatabase): AreaDao = db.areaDao()


    @Provides
    @Singleton
    fun provideFakeNetworkChecker(): NetworkChecker = FakeNetworkChecker()
}

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [BindAppModule::class]
)
abstract class TestBindAppModule {
    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(fake: FakeRemoteDataSource): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindRepository(impl: FakeRepository): Repository
}