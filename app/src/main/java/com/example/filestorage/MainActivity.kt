package com.example.filestorage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filestorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.writeFileToInternalStorage.setOnClickListener {
            startActivity(Intent(this,WriteToInternalStorage::class.java))
        }
        binding.writeFileToExternalStorage.setOnClickListener {
            startActivity(Intent(this,WriteToExternalStorage::class.java))
        }
        binding.readFileFromInternalStorage.setOnClickListener {
            startActivity(Intent(this,ReadFromInternalStorage::class.java))
        }
        binding.readFileFromExternalStorage.setOnClickListener {
            startActivity(Intent(this,Readfromexternalstorage::class.java))
        }
    }
}