package org.example.snipsnip.tags

import java.sql.Connection
import java.sql.Statement

class languageTags(val connection: Connection) {

    fun createDefaultTable(){
        val statement: Statement = connection.createStatement()

        val defLangTags = arrayOf("C",
            "C++",
            "Python",
            "Java",
            "Rust",
            "HTML",
            "CSS",
            "JavaScript",
            "Go",
            "Kotlin",
            "ASSEMBLY")
        statement.execute("""DROP TABLE IF EXISTS languageTags;""")
        statement.execute("""
        CREATE TABLE IF NOT EXISTS languageTags (
            id_language_tag INTEGER PRIMARY KEY AUTOINCREMENT,
            lang_tag TEXT NOT NULL
        ); """)


        for(tag in defLangTags){
            statement.execute("""INSERT INTO languageTags (lang_tag) VALUES ('$tag'); """)
        }

        println("Table defLangTags and default data inserted successfully.")
    }

    fun insertNewLanguageTag(tag: String){
        val sql = "INSERT INTO languageTags (lang_tag) VALUES (?)"
        val statement = connection.prepareStatement(sql)

        statement.setString(1, tag)

        statement.executeUpdate()
    }

    fun deleteLanguageTag(tag: String){
        val sql = "DELETE FROM languageTags WHERE lang_tag = (?)"
        val statement = connection.prepareStatement(sql)

        statement.setString(1, tag)

        statement.executeUpdate()
    }

    fun getAllLanguageTags(): MutableList<String> {
        val sql = "SELECT lang_tag FROM languageTags"
        val statement = connection.prepareStatement(sql)
        val resultSet = statement.executeQuery()

        val tags = mutableListOf<String>()
        while (resultSet.next()) {
            val tag = resultSet.getString("lang_tag")
            tags.add(tag)
        }

        resultSet.close()
        return tags
    }
}