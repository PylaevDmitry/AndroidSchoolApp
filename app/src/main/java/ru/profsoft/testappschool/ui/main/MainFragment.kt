package ru.profsoft.testappschool.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.auth_fragment.*
import kotlinx.android.synthetic.main.main_fragment.*
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.app.App
import ru.profsoft.testappschool.extentions.formatIsCorrect
import ru.profsoft.testappschool.ui.auth.ForgotPasswordDialogFragment
import ru.profsoft.testappschool.ui.base.BaseFragment
import ru.profsoft.testappschool.viewModel.HomeViewModel
import ru.profsoft.testappschool.viewModel.HomeViewModelFactory
import ru.profsoft.testappschool.viewModel.base.SavedStateViewModelFactory
import javax.inject.Inject

class MainFragment : BaseFragment<HomeViewModel>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    init {
        App.INSTANCE.appComponent.inject(this@MainFragment)
    }

//    private var viewBinding: MainFragmentBinding? = null

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    override val viewModel: HomeViewModel by viewModels {
        SavedStateViewModelFactory(homeViewModelFactory, this)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return inflater.inflate(R.layout.main_fragment, container, false)
//    }

    override val layout: Int = R.layout.main_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewBinding = MainFragmentBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        viewBinding = null
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//    }

    override fun setupViews() {

        val login = loginText.text
        val password = passwordText.text
        val invalidSymbols = "\\s*(\\s|,|!|;|:|\\.)\\s*".toRegex()

        button.setOnClickListener {
                ForgotPasswordDialogFragment().show(this.parentFragmentManager, "myDialog")
        }

        loginText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (login.formatIsCorrect()) {
                    loginTextLayout.error = null
                    if (password.formatIsCorrect(invalidSymbols, 6)) buttonAuth.setImageDrawable(resources.getDrawable(R.drawable.button_1))
                }
                else {
                    loginTextLayout.error = "Неверная форма ввода"
                    buttonAuth.setImageDrawable(resources.getDrawable(R.drawable.button_4))
                }
            }
        }

        passwordText.setOnClickListener {
            if (password.formatIsCorrect(invalidSymbols, 6)) {
                passwordTextLayout.error = null
                if (login.formatIsCorrect()) buttonAuth.setImageDrawable(resources.getDrawable(R.drawable.button_1))
            }
            else {
                passwordTextLayout.error = "Пароль короткий или содержит недопустимые символы"
                buttonAuth.setImageDrawable(resources.getDrawable(R.drawable.button_4))
            }
        }

        buttonAuth.setOnClickListener {
            if (password.formatIsCorrect(invalidSymbols, 6)&&login.formatIsCorrect()) {
                viewModel.navigateMainToAuthFragment(login.toString())
            }
        }

    }
}
