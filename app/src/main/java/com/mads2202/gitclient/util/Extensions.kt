package com.mads2202.gitclient.util

import android.content.Context
import com.mads2202.gitclient.App

public val Context.app: App
get(){
    return applicationContext as App
}