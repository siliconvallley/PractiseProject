package com.xjsd.practiseproject.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.xjsd.practiseproject.room.entity.PlanWithUsers
import com.xjsd.practiseproject.room.entity.UserPlanCrossRef
import com.xjsd.practiseproject.room.entity.UserWithPlans

/**
 * 用户和对应计划表数据库操作对象.
 */
@Dao
interface UserPlanDao {

    /**
     * 查询用户和对应计划数据.
     *
     * @return 用户和对应计划数据
     */
    @Transaction
    @Query("SELECT *FROM user")
    fun getUserWithPlans(): List<UserWithPlans>

    /**
     * 查询计划和对应用户数据.
     *
     * @return 查询计划和对应用户数据
     */
    @Transaction
    @Query("SELECT *FROM `plan`")
    fun getPlanWithUsers(): List<PlanWithUsers>
}