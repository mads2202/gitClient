package com.mads2202.gitclient.ui

import android.os.Build
import androidx.annotation.RequiresApi
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.mads2202.gitclient.domen.retrofit.GitUser

object Screens {
    fun openMainScreen() = FragmentScreen { MainScreenFragment.newInstance() }
    fun openSignUpScreen() = FragmentScreen { SignupFragment.newInstance() }
    fun openForgotPasswordScreen() = FragmentScreen() {ForgotPasswordFragment.newInstance() }
    fun openLoginScreen() = FragmentScreen{LoginFragment.newInstance()}
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun openReposScreen(user:GitUser) = FragmentScreen{ ReposListFragment(user) }


}