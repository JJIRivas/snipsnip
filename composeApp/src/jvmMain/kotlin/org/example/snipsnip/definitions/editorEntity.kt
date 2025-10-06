package org.example.snipsnip.definitions

import org.dizitart.no2.collection.Document
import org.dizitart.no2.collection.NitriteId

interface editorEntity {
    val id: NitriteId?
    val title: String
    val editorLinked: Document?
    val dateCreated: String
    val dateUpdated: String
    val writtenText: String
}