package by.itacademy.training.travelhelper.di.component

import by.itacademy.training.travelhelper.di.module.MainActivityModule
import by.itacademy.training.travelhelper.ui.view.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(mainActivity: MainActivity): Builder
        fun build(): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}
