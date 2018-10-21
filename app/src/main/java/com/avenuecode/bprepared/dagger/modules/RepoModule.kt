package com.avenuecode.bprepared.dagger.modules

import com.avenuecode.bprepared.network.NetworkClient
import com.avenuecode.bprepared.repos.DataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideDataRespository(service: NetworkClient): DataRepository {
        return DataRepository(service)
    }
}