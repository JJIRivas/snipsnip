package org.example.snipsnip.definitions

import androidx.compose.ui.text.font.Font
import org.dizitart.no2.collection.NitriteId

data class normalTextData(
    override val id: NitriteId?,
    override val title: String,
    override val editorLinked: NitriteId?,
    override val dateCreated: String,
    override val dateUpdated: String,
    override val writtenText: String,
    val font: Font,
    val size: Double
): editorEntity
