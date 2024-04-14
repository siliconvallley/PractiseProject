package com.xjsd.practiseproject.room.annotation

/**
 * 性别.
 */
@Target(
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FIELD
)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class GenderType {
    companion object {
        /**
         * 男性.
         */
        const val MALE = 0

        /**
         * 女性.
         */
        const val FEMALE = 1
    }
}
