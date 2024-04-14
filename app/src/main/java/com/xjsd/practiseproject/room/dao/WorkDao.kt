package com.xjsd.practiseproject.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xjsd.practiseproject.room.entity.Work

/**
 * 工作表数据库操作对象.
 */
@Dao
interface WorkDao {
    /**
     * 插入多个用户工作数据（碰到冲突就替换为最新的）.
     *
     * @param works 用户工作数据（可变对象）
     * @return 插入数据ID集合
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg works: Work): List<Long>

    /**
     * 删除多个用户工作数据.
     *
     * @param works 用户工作数据（可变对象）
     * @return 删除数据ID数组
     */
    @Delete
    fun delete(vararg works: Work)

    /**
     * （多表查询）根据用户名查询工作列表集合.
     *
     * @param name 用户名
     * @return 工作集合
     */
    @Query("SELECT *FROM work INNER JOIN user ON user.id = work.userId WHERE user.name LIKE :name")
    fun findByName(name: String): List<Work>

    /**
     * 根据用户ID查询用户工作.
     *
     * @param userId 用户ID
     * @return 用户工作
     */
    @Query("SELECT *FROM work WHERE userId = :userId")
    fun findByUserId(userId: Int): Work
}