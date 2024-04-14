package com.xjsd.practiseproject

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.xjsd.practiseproject.ext.logI
import com.xjsd.practiseproject.room.annotation.GenderType
import com.xjsd.practiseproject.room.dao.UserDao
import com.xjsd.practiseproject.room.dao.UserWorkDao
import com.xjsd.practiseproject.room.dao.WorkDao
import com.xjsd.practiseproject.room.db.TestDataBase
import com.xjsd.practiseproject.room.entity.User
import com.xjsd.practiseproject.room.entity.Work
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomTest {
    companion object {
        private const val TAG = "RoomTest"
    }

    private lateinit var mDb: TestDataBase
    private lateinit var mUserDao: UserDao
    private lateinit var mWorkDao: WorkDao
    private lateinit var mUserWorkDao: UserWorkDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        mDb = Room.databaseBuilder(
            context,
            TestDataBase::class.java,
            "pp-test-database"
        ).build()
        mUserDao = mDb.userDao()
        mWorkDao = mDb.workDao()
        mUserWorkDao = mDb.userWorkDao()
    }

    @After
    fun closeDb() {
        mDb.close()
    }

    @Test
    fun testInsert() {
        mUserDao.insertAll(
            User(name = "刘亦菲", age = 20, gender = GenderType.FEMALE),
            User(name = "唐嫣", age = 30, gender = GenderType.FEMALE)
        ).run {
            assertEquals(2, this.size)
            mUserDao.getAll().run {
                for ((index, value ) in this.withIndex()) {
                    // 插入数据
                    mWorkDao.insertAll(Work(introduce = "打工人$index", userId = value.id))
                }
            }

            mUserWorkDao.getAllUserWork().run {
                for ((index, value) in this.withIndex()) {
                    "index=$index, value=$value".logI(TAG)
                }
            }
        }
    }

    @Test
    fun testQuery() {
        mUserDao.getAll().run {
            assertEquals(1, this.size)
        }
    }
}