package com.mrndevs.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mrndevs.weatherapp.ui.screen.test.AuditTaskEntity
import com.mrndevs.weatherapp.ui.screen.test.TaskScreen
import com.tagsamurai.tscomponents.theme.TagSamuraiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TagSamuraiTheme {
                TaskScreen(tasks = getDummyListTask()) {}
            }
        }
    }
}

fun getDummyListTask(): List<AuditTaskEntity> {
    return listOf(
        AuditTaskEntity(
            id = "1",
            auditId = "AUD-230218-0001",
            categories = emptyList(),
            durations = 0,
            group = AuditTaskEntity.Group(name = "Group 1"),
            isGroupManager = false,
            isGroupMonitoring = false,
            isGroupStaff = false,
            progress = AuditTaskEntity.Progress(),
            startedAt = "",
            status = "In Audit",
            type = "Condition"
        ),
        AuditTaskEntity(
            id = "2",
            auditId = "AUD-230218-0002",
            categories = emptyList(),
            durations = 0,
            group = AuditTaskEntity.Group(name = "Group 2"),
            isGroupManager = false,
            isGroupMonitoring = false,
            isGroupStaff = false,
            progress = AuditTaskEntity.Progress(),
            startedAt = "",
            status = "In Audit",
            type = "Condition"
        ),
        AuditTaskEntity(
            id = "3",
            auditId = "AUD-230218-0003",
            categories = emptyList(),
            durations = 0,
            group = AuditTaskEntity.Group(name = "Group 3"),
            isGroupManager = false,
            isGroupMonitoring = false,
            isGroupStaff = false,
            progress = AuditTaskEntity.Progress(),
            startedAt = "",
            status = "In Audit",
            type = "Condition"
        ),
        AuditTaskEntity(
            id = "4",
            auditId = "AUD-230218-0004",
            categories = emptyList(),
            durations = 0,
            group = AuditTaskEntity.Group(name = "Group 4"),
            isGroupManager = false,
            isGroupMonitoring = false,
            isGroupStaff = false,
            progress = AuditTaskEntity.Progress(),
            startedAt = "",
            status = "In Audit",
            type = "Condition"
        ),
        AuditTaskEntity(
            id = "5",
            auditId = "AUD-230218-0005",
            categories = emptyList(),
            durations = 0,
            group = AuditTaskEntity.Group(name = "Group 5"),
            isGroupManager = false,
            isGroupMonitoring = false,
            isGroupStaff = false,
            progress = AuditTaskEntity.Progress(),
            startedAt = "",
            status = "In Audit",
            type = "Condition"
        )
    )
}