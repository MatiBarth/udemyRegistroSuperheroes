package com.example.registrodesuperheroes

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registrodesuperheroes.databinding.ActivityDetailBinding
import com.example.registrodesuperheroes.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras!!
        val superhero = bundle.getParcelable<Superhero>(SUPERHERO_KEY)!!
        val bitmap = bundle.getParcelable<Bitmap>(BITMAP_KEY)!!

        binding.imageView.setImageBitmap(bitmap)
        binding.superhero = superhero
    }

    companion object {
        const val SUPERHERO_KEY = "superhero"
        const val BITMAP_KEY = "bitmap"
    }
}