package com.xjsd.practiseproject.room.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 * 用户表对应的计划列表.
 */
data class UserWithPlans(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "planId",
        associateBy = Junction(UserPlanCrossRef::class)
    )
    val plans: MutableList<Plan>
)
