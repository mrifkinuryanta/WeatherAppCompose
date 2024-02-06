package com.mrndevs.weatherapp.ui.screen.test

import com.tagsamurai.tscomponents.model.Severity

data class AuditTaskEntity(
    val id: String,
    val auditId: String? = "",
    val categories: List<Category?>? = emptyList(),
    val durations: Int? = 0,
    val group: Group? = Group(),
    val isGroupManager: Boolean? = false,
    val isGroupMonitoring: Boolean? = false,
    val isGroupStaff: Boolean? = false,
    val progress: Progress? = Progress(),
    val startedAt: String? = "",
    val status: String? = "",
    val type: String? = ""
) {
    val statusSeverity: Severity
        get() = when (status) {
            "In Audit" -> Severity.WARNING
            "Grace Period" -> Severity.WARNING
            "Waiting for Approval" -> Severity.WARNING
            "On Schedule" -> Severity.PRIMARY
            "Overdue" -> Severity.DANGER
            else -> Severity.PRIMARY
        }

    data class Category(
        val id: String? = "",
        val name: String? = "",
        val key: Int? = 0
    )

    data class Group(
        val fullPath: String? = "",
        val id: String? = "",
        val key: Int? = 0,
        val name: String? = ""
    )

    data class Progress(
        val completed: Int? = 0,
        val total: Int? = 0
    )
}