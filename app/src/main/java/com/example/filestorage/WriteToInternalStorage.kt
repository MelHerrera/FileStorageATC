package com.example.filestorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.filestorage.databinding.ActivityWriteToInternalStorageBinding

class WriteToInternalStorage : AppCompatActivity() {
    lateinit var binding: ActivityWriteToInternalStorageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteToInternalStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener {
            val fileName = binding.enterFilename.text.toString()
            val content = binding.enterFileContent.text.toString()
            writeFileInternalStorage(content,this,fileName)
        }
    }

    private fun writeFileInternalStorage(content: String, context: Context, fileName: String) {

        try {
            val fos = context.openFileOutput(fileName,Context.MODE_PRIVATE)
            fos.write(content.toByteArray())
            fos.flush()
            fos.close()
            Toast.makeText(context,"File Written to internal memory",Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Toast.makeText(context,"Error occurred in writing File",Toast.LENGTH_LONG).show()
        }
    }
}