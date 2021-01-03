package by.itacademy.training.travelhelper.di.component

import by.itacademy.training.travelhelper.di.module.CountryActivityModule
import by.itacademy.training.travelhelper.ui.view.CountryActivity
import by.itacademy.training.travelhelper.ui.view.CountryDescriptionFragment
import by.itacademy.training.travelhelper.ui.view.MapsFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [CountryActivityModule::class])
@CountryActivityScope
interface CountryActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(countryActivity: CountryActivity): Builder
        fun build(): CountryActivityComponent
    }

    fun videoListSubComponentBuilder(): VideoListComponent.Builder
    fun routeListSubComponentBuilder(): RouteListFragmentComponent.Builder
    fun inject(countryActivity: CountryActivity)
    fun inject(countryDescriptionFragment: CountryDescriptionFragment)
    fun inject(mapsFragment: MapsFragment)
}
