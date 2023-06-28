package by.overpass.treemapchart.sample.shared.complex

import by.overpass.treemapchart.core.tree.Tree
import by.overpass.treemapchart.core.tree.tree
import by.overpass.treemapchart.sample.shared.complex.data.ProductTrade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal object ExportsTreeBuilder {

    suspend fun buildTree(productTrades: List<ProductTrade>): Tree<Export> = withContext(Dispatchers.Default) {
        val total = productTrades.sumOf(ProductTrade::tradeValue)
        tree(
            Export.Section(
                name = "Total Exports",
                exportsValue = total,
                percentage = 100.0,
                color = null,
            ),
        ) {
            productTrades.groupBy(ProductTrade::sectionId)
                .map { (sectionId, sectionProductTrades) ->
                    val sectionTotal = sectionProductTrades.sumOf(ProductTrade::tradeValue)
                    Triple(sectionId, sectionTotal, sectionProductTrades)
                }
                .sortedByDescending { it.second }
                .forEach { (sectionId, sectionTotal, sectionProductTrades) ->
                    val productSection = ProductSection.byId(sectionId)
                    val sectionPercentage = sectionTotal / total * 100
                    node(
                        Export.Section(
                            name = productSection.title,
                            exportsValue = sectionTotal,
                            percentage = sectionPercentage,
                            color = productSection.color,
                        ),
                    ) {
                        sectionProductTrades
                            .sortedByDescending(ProductTrade::tradeValue)
                            .forEach { productTrade ->
                                val productPercentage = productTrade.tradeValue / total * 100
                                node(
                                    Export.Product(
                                        name = productTrade.hs4Name,
                                        exportsValue = productTrade.tradeValue,
                                        percentage = productPercentage,
                                        sectionName = productSection.title,
                                        icon = productSection.icon,
                                        color = productSection.color,
                                    ),
                                )
                            }
                    }
                }
        }
    }
}
