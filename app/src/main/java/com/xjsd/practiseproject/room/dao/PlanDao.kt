package com.xjsd.practiseproject.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xjsd.practiseproject.room.entity.Plan
import com.xjsd.practiseproject.room.entity.Work

/**
 * 计划表数据库操作对象.
 */
@Dao
interface PlanDao {
    /**
     * 插入多个用户计划数据（碰到冲突就替换为最新的）.
     *
     * @param plans 用户计划数据（可变对象）
     * @return 插入数据ID集合
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg plans: Plan): List<Long>

    /**
     * 删除多个用户计划数据.
     *
     * @param works 用户计划数据（可变对象）
     */
    @Delete
    fun delete(vararg plans: Plan)

    /**
     * 根据用户ID查询用户计划.
     *
     * @param userId 用户ID
     * @return 用户工作
     */
    @Query("SELECT *FROM `Plan` WHERE userId = :userId")
    fun findByUserId(userId: Int): List<Plan>
}