package com.capstone.nusal.ui.learn

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.nusal.R
import com.capstone.nusal.data.ml.AksaraClassifier
import com.capstone.nusal.databinding.ActivityLearnLanguageAksaraBinding
import com.capstone.nusal.databinding.AksaraPopupFailureBinding
import com.capstone.nusal.databinding.AksaraPopupSuccessBinding
import com.capstone.nusal.helper.specifyAksara
import com.divyanshu.draw.widget.DrawView

class LearnLanguageAksaraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLearnLanguageAksaraBinding
    private var drawView: DrawView? = null
    private lateinit var aksaraClassifier: AksaraClassifier

    private lateinit var successPopupBinding: AksaraPopupSuccessBinding
    private lateinit var failurePopupBinding: AksaraPopupFailureBinding

    private lateinit var successPopup: Dialog
    private lateinit var failurePopup: Dialog

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnLanguageAksaraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDialog()

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
        val extraAksaraImg = intent.getStringExtra(EXTRA_AKSARA_IMG)

        Glide.with(this)
            .load(extraAksaraImg)
            .into(binding.imgLearnAksara)

        binding.tvLearnAksaraTitle.text = extraAksara


        drawView?.setOnTouchListener { _, motionEvent ->
            drawView?.onTouchEvent(motionEvent)

            true
        }

        binding.btnCheckAksara.setOnClickListener {
            classifyDrawing()
            binding.btnCheckAksara.isEnabled = false
            binding.pbInferenceLoading.visibility = View.VISIBLE
        }

        binding.btnClearCanvas.setOnClickListener {
            drawView?.clearCanvas()
        }

        aksaraClassifier
            .initialize()
            .addOnFailureListener { e -> Log.e(TAG, "Error to setting up aksara classifier.", e) }
    }

    private fun classifyDrawing() {
        val bitmap = drawView?.getBitmap()

        if ((bitmap != null) && (aksaraClassifier.isInitialized)) {
            aksaraClassifier
                .classifyAsync(bitmap)
                .addOnSuccessListener { result ->
                    binding.pbInferenceLoading.visibility = View.GONE
                    val aksaraResult = specifyAksara(result, intent.getStringExtra(EXTRA_LANGUAGE).toString())

                    Log.d(TAG, aksaraResult.toString())

                    if(binding.tvLearnAksaraTitle.text.toString() == aksaraResult) {
                        setSuccessDialogContent(aksaraResult)
                        successPopup.show()
                    } else {
                        failurePopup.show()
                    }

                    binding.btnCheckAksara.isEnabled = true
                }
                .addOnFailureListener { e ->
                    binding.pbInferenceLoading.visibility = View.GONE
                    Log.e(TAG, "Error classifying drawing.", e)
                    binding.btnCheckAksara.isEnabled = true
                }
        }
    }

    private fun initDialog() {
        successPopupBinding = AksaraPopupSuccessBinding.inflate(LayoutInflater.from(applicationContext))
        failurePopupBinding = AksaraPopupFailureBinding.inflate(LayoutInflater.from(applicationContext))

        successPopup = Dialog(this)
        failurePopup = Dialog(this)

        successPopup.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(successPopupBinding.root)
            setCancelable(true)
        }

        failurePopup.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(failurePopupBinding.root)
            setCancelable(true)
        }

        successPopupBinding.btnBack.setOnClickListener {
            finish()
        }

        successPopupBinding.btnTryAgain.setOnClickListener {
            successPopup.dismiss()
            drawView?.clearCanvas()
        }

        failurePopupBinding.btnTryAgain.setOnClickListener {
            failurePopup.dismiss()
        }
    }

    private fun setSuccessDialogContent(aksara: String) {
        successPopupBinding.tvCongrats.text = getString(R.string.pop_aksara_benar, aksara)
    }

    override fun onDestroy() {
        super.onDestroy()
        aksaraClassifier.close()
        successPopup.dismiss()
        failurePopup.dismiss()
    }

    companion object {
        const val EXTRA_AKSARA = "extra_aksara"
        const val EXTRA_AKSARA_IMG = "extra_aksara_img"

        const val EXTRA_LANGUAGE = "extra_language"
    }
}