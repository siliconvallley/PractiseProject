package com.xjsd.practiseproject.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xjsd.practiseproject.room.annotation.GenderType

/**
 * 用户表对象.
 *
 * @param id
 * 如果您在定义实体类时将一个字段标记为 @PrimaryKey(autoGenerate = true)，
 * 并且在插入记录时不为该字段赋值，Room 将会自动生成一个唯一的值并填充到该字段中。
 */
@Entity
class User(
    /**
     * 主键字段不能为空，并且必须具有唯一性，以确保每条记录都可以唯一标识.
     *
     * autoGenerate = true之后我们将主键默认设置为空或者0，
     * room也会自动自动生成一个唯一的值并填充到该字段中，
     * 但是不建议设置为空，建议默认设置为0。
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val age: Int = 0,
    @GenderType
    val gender: Int = GenderType.MALE
) {
    override fun toString(): String {
        return "User(id=$id, name='$name', age=$age, gender=$gender)"
    }
}