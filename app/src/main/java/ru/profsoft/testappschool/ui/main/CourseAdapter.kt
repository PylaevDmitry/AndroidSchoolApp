package ru.profsoft.testappschool.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_course.view.*
import ru.profsoft.testappschool.R
import ru.profsoft.testappschool.app.visible
import ru.profsoft.testappschool.data.model.Course
import ru.profsoft.testappschool.ui.auth.AuthFragment
import ru.profsoft.testappschool.ui.auth.ShareProfileDialogFragment
import ru.profsoft.testappschool.ui.base.BaseFragment
import ru.profsoft.testappschool.viewModel.AuthViewModel

class CourseAdapter(val courses:List<Course>):RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): CourseAdapter.CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseAdapter.CourseViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount():Int = courses.size


    inner class CourseViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind (item: Course) {
            itemView.certNumTextView.text = item.certNum
            itemView.courseTextView.text = item.courseName
            itemView.formTextView.text = item.form
            itemView.periodTextView.text = item.period
            itemView.contentTextView.text = item.content
            itemView.teacherTextView.text = item.teacher

//            Glide.with(itemView.context).load(item.image).into(itemView.certImage)

            itemView.expandMoreButton.setOnClickListener {
                itemView.contentTextView2.visible()
                itemView.contentTextView.visible()
                itemView.teacherTextView2.visible()
                itemView.teacherTextView.visible()
                itemView.expandMoreButton.visible()
                itemView.expandLessButton.visible()
                itemView.certImage.visible()
                itemView.shareButton.visible()
            }

            itemView.expandLessButton.setOnClickListener {
                itemView.contentTextView2.visible()
                itemView.contentTextView.visible()
                itemView.teacherTextView2.visible()
                itemView.teacherTextView.visible()
                itemView.expandMoreButton.visible()
                itemView.expandLessButton.visible()
                itemView.certImage.visible()
                itemView.shareButton.visible()
            }

            itemView.shareButton.setOnClickListener {
                val myDialogFragment = ShareProfileDialogFragment()
                val manager = AuthFragment().parentFragmentManager
                myDialogFragment.show(manager, "myDialog")
            }
        }
    }
}

