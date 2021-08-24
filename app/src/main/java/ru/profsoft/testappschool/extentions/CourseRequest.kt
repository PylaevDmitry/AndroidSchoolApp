package ru.profsoft.testappschool.extentions

import ru.profsoft.testappschool.data.local.entity.Course
import ru.profsoft.testappschool.data.model.db.CourseRequest


fun CourseRequest.toCourse(): Course = Course(
    certNum = certNum,

    courseName = courseName,

    form = form,

    period = period,

    content = content,

    teacher = teacher,

    image = image)