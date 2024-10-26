package dev.mbo.springkotlinvalidation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator

class EmailsValidator : ConstraintValidator<Emails, List<String>> {
    companion object {
        private val emailValidator = EmailValidator()
    }

    override fun isValid(emailList: List<String>?, context: ConstraintValidatorContext?): Boolean {
        if (emailList == null) {
            return true // Null lists are considered valid
        }

        for (email in emailList) {
            if (!emailValidator.isValid(email, context)) {
                return false // If any email in the list is invalid, return false
            }
        }

        return true // All emails in the list are valid
    }
}