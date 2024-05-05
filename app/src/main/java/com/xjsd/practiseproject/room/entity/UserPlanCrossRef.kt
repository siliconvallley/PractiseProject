package com.xjsd.practiseproject.room.entity

import androidx.room.Entity

/**
 * 用户和对应计划数据.
 *
 * 多对多的关系的交叉引用表
 */
@Entity(primaryKeys = ["id", "planId"])
data class UserPlanCrossRef(
    val id: Long,
    val planId: Long
)
