package by.itacademy.training.travelhelper.di.component

import androidx.appcompat.app.AppCompatActivity
import by.itacademy.training.travelhelper.di.module.CountryActivityModule
import by.itacademy.training.travelhelper.ui.view.CountryActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [CountryActivityModule::class])
interface CountryActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(countryActivity: AppCompatActivity): Builder
        fun build(): CountryActivityComponent
    }

    fun inject(countryActivity: CountryActivity)
}
