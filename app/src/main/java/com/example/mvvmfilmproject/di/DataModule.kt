package com.example.mvvmfilmproject.di

import com.example.mvvmfilmproject.data.FilmRepositoryImpl
import com.example.mvvmfilmproject.domain.FilmRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun getRepository(impl: FilmRepositoryImpl): FilmRepository
}