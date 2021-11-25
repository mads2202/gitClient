package com.mads2202.gitclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mads2202.gitclient.R
import com.mads2202.gitclient.databinding.MainScreenLayoutBinding
import com.mads2202.gitclient.network.GitHubUsersRepoImpl
import com.mads2202.gitclient.presenters.MainContact
import com.mads2202.gitclient.presenters.MainFragmentPresenter
import com.mads2202.gitclient.ui.adapters.UsersListAdapter
import com.mads2202.gitclient.util.app
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class MainScreenFragment: MvpAppCompatFragment(),MainContact.MainView {
    private var binding:MainScreenLayoutBinding?=null
    private val presenter: MainFragmentPresenter by moxyPresenter {
        MainFragmentPresenter(
            AndroidSchedulers.mainThread(),
            GitHubUsersRepoImpl(requireContext().app.api),
            requireContext().app.router, Screens
        )
    }
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
        binding!!.usersList.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding!!.usersList.adapter=UsersListAdapter(presenter.usersList,requireContext().app.router)
        return view
    }




    override fun onDestroyView() {
        binding=null
        super.onDestroyView()
    }

    override fun init() {
        binding!!.usersList.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding!!.usersList.adapter=UsersListAdapter(presenter.usersList,requireContext().app.router)
    }

    override fun updateList() {
        binding!!.usersList.adapter=UsersListAdapter(presenter.usersList,requireContext().app.router)
        binding!!.usersList.adapter!!.notifyDataSetChanged()
    }
}