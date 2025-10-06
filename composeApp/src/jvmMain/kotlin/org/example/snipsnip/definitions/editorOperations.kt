package org.example.snipsnip.definitions

interface editorOperations <T, L, S> {
    fun insertNew(tags: List<T>?, lang: L): S?
    fun delete(doc: S): Boolean
    fun update(doc: S): S?
    fun getAll(): List<S?>
    fun getByName(name: String): S?
}