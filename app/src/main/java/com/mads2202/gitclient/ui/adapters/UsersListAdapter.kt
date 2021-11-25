package com.mads2202.gitclient.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.terrakok.cicerone.Router
import com.mads2202.gitclient.R
import com.mads2202.gitclient.databinding.UsersListItemLayoutBinding
import com.mads2202.gitclient.domen.retrofit.GitUser
import com.mads2202.gitclient.ui.Screens

class UsersListAdapter(val usersList:List<GitUser>, val router: Router): RecyclerView.Adapter<UsersListAdapter.UsersListViewHolder>() {
    inner class UsersListViewHolder(val binding:UsersListItemLayoutBinding):RecyclerView.ViewHolder(binding.root){
        private lateinit var avatar:AppCompatImageView
        private lateinit var nameTextView: AppCompatTextView
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun onBind(){
            avatar=itemView.findViewById(R.id.avatar)
            Glide.with(binding.root)
                .load(usersList[adapterPosition].avatarUrl)
                .into(avatar)
            nameTextView=itemView.findViewById(R.id.name)
            nameTextView.text=usersList[adapterPosition].login
            binding.root.setOnClickListener {
                usersList[adapterPosition]
                    router.navigateTo(Screens.openReposScreen(usersList[adapterPosition]))

                 }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        return  UsersListViewHolder(UsersListItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount()=usersList.size
}