package dev.mbo.springkotlinvalidation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * Validator that can be used to make sure that at least one collection of the given ones isn't empty.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [OneNotEmptyValidator::class])
annotation class OneNotEmpty(
    val message: String = "{all_lists_empty_message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val fields: Array<String>
)