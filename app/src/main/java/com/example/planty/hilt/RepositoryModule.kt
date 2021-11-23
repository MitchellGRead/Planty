package com.example.planty.hilt

import com.example.planty.domain.PlantEntryRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providePlantEntryRepo(
        @AppIoScope appIoScope: CoroutineScope
    ): PlantEntryRepo {
        return PlantEntryRepo(
            appIoScope = appIoScope
        )
    }
}
