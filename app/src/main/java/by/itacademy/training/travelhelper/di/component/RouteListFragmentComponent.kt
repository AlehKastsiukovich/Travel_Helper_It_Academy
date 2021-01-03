package by.itacademy.training.travelhelper.di.component

import by.itacademy.training.travelhelper.di.module.RouteListFragmentModule
import by.itacademy.training.travelhelper.ui.view.RouteListFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [RouteListFragmentModule::class])
@RouteListFragmentScope
interface RouteListFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(routeListFragment: RouteListFragment): Builder
        fun build(): RouteListFragmentComponent
    }

    fun inject(routeListFragment: RouteListFragment)
}
