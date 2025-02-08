package blogpost.post

import blogpost.post.dto.PostRequest
import blogpost.post.dto.PostResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/posts")
class PostController(private val postService: PostService) {

    @GetMapping
    fun getAllPosts(): List<PostResponse> = postService.getAllPosts()

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: UUID): PostResponse =
        postService.getPostById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(@Valid @RequestBody postRequest: PostRequest): PostResponse =
        postService.createPost(postRequest)

    @PutMapping("/{id}")
    fun updatePost(
        @PathVariable id: UUID,
        @Valid @RequestBody postRequest: PostRequest
    ): PostResponse = postService.updatePost(id, postRequest)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(@PathVariable id: UUID) = postService.deletePost(id)
}