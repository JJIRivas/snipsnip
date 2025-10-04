package org.example.snipsnip.definitions

import org.dizitart.no2.collection.Document
import org.dizitart.no2.collection.NitriteId

interface dbOperations<T> {
    fun createDefault()
    fun insert(item: T)
    fun delete(item: T)
    fun update(item: T)
    fun getAll(): List<T>
    fun getById(id: NitriteId): T?
    fun getByName(name: String): T?
}