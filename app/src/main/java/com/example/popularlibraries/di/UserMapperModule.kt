package com.example.popularlibraries.di

import com.example.popularlibraries.core.mapper.UserMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserMapperModule {

    @Provides
    @Singleton
    fun provideUserMapper(): UserMapper {
        val userMapper = UserMapper()
        return userMapper
    }
}