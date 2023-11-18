package com.kishan.pdfeditor.ui.home

import android.Manifest
import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File


class HomeViewModel : ViewModel() {


//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text

//    private val _list = MutableLiveData<MutableList<File>>()
//
//    val pdfList: MutableLiveData<MutableList<File>> = _list

    val pdfList = arrayListOf<File>()

    fun pdfFile(file:File): ArrayList<File>{
        val arrayList = ArrayList<File>()
        val files: Array<out File> = file.listFiles()!!
            for(singleFile in files){
                if (singleFile.isDirectory && !singleFile.isHidden){
                    arrayList.addAll(pdfFile(singleFile))
                }
                else{
                    if(singleFile.name.endsWith(".pdf")){
                        arrayList.add(singleFile)
                    }
                }
            }
        return arrayList
    }




}