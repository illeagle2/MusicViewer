package com.example.musicviewer.di

import com.example.musicviewer.presenter.*
import dagger.Binds
import dagger.Module


@Module
abstract class PresentersModule {
    @Binds
    abstract fun providesRockPresenter(
        rockPresenter: RockPresenter
    ): RockPresenterContract

    @Binds
    abstract fun providesPopPresenter(
        popPresenter: PopPresenter
    ): PopPresenterContract

    @Binds
    abstract fun providesClassicPresenter(
        classicPresenter: ClassicPresenter
    ): ClassicPresenterContract

}