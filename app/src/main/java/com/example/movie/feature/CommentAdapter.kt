package com.example.movie.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.data.Comment
import com.example.movie.databinding.ItemCommentBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    val items = ArrayList<Comment>()

    inner class ViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: Comment) {
            binding.textViewId.text = item.id
            binding.textViewTime.text = item.time
            binding.textViewComment.text = item.comment
            binding.textViewLike.text = item.like
            binding.ratingBar.rating = item.rate

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    override fun getItemCount() = items.size
}