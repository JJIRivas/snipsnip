package org.example.snipsnip.starters

import org.dizitart.kno2.KNO2Module
import org.dizitart.kno2.nitrite
import org.dizitart.no2.Nitrite
import org.dizitart.no2.mvstore.MVStoreModule
import java.nio.file.Path

class DatabaseStarter {
    fun createMainDB(route: Path): Nitrite {
        val routeString = route.resolve("SnipSnip.db").toString()

        val storeModule: MVStoreModule = MVStoreModule.withConfig()
            .filePath(routeString)
            .build()

        val db = nitrite {
            loadModule(storeModule)
            loadModule(KNO2Module())
        }

        return db
    }
}