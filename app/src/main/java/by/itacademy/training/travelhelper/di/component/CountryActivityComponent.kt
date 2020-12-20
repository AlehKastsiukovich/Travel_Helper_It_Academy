package by.itacademy.training.travelhelper.di.component

import by.itacademy.training.travelhelper.di.module.CountryActivityModule
import by.itacademy.training.travelhelper.ui.view.CountryActivity
import by.itacademy.training.travelhelper.ui.view.CountryDescriptionFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [CountryActivityModule::class])
interface CountryActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(countryActivity: CountryActivity): Builder
        fun build(): CountryActivityComponent
    }

    fun inject(countryActivity: CountryActivity)
    fun inject(countryDescriptionFragment: CountryDescriptionFragment)
}
