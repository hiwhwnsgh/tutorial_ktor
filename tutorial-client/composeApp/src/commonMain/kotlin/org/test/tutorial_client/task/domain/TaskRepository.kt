package org.test.tutorial_client.task.domain

import org.test.tutorial_client.task.data.Priority
import org.test.tutorial_client.task.data.Task

interface TaskRepository {
    fun allTasks(): List<Task>
    fun tasksByPriority(priority: Priority): List<Task>
    fun taskByName(name: String): Task?
    fun addOrUpdateTask(task: Task)
    fun removeTask(name: String): Boolean
}