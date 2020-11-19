package com.pradioep.jokesonyou.di

import com.pradioep.jokesonyou.repository.Repository
import org.koin.dsl.module

val RepositoryModule = module {
    single { Repository(get()) }
}