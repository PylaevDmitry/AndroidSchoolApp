package ru.profsoft.testappschool.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.databinding.MainFragmentBinding
import ru.profsoft.testappschool.viewModel.MainViewModel

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
        viewBinding = MainFragmentBinding.bind(view)

        requireContext().resources.getString(R.string.app_name)

        viewBinding?.btnAuth?.setOnClickListener {
            Toast.makeText(requireContext(), "Вы авторизованы!", Toast.LENGTH_SHORT).show()
            viewBinding?.btnAuth?.text = "Вы авторизованы!"
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