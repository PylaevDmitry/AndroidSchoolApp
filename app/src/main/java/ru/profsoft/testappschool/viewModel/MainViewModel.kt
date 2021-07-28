package ru.profsoft.testappschool.viewModel

import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.viewModel.base.BaseViewModel
import ru.profsoft.testappschool.viewModel.base.IViewModelFactory
import ru.profsoft.testappschool.viewModel.base.IViewModelState
import ru.profsoft.testappschool.viewModel.base.NavigationCommand
import javax.inject.Inject

class MainViewModel(
    handle: SavedStateHandle
) : BaseViewModel<MainState>(handle, MainState()) {

    private val privateRoutes = listOf(R.id.bottom_navigate)

    override fun navigate(navigationCommand: NavigationCommand) {
        when (navigationCommand) {
            is NavigationCommand.To -> {
                if (privateRoutes.contains(navigationCommand.destination)) {
                    super.navigate(NavigationCommand.StartLogin(navigationCommand.destination))
                } else {
                    super.navigate(navigationCommand)
                }
            }
            else -> super.navigate(navigationCommand)
        }
    }

    fun navigateMainToAuthFragment() {
        val bundle = bundleOf("somekey" to "429489328932")
        navigate(NavigationCommand.To(R.id.action_mainFragment_to_authFragment,bundle))
    }
}

class MainViewModelFactory @Inject constructor() : IViewModelFactory<MainViewModel> {
    override fun create(handle: SavedStateHandle): MainViewModel {
        return MainViewModel(handle)
    }
}

class MainState : IViewModelState