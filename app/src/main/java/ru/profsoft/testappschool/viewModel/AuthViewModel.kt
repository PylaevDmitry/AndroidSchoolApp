package ru.profsoft.testappschool.viewModel

import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.data.local.entity.Course
import ru.profsoft.testappschool.data.repositories.IUserRepository
import ru.profsoft.testappschool.data.repositories.UserRepository
import ru.profsoft.testappschool.viewModel.base.BaseViewModel
import ru.profsoft.testappschool.viewModel.base.IViewModelFactory
import ru.profsoft.testappschool.viewModel.base.IViewModelState
import ru.profsoft.testappschool.viewModel.base.NavigationCommand
import javax.inject.Inject

class AuthViewModel(
    handle:SavedStateHandle,
    private val repository: IUserRepository
) : BaseViewModel<AuthViewModelState>(handle, AuthViewModelState()) {

    private val usersLiveData:MutableLiveData<List<Course>> by lazy { MutableLiveData<List<Course>>() }

    fun observeCourses(owner:LifecycleOwner, onChange: (List<Course>) -> Unit) {
        usersLiveData.observe(owner, Observer {onChange(it)})
    }

    fun navigateToAuthFragment() {
        val bundle = bundleOf("somekey" to "429489328932")
        navigate(NavigationCommand.To(R.id.action_authMainFragment_to_authFragment,bundle))
    }

    fun navigateToMainFragment() {
        val bundle = bundleOf("somekey" to "429489328932")
        navigate(NavigationCommand.To(R.id.action_authMainFragment_to_mainFragment,bundle))
    }

    fun getCourses() {
        launchSafety {
            val courses = repository.getCoursesFromDatabase()
            usersLiveData.value = courses
        }
    }

}

class AuthViewModelFactory @Inject constructor(private val repository:IUserRepository) : IViewModelFactory<AuthViewModel> {
    override fun create(handle: SavedStateHandle): AuthViewModel {
        return AuthViewModel(handle, repository)
    }
}

class AuthViewModelState:IViewModelState