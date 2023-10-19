package com.example.kiwisaverinvestment.data.koin

import com.example.kiwisaverinvestment.data.repo.description.InvestorTypeDataSource
import com.example.kiwisaverinvestment.data.repo.description.InvestorTypeRepository
import com.example.kiwisaverinvestment.data.repo.questionnaire.QuestionnaireDataSource
import com.example.kiwisaverinvestment.data.repo.questionnaire.QuestionnaireRepository
import com.example.kiwisaverinvestment.data.repo.submit.SubmitDataSource
import com.example.kiwisaverinvestment.data.repo.submit.SubmitRepository
import com.example.kiwisaverinvestment.ui.description.viewmodel.InvestorTypeViewModel
import com.example.kiwisaverinvestment.ui.questionnaire.viewmodel.QuestionnaireResultViewModel
import com.example.kiwisaverinvestment.ui.questionnaire.viewmodel.QuestionnaireViewModel
import com.example.kiwisaverinvestment.ui.submit.SubmitViewModel
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

val submitModule = module {
    single {
        SubmitDataSource()
    }

    single {
        SubmitRepository(get())
    }

    viewModel {
        SubmitViewModel(get())
    }
}

val allKoinModules = listOf(
    investorTypeModule,
    questionnaireModule,
    questionnaireResultModule,
    submitModule,
)
