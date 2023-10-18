package com.example.kiwisaverinvestment.ui.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.kiwisaverinvestment.databinding.DonutChartBinding
import com.example.kiwisaverinvestment.databinding.FragmentInvestorTypeBinding
import com.example.kiwisaverinvestment.ui.description.recyclerview.FundDetailsAdapter
import com.example.kiwisaverinvestment.ui.home.DrawerLocker
import org.koin.androidx.viewmodel.ext.android.viewModel


class InvestorTypeFragment : Fragment() {

    private lateinit var fundDetailsAdapter: FundDetailsAdapter
    private val viewModel: InvestorTypeViewModel by viewModel()
    private lateinit var binding: FragmentInvestorTypeBinding
    private lateinit var chartBinding: DonutChartBinding
    private val arg: InvestorTypeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInvestorTypeBinding.inflate(inflater, container, false)
        (activity as DrawerLocker?)?.setDrawerLocked(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.dataPreload()
        viewModel.investorTypeResult.observe(viewLifecycleOwner) { result ->
            result.success?.get(arg.investorType)?.apply {
                binding.fundName.text = this.fundName
                binding.recyclerviewDetails.adapter = FundDetailsAdapter().apply {
                    fundDetailsAdapter = this
                }
                fundDetailsAdapter.refreshData(this.fundDetails)

                this.fundInvestmentMix.forEach {
                    
                }


            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as DrawerLocker?)?.setDrawerLocked(false)
    }

}