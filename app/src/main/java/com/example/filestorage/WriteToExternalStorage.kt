package com.example.filestorage

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PermissionInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.filestorage.databinding.ActivityWriteToExternalStorageBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.lang.Exception

class WriteToExternalStorage : AppCompatActivity() {
    lateinit var binding: ActivityWriteToExternalStorageBinding
    val PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteToExternalStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener {
            val permisssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

            if(permisssionCheck == PackageManager.PERMISSION_GRANTED)
                procedToWriteFile()
            else{
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
                )
            }
        }
    }

    private fun procedToWriteFile(){
        val fileName = binding.enterFilename.text.toString()
        val content = binding.enterFileContent.text.toString()
        writeFileExternalStorage(content, this, fileName)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    procedToWriteFile()
                else{
                    Toast.makeText(this, "permission Denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

    private fun writeFileExternalStorage(strWrite:String, context: Context, fileName:String){
        try {
            if (CommonUtil.isSdReadable()){
                val fullPath = Environment.getExternalStorageDirectory().absolutePath
                val myFile = File(fullPath + File.separator + "/" + fileName)
                val fOut = FileOutputStream(myFile)
                val myOutWritter = OutputStreamWriter(fOut)
                myOutWritter.append(strWrite)
                myOutWritter.close()
                fOut.close()

                Toast.makeText(this, "File written to external memory", Toast.LENGTH_LONG).show()
            }
        }catch (e:Exception){
            Toast.makeText(this, "Error ocurred in writing file", Toast.LENGTH_LONG).show()
        }
    }
}