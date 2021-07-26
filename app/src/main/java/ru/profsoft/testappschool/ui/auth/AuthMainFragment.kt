package ru.profsoft.testappschool.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.auth_main_fragment.*
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.app.App
import ru.profsoft.testappschool.databinding.AuthMainFragmentBinding
import ru.profsoft.testappschool.databinding.MainFragmentBinding
import ru.profsoft.testappschool.ui.base.BaseFragment
import ru.profsoft.testappschool.viewModel.AuthViewModel
import ru.profsoft.testappschool.viewModel.AuthViewModelFactory
import ru.profsoft.testappschool.viewModel.base.SavedStateViewModelFactory
import javax.inject.Inject

class AuthMainFragment : BaseFragment<AuthViewModel>() {

    init {
        App.INSTANCE.appComponent.inject(this@AuthMainFragment)
    }

    private var viewBinding: AuthMainFragmentBinding? = null

    @Inject
    lateinit var authViewModelFactory:AuthViewModelFactory

    override val viewModel: AuthViewModel by viewModels {
        SavedStateViewModelFactory(authViewModelFactory, this)
    }

    override val layout: Int = R.layout.auth_main_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = AuthMainFragmentBinding.bind(view)
    }

    override fun setupViews() {
        btn_foward.setOnClickListener {
            viewModel.navigateToAuthFragment()
            //findNavController().navigate(R.id.action_authMainFragment_to_authFragment)
        }
    }
}