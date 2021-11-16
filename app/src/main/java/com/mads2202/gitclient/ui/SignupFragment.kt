package com.mads2202.gitclient.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.mads2202.gitclient.R
import com.mads2202.gitclient.databinding.SingUpScreenLayoutBinding
import com.mads2202.gitclient.presenters.SignUpContract
import com.mads2202.gitclient.presenters.SignUpPresenterImpl
import com.mads2202.gitclient.util.app
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SignupFragment : MvpAppCompatFragment(), SignUpContract.SignUpView {
    private var binding: SingUpScreenLayoutBinding? = null
    val presenter by moxyPresenter { SignUpPresenterImpl(requireContext().app.router) }

    companion object {
        fun newInstance(): SignupFragment {
            val args = Bundle()
            val fragment = SignupFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SingUpScreenLayoutBinding.inflate(inflater)
        setupOnClickListeners()
        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupOnClickListeners() {
        binding?.let { binding ->
            binding.signupButton.setOnClickListener {
                presenter.onSingUp(
                    binding.mailEditText.text.toString(),
                    binding.passwordInputEditText.text.toString(),
                    binding.nicknameInputEditText.text.toString()
                )
            }
            binding.mailEditText.addTextChangedListener(object : TextWatcher {
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

    override fun setState(state: ViewState) {
        when (state) {
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
                binding?.mailEditText?.error = getString(R.string.wrong_email_format_error)
            }
            ViewState.PASSWORD_ERROR -> {
                binding?.passwordInputEditText?.error =
                    getString(R.string.wrong_password_format_error)
            }
            ViewState.NICKNAME_ERROR -> {
                view?.let {
                    Snackbar.make(
                        it,
                        getString(R.string.user_with_this_nickname_already_exists),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
            ViewState.SUCCESS -> {
                // this state will be later when I go to network
            }
        }
    }

}