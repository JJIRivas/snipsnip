package org.example.snipsnip.managers

import org.dizitart.no2.Nitrite
import org.dizitart.no2.collection.Document
import org.dizitart.no2.collection.NitriteCollection
import org.example.snipsnip.definitions.normalTextData
import org.example.snipsnip.editors.normalTextEditor

class normalTextManager(db: Nitrite) {
    val normalTextCollection: NitriteCollection = db.getCollection("normalText")
    val normalTextCoordinator = normalTextEditor(normalTextCollection)


    fun createNewNormalEditor(tags: List<Document>?, lang: Document): normalTextData {
        val counter = normalTextCoordinator.getAll().size
        return normalTextCoordinator.insertNew(tags, lang, counter)!!
    }

    fun deleteNormalEditor(info: normalTextData){
        if(info.id != null){normalTextCoordinator.delete(info)}
    }

    fun alterData(doc: normalTextData): normalTextData? =  normalTextCoordinator.update(doc)

    fun getAllData(): List<normalTextData?> = normalTextCoordinator.getAll()

    fun getDataByName(name: String): normalTextData? = normalTextCoordinator.getByName(name)
}