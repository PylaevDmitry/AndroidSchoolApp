package ru.profsoft.testappschool.extentions

import ru.profsoft.testappschool.data.local.entity.User
import ru.profsoft.testappschool.data.model.UserRes


fun UserRes.toUser(): User = User(
    id = id,
    username = username)