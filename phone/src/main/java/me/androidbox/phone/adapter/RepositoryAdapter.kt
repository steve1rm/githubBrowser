package me.androidbox.phone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.androidbox.phone.databinding.ItemRepositoryBinding
import me.androidbox.phone.model.RepoItem
import me.androidbox.phone.viewholder.RepositoryViewholder

class RepositoryAdapter : RecyclerView.Adapter<RepositoryViewholder>() {
    private val dataRepository = mutableListOf<RepoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewholder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RepositoryViewholder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewholder, position: Int) {
        holder.repName.text = dataRepository[position].name
        holder.repoDescription.text = dataRepository[position].description
        holder.starsCount.text = dataRepository[position].starsCount.toString()
        holder.forkCount.text = dataRepository[position].forkCount.toString()
    }

    override fun getItemCount(): Int = dataRepository.count()

    fun setRepositoryItems(repositoryItems: List<RepoItem>) {
        dataRepository.clear()
        dataRepository.addAll(repositoryItems)
        notifyDataSetChanged()
    }
}
