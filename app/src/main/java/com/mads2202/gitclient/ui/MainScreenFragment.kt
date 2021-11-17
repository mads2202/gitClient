package com.mads2202.gitclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mads2202.gitclient.R
import com.mads2202.gitclient.databinding.MainScreenLayoutBinding
import com.mads2202.gitclient.eventBus.LoginEvent
import com.mads2202.gitclient.util.app
import io.reactivex.disposables.CompositeDisposable

class MainScreenFragment: Fragment() {
    private var binding:MainScreenLayoutBinding?=null
    private val disposable:CompositeDisposable= CompositeDisposable()
    private var counter=0
    companion object{
        fun newInstance():MainScreenFragment {
            val args = Bundle()
            val fragment = MainScreenFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.main_screen_layout,container,false)
        binding= MainScreenLayoutBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        disposable.add(requireContext().app.eventBus.get().subscribe{event->
            when(event){
                is LoginEvent -> {
                    counter+=1
                    binding!!.loginCounter.text=counter.toString()
                }
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        disposable.dispose()
        binding=null
        super.onDestroyView()
    }
}