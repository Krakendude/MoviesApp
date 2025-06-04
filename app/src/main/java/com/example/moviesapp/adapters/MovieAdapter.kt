package com.example.moviesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviesapp.data.Movie
import com.example.moviesapp.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieAdapter (
    var items: List<Movie>,
    val onItemClick: (position: Int) -> Unit
): RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = items[position]
        holder.render(movie)
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    fun updateItems(items: List<Movie>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class MovieViewHolder(val binding: ItemMovieBinding) : ViewHolder (binding.root) {

    fun render (movie: Movie) {
        binding.nameTextView.text = movie.Title
        binding.yearTextView.text = movie.Year
        Picasso.get().load(movie.Poster).into(binding.posterImageView)
    }
}