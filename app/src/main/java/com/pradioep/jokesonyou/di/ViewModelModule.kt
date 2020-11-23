package com.pradioep.jokesonyou.di

import com.pradioep.jokesonyou.ui.detail.DetailViewModel
import com.pradioep.jokesonyou.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}