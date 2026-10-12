package br.com.rodroid.cuidadose.presentation.components.design_system

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.rodroid.cuidadose.R
import br.com.rodroid.cuidadose.presentation.components.models.StyleType
import br.com.rodroid.cuidadose.presentation.ui.theme.CuidaDoseTheme

@Composable
fun DSButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    styleType: StyleType = StyleType.PRIMARY
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = when (styleType) {
            StyleType.PRIMARY -> ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            )

            StyleType.SECONDARY -> ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
            )
        },
        shape = RoundedCornerShape(10.dp)
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

@Preview
@Composable
private fun Preview() {
    CuidaDoseTheme {
        DSButton(
            text = "Teste",
            icon = painterResource(R.drawable.ic_check),
            onClick = {},
        )
    }
}