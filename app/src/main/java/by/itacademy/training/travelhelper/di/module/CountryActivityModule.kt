package by.itacademy.training.travelhelper.di.module

import androidx.lifecycle.ViewModelProvider
import by.itacademy.training.travelhelper.di.component.CountryActivityScope
import by.itacademy.training.travelhelper.ui.view.CountryActivity
import by.itacademy.training.travelhelper.ui.viewmodel.CountryDescriptionViewModel
import dagger.Module
import dagger.Provides

@Module
class CountryActivityModule {

    @Provides
    @CountryActivityScope
    fun provideCountriesDescriptionViewModel(activity: CountryActivity): CountryDescriptionViewModel =
        ViewModelProvider(activity).get(CountryDescriptionViewModel::class.java)
}
