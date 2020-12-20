package by.itacademy.training.travelhelper.di.module

import androidx.lifecycle.ViewModelProvider
import by.itacademy.training.travelhelper.di.component.VideoListFragmentScope
import by.itacademy.training.travelhelper.ui.view.VideoListFragment
import by.itacademy.training.travelhelper.ui.viewmodel.VideoListViewModel
import dagger.Module
import dagger.Provides

@Module
class VideoListFragmentModule {

    @Provides
    @VideoListFragmentScope
    fun provideVideoListViewModel(fragment: VideoListFragment) =
        ViewModelProvider(fragment).get(VideoListViewModel::class.java)
}
