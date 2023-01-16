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
<<<<<<< HEAD
        return UserMapper()
=======
        val userMapper = UserMapper()
        return userMapper
>>>>>>> a09c566 (8 ДЗ. Добавил компонент для зависимости класса UserMapper.)
    }
}