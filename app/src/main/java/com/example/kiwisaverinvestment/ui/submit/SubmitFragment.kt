package com.example.kiwisaverinvestment.ui.submit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kiwisaverinvestment.KiwisaverInvestmentApplication
import com.example.kiwisaverinvestment.R
import com.example.kiwisaverinvestment.databinding.FragmentSubmitBinding
import com.example.kiwisaverinvestment.ui.home.DrawerLocker
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubmitFragment : Fragment() {

    private val viewModel: SubmitViewModel by viewModel()
    private lateinit var binding: FragmentSubmitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubmitBinding.inflate(inflater, container, false)
        (activity as DrawerLocker?)?.setDrawerLocked(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (KiwisaverInvestmentApplication.user.score == null) {
            showStopDialog()
        }

        binding.progressbar.visibility = View.INVISIBLE

        binding.nameInput.addTextChangedListener { validateName(it.toString()) }

        binding.emailInput.addTextChangedListener { validateEmail(it.toString()) }

        binding.phoneInput.addTextChangedListener { validatePhone(it.toString()) }

        binding.btnSubmit.setOnClickListener {
            validateAndSubmit()
        }

        viewModel.submitResult.observe(viewLifecycleOwner) {
            if (it) {
                binding.apply {
                    progressbar.visibility = View.INVISIBLE
                    btnSubmit.text = getString(R.string.nav_result_submitted)
                    showSuccessDialog()
                }
            } else {
                binding.btnSubmit.isEnabled = true
            }
        }

        binding.fabButton.setOnClickListener {
            findNavController().navigate(R.id.mainscreen)
        }

    }

    private fun validateName(name: String): Boolean {
        val regex = "^[a-zA-Z ]+\$".toRegex()
        val isValid = regex.matches(name)
        if (!isValid) {
            binding.nameInput.error = "Invalid name"
        } else {
            binding.nameInput.error = null
        }
        return isValid
    }

    private fun validateEmail(email: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@(.+)\$".toRegex()
        val isValid = regex.matches(email)
        if (!isValid) {
            binding.emailInput.error = "Invalid email"
        } else {
            binding.emailInput.error = null
        }
        return isValid
    }

    private fun validatePhone(phone: String): Boolean {
        val regex = "^[0-9]{9,10}\$".toRegex()
        val isValid = regex.matches(phone)
        if (!isValid) {
            binding.phoneInput.error = "Invalid phone number"
        } else {
            binding.phoneInput.error = null
        }
        return isValid
    }

    private fun validateAndSubmit() {

        val name = binding.nameInput.text.toString()
        val email = binding.emailInput.text.toString()
        val phone = binding.phoneInput.text.toString()

        if (validateName(name) && validateEmail(email) && validatePhone(phone)) {
            binding.apply {
                progressbar.visibility = View.VISIBLE
                btnSubmit.isEnabled = false
            }
            KiwisaverInvestmentApplication.user?.apply {
                this.name = name
                this.email = email
                this.phoneNumber = phone
                viewModel.submitToServer()
            }
        }
    }

    private fun showSuccessDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.nav_result_submitted))
            .setMessage(getString(R.string.submitted_message))
            .setPositiveButton(getString(R.string.dialog_ok)) { dialog, _ ->
                dialog.dismiss()
                KiwisaverInvestmentApplication.user.apply {
                    name = null
                    phoneNumber = null
                    email = null
                    score = null
                    investorType = null
                    investorTypeName = null
                }
                val action = SubmitFragmentDirections.submittedToHome(-1)
                findNavController().navigate(action)
            }
            .create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()
    }

    private fun showStopDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_stop_title))
            .setMessage(getString(R.string.dialog_stop_message))
            .setPositiveButton(getString(R.string.dialog_ok)) { dialog, _ ->
                dialog.dismiss()
                val action = SubmitFragmentDirections.submittedToHome(-1)
                findNavController().navigate(action)
            }
            .create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as DrawerLocker?)?.setDrawerLocked(false)
    }
}