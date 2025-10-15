package org.example.snipsnip.managers

import org.dizitart.kno2.filters.eq
import org.dizitart.no2.collection.NitriteCollection
import org.example.snipsnip.definitions.languageTagData
import org.example.snipsnip.definitions.userTagData
import org.example.snipsnip.tags.LanguageTags
import org.example.snipsnip.tags.UserTags

class TagManager(val languageCollection: NitriteCollection, val userTagCollection: NitriteCollection) {
    val langTags = LanguageTags(languageCollection)
    val usrTags = UserTags(userTagCollection)

    fun createDefaultTables(): Pair<List<languageTagData>, List<userTagData>>?{
        val emptyLangs = languageCollection.find().isEmpty
        val emptyUsr = userTagCollection.find().isEmpty
        return when{
            emptyLangs && emptyUsr-> {
                Pair(langTags.createDefault(), usrTags.createDefault())
            }
            else -> null
        }
    }
    inline fun<L, U> operateOnTags(langData: languageTagData?,
                                      tagData: userTagData?,
                                      noinline funcLang: ((languageTagData) -> L)? = null,
                                      noinline funcUsrTag: ((userTagData) -> U)? = null
    ): Any? {
        val langCheck = langData?.id != null &&
                languageCollection.find("_id" eq langData.id).firstOrNull() != null

        val tagCheck = tagData?.id != null &&
                userTagCollection.find("_id" eq tagData.id).firstOrNull() != null

        return when {
            langCheck && tagCheck -> Pair(funcLang?.invoke(langData), funcUsrTag?.invoke(tagData))
            langCheck -> funcLang?.invoke(langData)
            tagCheck -> funcUsrTag?.invoke(tagData)
            else -> null
        }
    }


}