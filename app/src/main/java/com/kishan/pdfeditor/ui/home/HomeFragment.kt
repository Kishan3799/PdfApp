package com.kishan.pdfeditor.ui.home

import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.kishan.pdfeditor.adapter.HomeAdapter
import com.kishan.pdfeditor.databinding.FragmentHomeBinding
import java.io.File

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var homeAdapter: HomeAdapter
    lateinit var recyclerView: RecyclerView
//    lateinit var pdfList:ArrayList<File>
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)




        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView = binding.homeRecyclerView


        runtimePermission()


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun displayPdf(){
        recyclerView.apply{
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            val pdfList = arrayListOf<File>()
            pdfList.addAll(pdfFile(Environment.getExternalStorageDirectory()))
            homeAdapter = HomeAdapter(requireContext(), pdfList)
            adapter = homeAdapter
        }
    }

    fun runtimePermission(){
        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    displayPdf()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {

                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?,
                ) {
                    token!!.continuePermissionRequest()
                }
            }).check()
    }

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