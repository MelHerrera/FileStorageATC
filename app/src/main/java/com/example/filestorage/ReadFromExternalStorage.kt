package com.example.filestorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.example.filestorage.databinding.ActivityReadFromExternalStorageBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class Readfromexternalstorage : AppCompatActivity() {
    lateinit var binding:ActivityReadFromExternalStorageBinding
    val fileName = "ExtFile.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadFromExternalStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fileContent = readFileExternalStorage(fileName)
        binding.fileContentE.text = fileContent
    }

    private fun readFileExternalStorage(fileName:String):String{
        var result = ""

        try {
            if(CommonUtil.isSdReadable()){
               val sdCard = Environment.getExternalStorageDirectory()
                val file = File(sdCard, fileName)
                val br = BufferedReader(FileReader(file))
                result = br.use(BufferedReader::readText)
            }

        }catch (e:IOException){
            e.printStackTrace()
        }
        return  result
    }
}