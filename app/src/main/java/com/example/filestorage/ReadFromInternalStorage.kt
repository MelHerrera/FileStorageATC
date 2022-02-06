package com.example.filestorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.filestorage.databinding.ActivityReadFromInternalStorageBinding
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException

class ReadFromInternalStorage : AppCompatActivity() {
    lateinit var binding: ActivityReadFromInternalStorageBinding
    var filename = "testFile.txt"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadFromInternalStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fileContent = readFileInternalStorage(filename,this)
        binding.fileContentI.text = fileContent

    }

    private fun readFileInternalStorage(filename: String, context: Context): String {
        var stringToRetun = ""
        try{
            val inputStream = context.openFileInput(filename)
            if(inputStream != null)
            {
                stringToRetun = inputStream.bufferedReader().use(BufferedReader::readText)
            }
        } catch(e: FileNotFoundException) {
            Log.e("TAG","The file was not found: " + e.toString())
        }catch (e: IOException){
            Log.e("TAG","Cannot read file: " + e.toString())
        }
        return stringToRetun
    }
}