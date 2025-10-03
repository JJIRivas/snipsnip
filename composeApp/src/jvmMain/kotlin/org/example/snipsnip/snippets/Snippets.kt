package org.example.snipsnip.snippets

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Snippets (val connection: Connection) {

    fun createDefaultSnippetTable(){
        val statement = connection.createStatement()
        statement.execute("""DROP TABLE IF EXISTS snippets;""")
        statement.execute("""CREATE TABLE IF NOT EXISTS snippets
                            (
                                id_snippet      INTEGER PRIMARY KEY AUTOINCREMENT,
                                id_language_tag INTEGER,
                                id_usr_tag      INTEGER,
                                name            TEXT      NOT NULL,
                                code            TEXT,
                                created_at      TEXT NOT NULL,
                                edited_at       TEXT NOT NULL,
                                FOREIGN KEY (id_language_tag) REFERENCES languageTags (id_language_tag),
                                FOREIGN KEY (id_usr_tag) REFERENCES userTags (id_usr_tag)
                            );""")

        println("Table snippets created successfully.")
    }

    fun createEmptySnippet(name: String = "", languageID: Int = 0){
        val formattedDateTime: String = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val localDateTime = LocalDateTime.parse(formattedDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val timestamp = Timestamp.valueOf(localDateTime)
        val sql = "INSERT INTO snippets (name, id_language_tag, created_at, edited_at) VALUES (?, ?, ?, ?);"
        val statement = connection.prepareStatement(sql)

        if(!name.isEmpty() && languageID != 0){
            emptySnippetValues(statement, name, languageID, timestamp)
        }else{
            emptySnippetValues(statement, timestamp = timestamp)
        }

    }

    fun emptySnippetValues(statement: PreparedStatement, name: String = "", languageID: Int = 0, timestamp: Timestamp){
        if(!name.isEmpty() && languageID != 0){
            statement.setString(1, name)
            statement.setInt(2, languageID)
            statement.setTimestamp(3, timestamp)
            statement.setTimestamp(4, timestamp)
            statement.executeUpdate()
            return
        }

        val statement1 = connection.createStatement()
        val resultSet = statement1.executeQuery("""SELECT COUNT(*) FROM snippets;""")

        if (resultSet.next()) {
            val position = resultSet.getInt(1)
            statement.setString(1, "New Snippet $position")
            statement.setInt(2, 1)
            statement.setTimestamp(3, timestamp)
            statement.setTimestamp(4, timestamp)
            statement.executeUpdate()
            return
        }
    }

    fun obtainSnippetId(){}
    fun editSnippet(){}
    fun deleteSnippet(){}
    fun obtainSnippetByName(){}
    fun obtainSnippetTotal(){}
}