package com.mads2202.gitclient.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.material.snackbar.Snackbar
import com.mads2202.gitclient.R
import com.mads2202.gitclient.databinding.LoginScreenFragmetBinding
import com.mads2202.gitclient.eventBus.LoginEvent
import com.mads2202.gitclient.preferences.PreferenceKeys.Companion.STORED_EMAIL_ADDRESS
import com.mads2202.gitclient.preferences.PreferenceKeys.Companion.STORED_PASSWORD_ADDRESS
import com.mads2202.gitclient.presenters.LoginContract
import com.mads2202.gitclient.presenters.LoginPresenterImpl
import com.mads2202.gitclient.util.APP_PREFERENCES
import com.mads2202.gitclient.util.app
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class LoginFragment : MvpAppCompatFragment(), LoginContract.View {
    var binding: LoginScreenFragmetBinding? = null
    val presenter by moxyPresenter { LoginPresenterImpl(requireContext().app.router)}

    companion object {
        fun newInstance(): LoginFragment {
            val args = Bundle()
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_screen_fragmet, container, false)
        binding = LoginScreenFragmetBinding.bind(view)
        setUpOnClickListeners()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireContext().app.eventBus.post(LoginEvent())
    }

    override fun setState(viewState: ViewState) {
        when (viewState) {
            ViewState.ERROR -> {
                view?.let {
                    Snackbar.make(
                        it,
                        getString(R.string.something_wrong_error),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
            ViewState.LOADING -> {
            // this state will be later when I go to network
            }
            ViewState.MAIL_ERROR -> {
                binding?.loginInputEditText?.error = getString(R.string.wrong_email_format_error)
            }
            ViewState.PASSWORD_ERROR -> {
                binding?.passwordInputEditText?.error =
                    getString(R.string.wrong_password_format_error)
            }
            ViewState.SUCCESS -> {
                // this state will be later when I go to network
            }
        }
    }

    private fun setUpOnClickListeners() {
        binding?.let { binding ->
            binding.loginButton.setOnClickListener {
                presenter.onLogin(
                    binding.loginInputEditText.text.toString(),
                    binding.passwordInputEditText.text.toString()
                )
            }
            binding.forgetPasswordButton.setOnClickListener {
                presenter.onForgetPassword()
            }
            binding.signupButton.setOnClickListener {
                presenter.onSingUp()
            }
            binding.loginInputEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    presenter.validateEmail(s.toString())
                }
            })
            binding.passwordInputEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    presenter.validatePassword(s.toString())
                }
            })
        }

    }

    override fun rememberCredentials(email: String, password: String) {

        binding?.let{binding->
            requireActivity().getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
                .edit()
                .putString(STORED_EMAIL_ADDRESS,binding.loginInputEditText.text.toString())
                .putString(STORED_PASSWORD_ADDRESS,binding.passwordInputEditText.text.toString())
                .apply()
        }

    }
}