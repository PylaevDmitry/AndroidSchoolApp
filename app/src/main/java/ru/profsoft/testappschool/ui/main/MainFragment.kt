package ru.profsoft.testappschool.ui.main

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.main_fragment.*
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.app.App
import ru.profsoft.testappschool.databinding.MainFragmentBinding
import ru.profsoft.testappschool.ui.auth.AuthFragment
import ru.profsoft.testappschool.viewModel.MainViewModel
import ru.profsoft.testappschool.ui.auth.CustomDialogFragment
import ru.profsoft.testappschool.ui.base.BaseFragment
import ru.profsoft.testappschool.viewModel.AuthViewModel
import ru.profsoft.testappschool.viewModel.AuthViewModelFactory
import ru.profsoft.testappschool.viewModel.base.SavedStateViewModelFactory
import javax.inject.Inject

class MainFragment : BaseFragment<AuthViewModel>() {
    init {
        App.INSTANCE.appComponent.inject(this@MainFragment)
    }

    @Inject
    lateinit var authViewModelFactory: AuthViewModelFactory

    override val viewModel: AuthViewModel by viewModels {
        SavedStateViewModelFactory(authViewModelFactory, this)
    }

//    companion object {
//        fun newInstance() = MainFragment()
//    }

    private var viewBinding: MainFragmentBinding? = null
//    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = MainFragmentBinding.bind(view)

        button.setOnClickListener {
            val myDialogFragment = CustomDialogFragment()
            val manager = this.parentFragmentManager
            myDialogFragment.show(manager, "myDialog")
        }

        val login = loginText.text
        val password = passwordText.text
        val invalidSymbols = "\\s*(\\s|,|!|;|:|\\.)\\s*".toRegex()

        loginText.setOnClickListener {
            if (login.toString().indexOf('@')==-1 || login.toString().indexOf('@')!=login.toString().lastIndexOf('@') )
                loginTextLayout.error = "Неверная форма ввода"
            else buttonAuth.setImageDrawable(resources.getDrawable(R.drawable.button_1))
        }

        buttonAuth.setOnClickListener {
            val message:String
            val loginEnable = (!invalidSymbols.containsMatchIn(password.toString())) && (password!!.length>=6)
            if (loginEnable) {
                message = "Вы авторизованы, $login"
                viewModel.navigateMainToAuthFragment()
            }
            else {
                message = "Некорректный пароль"
                passwordTextLayout.error = "Пароль должен быть не менее 6 символов"
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
//            viewBinding.errorTextMessage1.text = message
//            viewBinding.errorTextMessage2.text = password
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        viewBinding = null
//    }
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//    }

    override val layout: Int = R.layout.main_fragment

    override fun setupViews() {

    }
}