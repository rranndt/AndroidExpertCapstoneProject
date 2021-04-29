package id.expert.capstoneproject.di

import id.expert.capstoneproject.core.domain.usecase.MoviesInteractor
import id.expert.capstoneproject.core.domain.usecase.MoviesUseCase
import id.expert.capstoneproject.ui.detail.DetailMoviesViewModel
import id.expert.capstoneproject.ui.movies.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoviesUseCase> { MoviesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { DetailMoviesViewModel(get()) }
}