package ru.profsoft.testappschool.viewModel

import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.viewModel.base.BaseViewModel
import ru.profsoft.testappschool.viewModel.base.IViewModelFactory
import ru.profsoft.testappschool.viewModel.base.IViewModelState
import ru.profsoft.testappschool.viewModel.base.NavigationCommand
import javax.inject.Inject

class HomeViewModel(
    handle: SavedStateHandle
) : BaseViewModel<MainState>(handle, MainState()) {

    fun navigateMainToAuthFragment(login:String) {
        val bundle = bundleOf("email" to login)
        navigate(NavigationCommand.To(R.id.action_mainFragment_to_authFragment,bundle))
    }
}

class HomeViewModelFactory @Inject constructor() : IViewModelFactory<HomeViewModel> {
    override fun create(handle: SavedStateHandle): HomeViewModel {
        return HomeViewModel(handle)
    }
}

class HomeModelState : IViewModelState