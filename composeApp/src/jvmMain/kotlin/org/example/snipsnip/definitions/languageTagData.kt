package org.example.snipsnip.definitions

import androidx.compose.ui.graphics.Color
import org.dizitart.no2.collection.NitriteId

data class languageTagData(
    override val id: NitriteId,
    override val name: String,
    override val color: String
): tagEntity
