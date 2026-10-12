package br.com.rodroid.cuidadose.presentation.components.design_system

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.rodroid.cuidadose.R
import br.com.rodroid.cuidadose.presentation.components.models.StyleType
import br.com.rodroid.cuidadose.presentation.ui.theme.CuidaDoseTheme

@Composable
fun DSFab(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    styleType: StyleType = StyleType.PRIMARY
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        contentColor = when (styleType) {
            StyleType.PRIMARY -> MaterialTheme.colorScheme.onPrimary
            StyleType.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        },
        containerColor = when (styleType) {
            StyleType.PRIMARY -> MaterialTheme.colorScheme.primary
            StyleType.SECONDARY -> MaterialTheme.colorScheme.secondary
        },
        shape = RoundedCornerShape(32.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text)
        }
    }
}

@Preview
@Composable
private fun Preview() {

    CuidaDoseTheme {
        DSFab(
            text = "Teste",
            icon = painterResource(R.drawable.ic_check),
            onClick = {},
        )
    }
}