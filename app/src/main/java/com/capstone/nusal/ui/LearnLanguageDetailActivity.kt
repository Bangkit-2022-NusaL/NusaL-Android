package com.capstone.nusal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.nusal.R
import com.capstone.nusal.databinding.ActivityLearnLanguageDetailBinding

class LearnLanguageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLearnLanguageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnLanguageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get extra item from intent
        // Load image and text

        binding.imgCanvas.apply {
            isDrawingCacheEnabled = true
            buildDrawingCache()
        }

        /*
        This part is successfully retrieve Bitmap. Apply to Button when ready.
        This button should be the sending image to .tflite model.

        binding.btnGetBitmap.setOnClickListener {
            binding.canvasDrawer.invalidate()
            binding.imgDrawableResult.setImageBitmap(binding.canvasDrawer.getDrawingCache())
        }
         */


    }
}