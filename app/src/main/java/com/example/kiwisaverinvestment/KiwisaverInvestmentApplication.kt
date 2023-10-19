package com.example.kiwisaverinvestment

import android.app.Application
import com.example.kiwisaverinvestment.data.koin.allKoinModules
import com.example.kiwisaverinvestment.data.model.User
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KiwisaverInvestmentApplication: Application() {

    companion object {
        lateinit var instance: KiwisaverInvestmentApplication
            private set

        var user: User = User()

    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@KiwisaverInvestmentApplication)
            modules(allKoinModules)
        }
    }
}