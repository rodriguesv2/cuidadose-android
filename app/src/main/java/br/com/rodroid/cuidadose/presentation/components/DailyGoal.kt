package br.com.rodroid.cuidadose.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.rodroid.cuidadose.R
import br.com.rodroid.cuidadose.presentation.ui.theme.CuidaDoseTheme
import br.com.rodroid.cuidadose.presentation.ui.theme.DSColors

@Composable
fun DailyGoal(
    formattedDate: String,
    subtitle: String,
    takenDoses: Int,
    totalDoses: Int,
    remainingDoses: Int,
    percentage: Int,
    modifier: Modifier = Modifier,
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
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MainTexts(
                    formattedDate,
                    subtitle
                )
                PercentageCircle(percentage)
            }

            Spacer(modifier = Modifier.height(18.dp))
            GoalProgressBar(percentage / 100f)
            Spacer(modifier = Modifier.height(10.dp))
            FooterTexts(
                stringResource(
                    R.string.goal_taken,
                    takenDoses,
                    totalDoses,
                ),
                pluralStringResource(
                    R.plurals.goal_remaining,
                    remainingDoses,
                    remainingDoses
                )
            )
        }
    }
}

@Composable
private fun FooterTexts(
    resolvedTakenText: String,
    resolvedRemainingText: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = resolvedTakenText,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            color = DSColors.textSecondary
        )
        Text(
            text = resolvedRemainingText,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = DSColors.green
        )
    }
}

@Composable
private fun RowScope.MainTexts(
    formattedDate: String,
    subtitle: String
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .padding(end = 12.dp)
    ) {
        Text(
            text = formattedDate,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DSColors.textPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = subtitle,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = DSColors.textSecondary
        )
    }
}

@Composable
private fun GoalProgressBar(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(14.dp)
            .clip(CircleShape)
            .background(DSColors.progressTrack)
    ) {
        if (progress > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = progress)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(DSColors.green)
            )
        }
    }
}

@Composable
private fun PercentageCircle(percentage: Int) {
    Surface(
        modifier = Modifier.size(60.dp),
        shape = CircleShape,
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(
                    R.string.goal_percentage,
                    percentage,
                ),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = DSColors.green
            )
        }
    }
}

@Preview(
    name = "Daily Goal - 75%",
    showBackground = true
)
@Composable
private fun DailyGoalPreview() {
    CuidaDoseTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            DailyGoal(
                "15 de Outubro",
                "Meta de adesão quase concluída!",
                3,
                4,
                1,
                75
            )
        }
    }
}

@Preview(
    name = "Daily Goal - 100%",
    showBackground = true
)
@Composable
private fun DailyGoalCompletedPreview() {
    CuidaDoseTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            DailyGoal(
                "15 de Outubro",
                "Meta de adesão concluída!",
                4,
                4,
                0,
                100
            )
        }
    }
}
