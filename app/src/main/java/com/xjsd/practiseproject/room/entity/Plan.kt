package com.xjsd.practiseproject.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 计划表.
 */
@Entity
data class Plan(
    @PrimaryKey(autoGenerate = true)
    val planId: Long = 0,
    val planName: String,
    val planTime: Long
) {
    override fun toString(): String {
        return "Plan(planId=$planId, planName='$planName', planTime=$planTime)"
    }
}
