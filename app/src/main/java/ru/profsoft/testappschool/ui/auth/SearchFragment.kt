package ru.profsoft.testappschool.ui.auth

import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.auth_fragment.*
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.app.App
import ru.profsoft.testappschool.ui.base.BaseFragment
import ru.profsoft.testappschool.viewModel.AuthViewModel
import ru.profsoft.testappschool.viewModel.AuthViewModelFactory
import ru.profsoft.testappschool.viewModel.base.SavedStateViewModelFactory
import javax.inject.Inject

class SearchFragment:BaseFragment<AuthViewModel>() {
    init {
        App.INSTANCE.appComponent.inject(this@SearchFragment)
    }

    @Inject
    lateinit var authViewModelFactory: AuthViewModelFactory

    override val viewModel: AuthViewModel by viewModels {
        SavedStateViewModelFactory(authViewModelFactory, this)
    }
    override val layout: Int = R.layout.search_fragment

    override fun setupViews() {


    }
}
