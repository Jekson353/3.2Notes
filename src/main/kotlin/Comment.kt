data class Comment(
    val commentId: Int? = null,
    val noteId: Int,
    val replyTo: Int,
    val message: String,
    val isDelete: Boolean = false
)