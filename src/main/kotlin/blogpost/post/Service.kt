package blogpost.post

import blogpost.post.dto.PostRequest
import blogpost.post.dto.PostResponse
import org.springframework.stereotype.Service
import jakarta.persistence.EntityNotFoundException
import org.springframework.context.annotation.ComponentScan
import java.time.LocalDateTime
import java.util.*
import kotlin.time.toDuration

@Service
@ComponentScan(basePackages = ["blogpost.post.dto"])
class PostService(private val postRepository: PostRepository) {

    fun getAllPosts(): List<PostResponse> =
        postRepository.findAll().map { it.toResponse() }

    fun getPostById(id: UUID): PostResponse =
        postRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Post not found with id: $id") }
            .toResponse()

    fun createPost(postRequest: PostRequest): PostResponse =
        postRequest.toEntity().let {
            postRepository.save(it)
        }.toResponse()

    fun updatePost(id: UUID, postRequest: PostRequest): PostResponse =
        postRepository.findById(id).map { existingPost ->
            existingPost.title = postRequest.title
            existingPost.content = postRequest.content
            existingPost.updatedAt = LocalDateTime.now()
            postRepository.save(existingPost).toResponse()
        }.orElseThrow { EntityNotFoundException("Post not found with id: $id") }

    fun deletePost(id: UUID) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id)
        } else {
            throw EntityNotFoundException("Post not found with id: $id")
        }
    }

    private fun Post.toResponse() = PostResponse(
        id = id!!,
        title = title,
        content = content,
        author = author,
        createdAt = createdAt!!,
        updatedAt = updatedAt!!
    )

    private fun PostRequest.toEntity() = Post(
        title = title,
        content = content,
        author = author
    )
}