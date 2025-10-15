package org.example.snipsnip.managers

import org.dizitart.no2.collection.Document
import org.dizitart.no2.collection.NitriteCollection
import org.example.snipsnip.definitions.normalTextData
import org.example.snipsnip.editors.SnippetEditor
import org.example.snipsnip.editors.normalTextEditor
import org.example.snipsnip.definitions.snippetData

class EditorManager(snippetCollection: NitriteCollection, normalTextCollection: NitriteCollection) {
    val normalTextCoordinator = normalTextEditor(normalTextCollection)
    val snippetCoordinator = SnippetEditor(snippetCollection)


    fun newEditor(tags: List<Document>?, lang: Document): Pair<normalTextData, snippetData> {
        val normal = normalTextCoordinator.insertNew(tags, lang, normalTextCoordinator.getAll().size)!!
        val snippet = snippetCoordinator.insertNew(tags, lang, snippetCoordinator.getAll().size)!!
        val (linkedNormal, linkedSnippet) = linkData(normal, snippet)

        normalTextCoordinator.update(linkedNormal!!)
        snippetCoordinator.update(linkedSnippet!!)

        return linkedNormal to linkedSnippet
    }

    fun linkData(normal: normalTextData, snippet: snippetData): Pair<normalTextData?, snippetData?> {
        val linkedNormal = normal.copy(editorLinked = snippetCoordinator.dataToDoc(snippet))
        val linkedSnippet = snippet.copy(editorLinked = normalTextCoordinator.dataToDoc(normal))
        return linkedNormal to linkedSnippet
    }

    fun deleteEditorData(infoNormal: normalTextData, infoSnippet: snippetData): Boolean{
        return if(infoNormal.id != null && infoSnippet.id != null){
            normalTextCoordinator.delete(infoNormal)
            snippetCoordinator.delete(infoSnippet)
            true
        } else false
    }

    fun alterData(normalDoc: normalTextData?, snipDoc: snippetData?): Any?{
        return if(normalDoc?.id != null){
            normalTextCoordinator.update(normalDoc)
        }else if(snipDoc?.id != null){
            snippetCoordinator.update(snipDoc)
        } else null
    }

    fun getAllData(): Pair<List<normalTextData?>, List<snippetData>> = normalTextCoordinator.getAll() to snippetCoordinator.getAll()


    fun getDataByName(name: String): Pair<normalTextData, snippetData>?{
        val snip = snippetCoordinator.getByName(name)
        val normal = normalTextCoordinator.getByName(name)
        return if(snip != null && normal != null){
            normal to snip
        }else null
    }
}