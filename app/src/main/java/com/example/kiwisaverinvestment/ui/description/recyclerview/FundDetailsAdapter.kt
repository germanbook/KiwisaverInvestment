package com.example.kiwisaverinvestment.ui.description.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kiwisaverinvestment.databinding.FundDetailsItemBinding

class FundDetailsAdapter: RecyclerView.Adapter<FundDetailsAdapter.ViewHolder>() {

    private val fundDetails = ArrayList<String>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FundDetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return fundDetails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(fundDetails[position])
    }

    fun refreshData(data: List<String>) {
        fundDetails.clear()
        fundDetails.addAll(data)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private var binding: FundDetailsItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(fundDetail: String) {
            binding.text.text = fundDetail
        }
    }
}