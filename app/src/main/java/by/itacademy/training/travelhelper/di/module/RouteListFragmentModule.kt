package by.itacademy.training.travelhelper.di.module

import by.itacademy.training.travelhelper.di.component.RouteListFragmentScope
import by.itacademy.training.travelhelper.ui.adapter.OnRouteClickListener
import by.itacademy.training.travelhelper.ui.view.RouteListFragment
import dagger.Module
import dagger.Provides

@Module
class RouteListFragmentModule {

    @Provides
    @RouteListFragmentScope
    fun provideOnRouteClickListener(routeListFragment: RouteListFragment): OnRouteClickListener =
        routeListFragment
}
