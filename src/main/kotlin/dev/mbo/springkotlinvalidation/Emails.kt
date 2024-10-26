package dev.mbo.springkotlinvalidation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * Validator that can be used for list of email addresses. Uses the @Email validator for every entry in the list.
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [EmailsValidator::class])
annotation class Emails(
    val message: String = "{invalid_email_list_message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)