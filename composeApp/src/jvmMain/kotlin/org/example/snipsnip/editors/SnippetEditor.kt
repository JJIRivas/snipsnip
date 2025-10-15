package org.example.snipsnip.editors

import org.dizitart.kno2.documentOf
import org.dizitart.kno2.filters.eq
import org.dizitart.no2.collection.Document
import org.dizitart.no2.collection.NitriteCollection
import org.dizitart.no2.collection.NitriteId
import org.dizitart.no2.exceptions.NotIdentifiableException
import org.example.snipsnip.definitions.editorOperations
import org.example.snipsnip.definitions.snippetData
import java.time.LocalDateTime

class SnippetEditor (private val snippetCollection: NitriteCollection): editorOperations<Document, Document, snippetData> {

    override fun insertNew(tags: List<Document>?, lang: Document, num: Int): snippetData? {
        val time = LocalDateTime.now().toString()
        val docId = NitriteId.newId()

        snippetCollection.insert(documentOf(
            "_id" to docId,
            "title" to "Untitled snippet: $num",
            "language" to lang,
            "UserTags" to tags,
            "dateCreated" to time,
            "dateUpdated" to time,
            "writtenText" to " ",
            "editorLinked" to documentOf())
        )

        return docToData(snippetCollection.getById(docId))
    }


    override fun delete(doc: snippetData): Boolean {
        try {
            snippetCollection.remove("_id" eq doc.id, true)
        }catch (e: NotIdentifiableException) {return false}

        return true
    }

    override fun update(doc: snippetData): snippetData? {
        val newDoc = dataToDoc(doc) ?: return null
        snippetCollection.update("_id" eq doc.id, newDoc)
        return docToData(snippetCollection.getById(doc.id))
    }

    override fun getAll(): List<snippetData> = snippetCollection.find().mapNotNull { docToData(it) }



    override fun getByName(name: String): snippetData? = snippetCollection.find("title" eq name).firstOrNull()?.let { docToData(it) }



    fun docToData(doc: Document): snippetData? {

        return try{
            snippetData(doc.id,
                doc["title"] as String,
                doc["language"] as Document,
                doc["UserTags"] as? List<Document>,
                doc["dateCreated"] as String,
                doc["dateUpdated"] as String,
                doc["writtenText"] as String,
                doc["editorLinked"] as? Document)
        }catch(e: Exception){null}
    }

    fun dataToDoc(data: snippetData): Document?{
        val currentTime = LocalDateTime.now().toString()
        return try{
            documentOf(
                "_id" to data.id,
                "title" to data.title,
                "language" to data.language,
                "UserTags" to data.userTags,
                "dateCreated" to data.dateCreated,
                "dateUpdated" to currentTime,
                "writtenText" to data.writtenText,
                "editorLinked" to data.editorLinked)
        }catch(e: java.lang.Exception){null}
    }


}