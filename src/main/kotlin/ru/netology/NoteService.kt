package ru.netology

import ru.netology.data.Comment
import ru.netology.data.CrudService
import ru.netology.data.Notes
import ru.netology.exception.FileNotFoundException

open class NoteService() : CrudService<Notes, Comment> {

    private var notesList = mutableListOf<Notes>()
    private var commentList = mutableListOf<Comment>()

    override fun add(element: Notes): Int {
        notesList.add(element.copy(nid = notesList.size + 1))
        return notesList.last().nid
    }

    override fun delete(element: Notes): Boolean {
        for ((index, value) in notesList.withIndex()) {
            if (element.nid == value.nid) {
                notesList.removeAt(index)
                return true
            }
        }
        throw FileNotFoundException("Заметка с id ${element.nid} не найдена")
    }

    override fun edit(element: Notes): Boolean {
        notesList.withIndex().forEach { (index, value) ->
            if (element.nid == value.nid) {
                notesList[index] = element.copy()
                return true
            }
        }
        throw FileNotFoundException("Заметка с id ${element.nid} не найдена")
    }

    override fun getById(element: Notes): Notes {
        for (i in notesList) {
            if (i.nid == element.nid) {
                println("По этому id найдена заметка: $i")
                return i
            }
        }
        throw FileNotFoundException("Заметка с id ${element.nid} не найдена")
    }

    override fun get(): Boolean {
        println("Список заметок пользователя:")
        notesList.forEach { println("Заметка$: $it") }
        return true
    }


    override fun createComment(element: Comment): Int {
        notesList.forEach { i ->
            if (i.nid == element.noteId) {
                commentList.add(element.copy(commentId = commentList.size + 1))
                return commentList.last().commentId
            }
        }
        throw FileNotFoundException("Не могу добавить коментарий! Заметка с id ${element.noteId} не найдена")
    }

    override fun deleteComment(element: Comment): Boolean {
        for (i in commentList) {
            if (i.commentId == element.commentId) {
                i.delOrNot = true
                return true
            }
        }
        throw FileNotFoundException("Коментарий с id ${element.commentId} не найден")
    }

    override fun editComment(element: Comment): Boolean {
        for ((index, value) in commentList.withIndex()) {
            if (element.commentId == value.commentId && !element.delOrNot) {
                commentList[index] = element.copy()
                return true
            }
        }
        throw FileNotFoundException("Коментарий с id ${element.commentId} не найден")
    }

    override fun getComments(element: Comment): Boolean {
        println("Список комментариев к заметке с id ${element.noteId}")
        val noDell = commentList.filter { it.noteId == element.noteId && !it.delOrNot }
        println(noDell)
        return true
    }

    override fun restoreComment(element: Comment): Boolean {
        commentList.forEach { i ->
            if (i.commentId != element.commentId || !i.delOrNot) return@forEach
            i.delOrNot = false
            return true
        }
        throw FileNotFoundException("Упс! Коментарий с id ${element.commentId} не найден, или не удален")
    }
}