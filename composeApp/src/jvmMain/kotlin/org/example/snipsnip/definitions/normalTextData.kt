package org.example.snipsnip.definitions

import org.dizitart.no2.collection.Document
import org.dizitart.no2.collection.NitriteId

data class normalTextData(
    override val id: NitriteId?,
    override val title: String,
    override val editorLinked: Document?,
    override val dateCreated: String,
    override val dateUpdated: String,
    override val writtenText: String,
    val font: String,
    val size: Double
): editorEntity
