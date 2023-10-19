package com.example.kiwisaverinvestment.ui.questionnaire.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kiwisaverinvestment.data.model.Answer
import com.example.kiwisaverinvestment.data.model.Question
import com.example.kiwisaverinvestment.databinding.AnswerItemBinding

class AnswerAdapter: RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {

    private var answerClickListener: OnAnswerClickListener? = null
    private var selectedPosition = RecyclerView.NO_POSITION
    private val answers = ArrayList<Answer>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnswerAdapter.ViewHolder {
        val binding = AnswerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswerAdapter.ViewHolder, position: Int) {
        holder.bind(answers[position])

        holder.itemView.setOnClickListener {
            val previousSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition

            // update UI
            notifyItemChanged(previousSelectedPosition)
            notifyItemChanged(selectedPosition)

            // get selected answer
            val selectedAnswer = answers[selectedPosition]

            val aa = selectedAnswer

            answerClickListener?.onAnswerClick(selectedPosition)


        }
        holder.itemView.isSelected = selectedPosition == position
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    fun refreshData(data: List<Answer>) {
        answers.clear()
        answers.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: AnswerItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(answer: Answer) {
            binding.question.text = answer.answerText
            binding.mark.text = answer.score.toString()
        }
    }

    interface OnAnswerClickListener {
        fun onAnswerClick(position: Int)
    }

    fun setOnAnswerClickListener(listener: OnAnswerClickListener) {
        answerClickListener = listener
    }

}