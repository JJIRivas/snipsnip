package org.example.snipsnip.definitions

import org.dizitart.no2.collection.NitriteId


data class snippetData(
    override val id: NitriteId?,
    override val title: String,
    val language: NitriteId?,
    val userTags: List<NitriteId>?,
    override val dateCreated: String,
    override val dateUpdated: String,
    override val writtenText: String,
    override val editorLinked: NitriteId?
): editorEntity