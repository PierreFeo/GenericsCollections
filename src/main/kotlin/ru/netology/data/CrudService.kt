package ru.netology.data

interface CrudService<T, B> {

    fun add(element: T): Int //Создает новую заметку у текущего пользователя.
    fun delete(element: T): Boolean // Удаляет заметку текущего пользователя.
    fun edit(element: T): Boolean //Редактирует заметку текущего пользователя.
    fun getById(element: T): T //Возвращает заметку по её id.
    fun get():Boolean// Возвращает список заметок, созданных пользователем.

    fun createComment(element: B): Int //Добавляет новый комментарий к заметке
    fun deleteComment(element: B): Boolean //Удаляет комментарий к заметке.
    fun editComment(element: B): Boolean // Редактирует указанный комментарий у заметки.
    fun getComments(element: B): Boolean // Возвращает список комментариев к заметке.

    fun restoreComment(element: B): Boolean //Восстанавливает удалённый комментарий

}