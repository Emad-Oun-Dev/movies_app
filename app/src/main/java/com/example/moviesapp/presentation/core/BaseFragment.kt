package com.example.moviesapp.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.moviesapp.R

/**
 * Created by Emad Mohamed Oun
 * Movies App
 * emad.3oon@gmail.com
 */

abstract class BaseFragment<VB: ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!
    private var progressDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        initializeProgressDialog()
        return binding.root
    }

    private fun initializeProgressDialog() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext()).setCancelable(false)
        alertDialogBuilder.setView(R.layout.progress_dialog_loader)
        progressDialog = alertDialogBuilder.create()
        progressDialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        progressDialog?.window?.setDimAmount(.1f)
        progressDialog?.setCancelable(false)
        progressDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun showProgressDialog(show: Boolean) {
        try {
            if (progressDialog != null) {
                if (show && !progressDialog!!.isShowing) progressDialog!!.show() else progressDialog!!.cancel()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
