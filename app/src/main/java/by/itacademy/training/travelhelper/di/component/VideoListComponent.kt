package by.itacademy.training.travelhelper.di.component

import by.itacademy.training.travelhelper.di.module.VideoListFragmentModule
import by.itacademy.training.travelhelper.di.module.YoutubeApiModule
import by.itacademy.training.travelhelper.ui.view.VideoListFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [VideoListFragmentModule::class, YoutubeApiModule::class])
@VideoListFragmentScope
interface VideoListComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(videoListFragment: VideoListFragment): Builder
        fun build(): VideoListComponent
    }

    fun inject(fragment: VideoListFragment)
}
