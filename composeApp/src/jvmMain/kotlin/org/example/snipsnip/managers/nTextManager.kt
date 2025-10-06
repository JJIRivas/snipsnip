package org.example.snipsnip.managers

import org.dizitart.no2.collection.NitriteCollection
import org.dizitart.no2.collection.NitriteId
import org.example.snipsnip.definitions.tagsDbOperations
import org.example.snipsnip.definitions.normalTextData

class nTextManager(private val collection: NitriteCollection): tagsDbOperations<normalTextData> {
    override fun createDefault(): normalTextData? {
        TODO("Not yet implemented")
    }
//    override fun createDefault() {
//        TODO("Not yet implemented")
//    }

    override fun insert(item: normalTextData) {
        TODO("Not yet implemented")
    }

    override fun delete(item: normalTextData) {
        TODO("Not yet implemented")
    }

    override fun update(item: normalTextData) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<normalTextData> {
        TODO("Not yet implemented")
    }

    override fun getById(id: NitriteId): normalTextData? {
        TODO("Not yet implemented")
    }

    override fun getByName(name: String): normalTextData? {
        TODO("Not yet implemented")
    }

}