package org.example.snipsnip.definitions

import androidx.compose.ui.graphics.Color
import org.dizitart.no2.collection.NitriteId

data class userTagData(
    override val id: NitriteId?,
    override val name: String,
    override val color: Color
): tagEntity
