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
import androidx.navigation.fragment.findNavController
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.databinding.MainFragmentBinding
import ru.profsoft.testappschool.ui.auth.AuthFragment
import ru.profsoft.testappschool.viewModel.MainViewModel
import ru.profsoft.testappschool.ui.auth.CustomDialogFragment

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var viewBinding: MainFragmentBinding? = null
    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewBinding = MainFragmentBinding.bind(view)

        viewBinding.button.setOnClickListener {
            val myDialogFragment = CustomDialogFragment()
            val manager = this.parentFragmentManager
            myDialogFragment.show(manager, "myDialog")
        }

        val login = viewBinding.loginText.text
        val password = viewBinding.passwordText.text
        val invalidSymbols = "\\s*(\\s|,|!|;|:|\\.)\\s*".toRegex()

        viewBinding.loginText.setOnClickListener {
            if (login.toString().indexOf('@')==-1 || login.toString().indexOf('@')!=login.toString().lastIndexOf('@') )
                viewBinding.loginTextLayout.error = "Неверная форма ввода"
            else viewBinding.buttonAuth.setImageDrawable(resources.getDrawable(R.drawable.button_1))
        }

        var message:String

        viewBinding.buttonAuth.setOnClickListener {
            val loginEnable = (!invalidSymbols.containsMatchIn(password.toString())) && (password!!.length>=6)
            if (loginEnable) {
                message = "Вы авторизованы, $login"
                startActivity(Intent(context, AuthFragment::class.java))
            }
            else {
                message = "Некорректный пароль"
                viewBinding.passwordTextLayout.error = "Пароль должен быть не менее 6 символов"
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
//            viewBinding.errorTextMessage1.text = message
//            viewBinding.errorTextMessage2.text = password
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

}