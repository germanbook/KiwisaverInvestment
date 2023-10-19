package com.example.kiwisaverinvestment.ui.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.futured.donut.DonutSection
import com.example.kiwisaverinvestment.R
import com.example.kiwisaverinvestment.data.model.FundInvestmentMix
import com.example.kiwisaverinvestment.databinding.FragmentInvestorTypeBinding
import com.example.kiwisaverinvestment.ui.description.adapter.FundDetailsAdapter
import com.example.kiwisaverinvestment.ui.description.adapter.InvestmentMixAdapter
import com.example.kiwisaverinvestment.ui.description.viewmodel.InvestorTypeViewModel
import com.example.kiwisaverinvestment.ui.home.DrawerLocker
import org.koin.androidx.viewmodel.ext.android.viewModel


class InvestorTypeFragment : Fragment() {

    private lateinit var fundDetailsAdapter: FundDetailsAdapter
    private lateinit var investmentMixAdapterRight: InvestmentMixAdapter
    private lateinit var investmentMixAdapterLeft: InvestmentMixAdapter
    private val viewModel: InvestorTypeViewModel by viewModel()
    private lateinit var binding: FragmentInvestorTypeBinding
    private val arg: InvestorTypeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInvestorTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as DrawerLocker?)?.setDrawerLocked(true)
        viewModel.dataPreload()
        viewModel.investorTypeResult.observe(viewLifecycleOwner) { result ->
            result.success?.get(arg.investorType)?.apply {
                binding.fundName.text = this.fundName
                binding.recyclerviewDetails.adapter = FundDetailsAdapter().apply {
                    fundDetailsAdapter = this
                }
                fundDetailsAdapter.refreshData(this.fundDetails)

                binding.donutChart.recyclerviewInvestmentLeft.adapter = InvestmentMixAdapter().apply {
                    investmentMixAdapterLeft = this
                }
                investmentMixAdapterLeft.refreshData(this.fundInvestmentMix, 0)

                binding.donutChart.recyclerviewInvestmentRight.adapter = InvestmentMixAdapter().apply {
                    investmentMixAdapterRight = this
                }
                investmentMixAdapterRight.refreshData(this.fundInvestmentMix, 3)

                setDonutChart(this.fundInvestmentMix)
                calculateAssets(this.fundInvestmentMix)

            }
        }
        binding.fabButton.setOnClickListener {
            findNavController().navigate(R.id.mainscreen)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as DrawerLocker?)?.setDrawerLocked(false)
    }

    private fun setDonutChart(fundInvestmentMix: List<FundInvestmentMix>) {
        val greenColorMap = mapOf(
            "Australasian equities" to R.color.green_start,
            "International equities" to R.color.green_middle,
            "Listed property" to R.color.green_end,
        )
        val blueColorMap = mapOf(
            "Cash and cash equivalents" to R.color.blue_start,
            "New Zealand fixed interest" to R.color.blue_middle,
            "International fixed interest" to R.color.blue_end,
        )

        var sections = mutableListOf<DonutSection>()
        var colorResId: Int

        for (item in fundInvestmentMix) {

            colorResId = when (item.sectionTitle) {
                "Australasian equities", "International equities", "Listed property" -> {
                    greenColorMap[item.sectionTitle]?: R.color.green_middle
                }
                else -> {
                    blueColorMap[item.sectionTitle]?: R.color.blue_middle
                }
            }
            context?.apply {
                val section = DonutSection(
                    name = item.sectionTitle,
                    color = ContextCompat.getColor(this, colorResId),
                    amount = (item.percentage / 100f)
                )
                sections.add(section)
            }
        }

        binding.donutChart.donutView.submitData(sections)
    }

    private fun calculateAssets(fundInvestmentMix: List<FundInvestmentMix>) {
        var growthAssets: Int = 0
        var incomeAssets: Int = 0

        for (item in fundInvestmentMix) {
            when (item.assetType) {
                "growth" -> growthAssets += item.percentage
                "income" -> incomeAssets += item.percentage
                else -> growthAssets
            }

        }

        binding.apply {
            this.growthAssets.apply {
                text = "${growthAssets}%"
                setTextColor(ContextCompat.getColor(this.context, R.color.green_start))
            }
            this.incomeAssets.apply {
                text = "${incomeAssets}%"
                setTextColor(ContextCompat.getColor(this.context, R.color.blue_start))
            }
        }
    }

}