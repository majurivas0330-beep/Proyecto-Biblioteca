package com.sena.proyecto_biblioteca.ui.theme.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BotonPersonalizado(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icono: ImageVector? = null,
    habilitado: Boolean = true,
    colorFondo: Color = MaterialTheme.colorScheme.primary,
    colorTexto: Color = MaterialTheme.colorScheme.onPrimary,
    altura: Dp = 52.dp
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(altura),
        enabled = habilitado,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorFondo,
            contentColor = colorTexto
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icono != null) {
                Icon(
                    imageVector = icono,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = texto,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
