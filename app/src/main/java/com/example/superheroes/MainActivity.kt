package com.example.superheroes

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.example.superheroes.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btSend.setOnClickListener {
            val intent = Intent(this, VerDatosActivity::class.java)
            val heroe:String = viewBinding.etHeroeName.text.toString()
            val power:Float = viewBinding.rbPower.rating
            val bitmap:Bitmap = viewBinding.ivPhoto.drawable.toBitmap()

            intent.putExtra(VerDatosActivity.HEROE_KEY, heroe)
            intent.putExtra(VerDatosActivity.POWER_KEY, power)
            intent.putExtra(VerDatosActivity.IMAGEN_KEY, bitmap)
            startActivity(intent)
        }

        viewBinding.ivPhoto.setOnClickListener {
            val intentImplicito = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intentImplicito, 7)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 7 && resultCode == Activity.RESULT_OK){
            val imagen:Bundle? = data?.extras
            val heroeImagen:Bitmap? = imagen?.getParcelable<Bitmap>("data")
            viewBinding.ivPhoto.setImageBitmap(heroeImagen)
        }
    }
}