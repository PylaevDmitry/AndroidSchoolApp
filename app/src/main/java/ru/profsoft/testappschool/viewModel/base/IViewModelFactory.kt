package ru.profsoft.testappschool.viewModel.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface IViewModelFactory <T: ViewModel> {
    fun create(handle: SavedStateHandle) : T
}