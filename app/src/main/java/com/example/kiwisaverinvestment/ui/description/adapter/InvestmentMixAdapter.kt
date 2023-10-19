package com.example.kiwisaverinvestment.ui.description.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kiwisaverinvestment.R
import com.example.kiwisaverinvestment.data.model.FundInvestmentMix
import com.example.kiwisaverinvestment.databinding.InvestmentMixItemBinding
import java.lang.Integer.min

class InvestmentMixAdapter: RecyclerView.Adapter<InvestmentMixAdapter.ViewHolder>() {

    private val fundInvestmentMix = ArrayList<FundInvestmentMix>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = InvestmentMixItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return fundInvestmentMix.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(fundInvestmentMix[position])
    }

    fun refreshData(data: List<FundInvestmentMix>, startIndex: Int) {
        fundInvestmentMix.clear()
        if (startIndex >= 0 && startIndex < data.size) {
            val endIndex = min(startIndex + 3, data.size)
            fundInvestmentMix.addAll(data.subList(startIndex, endIndex))
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: InvestmentMixItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(fundInvestmentMix: FundInvestmentMix) {
            binding.text.text = fundInvestmentMix.sectionTitle
            binding.percentage.text = "${fundInvestmentMix.percentage.toString()}%"
            if (fundInvestmentMix.assetType == "growth") {
                binding.percentage.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green_start))
            }
        }
    }
}