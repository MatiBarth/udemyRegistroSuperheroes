package com.example.registrodesuperheroes

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil.setContentView
import com.example.registrodesuperheroes.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var heroImage: ImageView
    private lateinit var binding: ActivityMainBinding
    private var heroBitmap: Bitmap? = null

    val getContent = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
        bitmap ->
            heroBitmap = bitmap
            heroImage.setImageBitmap(heroBitmap!!)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener(){
            val superheroName = binding.heroNameEdit.text.toString()
            val alterEgo = binding.alterEgoEdit.text.toString()
            val bio = binding.heroBioEdit.text.toString()
            val power = binding.powerRating.rating

            val hero = Superhero(superheroName,alterEgo,bio,power)
            openDetailActivity(hero)
        }

            heroImage = binding.heroImage
            heroImage.setOnClickListener{
                openCamera()
            }
    }

    private fun openCamera() {
        getContent.launch(null)
    }

    private fun openDetailActivity(superhero: Superhero) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(DetailActivity.SUPERHERO_KEY,superhero)
        intent.putExtra(DetailActivity.BITMAP_KEY,heroImage.drawable.toBitmap())
        startActivity(intent)
    }
}
