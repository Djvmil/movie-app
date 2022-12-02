package com.test.movieappforyassirtest.client

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author Moustapha S. Dieme ( Djvmil_ ) on 1/12/22.
 */
class MoviePaginationAdapter(
    private val layout: Int,
    private val differCallback: DiffUtil.ItemCallback<ResultsItem>,
    private val onBind: (view: View, item: Any, position: Int) -> Unit,
    private val onItemClickListener: (view: View, item: Any, position: Int) -> Unit
) : RecyclerView.Adapter<MoviePaginationAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            setOnClickListener { view ->
                onItemClickListener(view, item, position)
            }

            onBind(holder.itemView, item, position)
        }
    }

}













