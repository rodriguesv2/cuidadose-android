package br.com.rodroid.cuidadose.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.rodroid.cuidadose.presentation.ui.theme.CuidaDoseTheme

@Composable
fun SelectableCountChip(
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean,
    count: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(
                onClick = onClick
            )
            .background(
                shape = RoundedCornerShape(32.dp),
                color = when (isSelected) {
                    true -> MaterialTheme.colorScheme.primary
                    false -> MaterialTheme.colorScheme.secondary
                }
            )
            .height(48.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                color = when (isSelected) {
                    true -> MaterialTheme.colorScheme.onPrimary
                    false -> MaterialTheme.colorScheme.onSecondary
                }
            )
            Spacer(Modifier.padding(horizontal = 8.dp))
            CountDot(
                count,
                isSelected
            )
        }
    }
}

@Composable
private fun CountDot(
    count: String,
    isSelected: Boolean
) {
    Box(

    ) {
        Text(
            text = count,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = when (isSelected) {
                true -> MaterialTheme.colorScheme.onPrimary
                false -> MaterialTheme.colorScheme.onSecondary
            }
        )
    }
}

@Preview
@Composable
private fun PreviewTrue() {
    Preview(isSelected = true)
}

@Preview
@Composable
private fun PreviewFalse() {
    Preview(isSelected = false)
}

@Composable
private fun Preview(isSelected: Boolean) {
    CuidaDoseTheme(darkTheme = false) {
        SelectableCountChip(
            text = "Teste",
            onClick = {},
            count = "4",
            isSelected = isSelected
        )
    }
}