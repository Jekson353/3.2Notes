import Notepad.createComment
import org.junit.Test

import org.junit.Assert.*

class NotepadTest {

    val service = Notepad

    @Test
    fun add() {
        val notes = Notes(
                noteId = 0,
                userId = 10,
                date = "1623344295690",
                tittle = "Задание на утро",
                text = "Вынести мусор, приготовить завтрак"
        )
        val needResult = Notes(
                noteId=0,
                userId = 10,
                date = "1623344295690",
                tittle = "Задание на утро",
                text = "Вынести мусор, приготовить завтрак"
        )
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        val result = service.add(notes)
        assertEquals(needResult, result)
    }

    @Test
    fun createComment() {
        val notes = Notes(
                noteId = 0,
                userId = 10,
                date = "1623344295690",
                tittle = "Задание на утро",
                text = "Вынести мусор, приготовить завтрак"
        )
        val comment = Comment(
                commentId = 0,
                noteId = 0,
                replyTo = 2,
                message = "Комментарий"
        )
        service.add(notes)
        val result = service.createComment(0, comment)
        assertEquals(comment, result)
    }

    @Test
    fun createCommentNext() {
        val notes = Notes(
                noteId = 0,
                userId = 10,
                date = "1623344295690",
                tittle = "Задание на утро",
                text = "Вынести мусор, приготовить завтрак"
        )
        val comment = Comment(
                commentId = 0,
                noteId = 0,
                replyTo = 2,
                message = "Комментарий"
        )
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        val result = service.createComment(0, comment)
        assertEquals(comment, result)
    }

    @Test(expected = Exception::class)
    fun createComment_noNotes() {
        val comment = Comment(
                noteId = 1,
                replyTo = 2,
                message = "Комментарий"
        )
        createComment(200, comment)
    }

    @Test
    fun delete() {
        val notes = Notes(
                noteId = 0,
                userId = 10,
                date = "1623344295690",
                tittle = "Задание на утро",
                text = "Вынести мусор, приготовить завтрак",
        )
        service.add(notes)
        val result = service.delete(10)

        assertEquals(false, result)
    }

    @Test
    fun delete_2() {
        val notes = Notes(
                userId = 10,
                date = "1623344295690",
                tittle = "Задание на утро",
                text = "Вынести мусор, приготовить завтрак",
        )
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        val result = service.delete(3)

        assertEquals(true, result)
    }

    @Test
    fun delete_noNotes() {
        val result = service.delete(3)
        assertEquals(false, result)
    }

    @Test
    fun deleteComment() {
        val notes = Notes(
                userId = 10,
                date = "1623344295690",
                tittle = "Задание на утро",
                text = "Вынести мусор, приготовить завтрак"
        )
        val comment = Comment(
                noteId = 1,
                replyTo = 2,
                message = "Комментарий"
        )
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.createComment(2, comment)
        service.createComment(2, comment)
        service.createComment(2, comment)
        service.createComment(2, comment)
        service.createComment(2, comment)
        service.createComment(2, comment)
        val result = service.deleteComment(2)
        assertEquals(true, result)
    }

    @Test(expected = Exception::class)
    fun deleteComment_Exception() {
        service.deleteComment(500)
    }

    @Test
    fun edit() {
        val notes = Notes(
                userId = 10,
                date = "1623344295690",
                tittle = "Задание на утро",
                text = "Вынести мусор, приготовить завтрак"
        )
        val editedNotes = Notes(
                userId = 10,
                date = "1623344295690",
                tittle = "Задание на вечер",
                text = "Выучить уроки программирования"
        )
        val needResult = Notes(
                noteId = 1,
                userId = 10,
                date = "1623344295690",
                tittle = "Задание на вечер",
                text = "Выучить уроки программирования"
        )
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        val result = service.edit(1, editedNotes)
        assertEquals(needResult, result)
    }

    @Test
    fun editComment() {
        val notes = Notes(
                userId = 10,
                date = "1623344295690",
                tittle = "Задание на утро",
                text = "Вынести мусор, приготовить завтрак"
        )
        val comment = Comment(
                noteId = 1,
                replyTo = 2,
                message = "Комментарий"
        )
        val editedComment = Comment(
                commentId = 2,
                noteId = 2,
                replyTo = 2,
                message = "Комментарий отредактирован",
                isDelete=false
        )
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.createComment(2, comment)
        service.createComment(2, comment)
        service.createComment(2, comment)
        service.createComment(2, comment)
        service.createComment(2, comment)
        service.createComment(2, comment)
        val result = service.editComment(2, editedComment)
        assertEquals(editedComment, result)
    }

    @Test(expected = Exception::class)
    fun editComment_Exception() {
        val editedNotes = Notes(
                userId = 10,
                date = "1623344295690",
                tittle = "Задание на вечер",
                text = "Выучить уроки программирования"
        )
        service.edit(200, editedNotes)
    }

    @Test
    fun getNotes() {
    }

    @Test(expected = Exception::class)
    fun getNotes_NoFind() {
        service.getNotes(500)
    }

    @Test
    fun getById() {
        val notes = Notes(
                userId = 10,
                date = "1623344295690",
                tittle = "Задание на утро",
                text = "Вынести мусор, приготовить завтрак",
        )
        val needResult = Notes(noteId=3,
                userId=10,
                tittle="Задание на утро",
                text="Вынести мусор, приготовить завтрак",
                date="10.06.2021 23:58:15",
                privacyView=false,
                privacyComment=false,
                isDelete=false
        )
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        service.add(notes)
        val result = service.getById(3)

        assertEquals(needResult, result)
    }

    @Test(expected = Exception::class)
    fun getById_Exception_NoFindNotes() {
        service.getById(300)
    }

    @Test
    fun getComments() {
    }

    @Test(expected = Exception::class)
    fun restoreComment() {
        service.restoreComment(500)
    }

    @Test(expected = Exception::class)
    fun restoreComment_Exception() {
        service.restoreComment(500)
    }
}