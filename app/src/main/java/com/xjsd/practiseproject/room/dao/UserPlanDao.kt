package com.xjsd.practiseproject.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.xjsd.practiseproject.room.entity.UserPlan

/**
 * 用户和对应计划表数据库操作对象.
 */
@Dao
interface UserPlanDao {
    /**
     * 查询用户和用户计划数据.
     *
     * @return 用户和用户计划数据集合
     */
    @Transaction
    @Query("SELECT *FROM user")
    fun getAllUserPlan(): List<UserPlan>
}