package com.example.kiwisaverinvestment.ui.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import com.example.kiwisaverinvestment.R
import com.example.kiwisaverinvestment.databinding.FragmentMainScreenBinding
import kotlin.system.exitProcess

class MainScreenFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val alertDialog = AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.dialog_title_quit))
                .setMessage(getString(R.string.dialog_title_quit_message))
                .setPositiveButton(getString(R.string.dialog_ok)) { dialog, _ ->
                    dialog.dismiss()
                    requireActivity().finishAffinity()
                    exitProcess(0)
                }
                .create()
            alertDialog.show()
        }
    }
}