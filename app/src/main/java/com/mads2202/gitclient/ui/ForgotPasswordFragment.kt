package com.mads2202.gitclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mads2202.gitclient.R

class ForgotPasswordFragment : Fragment() {
    companion object {
        fun newInstance(): ForgotPasswordFragment {
            val args = Bundle()
            val fragment = ForgotPasswordFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.retrieve_password_screen_layout, container, false)
        return view
    }
}