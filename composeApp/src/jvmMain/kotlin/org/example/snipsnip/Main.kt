package org.example.snipsnip

import org.example.snipsnip.managers.tagManager
import org.example.snipsnip.starters.DatabaseStarter
import org.example.snipsnip.starters.DirectoryStarter
import org.example.snipsnip.starters.UIStarter


fun main() {
    /*
    So.... the order of operations so far should be

     1. Create directory for the app in the /home folder of the user (or equivalent).

     2. Start the DB, same command will create it on the previous folder if it doesn't exist already.

     3. Start basic tagging functionality AKA both language tags and user created tags, if the documents
     don't exist the same command will create them in the db with their corresponding collection

     4. Ideally... the UI would start here so the user can do whatever, if they decide to create a new snippet a pop-up
     window would appear, and they would have to select the language of the snippet with optional user tags. Only then
     a snippet would be created and the program shows the editors for the user to fuck around.

     Thing is, since compose takes the focus of the app the moment it starts... how the fuck am I supposed to run the
     stuff? There's probably ways to run multiple things at the same time, so I don't have to put backend logic on the
     UI related classes. Need to check.

     5. After basic functionality with the UI is done, then the last thing would be implementing an API for user plugins,
     expose functions and whatnot.

     6. Polishing, unit testing and bug fixing.

     7. ??? Don't know, wouldn't surprise me if I dropped the project before I get anywhere near close to here.

     */
        val mainFolder = DirectoryStarter().createMainDirectory()
        val db = DatabaseStarter().createMainDB(mainFolder)
        val tagCoordinator = tagManager(db)
        tagCoordinator.createDefaultTables()
        val startUI = UIStarter().UIStarter()
//    val tesst = tagCoordinator.getAllData(" s", null)
//    for(i in tesst){
//        println(i)
//        println("------")
//    }
//        val snippetCoordinator = snippetManager()
//        val nTextCoordinator = nTextManager()
}



