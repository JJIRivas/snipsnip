package org.example.snipsnip.tags

import androidx.compose.ui.graphics.Color
import org.dizitart.kno2.documentOf
import org.dizitart.kno2.filters.eq
import org.dizitart.no2.collection.Document
import org.dizitart.no2.collection.NitriteCollection
import org.dizitart.no2.collection.NitriteId
import org.example.snipsnip.definitions.dbOperations
import org.example.snipsnip.definitions.languageTagData
import kotlin.random.Random

class languageTags(private val collection: NitriteCollection): dbOperations<languageTagData> {

    override fun createDefault(){

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
            collection.insert(
                documentOf(
                    "name" to i,
                    "color" to Color(
                        Random.nextInt()
                    )
                )
            )
        }
    }

    override fun insert(item: languageTagData) {
        val doc = collection.find("name" eq item.name)
        if(doc == null || doc.isEmpty) {
            collection.insert(documentOf("_id" to item.id, "name" to item.name, "color" to item.color))
        }
    }

    override fun delete(item: languageTagData) {
        val filter = ("name" eq item.name)
        collection.remove(filter, true)
    }

    override fun update(item: languageTagData) {}

    override fun getAll(): ArrayList<Document> {
        val docs = collection.find()
        val info = ArrayList<Document>()

        for (document in docs){
            info.add(document)
        }

        return info
    }

    override fun getById(id: NitriteId): Document? {
        TODO("Not yet implemented")
    }

    override fun getByName(name: String): Document? {
        TODO("Not yet implemented")
    }
}