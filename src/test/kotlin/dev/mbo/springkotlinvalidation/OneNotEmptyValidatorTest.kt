package dev.mbo.springkotlinvalidation

import jakarta.validation.Validation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class OneNotEmptyValidatorTest {

    private val validator = Validation.buildDefaultValidatorFactory().validator

    @ParameterizedTest
    @MethodSource("samples")
    fun isValid(sample: Sample, validationErrorCount: Int) {
        assertThat(validator.validate(sample).size).isEqualTo(validationErrorCount)
    }

    @Test
    fun validateNullObject() {
        val nullSample: Sample? = null
        assertThrows(IllegalArgumentException::class.java) {
            validator.validate(nullSample)
        }
    }

    @OneNotEmpty(fields = ["list1", "list2"])
    data class Sample(
        val list1: List<String>?,
        val list2: List<String>?,
    )

    companion object {
        @JvmStatic
        fun samples(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Sample(emptyList(), emptyList()), 1),

                Arguments.of(Sample(null, emptyList()), 1),
                Arguments.of(Sample(emptyList(), null), 1),
                Arguments.of(Sample(null, null), 1),

                Arguments.of(Sample(listOf("test"), emptyList()), 0),
                Arguments.of(Sample(emptyList(), listOf("test")), 0),
                Arguments.of(Sample(listOf("test"), listOf("test")), 0),
            )
        }
    }
}