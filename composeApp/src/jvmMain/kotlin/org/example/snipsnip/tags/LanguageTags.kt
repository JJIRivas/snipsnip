package org.example.snipsnip.tags

import androidx.compose.ui.graphics.Color
import org.dizitart.kno2.documentOf
import org.dizitart.kno2.filters.eq
import org.dizitart.no2.collection.Document
import org.dizitart.no2.collection.NitriteCollection
import org.dizitart.no2.collection.NitriteId
import org.dizitart.no2.exceptions.NotIdentifiableException
import org.dizitart.no2.exceptions.ValidationException
import org.example.snipsnip.definitions.languageTagData
import org.example.snipsnip.definitions.tagsDbOperations

class LanguageTags(private val collection: NitriteCollection): tagsDbOperations<languageTagData> {

    override fun createDefault(): List<languageTagData> {
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
            val id = NitriteId.newId()
            collection.insert(documentOf("_id" to id, "name" to i, "color" to Color.Cyan.toString()))
        }

        return collection.find().mapNotNull { docToTag(it) }
    }

    override fun docToTag(docToParse: Document?): languageTagData? {
        return docToParse?.let { doc ->
            val idFound = doc["_id"] as? NitriteId ?: return null
            val name = doc["name"] as? String ?: return null
            val color = doc["color"] as? String ?: return null

            languageTagData(idFound, name, color)
        }
    }

    override fun tagToDoc(tag: languageTagData?): Document? =
        tag?.let{documentOf("_id" to tag.id,"name" to tag.name, "color" to tag.color)}

    override fun insert(item: languageTagData): Boolean {
        val doc = collection.find("name" eq item.name).firstOrNull()
        return when{ doc == null->{
            collection.insert(tagToDoc(item))
            true
            }else -> false
        }
    }

    override fun delete(item: languageTagData): Boolean {
        val filter = ("name" eq item.name)
        return try {
            collection.remove(filter, true)
            true
        }catch(_: NotIdentifiableException){
            false
        }
    }

    override fun update(item: languageTagData): Boolean {
        return try{
            collection.update("_id" eq item.id, tagToDoc(item))
            true
        }catch(_: ValidationException){
            false
        }
    }

    override fun getAll(): List<languageTagData> =
        collection.find().mapNotNull {docToTag(it)}



    override fun getByName(name: String): languageTagData? =
        collection.find("name" eq name).firstOrNull().let{docToTag(it)}

}