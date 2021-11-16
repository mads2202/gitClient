package com.mads2202.gitclient.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun openMainScreen() = FragmentScreen { MainScreenFragment.newInstance() }
    fun openSignUpScreen() = FragmentScreen { SignupFragment.newInstance() }
    fun openForgotPasswordScreen() = FragmentScreen() {ForgotPasswordFragment.newInstance() }
    fun openLoginScreen() = FragmentScreen{LoginFragment.newInstance()}

}