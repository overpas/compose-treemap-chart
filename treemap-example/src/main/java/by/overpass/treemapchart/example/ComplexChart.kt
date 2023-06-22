package by.overpass.treemapchart.example

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import by.overpass.treemapchart.android.TreemapChart
import by.overpass.treemapchart.core.tree.Tree
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.Locale

private const val SAMPLE_JSON = """
   [
      {
        "Category": "01",
        "Description": "Live animals",
        "Value": 50000000,
        "Color": "#4287f5",
        "Subcategories": [
          {
            "Category": "0101",
            "Description": "Live horses, asses, mules, and hinnies",
            "Value": 10000000,
            "Color": null,
            "Subcategories": []
          },
          {
            "Category": "0102",
            "Description": "Live bovine animals",
            "Value": 20000000,
            "Color": null,
            "Subcategories": []
          },
          {
            "Category": "0103",
            "Description": "Live swine",
            "Value": 20000000,
            "Color": null,
            "Subcategories": []
          }
        ]
      },
      {
        "Category": "02",
        "Description": "Meat and edible meat offal",
        "Value": 35000000,
        "Color": "#f54287",
        "Subcategories": [
          {
            "Category": "0201",
            "Description": "Meat of bovine animals",
            "Value": 12000000,
            "Color": null,
            "Subcategories": []
          },
          {
            "Category": "0202",
            "Description": "Meat of poultry",
            "Value": 18000000,
            "Color": null,
            "Subcategories": []
          },
          {
            "Category": "0203",
            "Description": "Meat of swine",
            "Value": 5000000,
            "Color": null,
            "Subcategories": []
          }
        ]
      },
      {
        "Category": "03",
        "Description": "Fish and crustaceans, molluscs, and other aquatic invertebrates",
        "Value": 20000000,
        "Color": "#42f578",
        "Subcategories": [
          {
            "Category": "0301",
            "Description": "Live fish",
            "Value": 7000000,
            "Color": null,
            "Subcategories": []
          },
          {
            "Category": "0302",
            "Description": "Fish, fresh or chilled",
            "Value": 6000000,
            "Color": null,
            "Subcategories": []
          },
          {
            "Category": "0303",
            "Description": "Fish, frozen",
            "Value": 7000000,
            "Color": null,
            "Subcategories": []
          }
        ]
      },
      {
        "Category": "04",
        "Description": "Dairy products; birds' eggs; natural honey; edible products of animal origin, not elsewhere specified or included",
        "Value": 18000000,
        "Color": "#f2f542",
        "Subcategories": [
          {
            "Category": "0401",
            "Description": "Milk and cream",
            "Value": 7000000,
            "Color": null,
            "Subcategories": []
          },
          {
            "Category": "0402",
            "Description": "Cheese and curd",
            "Value": 5000000,
            "Color": null,
            "Subcategories": []
          },
          {
            "Category": "0403",
            "Description": "Butter and other fats and oils derived from milk",
            "Value": 6000000,
            "Color": null,
            "Subcategories": []
          }
        ]
      },
      {
        "Category": "05",
        "Description": "Products of animal origin, not elsewhere specified or included",
        "Value": 12000000,
        "Color": "#f54242",
        "Subcategories": []
      }
   ]
"""

@Composable
internal fun ComplexChart(modifier: Modifier = Modifier, onBack: () -> Unit) {
    BackHandler(onBack = onBack)
    var tree by remember { mutableStateOf<Tree<CategoryItem>?>(null) }
    LaunchedEffect(Unit) {
        tree = getTree()
    }
    if (tree != null) {
        TreemapChart(
            data = tree!!,
            evaluateItem = { it.value.toDouble() },
            modifier = modifier,
        ) { node, GroupContent ->
            if (node.children.isEmpty()) {
                CategoryItem(item = node.data)
            } else {
                val color = node.data.color
                if (color != null) {
                    Box(
                        modifier = Modifier
                            .background(color)
                            .border(2.dp, Color.White),
                    ) {
                        GroupContent(node)
                    }
                } else {
                    GroupContent(node)
                }
            }
        }
    }
}

@Composable
private fun CategoryItem(item: CategoryItem, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .border(1.dp, Color.White)
            .clickable {
                Toast
                    .makeText(context, "${item.name} clicked", Toast.LENGTH_SHORT)
                    .show()
            }
            .padding(4.dp),
    ) {
        Text(text = item.name, textAlign = TextAlign.Center)
        Text(text = "${String.format(Locale.getDefault(), "%.2f", item.percent)}%")
    }
}

private data class CategoryItem(
    val name: String,
    val value: Long,
    val percent: Double,
    val color: Color?,
)

@Serializable
private data class Category(
    @SerialName("Category") val name: String,
    @SerialName("Description") val description: String,
    @SerialName("Value") val value: Long,
    @SerialName("Color") val color: String?,
    @SerialName("Subcategories") val subcategories: List<Category>,
)

private suspend fun getCategoriesData(): List<Category> = withContext(Dispatchers.IO) {
    Json.decodeFromString(SAMPLE_JSON)
}

private suspend fun getTree(): Tree<CategoryItem> = withContext(Dispatchers.Default) {
    val categories = getCategoriesData()
    val totalSum = categories.sumOf { it.value }
    val root = Tree.Node(
        CategoryItem(
            "Total",
            totalSum,
            100.0,
            null,
        ),
    )
    val tree = Tree(root)
    fun add(node: Tree.Node<CategoryItem>, category: Category) {
        for (subcategory in category.subcategories) {
            val item = CategoryItem(
                subcategory.description,
                subcategory.value,
                subcategory.value.toDouble() / totalSum * 100,
                subcategory.color.asColor(),
            )
            val newNode = Tree.Node(item)
            node.addChild(newNode)
            add(newNode, subcategory)
        }
    }
    for (category in categories) {
        val item = CategoryItem(
            category.description,
            category.value,
            category.value.toDouble() / totalSum * 100,
            category.color.asColor(),
        )
        val newNode = Tree.Node(item)
        root.addChild(newNode)
        add(newNode, category)
    }
    tree
}

private fun String?.asColor(): Color? =
    this?.let(android.graphics.Color::parseColor)?.let(::Color)
