package ru.profsoft.testappschool.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.main_fragment.*
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.app.App
import ru.profsoft.testappschool.databinding.MainFragmentBinding
import ru.profsoft.testappschool.viewModel.MainViewModel
import ru.profsoft.testappschool.ui.auth.CustomDialogFragment
import ru.profsoft.testappschool.ui.base.BaseFragment
import ru.profsoft.testappschool.viewModel.MainViewModelFactory
import ru.profsoft.testappschool.viewModel.base.SavedStateViewModelFactory
import javax.inject.Inject

class MainFragment : BaseFragment<MainViewModel>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    init {
        App.INSTANCE.appComponent.inject(this@MainFragment)
    }

//    private var viewBinding: MainFragmentBinding? = null

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override val viewModel: MainViewModel by viewModels {
        SavedStateViewModelFactory(mainViewModelFactory, this)
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
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//    }

    override fun setupViews() {

        val login = loginText.text
        val password = passwordText.text
        val invalidSymbols = "\\s*(\\s|,|!|;|:|\\.)\\s*".toRegex()
        var passwordFormatIsCorrect = false
        var loginFormatIsCorrect = false

        button.setOnClickListener {
            val myDialogFragment = CustomDialogFragment()
            val manager = this.parentFragmentManager
            myDialogFragment.show(manager, "myDialog")
        }

        loginText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                loginFormatIsCorrect = login.toString().indexOf('@') != -1 && login.toString().indexOf('@') == login.toString().lastIndexOf('@')
                if (!loginFormatIsCorrect) {
                    loginTextLayout.error = "Неверная форма ввода"
                    buttonAuth.setImageDrawable(resources.getDrawable(R.drawable.button_4))
                }
                else {
                    loginTextLayout.error = null
                    if (passwordFormatIsCorrect) buttonAuth.setImageDrawable(resources.getDrawable(R.drawable.button_1))
                }
            }
        }

        passwordText.setOnClickListener {
            passwordFormatIsCorrect = (!invalidSymbols.containsMatchIn(password.toString())) && (password!!.length>=6)
            if (!passwordFormatIsCorrect) {
                passwordTextLayout.error = "Пароль короткий или содержит недопустимые символы"
                buttonAuth.setImageDrawable(resources.getDrawable(R.drawable.button_4))
            }
            else {
                passwordTextLayout.error = null
                if (loginFormatIsCorrect) buttonAuth.setImageDrawable(resources.getDrawable(R.drawable.button_1))
            }
        }

        buttonAuth.setOnClickListener {
            if (passwordFormatIsCorrect&&loginFormatIsCorrect) viewModel.navigateMainToAuthFragment()
        }
    }


}
