package by.itacademy.training.travelhelper.di.component

import by.itacademy.training.travelhelper.di.module.ApplicationContextModule
import by.itacademy.training.travelhelper.di.module.FirebaseModule
import by.itacademy.training.travelhelper.di.module.NetworkModule
import by.itacademy.training.travelhelper.di.module.RepositoryModule
import by.itacademy.training.travelhelper.di.module.RoomModule
import by.itacademy.training.travelhelper.ui.view.MainActivity
import by.itacademy.training.travelhelper.ui.view.VideoListFragment
import by.itacademy.training.travelhelper.ui.viewmodel.CountryListViewModel
import by.itacademy.training.travelhelper.ui.viewmodel.VideoListViewModel
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class, RepositoryModule::class, ApplicationContextModule::class,
        RoomModule::class, FirebaseModule::class
    ]
)
@Singleton
interface ApplicationComponent {
    fun inject(videoListFragment: VideoListFragment)
    fun inject(activity: MainActivity)
    fun inject(countryListViewModel: CountryListViewModel)
    fun inject(videoListViewModel: VideoListViewModel)
}
