package com.xjsd.lib.algorithm

/**
 * 三数之和问题.
 *
 * * 题目：输入一个数组，如何找出数组中所有和为0的3个数字的三元组?
 * 需要注意的是，返回值中不得包含重复的三元组。
 * 例如，在数组[-1，0，1，2，-1，-4]中有两个三元组的和为0，
 * 它们分别是[-1，0，1和[-1，-1，2]。
 */
class ThreeSums {

    /**
     * 三数之和问题.
     *
     * * 审题：
     * * 1、给定整数数组 nums；
     * * 2、找出所有和为 0 的不重复三元组；
     * * 3、三元组顺序无关，但不应包含重复组合。
     *
     * * 思路（排序 + 双指针法（时间复杂度 O(n²)））：
     * * 终止条件：当 i >= nums.size - 2，就不需要继续，因为至少要 3 个数才能构成三元组
     * * 1、 数组排序：先对数组进行升序排序，方便去重
     * * 2、 遍历数组：遍历数组中的每个元素nums[i]，将其作为三元组的第一个元素
     * * 3、 双指针法：在 i 之后，使用两个指针 left 和 right 分别指向i的右侧和数组末尾，从两端向中间靠拢，寻找剩下两个数使三数之和为 0
     * * 4、 去重：
     * * 4.1、 对于三元组第一个元素 nums[i]，如果它与前一个元素nums[i-1]相同，则跳过，避免重复组合
     * * 4.2、 对于 left 和 right 指针，当它们指向的元素与前一个元素相同时，跳过，避免重复组合
     * * 5、 移动指针：
     * * 5.1、 如果 nums[i] + nums[left] + nums[right] > 0，则将 right 指针左移，使三数之和减小
     * * 5.2、 如果 nums[i] + nums[left] + nums[right] < 0，则将 left 指针右移，使三数之和增大
     * * 5.3、 如果 nums[i] + nums[left] + nums[right] = 0，则找到了一个三元组，将其加入结果列表，并同时将 left 和 right 指针向中间移动，继续寻找其他可能的三元组
     * * 6、 重复步骤 2 到 5，直到遍历完整个数组
     * * 7、 返回结果：返回包含所有和为 0 的不重复三元组的列表
     *
     * @param nums 输入数组
     * @return 所有和为0的三元组
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        // 1. 数组长度不符合要求
        if (nums.size < 3) {
            return emptyList()
        }
        // 2. 创建结果集合
        val resultList = mutableListOf<List<Int>>()
        // 3. 排序
        val sortedList = nums.sorted()
        // 4. 遍历数组
        for (i in 0 until sortedList.size - 2) {
            // 4.1 去重
            if (i > 0 && sortedList[i] == sortedList[i - 1]) {
                continue
            }
            // 4.2 双指针法
            var left = i + 1
            var right = sortedList.size - 1
            while (left < right) {
                // 4.3 移动指针
                val sum = sortedList[i] + sortedList[left] + sortedList[right]
                when {
                    sum < 0 -> left++
                    sum > 0 -> right--
                    else -> {
                        resultList.add(listOf(sortedList[i], sortedList[left], sortedList[right]))
                        // 4.4 跳过重复的 left 和 right 值
                        while (left < right && sortedList[left] == sortedList[left + 1]) left++
                        while (left < right && sortedList[right] == sortedList[right - 1]) right--
                        left++
                        right--
                    }
                }
            }
        }
        return resultList
    }
}