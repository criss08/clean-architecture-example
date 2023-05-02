package com.example.cleanarquitectureexample.movie_list.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.cleanarquitectureexample.R
import com.example.cleanarquitectureexample.databinding.*

class MovieCardListAdapter(
    val onItemClicked: (MovieCardViewState) -> Unit
) : RecyclerView.Adapter<MovieCardListAdapter.ViewHolder>() {

    private var data: List<MovieCardViewState> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(movieList: List<MovieCardViewState>) {
        this.data = movieList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieCardViewState: MovieCardViewState) {
            val bind = MovieCardBinding.bind(itemView)

            bind.apply {
                viewMovieName.text = movieCardViewState.title
                viewMovieDescription.text = movieCardViewState.description

                enterButton.setOnClickListener {
                    onItemClicked(movieCardViewState)
                }

                Glide.with(movieImage)
                    .asBitmap()
                    .load(movieCardViewState.imageUrl)
                    .into(BitmapImageViewTarget(movieImage))
            }
        }
    }
}