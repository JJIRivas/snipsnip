package org.example.snipsnip

import org.example.snipsnip.managers.nTextManager
import org.example.snipsnip.managers.snippetManager
import org.example.snipsnip.managers.tagManager
import org.example.snipsnip.starters.DatabaseStarter
import org.example.snipsnip.starters.DirectoryStarter


fun main() {
        val mainFolder = DirectoryStarter().createMainDirectory()
        val db = DatabaseStarter().createMainDB(mainFolder)
        val tagCoordinator = tagManager(db)
        tagCoordinator.createDefaultTables()
    val tesst = tagCoordinator.getAllData(" s", null)
    for(i in tesst){
        println(i)
        println("------")
    }
        val snippetCoordinator = snippetManager()
        val nTextCoordinator = nTextManager()
}



