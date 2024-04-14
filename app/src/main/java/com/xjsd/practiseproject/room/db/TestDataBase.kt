package com.xjsd.practiseproject.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xjsd.practiseproject.room.dao.PlanDao
import com.xjsd.practiseproject.room.dao.UserDao
import com.xjsd.practiseproject.room.dao.UserPlanDao
import com.xjsd.practiseproject.room.dao.UserWorkDao
import com.xjsd.practiseproject.room.dao.WorkDao
import com.xjsd.practiseproject.room.entity.Plan
import com.xjsd.practiseproject.room.entity.User
import com.xjsd.practiseproject.room.entity.Work

@Database(entities = [User::class, Work::class, Plan::class], version = 1)
abstract class TestDataBase : RoomDatabase() {
    /**
     * 用户表操作类.
     */
    abstract fun userDao(): UserDao

    /**
     * 工作表操作类.
     */
    abstract fun workDao(): WorkDao

    /**
     * 计划表操作类.
     */
    abstract fun planDao(): PlanDao

    /**
     * 用户表和工作表操作类.
     */
    abstract fun userWorkDao(): UserWorkDao

    /**
     * 用户表和计划表操作类.
     */
    abstract fun userPlanDao(): UserPlanDao
}