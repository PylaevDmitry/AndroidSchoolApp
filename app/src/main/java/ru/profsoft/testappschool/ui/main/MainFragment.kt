package ru.profsoft.testappschool.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.auth_fragment.*
import kotlinx.android.synthetic.main.main_fragment.*
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.app.App
import ru.profsoft.testappschool.formatIsCorrect
import ru.profsoft.testappschool.ui.auth.AuthFragment
import ru.profsoft.testappschool.viewModel.MainViewModel
import ru.profsoft.testappschool.ui.auth.ForgotPasswordDialogFragment
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

        button.setOnClickListener {
            val myDialogFragment = ForgotPasswordDialogFragment()
            val manager = this.parentFragmentManager
            myDialogFragment.show(manager, "myDialog")
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

//        val intent = Intent(context, AuthFragment::class.java)

        val bundle = Bundle()
        val frag = AuthFragment()


        buttonAuth.setOnClickListener {
            if (password.formatIsCorrect(invalidSymbols, 6)&&login.formatIsCorrect()) {
//                intent.putExtra("email", login.toString())
//                startActivity(intent)
                bundle.putString("email", login.toString())
                frag.arguments = bundle

                viewModel.navigateMainToAuthFragment()
            }
        }

    }

}
