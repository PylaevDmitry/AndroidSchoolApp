package ru.profsoft.testappschool.ui.auth

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.auth_fragment.*
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.app.App
import ru.profsoft.testappschool.data.model.Course
import ru.profsoft.testappschool.ui.base.BaseFragment
import ru.profsoft.testappschool.ui.main.CourseAdapter
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
//        btn_back.setOnClickListener {
//           viewModel.navigateBack()
//        }

//        val course = Course("0809", "Design", "очная", "01.07-01.09", "Создание макетов", "Щелкунова Юлия Сергеевна", "im")
        val course1 = Course("1")
        val course2 = Course("2")
        val course3 = Course("3")
        val course4 = Course("4")
        val course5 = Course("5")
        val course6 = Course("6")
        val course7 = Course("7")
        val course8 = Course("8")
        val course9 = Course("9")
        val course10 = Course("10")
        val course11 = Course("11")
        val course12 = Course("12")

        val list = listOf(course1, course2, course3, course4, course5, course6, course7, course8, course9, course10, course11, course12)

        val recyclerView: RecyclerView = recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CourseAdapter(list)
    }
}