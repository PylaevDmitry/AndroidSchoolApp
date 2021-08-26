package ru.profsoft.testappschool.ui.auth

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.auth_fragment.*
import kotlinx.android.synthetic.main.item_course.*
import kotlinx.android.synthetic.main.item_course.view.*
import kotlinx.android.synthetic.main.main_fragment.*
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.app.App
import ru.profsoft.testappschool.extentions.visible
import ru.profsoft.testappschool.data.model.db.CourseRequest
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

        viewModel.getUser()

        viewModel.observeUsers(viewLifecycleOwner) { courses ->


        }

        emailTextView.text = arguments?.getString("email")

        photoImageView.setOnClickListener {
            photoEditImageView.visible()
        }

        photoEditImageView.setOnClickListener {
            photoEditImageView.visible()
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(
//                "content://media/internal/images/media"))
//            startActivity(intent)
            openGalleryForImage()
        }

        val course1 = CourseRequest("№0809-33434-233", "Design1", "очная", "01.07-01.09", "Создание макетов", "Щелкунова Юлия Сергеевна", "im")
        val course2 = CourseRequest("№0809-33434-233", "Design2", "очная", "01.07-01.09", "Создание 2 макетов", "Щелкунова Юлия Сергеевна", "im")
        val course3 = CourseRequest("№455-33434-233", "Design3", "заочная", "01.07-01.09", "Создание 3 макетов", "Щелкунова Юлия Сергеевна", "im")
        val course4 = CourseRequest("№065609-33434-233", "Design and UX", "очная", "01.07-01.09", "Создание 4 макетов", "Щелкунова Юлия Сергеевна", "im")
        val course5 = CourseRequest("№3466-33434", "Android", "очная", "01.07-01.09", "Создание android-приложения", "Щелкунова Юлия Сергеевна", "im")

        val list = listOf(course1, course2, course3, course4, course5)

        val recyclerView: RecyclerView = recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CourseAdapter(list) {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Мой профиль на Profsoft.pro")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
//            val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, data?.data)
//            photoImageView.setImageBitmap(bitmap)

//            val inputStream = data?.data?.let { activity?.contentResolver?.openInputStream(it) }
//            val drawable = Drawable.createFromStream(inputStream, data?.data.toString())
//            photoImageView.setImageDrawable(drawable)

            Glide.with(requireContext()).load(data?.data).into(photoImageView)
//            photoImageView.setImageURI(data?.data)

        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    companion object {
        private const val REQUEST_CODE = 100
    }

}