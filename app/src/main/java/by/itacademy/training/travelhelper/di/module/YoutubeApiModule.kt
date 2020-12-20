package by.itacademy.training.travelhelper.di.module

import by.itacademy.training.travelhelper.ui.adapter.YoutubePlayerListener
import by.itacademy.training.travelhelper.ui.view.VideoListFragment
import dagger.Binds
import dagger.Module

@Module
interface YoutubeApiModule {

    @Binds
    fun provideYoutubePlayerListener(fragment: VideoListFragment): YoutubePlayerListener
}
