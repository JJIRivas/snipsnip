package org.example.snipsnip.definitions

import org.dizitart.no2.collection.Document
import org.dizitart.no2.collection.NitriteId

interface tagsDbOperations<T> {
    fun createDefault(): List<T>
    fun insert(item: T): Boolean
    fun delete(item: T): Boolean
    fun update(item: T): Boolean
    fun getAll(): List<T>
    fun getByName(name: String): T?
    fun docToTag(docToParse: Document?): T?
    fun tagToDoc(tag: T?): Document?
}