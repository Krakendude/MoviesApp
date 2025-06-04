package com.example.moviesapp.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moviesapp.R
import com.example.moviesapp.data.Movie
import com.example.moviesapp.databinding.ActivityDetailBinding
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.utils.movieService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

            companion object {
        const val MOVIEID = "MOVIEID"
    }

    lateinit var binding: ActivityDetailBinding
    lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra(MOVIEID)!!

        getMovieById(id)

        binding.navigationView.setOnItemSelectedListener { menuItem ->
            binding.contentBasicInfo.root.visibility = View.GONE
            binding.contentSynopsis.root.visibility = View.GONE

            when (menuItem.itemId) {
                R.id.menu_basic_info -> binding.contentBasicInfo.root.visibility = View.VISIBLE
                R.id.menu_synopsis -> binding.contentSynopsis.root.visibility = View.VISIBLE
            }
            true
        }

        binding.navigationView.selectedItemId = R.id.menu_basic_info
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun getMovieById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = movieService.getInstance()
                val response = service.findById(imdbId = id)

                if (response.isSuccessful) {
                    response.body()?.let {
                        movie = it

                        withContext(Dispatchers.Main) {
                            loadData()
                        }
                    }
                } else {
                    Log.e("OMDb", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun loadData() {
        supportActionBar?.title = movie.Title
        supportActionBar?.subtitle = movie.Year
        Picasso.get().load(movie.Poster).into(binding.posterImageView)

        // Basic info
        binding.contentBasicInfo.runtimeTextView.text = movie.Runtime
        binding.contentBasicInfo.directorTextView.text = movie.Director
        binding.contentBasicInfo.genreTextView.text = movie.Genre
        binding.contentBasicInfo.countryTextView.text = movie.Country

        // Synopsis
        binding.contentSynopsis.synopsisTextView.text = movie.Synopsis

    }
}