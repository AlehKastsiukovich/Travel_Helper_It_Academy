package by.itacademy.training.travelhelper.di.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ActivityContextModule(private val context: Activity) {

    @Provides
    fun context(): Context {
        return context
    }
}