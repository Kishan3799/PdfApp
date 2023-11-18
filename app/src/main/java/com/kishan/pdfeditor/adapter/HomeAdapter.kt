package com.kishan.pdfeditor.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kishan.pdfeditor.PdfViewActivity
import com.kishan.pdfeditor.databinding.HomeItemRvCardLayoutBinding
import com.kishan.pdfeditor.model.PdfFile
import java.io.File

class HomeAdapter(val context: Context, val pdfFiles: List<File>):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(val binding: HomeItemRvCardLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
       return HomeViewHolder(
           HomeItemRvCardLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
       )
    }

    override fun getItemCount(): Int {
       return pdfFiles.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.apply {
            pdfName.text = pdfFiles[position].name
            pdfFileView.fromFile(pdfFiles[position]).load()

        }
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, PdfViewActivity::class.java)
            val pdfFilepath = File(pdfFiles[position].path)
            val pdfUri  = Uri.fromFile(pdfFilepath)
            intent.putExtra("pdfUri", pdfUri)
            context.startActivity(intent)
        }

    }
}