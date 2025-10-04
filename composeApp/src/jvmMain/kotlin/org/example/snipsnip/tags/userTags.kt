package org.example.snipsnip.tags

import androidx.compose.ui.graphics.Color
import org.dizitart.kno2.documentOf
import org.dizitart.kno2.filters.eq
import org.dizitart.no2.collection.NitriteCollection
import org.dizitart.no2.collection.NitriteId
import org.example.snipsnip.definitions.dbOperations
import org.example.snipsnip.definitions.languageTagData
import org.example.snipsnip.definitions.userTagData
import java.sql.Connection
import java.sql.Statement
import kotlin.random.Random

class userTags(private val collection: NitriteCollection): dbOperations<userTagData> {

    override fun createDefault(){
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

    override fun insert(item: userTagData){
        val doc = collection.find("name" eq item.name).firstOrNull()
        if(doc == null) {
            collection.insert(documentOf( "name" to item.name, "color" to item.color))
        }
    }

    override fun delete(item: userTagData){
        collection.remove("name" eq item.name, true)
    }

    override fun update(item: userTagData) {
        collection.update("_id" eq item.id, documentOf("name" to item.name, "color" to item.color))
    }

    override fun getAll(): List<userTagData>{
        return collection.find().mapNotNull { doc ->
            val id = doc.get("_id") as NitriteId
            val name = doc.get("name") as? String
            val color = doc.get("color") as? Color

            if (name != null && color != null) {
                userTagData(
                    id = id,
                    name = name,
                    color = color
                )
            } else null
        }
    }

    override fun getById(id: NitriteId): userTagData?{
        return collection.getById( id).let {doc ->
            val name = doc["name"] as String
            val color = doc["color"] as Color
            userTagData(id, name, color)
        }
    }

    override fun getByName(name: String): userTagData?{
        return collection.find("name" eq name).firstOrNull().let {doc ->
            val id = doc["_id"] as NitriteId
            val color = doc["color"] as Color
            userTagData(id, name, color)
        }
    }
}