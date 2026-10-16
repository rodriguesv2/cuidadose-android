package br.com.rodroid.cuidadose.presentation.components.models

data class CalendarDay(
    val weekday: String,
    val monthday: String,
    val isMedicationTaken: Boolean = false,
    val isSelected: Boolean = false,
) {
    val hasTakenMedications: Boolean get() = isMedicationTaken
    val medicationsTaken: Boolean get() = isMedicationTaken

    constructor(
        weekday: String,
        monthday: Int,
        isMedicationTaken: Boolean = false,
        isSelected: Boolean = false,
    ) : this(
        weekday = weekday,
        monthday = monthday.toString(),
        isMedicationTaken = isMedicationTaken,
        isSelected = isSelected,
    )
}

typealias DayItem = CalendarDay
