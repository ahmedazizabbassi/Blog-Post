package blogpost.post.dto

data class PostRequest(
    val title: String,
    val content: String,
    val author: String
)