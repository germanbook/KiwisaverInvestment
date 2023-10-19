package com.example.kiwisaverinvestment.ui.questionnaire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kiwisaverinvestment.KiwisaverInvestmentApplication
import com.example.kiwisaverinvestment.R
import com.example.kiwisaverinvestment.data.model.Answer
import com.example.kiwisaverinvestment.data.model.Question
import com.example.kiwisaverinvestment.databinding.FragmentQuestionnaireBinding
import com.example.kiwisaverinvestment.ui.questionnaire.adapter.AnswerAdapter
import com.example.kiwisaverinvestment.ui.questionnaire.viewmodel.QuestionnaireViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionnaireFragment : Fragment(), AnswerAdapter.OnAnswerClickListener {

    private var isFinished: Boolean = false
    private lateinit var selectedAnswer: Answer
    private var questionnaireScore: Int = 0
    private lateinit var _question: Question
    private lateinit var answerAdapter: AnswerAdapter
    private val viewModel: QuestionnaireViewModel by viewModel()
    private lateinit var binding: FragmentQuestionnaireBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionnaireBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.dataPreload()
        viewModel.question.observe(viewLifecycleOwner) { result ->
            _question = result
            binding.question.text = result.questionText
            binding.recyclerviewQuestion.adapter = AnswerAdapter().apply {
                answerAdapter = this
            }
            answerAdapter.apply {
                refreshData(result.answers)
                setOnAnswerClickListener(this@QuestionnaireFragment)
            }
        }

        binding.btnNext.setOnClickListener {

            if (isFinished) {
                KiwisaverInvestmentApplication.user?.score = questionnaireScore
                val action = QuestionnaireFragmentDirections.toQuestionnaireResult()
                findNavController().navigate(action)
            }

            _question?.apply {
                selectedAnswer?.apply {
                    questionnaireScore += this.score
                }
            }
            if (!viewModel.isQuestionnaireFinished()) {
                updateQuestion()
                binding.apply {
                    selectedQuestion.text = ""
                    selectedQuestion.visibility = View.INVISIBLE
                    btnNext.visibility = View.INVISIBLE
                }
            } else {
                binding.btnNext.setText(R.string.btn_finish)
                isFinished = true
            }


        }

    }

    private fun updateQuestion() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getNextQuestion()
        }
    }

    override fun onAnswerClick(position: Int) {

        _question?.apply {
            binding.apply {
                btnNext.visibility = View.VISIBLE
                selectedQuestion.visibility = View.VISIBLE
                selectedAnswer = _question.answers[position]
                selectedQuestion.text = selectedAnswer.answerText
            }
        }
    }

}