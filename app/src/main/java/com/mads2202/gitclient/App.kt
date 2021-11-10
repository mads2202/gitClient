package com.mads2202.gitclient

import android.app.Application
import com.github.terrakok.cicerone.Cicerone

class App:Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()
}