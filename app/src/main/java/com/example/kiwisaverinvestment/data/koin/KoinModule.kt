package com.example.kiwisaverinvestment.data.koin

import com.example.kiwisaverinvestment.data.repo.description.InvestorTypeDataSource
import com.example.kiwisaverinvestment.data.repo.description.InvestorTypeRepository
import com.example.kiwisaverinvestment.data.repo.questionnaire.QuestionnaireDataSource
import com.example.kiwisaverinvestment.data.repo.questionnaire.QuestionnaireRepository
import com.example.kiwisaverinvestment.ui.description.InvestorTypeViewModel
import com.example.kiwisaverinvestment.ui.questionnaire.viewmodel.QuestionnaireResultViewModel
import com.example.kiwisaverinvestment.ui.questionnaire.viewmodel.QuestionnaireViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val investorTypeModule = module {
    single {
        InvestorTypeDataSource(androidApplication())
    }

    single {
        InvestorTypeRepository(get())
    }

    viewModel {
        InvestorTypeViewModel(get())
    }
}

val questionnaireModule = module {
    single {
        QuestionnaireDataSource()
    }

    single {
        QuestionnaireRepository(get())
    }

    viewModel {
        QuestionnaireViewModel(get())
    }
}

val questionnaireResultModule = module {
    viewModel {
        QuestionnaireResultViewModel()
    }
}

val allKoinModules = listOf(
    investorTypeModule,
    questionnaireModule,
    questionnaireResultModule,
)
