package org.example.snipsnip.managers

import org.dizitart.no2.Nitrite
import org.dizitart.no2.collection.Document
import org.dizitart.no2.collection.NitriteCollection
import org.example.snipsnip.definitions.snippetData
import org.example.snipsnip.editors.SnippetEditor

class snippetManager(db: Nitrite) {
    val snippetCollection: NitriteCollection = db.getCollection("snippets")
    val snippetCoordinator = SnippetEditor(snippetCollection)

    fun deleteSnippet(info: snippetData){
        if(info.id != null){snippetCoordinator.delete(info)}
    }

    fun createNewSnippet(tags: List<Document>?, lang: Document): snippetData{
        val counter = snippetCoordinator.getAll().size
        return snippetCoordinator.insertNew(tags, lang, counter)!!
    }

    fun alterData(doc: snippetData): snippetData? =  snippetCoordinator.update(doc)

    fun getAllData(): List<snippetData> = snippetCoordinator.getAll()

    fun getDataByName(name: String): snippetData? = snippetCoordinator.getByName(name)

}