package com.example.gbookssample

import android.app.Application
import com.example.gbookssample.com.example.gbookssample.ViewModelModule
import com.facebook.stetho.Stetho
import dagger.Component

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = this
        appComponent = DaggerApplicationComponent.create()
        Stetho.initializeWithDefaults(this)
    }

    companion object {
        private lateinit var mContext: MyApplication
        private lateinit var appComponent: ApplicationComponent

        fun getContext(): MyApplication {
            return mContext
        }

        fun getAppComponent(): ApplicationComponent {
            return appComponent
        }
    }
}

@Component(modules = [ViewModelModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}