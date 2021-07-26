package ru.profsoft.testappschool.viewModel

import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.viewModel.base.BaseViewModel
import ru.profsoft.testappschool.viewModel.base.IViewModelFactory
import ru.profsoft.testappschool.viewModel.base.IViewModelState
import ru.profsoft.testappschool.viewModel.base.NavigationCommand
import javax.inject.Inject

class AuthViewModel(
    handle:SavedStateHandle
) : BaseViewModel<AuthViewModelState>(handle, AuthViewModelState()) {

    fun navigateToAuthFragment() {
        val bundle = bundleOf("somekey" to "429489328932")
        navigate(NavigationCommand.To(R.id.action_authMainFragment_to_authFragment,bundle))
    }

    fun navigateBack() {
        navigate(NavigationCommand.Back())
    }
}

class AuthViewModelFactory @Inject constructor() : IViewModelFactory<AuthViewModel> {
    override fun create(handle: SavedStateHandle): AuthViewModel {
        return AuthViewModel(handle)
    }
}

class AuthViewModelState:IViewModelState