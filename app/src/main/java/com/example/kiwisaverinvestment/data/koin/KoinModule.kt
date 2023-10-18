package com.example.kiwisaverinvestment.data.koin

import com.example.kiwisaverinvestment.data.repo.description.InvestorTypeDataSource
import com.example.kiwisaverinvestment.data.repo.description.InvestorTypeRepository
import com.example.kiwisaverinvestment.ui.description.InvestorTypeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingModule = module {
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

val allKoinModules = listOf(
    settingModule,
)
