package com.example.kiwisaverinvestment.ui.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.kiwisaverinvestment.R
import com.example.kiwisaverinvestment.ui.home.DrawerLocker
import kotlin.system.exitProcess

class MainScreenFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
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