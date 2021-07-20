package ru.profsoft.testappschool.viewModel

import androidx.lifecycle.SavedStateHandle
import ru.profsoft.testappschool.viewModel.base.BaseViewModel
import ru.profsoft.testappschool.viewModel.base.IViewModelState

class MainViewModel(
    handle:SavedStateHandle
) : BaseViewModel<MainState>(handle, MainState()) {

}

class MainState(): IViewModelState {
    override fun save(outState: SavedStateHandle) {
        //TODO("Not yet implemented")
    }
}