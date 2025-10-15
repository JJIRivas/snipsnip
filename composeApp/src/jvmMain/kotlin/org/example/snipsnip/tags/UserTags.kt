package org.example.snipsnip.tags

import androidx.compose.ui.graphics.Color
import org.dizitart.kno2.documentOf
import org.dizitart.kno2.filters.eq
import org.dizitart.no2.collection.Document
import org.dizitart.no2.collection.NitriteCollection
import org.dizitart.no2.collection.NitriteId
import org.dizitart.no2.exceptions.NotIdentifiableException
import org.dizitart.no2.exceptions.ValidationException
import org.example.snipsnip.definitions.tagsDbOperations
import org.example.snipsnip.definitions.userTagData

class UserTags(private val collection: NitriteCollection): tagsDbOperations<userTagData> {

    override fun createDefault(): List<userTagData> {
        val defUserTags = arrayOf("favorite",
            "draft",
            "test",
            "setup",
            "logging",
            "error-handling",
            "parsing",
            "validation",
            "parsing",
            "api-call",
            "example",
            "template")

        for(i in defUserTags){
            val id = NitriteId.newId()
            collection.insert(documentOf("_id" to id, "name" to i, "color" to Color.Green.toString()))
        }

        return collection.find().mapNotNull {docToTag(it)}
    }

    override fun insert(item: userTagData): Boolean{
        val doc = collection.find("name" eq item.name).firstOrNull()
        return when { doc == null ->{
            collection.insert(tagToDoc(item))
            true
            }else -> false
        }
    }


    override fun delete(item: userTagData): Boolean {
        val filter = ("name" eq item.name)
        return try{
        collection.remove(filter, true)
            true
        }catch(_: NotIdentifiableException){
            false
        }
    }

    override fun update(item: userTagData): Boolean {
        return try{
            collection.update("_id" eq item.id, tagToDoc(item))
            true
        }catch(_: ValidationException){false}
    }

    override fun getAll(): List<userTagData> =
        collection.find().mapNotNull {docToTag(it)}


    override fun getByName(name: String): userTagData? =
        collection.find("name" eq name).firstOrNull().let{docToTag(it)}

    override fun docToTag(docToParse: Document?): userTagData? {
        return docToParse?.let{doc ->
            val idFound = doc["_id"] as? NitriteId ?: return null
            val name = doc["name"] as? String ?: return null
            val color = doc["color"] as? String ?: return null

            userTagData(idFound, name, color)
        }
    }

    override fun tagToDoc(tag: userTagData?): Document? =
        tag?.let{documentOf("_id" to tag.id, "name" to tag.name,"color" to tag.color) }
}