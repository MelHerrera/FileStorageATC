package com.example.filestorage

import android.os.Environment
import android.util.Log
import java.lang.Exception

object CommonUtil {

    fun isSdReadable(): Boolean
    {
        var mExternalStorageAvailable = false
        try {
            val state = Environment.getExternalStorageState()
            if (Environment.MEDIA_MOUNTED.equals(state)){
                mExternalStorageAvailable = true
                Log.i("isSdReadable","External storage card is readable")

            }else if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
                Log.i("isSdReadable","External storage card is readable")
                mExternalStorageAvailable = true
            }
            else{
                mExternalStorageAvailable = false
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return mExternalStorageAvailable
    }
}