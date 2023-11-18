package com.kishan.pdfeditor

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.kishan.pdfeditor.R
import com.kishan.pdfeditor.databinding.ActivityPdfViewBinding

class PdfViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityPdfViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedIntent: Intent = intent
        val pdfUri: Uri? = intent.getParcelableExtra("pdfUri")

        binding.viewPdf.fromUri(pdfUri).load()
    }
}