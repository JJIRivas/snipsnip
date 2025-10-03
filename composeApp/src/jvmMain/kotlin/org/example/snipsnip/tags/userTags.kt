package org.example.snipsnip.tags

import java.sql.Connection
import java.sql.Statement

class userTags(val connection: Connection) {

    fun createDefaultTable(){
        val statement: Statement = connection.createStatement()

        val defuserTags = arrayOf("favorite",
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
        statement.execute("""DROP TABLE IF EXISTS userTags;""")
        statement.execute("""
        CREATE TABLE IF NOT EXISTS userTags (
            id_usr_tag INTEGER PRIMARY KEY AUTOINCREMENT,
            usr_tag TEXT NOT NULL
        ); """)

        for(tag in defuserTags){
            statement.execute("""INSERT INTO userTags (usr_tag) VALUES ('$tag'); """)
        }

        println("Table userTags and default data inserted successfully.")
    }

    fun insertNewUserTag(tag: String){
        val sql = "INSERT INTO userTags (usr_tag) VALUES (?)"
        val statement = connection.prepareStatement(sql)

        statement.setString(1, tag)

        statement.executeUpdate()
    }

    fun deleteUserTag(tag: String){
        val sql = "DELETE FROM userTags WHERE usr_tag = (?)"
        val statement = connection.prepareStatement(sql)

        statement.setString(1, tag)

        statement.executeUpdate()
    }

    fun getAllUserTags(): MutableList<String> {
        val sql = "SELECT usr_tag FROM userTags"
        val statement = connection.prepareStatement(sql)
        val resultSet = statement.executeQuery()

        val tags = mutableListOf<String>()
        while (resultSet.next()) {
            val tag = resultSet.getString("usr_tag")
            tags.add(tag)
        }

        resultSet.close()
        return tags
    }
}