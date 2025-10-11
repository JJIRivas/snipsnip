package org.example.snipsnip.tags

import androidx.compose.ui.graphics.Color
import org.dizitart.kno2.documentOf
import org.dizitart.kno2.filters.eq
import org.dizitart.no2.collection.NitriteCollection
import org.dizitart.no2.collection.NitriteId
import org.example.snipsnip.definitions.languageTagData
import org.example.snipsnip.definitions.tagsDbOperations

class languageTags(private val collection: NitriteCollection): tagsDbOperations<languageTagData> {

    override fun createDefault(): languageTagData? {

        val defLangTags = arrayOf("C",
            "C++",
            "Python",
            "Java",
            "Rust",
            "HTML",
            "CSS",
            "JavaScript",
            "Go",
            "Kotlin",
            "ASSEMBLY")

        for(i in defLangTags){
            collection.insert(documentOf("name" to i, "color" to Color.Cyan.toString()))
        }
        return null
    }

    override fun insert(item: languageTagData) {
        val doc = collection.find("name" eq item.name).firstOrNull()
        if(doc == null) {
            collection.insert(documentOf("_id" to item.id, "name" to item.name, "color" to item.color))
        }
    }

    override fun delete(item: languageTagData) {
        val filter = ("name" eq item.name)
        collection.remove(filter, true)
    }

    override fun update(item: languageTagData) {
        collection.update("_id" eq item.id, documentOf("name" to item.name, "color" to item.color))
    }

    override fun getAll(): List<languageTagData> {
        return collection.find().mapNotNull { doc ->
            val id = doc.id as NitriteId
            val name = doc.get("name") as String
            val color = doc.get("color") as String

            languageTagData(id, name, color)
        }
    }

    override fun getById(id: NitriteId): languageTagData? {
        return collection.getById( id).let {doc ->
            val name = doc["name"] as String
            val color = doc["color"] as String

            languageTagData(id, name, color)
        }
    }

    override fun getByName(name: String): languageTagData? {
        return collection.find("name" eq name).firstOrNull().let {doc ->
            val id = doc["_id"] as NitriteId
            val color = doc["color"] as String

            languageTagData(id, name, color)
        }

    }
}