package com.capstone.nusal.ui.learn

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.nusal.data.ml.AksaraClassifier
import com.capstone.nusal.databinding.ActivityLearnLanguageAksaraBinding
import com.capstone.nusal.helper.specifyAksara
import com.divyanshu.draw.widget.DrawView

class LearnLanguageAksaraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLearnLanguageAksaraBinding
    private var drawView: DrawView? = null
    private lateinit var aksaraClassifier: AksaraClassifier

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnLanguageAksaraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent get extra, is it Javanese or Sundanese
        val languageType = intent.getStringExtra(EXTRA_LANGUAGE)

        aksaraClassifier = AksaraClassifier(this, languageType.toString())

        supportActionBar?.hide()

        drawView = binding.drawView

        drawView?.apply {
            setStrokeWidth(20.0F)
            setColor(Color.BLACK)
            setBackgroundColor(Color.WHITE)
        }

        val extraAksara = intent.getStringExtra(EXTRA_AKSARA)
//        val extraAksaraImg = intent.getStringExtra(EXTRA_AKSARA_IMG)
//
//        Glide.with(this)
//            .load(extraAksaraImg)
//            .into(binding.imgLearnAksara)

        binding.tvLearnAksaraTitle.text = extraAksara


        drawView?.setOnTouchListener { _, motionEvent ->
            drawView?.onTouchEvent(motionEvent)

            if(motionEvent.action == MotionEvent.ACTION_UP) {
                classifyDrawing() // TODO: Change this into button action.
            }

            true
        }

        binding.btnClearCanvas.setOnClickListener {
            drawView?.clearCanvas()
            binding.tvPrediction.text = "Mulai menulis"
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
                    val aksaraResult = specifyAksara(result, intent.getStringExtra(EXTRA_LANGUAGE).toString())

                    Log.d(TAG, aksaraResult.toString())

                    if(binding.tvLearnAksaraTitle.text.toString() == aksaraResult) {
                        binding.tvPrediction.text = "$aksaraResult" // "Benar! Anda menulis $aksaraResult"
                        // TODO: Do something, lock button or what?
                    } else {
                        binding.tvPrediction.text = "Tulisan Anda belum benar"
                    }
                }
                .addOnFailureListener { e ->
                    binding.tvPrediction.text = "Fail to classify"
                    Log.e(TAG, "Error classifying drawing.", e)
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        aksaraClassifier.close()
    }

    companion object {
        const val EXTRA_AKSARA = "extra_aksara"
        const val EXTRA_AKSARA_IMG = "extra_aksara_img"

        const val EXTRA_LANGUAGE = "extra_language"
    }
}