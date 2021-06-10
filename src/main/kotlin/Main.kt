import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val calendar = Calendar.getInstance()
    val timestamp = calendar.timeInMillis

    val notes = Notes(
            userId = 10,
            date = timestamp.toString(),
            tittle = "Задание на утро",
            text = "Вынести мусор, приготовить завтрак"
    )

    val editedNotes = Notes(
            userId = 999,
            date = timestamp.toString(),
            tittle = "Задание на вечер",
            text = "Выучить уроки программирования"
    )

    val comment = Comment(
            noteId = 1,
            replyTo = 2,
            message = "Комментарий"
    )

    val editedComment = Comment(
            noteId = 0,
            replyTo = 2,
            message = "Комментарий отредактирован"
    )

    println("Добавление записки: " + Notepad.add(notes))
    println("Добавление записки: " + Notepad.add(notes))
    println("Добавление записки: " + Notepad.add(notes))
    println("Добавление записки: " + Notepad.add(notes.copy(userId = 14)))
    println("Добавление записки: " + Notepad.add(notes.copy(userId = 15)))
    println("Получение записки: " + Notepad.getById(3))
    println("Редактирование записки: " + Notepad.edit(3, editedNotes))
    println("Удаление записки: " + Notepad.delete(3))

    println("Добавление комментария: " + Notepad.createComment(0, comment))
    println("Добавление комментария: " + Notepad.createComment(0, comment))
    println("Добавление комментария: " + Notepad.createComment(0, comment))

    println("Редактирование комментария: " + Notepad.editComment(0, editedComment))
    println("Удаление комментария: " + Notepad.deleteComment(0))
    println("Восстановление комментария: " + Notepad.restoreComment(0))
    println("Удаление комментария: " + Notepad.deleteComment(0))
    println("Повторное удаление комментария: " + Notepad.deleteComment(0))

    //println(Notepad.getById(120))

}