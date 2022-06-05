package com.capstone.nusal.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.nusal.R
import com.capstone.nusal.databinding.FragmentSuccessResetPasswordBinding

class SuccessResetPasswordFragment : Fragment() {

    private var binding: FragmentSuccessResetPasswordBinding? = null
    private var mContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuccessResetPasswordBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnBackToLogin?.setOnClickListener {
            val mActivity = mContext as Activity
            mActivity.finish()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        binding = null
        mContext = null
    }
}