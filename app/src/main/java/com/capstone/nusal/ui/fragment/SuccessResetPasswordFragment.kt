package com.capstone.nusal.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.nusal.R
import com.capstone.nusal.databinding.FragmentSuccessResetPasswordBinding

class SuccessResetPasswordFragment : Fragment() {

    private lateinit var binding: FragmentSuccessResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuccessResetPasswordBinding.inflate(layoutInflater, container, false)
        return inflater.inflate(R.layout.fragment_success_reset_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}