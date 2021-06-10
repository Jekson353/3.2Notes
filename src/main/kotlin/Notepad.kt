import java.lang.RuntimeException
import java.text.SimpleDateFormat

object Notepad {
    private var comments = emptyArray<Comment>()
    private var notes = emptyArray<Notes>()

    private fun getIdNotes(): Int {
        return notes.lastIndex + 1
    }

    private fun getIdComments(): Int {
        return comments.lastIndex + 1
    }

    fun add(note: Notes): Notes {
        if (notes.isNotEmpty()) {
            for (i: Int in 0..notes.size) {
                val searchPost = notes[i]
                if (note.noteId == searchPost.noteId) {
                    break
                } else {
                    notes += note.copy(noteId = getIdNotes())
                    return notes.last()
                }
            }
            return notes.last()
        } else {
            notes += note.copy(noteId = getIdNotes())
            return notes.last()
        }
    }

    fun createComment(idNotes: Int, comment: Comment): Comment {
        getById(idNotes).let { notes ->
            if (comments.isNotEmpty()) {
                for (i: Int in 0..comments.size) {
                    val searchNotes = comments[i]
                    if (comment.noteId == searchNotes.noteId) {
                        break
                    } else {
                        comments += comment.copy(commentId = getIdComments(), noteId = idNotes)
                        return comments.last()
                    }
                }
                return comments.last()
            } else {
                notes?.noteId?.let {
                    comments += comment.copy(commentId = getIdComments(), noteId = idNotes)
                    return comments.last()
                }
            }
        }
        throw RuntimeException("Невозможно добавить коментарий")
    }

    fun delete(id: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (index == id) {
                val deleteNotes = note.copy(
                        isDelete = true
                )
                notes[index] = deleteNotes
                return true
            }
        }
        return false
    }

    fun deleteComment(id: Int): Boolean {
        getComments(id).let {
            for ((index, comment) in comments.filter { !it.isDelete }.withIndex()) {
                if (index == id) {
                    val deleteComment = comment.copy(
                            isDelete = true
                    )
                    comments[index] = deleteComment
                    return true
                }
            }
            return false
        }
    }

    fun edit(idEdit: Int, newNote: Notes): Notes {
        getById(idEdit).let { idNotes ->
            idNotes?.noteId?.let {id ->
                notes[idEdit] = newNote.copy(noteId = id, userId = idNotes.userId)
                return notes[id]
            }

        }
        throw RuntimeException("Невозможно отредактировать заметку. Возможно она была удалена")
    }

    fun editComment(idComment: Int, newComment: Comment?, restore: Boolean = false): Comment {
        var filter = true
        if (restore){
            filter = false
        }
        getComments(idComment, filter).let { idComment ->
            idComment?.commentId?.let { id ->
                if (restore){
                    comments[id] = comments[id].copy(isDelete = false)
                }else {
                    newComment?.let {
                        comments[id] = it.copy(commentId = id, noteId = idComment.noteId)
                    }
                }
                return comments[id]
            }
        }
        throw RuntimeException("Невозможно отредактировать комментарий. Возможно он был удален")
    }

    fun getNotes(idUser: Int): Notes? {
        for ((index, note) in notes.filter{ !it.isDelete }.withIndex()) {
            if (note.userId == idUser) {
                return notes[index]
            }
        }
        throw RuntimeException("Записки пользователя не найдены!")
    }

    fun getById(id: Int): Notes? {
        val formatForDateNow = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")

        for ((index, note) in notes.filter{ !it.isDelete }.withIndex()) {
            if (note.noteId == id) {
                return notes[index].copy(date = formatForDateNow.format(notes[index].date.toLong()))
            }
        }
        throw RuntimeException("Записка не найдена!")
    }

    fun getComments(idComment: Int, filterDeleteComment: Boolean = true): Comment? {
        val commentary: Iterable<IndexedValue<Comment>> = if (filterDeleteComment){
            comments.filter{ !it.isDelete }.withIndex()
        }else{
            comments.withIndex()
        }
        for ((index, comment) in commentary) {
            if (comment.commentId == idComment) {
                return comments[index]
            }
        }
        throw RuntimeException("Комментарий пользователя не найден!")
    }


    fun restoreComment(idCommentRestore: Int): Comment {
        return editComment(idCommentRestore, null, true)
    }
}