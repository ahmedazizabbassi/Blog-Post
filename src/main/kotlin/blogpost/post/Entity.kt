package blogpost.post

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class)
data class Post(
    @Id
    @GeneratedValue(generator = "UUID")
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    var title: String = "",

    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String = "",

    @Column(nullable = false)
    var author: String = "",

    @CreatedDate
    @Column(updatable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
)
