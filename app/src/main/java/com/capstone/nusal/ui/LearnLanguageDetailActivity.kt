package com.capstone.nusal.ui

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.capstone.nusal.R
import com.capstone.nusal.data.ml.AksaraClassifier
import com.capstone.nusal.databinding.ActivityLearnLanguageDetailBinding
import com.capstone.nusal.helper.specifyAksara
import com.divyanshu.draw.widget.DrawView

class LearnLanguageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLearnLanguageDetailBinding
    private var drawView: DrawView? = null
    private var aksaraClassifier = AksaraClassifier(this)

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnLanguageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawView = binding.drawView

        drawView?.apply {
            setStrokeWidth(20.0F)
            setColor(Color.BLACK)
            setBackgroundColor(Color.WHITE)
        }

        // Get extra item from intent. TODO: Get link too.
        val data = intent.getStringExtra(EXTRA_LANGUAGE)

        // Load image and text
        binding.tvLearnAksaraTitle.text = data

        drawView?.setOnTouchListener { _, motionEvent ->
            drawView?.onTouchEvent(motionEvent)

            if(motionEvent.action == MotionEvent.ACTION_UP) {
                classifyDrawing()
            }

            true
        }

        binding.btnClearCanvas.setOnClickListener {
            drawView?.clearCanvas()
        }

        aksaraClassifier
            .initialize()
            .addOnFailureListener { e -> Log.e(TAG, "Error to setting up digit classifier.", e) }
    }

    private fun classifyDrawing() {
        val bitmap = drawView?.getBitmap()

        if ((bitmap != null) && (aksaraClassifier.isInitialized)) {
            aksaraClassifier
                .classifyAsync(bitmap)
                .addOnSuccessListener { result ->
                    val aksaraResult = specifyAksara(result)

                    if(binding.tvLearnAksaraTitle.text.toString() == aksaraResult) {
                        binding.tvPrediction.text = "Benar! Anda menulis $aksaraResult"
                        // TODO: Do something, lock button or what?
                    } else {
                        binding.tvPrediction.text = "Tulisan Anda belum cocok"
                    }
                }
                .addOnFailureListener { e ->
                    binding.tvPrediction.text = "Fail to classify"
                    Log.e(TAG, "Error classifying drawing.", e)
                }
        }
    }

    companion object {
        const val EXTRA_LANGUAGE = "extra_language"
    }
}