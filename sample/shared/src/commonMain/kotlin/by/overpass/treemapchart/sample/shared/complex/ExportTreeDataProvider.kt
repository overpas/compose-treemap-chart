package by.overpass.treemapchart.sample.shared.complex

import by.overpass.treemapchart.core.tree.Tree
import by.overpass.treemapchart.sample.shared.complex.data.ProductTradesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

internal object ExportTreeDataProvider {

    private val mutex = Mutex()
    private var cached: Tree<Export>? = null

    suspend fun get(): Tree<Export> = cached ?: withContext(Dispatchers.Default) {
        mutex.withLock {
            val exports = ProductTradesRepository.getJapan2021Exports()
            ExportsTreeBuilder.buildTree(exports).also { tree ->
                cached = tree
            }
        }
    }
}
