package dev.mbo.springkotlinvalidation

import jakarta.validation.Validation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class EmailsValidatorTest {

    private val validator = Validation.buildDefaultValidatorFactory().validator

    @ParameterizedTest(name = "{0} has {1} violation(s)")
    @MethodSource("validationProvider")
    fun isValid(mails: List<String>?, validationErrorCount: Int) {
        assertThat(validator.validate(Sample(mails))).hasSize(validationErrorCount)
    }

    data class Sample(@field:Emails val emails: List<String>?)

    companion object {
        @JvmStatic
        fun validationProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf("test@example.com"), 0),
                Arguments.of(emptyList<String>(), 0),
                Arguments.of(listOf("test@example.com", "test2@example.com"), 0),
                Arguments.of(listOf("foo"), 1),
                Arguments.of(listOf("foo@bar"), 0),
                Arguments.of(listOf("test@example.com", "foo"), 1),
            )
        }
    }

}