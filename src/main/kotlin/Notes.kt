data class Notes (
        val noteId: Int? = null,
        val userId: Int,
        val tittle: String,
        val text: String,
        val date: String,
        val privacyView: Boolean = false,
        val privacyComment: Boolean = false,
        var isDelete: Boolean = false
)