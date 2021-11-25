package com.mads2202.gitclient.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.mads2202.gitclient.R
import com.mads2202.gitclient.databinding.GitRepoListItemLayoutBinding
import com.mads2202.gitclient.domen.retrofit.GitRepo


class GitRepoAdapter(val reposList:List<GitRepo>): RecyclerView.Adapter<GitRepoAdapter.ReposListViewHolder>() {
    inner class ReposListViewHolder(val binding: GitRepoListItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        private lateinit var name: AppCompatTextView
        private lateinit var description: AppCompatTextView
        fun onBind(){
            name=itemView.findViewById(R.id.name)
            name.text=reposList[adapterPosition].name
            description=itemView.findViewById(R.id.name)
            description.text=reposList[adapterPosition].description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposListViewHolder {
        return  ReposListViewHolder(GitRepoListItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: GitRepoAdapter.ReposListViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount()=reposList.size
}