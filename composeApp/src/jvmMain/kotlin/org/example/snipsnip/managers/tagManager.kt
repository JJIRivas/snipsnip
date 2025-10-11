package org.example.snipsnip.managers

import org.dizitart.no2.Nitrite
import org.dizitart.no2.collection.NitriteCollection
import org.dizitart.no2.collection.NitriteId
import org.example.snipsnip.definitions.languageTagData
import org.example.snipsnip.definitions.userTagData
import org.example.snipsnip.tags.languageTags
import org.example.snipsnip.tags.userTags

class tagManager(db: Nitrite) {

    val languageCollection: NitriteCollection = db.getCollection("languages")
    val userTagCollection: NitriteCollection = db.getCollection("userTags")
    val langTags = languageTags(languageCollection)
    val usrTags = userTags(userTagCollection)

    fun createDefaultTables(){
        if(languageCollection.find().isEmpty){
            langTags.createDefault()
        }

        if(userTagCollection.find().isEmpty){
            usrTags.createDefault()
        }
    }

    fun deleteData(isLanguageData: languageTagData?, isTagData: userTagData?){
        if(isLanguageData != null){
            langTags.delete(isLanguageData)
        }else{
            usrTags.delete(isTagData!!)
        }
    }

    fun insertData(isLanguageData: languageTagData?, isTagData: userTagData?){
        if(isLanguageData != null){
            langTags.insert(isLanguageData)
        }else{
            usrTags.insert(isTagData!!)
        }
    }

    fun alterData(isLanguageData: languageTagData?, isTagData: userTagData?){
        if(isLanguageData != null){
            langTags.update(isLanguageData)
        }else{
            usrTags.update(isTagData!!)
        }
    }

    //TODO(Fix this stupid if else lmao. ...Also just fix this class in general, too many if else's for my liking.)
    fun getAllData(test: String, isTagData: userTagData?): List<Any>{
        return if(test != null){
            langTags.getAll()
        }else if (isTagData != null){
            usrTags.getAll()
        } else {
            listOf()
        }
    }

    fun getDataById(isLanguageData: languageTagData?, isTagData: userTagData?, id: NitriteId): Any? {
        return if(isLanguageData != null){
            langTags.getById(id)
        }else if (isTagData != null){
            usrTags.getById(id)
        }else{
            return null
        }
    }

    fun getDataByName(isLanguageData: languageTagData?, isTagData: userTagData?, name: String): Any? {
        return if(isLanguageData != null){
            langTags.getByName(name)
        }else if (isTagData != null){
            usrTags.getByName(name)
        }else{
            return null
        }
    }

}