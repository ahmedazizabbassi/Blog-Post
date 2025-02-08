import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.ConstraintViolationException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleNotFound(ex: EntityNotFoundException): ResponseEntity<Any> =
        ResponseEntity(ex.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleValidationErrors(ex: ConstraintViolationException): ResponseEntity<Any> {
        val errors = ex.constraintViolations
            .associate { it.propertyPath.toString() to it.message }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}