package ru.netology

import org.junit.Test

import org.junit.Assert.*
import ru.netology.data.Comment
import ru.netology.data.Notes
import ru.netology.exception.FileNotFoundException

class NoteServiceTest {

    @Test
    fun addTest() {
        val service = NoteService()
        val expectedId = 1
        val actualId = service.add(Notes())
        assertEquals(expectedId, actualId)
    }

    @Test
    fun deleteTrue() {
        val service = NoteService()

        service.add(
            Notes(nid = 1)
        )
        service.add(
            Notes(nid = 2)
        )
        val dell = Notes(nid = 2)
        val result = service.delete(dell)
        assertTrue(result)
    }

    @Test(expected = FileNotFoundException::class)
    fun deleteFalse_shouldThrow() {
        val service = NoteService()
        service.add(Notes(nid = 1))
        service.delete(Notes(nid = 2))
    }

    @Test
    fun editTrue() {
        val service = NoteService()

        service.add(
            Notes(nid = 1)
        )
        service.add(
            Notes(nid = 2)
        )
        val edit = Notes(nid = 2)
        val result = service.delete(edit)
        assertTrue(result)
    }

    @Test(expected = FileNotFoundException::class)
    fun editFalse_shouldThrow() {
        val service = NoteService()
        service.add(Notes(nid = 1))
        service.edit(Notes(nid = 2))
    }

    @Test
    fun getByIdAdd() {
        val service = NoteService()

        service.add(
            Notes(nid = 1)
        )
        service.add(
            Notes(nid = 2)
        )
        val edit = Notes(nid = 1)
        val result = service.getById(edit)
        assertNotNull(result)
    }

    @Test(expected = FileNotFoundException::class)
    fun getByIdAdd_shouldThrow() {
        val service = NoteService()
        service.add(Notes(nid = 1))
        service.getById(Notes(nid = 2))
    }

        @Test
    fun get() {
        val service = NoteService()

        service.add(
            Notes(nid = 1)
        )
        service.add(
            Notes(nid = 2)
        )
        val edit = Notes(nid = 3)
        val result = service.get()
        assertTrue(result)
    }
    @Test
    fun createComment_true() {
        val service = NoteService()
        service.add(Notes())
        val expectedId = 1
        val actualId = service.createComment(Comment(noteId = 1))
        assertEquals(expectedId, actualId)
    }

    @Test(expected = FileNotFoundException::class)
    fun createCommentFalse_shouldThrow() {
        val service = NoteService()
        service.createComment(Comment())
    }

    @Test
    fun deleteCommentTrue() {
        val service = NoteService()
        service.add(Notes(nid = 1))
        service.createComment(Comment(noteId = 1))
        val dell = Comment(commentId = 1)
        val result = service.deleteComment(dell)
        assertTrue(result)
    }

    @Test(expected = FileNotFoundException::class)
    fun deleteCommentFalse_shouldThrow() {
        val service = NoteService()
        service.deleteComment(Comment())
    }

    @Test
    fun editCommentTrue() {
        val service = NoteService()
        service.add(Notes())
        service.createComment(Comment(noteId = 1))
        val result = service.editComment(Comment(commentId = 1))
        assertTrue(result)
    }

    @Test(expected = FileNotFoundException::class)
    fun editCommentFalse_shouldThrow() {
        val service = NoteService()
        service.editComment(Comment())
    }
    @Test
    fun getCommentsTrue() {
        val service = NoteService()
        val result = service.getComments(Comment())
        assertTrue(result)
    }
    @Test
    fun restoreCommentTrue() {
        val service = NoteService()
        service.add(Notes())
        service.createComment(Comment(noteId = 1, delOrNot = true))
        val result = service.restoreComment(Comment(commentId = 1))
        assertTrue(result)
    }
    @Test(expected = FileNotFoundException::class)
    fun restoreCommentFalse_shouldThrow() {
        val service = NoteService()
        service.restoreComment(Comment())
    }
}

