package blogpost.post.dto

import java.time.LocalDateTime
import java.util.*

data class PostResponse(
    val id: UUID,
    val title: String,
    val content: String,
    val author: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)