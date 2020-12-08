package by.itacademy.training.travelhelper.di.component

import by.itacademy.training.travelhelper.di.module.ActivityContextModule
import by.itacademy.training.travelhelper.di.module.ApplicationContextModule
import by.itacademy.training.travelhelper.di.module.NetworkModule
import by.itacademy.training.travelhelper.di.module.RepositoryModule
import by.itacademy.training.travelhelper.di.module.RoomModule
import by.itacademy.training.travelhelper.ui.viewmodel.CountryListViewModel
import by.itacademy.training.travelhelper.ui.viewmodel.VideoListViewModel
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class, RepositoryModule::class, ApplicationContextModule::class,
        ActivityContextModule::class, RoomModule::class
    ]
)
@Singleton
interface ApplicationComponent {

    fun inject(videoListViewModel: VideoListViewModel)
    fun inject(countryListViewModel: CountryListViewModel)
}
