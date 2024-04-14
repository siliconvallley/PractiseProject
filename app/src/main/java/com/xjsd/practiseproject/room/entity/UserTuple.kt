package com.xjsd.practiseproject.room.entity

/**
 * 偶尔我们只是需要查询用户数据的列子集。
 * 借助 Room，可以从任何查询返回简单对象，前提是可以将一组结果列映射到返回的对象。
 */
data class UserTuple(
    val id: Long,
    val name: String
) {
    override fun toString(): String {
        return "UserTuple(id=$id, name='$name')"
    }
}
