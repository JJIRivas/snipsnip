package org.example.snipsnip.editors

import org.dizitart.kno2.documentOf
import org.dizitart.kno2.filters.eq
import org.dizitart.no2.collection.Document
import org.dizitart.no2.collection.NitriteCollection
import org.dizitart.no2.collection.NitriteId
import org.dizitart.no2.exceptions.NotIdentifiableException
import org.example.snipsnip.definitions.editorOperations
import org.example.snipsnip.definitions.normalTextData
import java.time.LocalDateTime

class normalTextEditor(private val collection: NitriteCollection): editorOperations<Document, Document, normalTextData> {

    // TODO("EDIT TO ACCEPT TAGS IN THE NORMAL EDITOR")
    override fun insertNew(tags: List<Document>?, lang: Document, num: Int): normalTextData? {
        val time = LocalDateTime.now().toString()
        val docId = NitriteId.newId()

        collection.insert(documentOf(
            "_id" to docId,
            "title" to "Untitled document: $num",
            "editorLinked" to documentOf(),
            "dateCreated" to time,
            "dateUpdated" to time,
            "writtenText" to " ",
            "font" to "arial",
            "size" to 12)
        )

        return docToData(collection.getById(docId))
    }

    override fun delete(doc: normalTextData): Boolean {
        try {
            collection.remove("_id" eq doc.id, true)
        }catch (e: NotIdentifiableException) {return false}

        return true
    }

    override fun update(doc: normalTextData): normalTextData? {
        val newDoc = dataToDoc(doc) ?: return null
        collection.update("_id" eq doc.id, newDoc)
        return docToData(collection.getById(doc.id))
    }

    override fun getAll(): List<normalTextData?> = collection.find().mapNotNull { docToData(it) }

    override fun getByName(name: String): normalTextData? = collection.find("title" eq name).firstOrNull()?.let { docToData(it) }

    fun docToData(doc: Document): normalTextData? {
        return try{
            normalTextData(doc.id,
                doc["title"] as String,
                doc["editorLinked"] as? Document,
                doc["dateCreated"] as String,
                doc["dateUpdated"] as String,
                doc["writtenText"] as String,
                doc["font"] as String,
                doc["size"] as Double)
        }catch(e: Exception){null}
    }

    fun dataToDoc(data: normalTextData): Document?{
        val currentTime = LocalDateTime.now().toString()
        return try{
            documentOf(
                "_id" to data.id,
                "title" to data.title,
                "editorLinked" to data.editorLinked,
                "dateCreated" to data.dateCreated,
                "dateUpdated" to currentTime,
                "writtenText" to data.writtenText,
                "font" to data.font,
                "size" to data.size)
        }catch(e: java.lang.Exception){null}
    }
}