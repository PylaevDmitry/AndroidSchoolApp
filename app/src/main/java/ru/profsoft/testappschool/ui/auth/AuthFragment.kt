package ru.profsoft.testappschool.ui.auth

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.auth_fragment.*
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.app.App
import ru.profsoft.testappschool.ui.base.BaseFragment
import ru.profsoft.testappschool.viewModel.AuthViewModel
import ru.profsoft.testappschool.viewModel.AuthViewModelFactory
import ru.profsoft.testappschool.viewModel.base.SavedStateViewModelFactory
import javax.inject.Inject

class AuthFragment:BaseFragment<AuthViewModel>() {
    init {
        App.INSTANCE.appComponent.inject(this@AuthFragment)
    }

    @Inject
    lateinit var authViewModelFactory: AuthViewModelFactory

    override val viewModel: AuthViewModel by viewModels {
        SavedStateViewModelFactory(authViewModelFactory, this)
    }
    override val layout: Int = R.layout.auth_fragment

    override fun setupViews() {
        btn_back.setOnClickListener {
           viewModel.navigateBack()
        }
    }
}