package org.example.snipsnip.tags

import java.sql.Connection

class tagManager(val connection: Connection) {

    val langTags = languageTags(connection)
    val usrTags = userTags(connection)

    fun createDefaultTables(){
        langTags.createDefaultTable()
        usrTags.createDefaultTable()
    }
    fun deleteData(){

    }
    fun insertData(){}
    fun alterData(){}
    fun getAllData(){}
}