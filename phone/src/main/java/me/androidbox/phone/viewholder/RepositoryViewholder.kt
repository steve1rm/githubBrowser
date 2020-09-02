package me.androidbox.phone.viewholder

import androidx.recyclerview.widget.RecyclerView
import me.androidbox.phone.databinding.ItemRepositoryBinding

class RepositoryViewholder(bindings: ItemRepositoryBinding)
    : RecyclerView.ViewHolder(bindings.root) {

    val repName = bindings.repoName
    val repoDescription = bindings.repoDescription
    val starsCount = bindings.starsCount
    val forkCount = bindings.forkCount
}
