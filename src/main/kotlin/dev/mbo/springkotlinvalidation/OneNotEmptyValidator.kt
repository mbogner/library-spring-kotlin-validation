package dev.mbo.springkotlinvalidation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

class OneNotEmptyValidator : ConstraintValidator<OneNotEmpty, Any> {
    private lateinit var fields: Array<String>

    override fun initialize(constraintAnnotation: OneNotEmpty) {
        fields = constraintAnnotation.fields
    }

    override fun isValid(
        value: Any, // object to validate must not be null
        context: ConstraintValidatorContext
    ): Boolean {
        val members = value::class.memberProperties
        for (fieldName in fields) {
            val property = members.find { it.name == fieldName } ?: continue
            property.isAccessible = true
            val fieldValue = property.call(value)
            if (fieldValue is Collection<*> && fieldValue.isNotEmpty()) {
                return true
            }
        }
        return false // if none returned all are empty
    }

}