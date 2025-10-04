package org.example.snipsnip

import org.example.snipsnip.managers.nTextManager
import org.example.snipsnip.managers.snippetManager
import org.example.snipsnip.managers.tagManager
import org.example.snipsnip.starters.DatabaseStarter
import org.example.snipsnip.starters.DirectoryStarter

class Main{
    fun main() {
        val mainFolder = DirectoryStarter().createMainDirectory()
        val db = DatabaseStarter().createMainDB(mainFolder)
        val tagCoordinator = tagManager(db)
        val snippetCoordinator = snippetManager()
        val nTextCoordinator = nTextManager()
    }
}



