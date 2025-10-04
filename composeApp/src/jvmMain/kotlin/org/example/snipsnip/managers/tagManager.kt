package org.example.snipsnip.managers

import org.dizitart.no2.Nitrite
import org.example.snipsnip.tags.languageTags
import org.example.snipsnip.tags.userTags
import org.dizitart.no2.collection.NitriteCollection

class tagManager(val db: Nitrite) {

    val languageCollection: NitriteCollection = db.getCollection("languages")
    val userTagCollection: NitriteCollection = db.getCollection("userTags")
    val langTags = languageTags(languageCollection)
//    val usrTags = userTags(userTagCollection)

    fun createDefaultTables(){
        if(languageCollection.find().isEmpty){
            langTags.createDefault()
        }

//        if(userTagCollection.find().isEmpty){
//            usrTags.createDefaultTable()
//        }
    }
    fun deleteData(){

    }
    fun insertData(){}
    fun alterData(){}
    fun getAllData(){}
}