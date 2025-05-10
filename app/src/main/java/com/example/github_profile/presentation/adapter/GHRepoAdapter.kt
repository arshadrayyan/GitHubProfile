package com.example.github_profile.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.github_profile.R
import com.example.github_profile.data.model.GHRepo

// A RecyclerView Adapter to bind data.

class GHRepoAdapter(private var repoList: List<GHRepo>, private val onClick: (GHRepo) -> Unit) :
    RecyclerView.Adapter<GHRepoAdapter.RepoViewHolder>() {

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repoId: TextView = itemView.findViewById(R.id.tvRepoId)
        val repoName: TextView = itemView.findViewById(R.id.tvRepoName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repoList[position]
        holder.repoId.text = "ID: ${repo.id}"
        holder.repoName.text = "Name: ${repo.name}"
        holder.itemView.setOnClickListener { onClick(repo) }
    }

    fun updateList(newList: List<GHRepo>) {
        repoList = newList
        notifyDataSetChanged()
    }
}
