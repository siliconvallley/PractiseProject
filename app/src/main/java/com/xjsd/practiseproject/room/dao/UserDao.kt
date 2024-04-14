package com.xjsd.practiseproject.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.xjsd.practiseproject.room.entity.User
import com.xjsd.practiseproject.room.entity.UserTuple

/**
 * 用户表数据库操作对象.
 */
@Dao
interface UserDao {

    /**
     * 插入多个用户数据（碰到冲突就替换为最新的）.
     *
     * @param users 用户数据（可变对象）
     * @return 插入数据ID集合
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User): List<Long>

    /**
     * 插入用户集合数据.
     *
     * @param users 用户数据集合
     * @return 插入数据ID集合
     */
    @Insert
    fun insertAll(users: List<User>): List<Long>

    /**
     * 删除多个用户数据.
     *
     * @param users 用户数据（可变对象）
     */
    @Delete
    fun delete(vararg users: User)

    /**
     * 删除用户集合.
     *
     * @param users 用户集合
     */
    @Delete
    fun delete(users: List<User>)

    /**
     * 更新用户数据.
     *
     * @param users 用户数据（可变对象）
     * @return 更新数据ID集合
     */
    @Update
    fun update(vararg users: User)

    /**
     * 查询所有用户数据.
     *
     * @return 用户数据集合
     */
    @Query("SELECT *FROM user")
    fun getAll(): List<User>

    /**
     * 查询用户数据的列子集.
     *
     * @return 返回用户数据列子集
     */
    @Query("SELECT id, name FROM user")
    fun getSimpleAll(): List<UserTuple>

    /**
     * 通过用户ID查询用户数据.
     *
     * @param id 用户ID
     * @return 用户数据
     */
    @Query("SELECT *FROM user WHERE id = :id")
    fun getById(id: Int): User

    /**
     * 通过用户ID查询用户数据.
     *
     * @param userIds 用户ID集合
     * @return 用户数据集合
     */
    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun getAllByIds(userIds: IntArray): List<User>

    /**
     * 通过用户名查询用户数据（没啥意义，用户名可以重名，查询的是最近的用户）.
     *
     * @param name 用户名
     * @return 用户数据
     */
    @Query("SELECT *FROM user WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): User

    /**
     * 通过用户名查询用户数据集合.
     *
     * @param name 用户名
     * @return 用户数据集合
     */
    @Query("SELECT *FROM user WHERE name LIKE :name ORDER BY id DESC")
    fun findAllByName(name: String): List<User>

    /**
     * 查询某一年龄段用户数据.
     *
     * @param minAge 最小年龄
     * @param maxAge 最大年龄
     * @return 用户数据集合
     */
    @Query("SELECT *FROM user WHERE age BETWEEN :minAge AND :maxAge")
    fun getAllBetweenAges(minAge: Int, maxAge: Int): List<User>

}