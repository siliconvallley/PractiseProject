package com.xjsd.practiseproject.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.xjsd.practiseproject.room.entity.User
import com.xjsd.practiseproject.room.entity.UserWork
import com.xjsd.practiseproject.room.entity.UserWorkList
import com.xjsd.practiseproject.room.entity.Work

/**
 * 用户和对应工作表数据库操作对象.
 */
@Dao
interface UserWorkDao {
    /**
     * 查询用户和用户工作数据.
     *
     * @return 用户和用户工作数据集合
     */
    @Transaction
    @Query("SELECT *FROM user")
    fun getAllUserWork(): List<UserWork>

    @Query("SELECT *FROM user JOIN work ON user.id = work.userId")
    fun getUserWithWorks(): Map<User, List<Work>>

    /**
     * 查询用户和用户工作数据.
     *
     * @return 用户和用户工作数据集合
     */
    @Transaction
    @Query("SELECT *FROM user")
    fun getUserWithWorkList(): List<UserWorkList>
}