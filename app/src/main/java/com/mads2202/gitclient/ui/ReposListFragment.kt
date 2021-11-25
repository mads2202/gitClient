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
import com.mads2202.gitclient.presenters.ReposPresenter
import com.mads2202.gitclient.ui.adapters.GitRepoAdapter
import com.mads2202.gitclient.util.app
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ReposListFragment(val url:String): MvpAppCompatFragment(), MainContact.MainView {
    private var binding: MainScreenLayoutBinding?=null
    private val presenter: ReposPresenter by moxyPresenter {
        ReposPresenter(
            AndroidSchedulers.mainThread(),
            GitHubUsersRepoImpl(requireContext().app.api),
            url

        )
    }
    companion object{
        val URL="url"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.main_screen_layout,container,false)
        binding= MainScreenLayoutBinding.bind(view)
        binding!!.usersList.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        binding!!.usersList.adapter= GitRepoAdapter(presenter.reposList)
        return view
    }




    override fun onDestroyView() {
        binding=null
        super.onDestroyView()
    }

    override fun init() {
        binding!!.usersList.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        binding!!.usersList.adapter= GitRepoAdapter(presenter.reposList)
    }

    override fun updateList() {
        binding!!.usersList.adapter= GitRepoAdapter(presenter.reposList)
        binding!!.usersList.adapter!!.notifyDataSetChanged()
    }
}