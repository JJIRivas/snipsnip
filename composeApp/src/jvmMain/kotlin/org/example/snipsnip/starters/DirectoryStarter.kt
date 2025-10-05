package org.example.snipsnip.starters

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class DirectoryStarter {
    fun createMainDirectory(): Path {
        val appDataDir = Paths.get(System.getProperty("user.home"), "SnipSnipData")

        if(!Files.exists(appDataDir)) {
            Files.createDirectories(appDataDir)
        }else {
            return appDataDir
        }

        return appDataDir
    }
}