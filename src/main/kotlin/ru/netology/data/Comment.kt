package ru.netology.data

data class Comment(
    val commentId: Int = 0,
    val noteId: Int = 0,
    val message: String = "Текст комментария",
    var delOrNot: Boolean = false
)