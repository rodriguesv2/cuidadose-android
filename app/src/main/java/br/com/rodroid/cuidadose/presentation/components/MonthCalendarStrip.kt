package br.com.rodroid.cuidadose.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.rodroid.cuidadose.R
import br.com.rodroid.cuidadose.presentation.components.models.CalendarDay
import br.com.rodroid.cuidadose.presentation.ui.theme.CuidaDoseTheme
import br.com.rodroid.cuidadose.presentation.ui.theme.DSColors

@Composable
fun MonthCalendarStrip(
    monthYear: String,
    days: List<CalendarDay>,
    onSeeMonthClick: () -> Unit,
    onDayClick: (CalendarDay) -> Unit,
    modifier: Modifier = Modifier,
    selectedDay: CalendarDay? = null,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            HeaderRow(
                monthYear = monthYear,
                onSeeMonthClick = onSeeMonthClick
            )
            Spacer(modifier = Modifier.height(18.dp))
            DaysRow(
                days = days,
                selectedDay = selectedDay,
                onDayClick = onDayClick
            )
        }
    }
}

@Composable
fun CalendarStrip(
    monthYear: String,
    days: List<CalendarDay>,
    onSeeMonthClick: () -> Unit,
    onDayClick: (CalendarDay) -> Unit,
    modifier: Modifier = Modifier,
    selectedDay: CalendarDay? = null,
) = MonthCalendarStrip(
    monthYear = monthYear,
    days = days,
    onSeeMonthClick = onSeeMonthClick,
    onDayClick = onDayClick,
    modifier = modifier,
    selectedDay = selectedDay,
)

@Composable
fun MonthCalendar(
    monthYear: String,
    days: List<CalendarDay>,
    onSeeMonthClick: () -> Unit,
    onDayClick: (CalendarDay) -> Unit,
    modifier: Modifier = Modifier,
    selectedDay: CalendarDay? = null,
) = MonthCalendarStrip(
    monthYear = monthYear,
    days = days,
    onSeeMonthClick = onSeeMonthClick,
    onDayClick = onDayClick,
    modifier = modifier,
    selectedDay = selectedDay,
)

@Composable
private fun HeaderRow(
    monthYear: String,
    onSeeMonthClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = monthYear,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DSColors.textPrimary
        )
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable(onClick = onSeeMonthClick)
                .padding(
                    horizontal = 4.dp,
                    vertical = 2.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.see_month),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = DSColors.green
            )
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                painter = painterResource(R.drawable.ic_calendar),
                contentDescription = null,
                tint = DSColors.green,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun DaysRow(
    days: List<CalendarDay>,
    selectedDay: CalendarDay?,
    onDayClick: (CalendarDay) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(
            items = days,
            key = { "${it.weekday}_${it.monthday}" }
        ) { day ->
            val isSelected = if (selectedDay != null) {
                day == selectedDay || day.monthday == selectedDay.monthday
            } else {
                day.isSelected
            }
            DayItem(
                day = day,
                isSelected = isSelected,
                onClick = { onDayClick(day) }
            )
        }
    }
}

@Composable
private fun DayItem(
    day: CalendarDay,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
            )
            .clickable(onClick = onClick)
            .padding(
                horizontal = 10.dp,
                vertical = 12.dp
            )
            .defaultMinSize(minWidth = 44.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = day.weekday,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Color.White else DSColors.textSecondary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = day.monthday,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.White else DSColors.textPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            DayDot(
                isMedicationTaken = day.isMedicationTaken,
                isSelected = isSelected
            )
        }
    }
}

@Composable
private fun DayDot(
    isMedicationTaken: Boolean,
    isSelected: Boolean
) {
    Box(
        modifier = Modifier
            .size(6.dp)
            .clip(CircleShape)
            .background(
                color = if (isMedicationTaken) {
                    DSColors.mintGreen
                } else {
                    if (isSelected) Color(0xFF3B4D7E) else DSColors.dotInactive
                }
            )
    )
}

@Preview(
    name = "Month Calendar Strip - Default",
    showBackground = true
)
@Composable
private fun MonthCalendarStripPreview() {
    val sampleDays = listOf(
        CalendarDay(
            weekday = "Seg",
            monthday = "13",
            isMedicationTaken = true
        ),
        CalendarDay(
            weekday = "Ter",
            monthday = "14",
            isMedicationTaken = true
        ),
        CalendarDay(
            weekday = "Qua",
            monthday = "15",
            isMedicationTaken = true,
            isSelected = true
        ),
        CalendarDay(
            weekday = "Qui",
            monthday = "16",
            isMedicationTaken = false
        ),
        CalendarDay(
            weekday = "Sex",
            monthday = "17",
            isMedicationTaken = false
        ),
        CalendarDay(
            weekday = "Sáb",
            monthday = "18",
            isMedicationTaken = false
        ),
        CalendarDay(
            weekday = "Dom",
            monthday = "19",
            isMedicationTaken = false
        ),
    )

    CuidaDoseTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            MonthCalendarStrip(
                monthYear = "Outubro 2024",
                days = sampleDays,
                onSeeMonthClick = {},
                onDayClick = {}
            )
        }
    }
}
