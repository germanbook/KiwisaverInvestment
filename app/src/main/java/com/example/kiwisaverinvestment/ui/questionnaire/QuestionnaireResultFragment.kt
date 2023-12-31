package com.example.kiwisaverinvestment.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kiwisaverinvestment.KiwisaverInvestmentApplication
import com.example.kiwisaverinvestment.databinding.FragmentQuestionnaireResultBinding
import com.example.kiwisaverinvestment.ui.questionnaire.viewmodel.QuestionnaireResultViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionnaireResultFragment : Fragment() {

    private val viewModel: QuestionnaireResultViewModel by viewModel()
    private lateinit var binding: FragmentQuestionnaireResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionnaireResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        KiwisaverInvestmentApplication.user?.score?.apply {
            binding.resultScore.text = this.toString()
            binding.investorTypeResult.text = viewModel.getInvestorTypeByScore(this).investorTypeName

            val navView = requireActivity()
        }

        binding.btnShowMore.setOnClickListener {
            KiwisaverInvestmentApplication.user?.investorType?.apply {
                val action = QuestionnaireResultFragmentDirections.resultToInvestorTypeDetails(this)
                findNavController().navigate(action)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // Disable the back button in this page
        }
    }

}