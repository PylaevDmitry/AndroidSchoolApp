package ru.profsoft.testappschool.ui.auth

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.auth_fragment.*
import kotlinx.android.synthetic.main.item_course.*
import kotlinx.android.synthetic.main.item_course.view.*
import kotlinx.android.synthetic.main.main_fragment.*
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.app.App
import ru.profsoft.testappschool.app.visible
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

        emailTextView.text = arguments?.getString("email")

        photoImageView.setOnClickListener {
            photoEditImageView.visible()
        }

        photoEditImageView.setOnClickListener {
            photoEditImageView.visible()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(
                "content://media/internal/images/media"))
            startActivity(intent)
        }

        val course1 = Course("№0809-33434-233", "Design1", "очная", "01.07-01.09", "Создание макетов", "Щелкунова Юлия Сергеевна", "im")
        val course2 = Course("№0809-33434-233", "Design2", "очная", "01.07-01.09", "Создание 2 макетов", "Щелкунова Юлия Сергеевна", "im")
        val course3 = Course("№455-33434-233", "Design3", "заочная", "01.07-01.09", "Создание 3 макетов", "Щелкунова Юлия Сергеевна", "im")
        val course4 = Course("№065609-33434-233", "Design and UX", "очная", "01.07-01.09", "Создание 4 макетов", "Щелкунова Юлия Сергеевна", "im")
        val course5 = Course("№3466-33434", "Android", "очная", "01.07-01.09", "Создание android-приложения", "Щелкунова Юлия Сергеевна", "im")

        val list = listOf(course1, course2, course3, course4, course5)

        val recyclerView: RecyclerView = recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CourseAdapter(list, this.parentFragmentManager)

    }
}