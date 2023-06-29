package by.overpass.treemapchart.sample.shared.complex

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import by.overpass.treemapchart.sample.shared.complex.icons.ProductIcons
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.AnimalAndVegetableBiProducts
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.AnimalHides
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.AnimalProducts
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.ArtsAndAntiques
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.ChemicalProducts
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.Foodstuffs
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.FootwearAndHeadwear
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.Instruments
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.Machines
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.Metals
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.MineralProducts
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.Miscellaneous
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.PaperGoods
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.PlasticsAndRubbers
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.PreciousMetals
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.StoneAndGlass
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.Textiles
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.Transportation
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.Unspecified
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.VegetableProducts
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.Weapons
import by.overpass.treemapchart.sample.shared.complex.icons.producticons.WoodProducts

@Immutable
internal enum class ProductSection(
    val sectionId: Long,
    val title: String,
    val color: Color,
    val icon: ImageVector,
) {

    ANIMAL_PRODUCTS(
        sectionId = 1L,
        title = "Animal Products",
        color = Color(0xFFF2AA86),
        icon = ProductIcons.AnimalProducts,
    ),
    VEGETABLE_PRODUCTS(
        sectionId = 2L,
        title = "Vegetable Products",
        color = Color(0xFFF4CE0F),
        icon = ProductIcons.VegetableProducts,
    ),
    ANIMAL_AND_VEGETABLE_BI_PRODUCTS(
        sectionId = 3L,
        title = "Animal and Vegetable Bi-Products",
        color = Color(0xFFEDB73E),
        icon = ProductIcons.AnimalAndVegetableBiProducts,
    ),
    FOODSTUFFS(
        sectionId = 4L,
        title = "Foodstuffs",
        color = Color(0xFFA0D447),
        icon = ProductIcons.Foodstuffs,
    ),
    MINERAL_PRODUCTS(
        sectionId = 5L,
        title = "Mineral Products",
        color = Color(0xFFA53200),
        icon = ProductIcons.MineralProducts,
    ),
    CHEMICAL_PRODUCTS(
        sectionId = 6L,
        title = "Chemical Products",
        color = Color(0xFFED40F2),
        icon = ProductIcons.ChemicalProducts,
    ),
    PLASTICS_AND_RUBBERS(
        sectionId = 7L,
        title = "Plastics and Rubbers",
        color = Color(0xFFFF73FF),
        icon = ProductIcons.PlasticsAndRubbers,
    ),
    ANIMAL_HIDES(
        sectionId = 8L,
        title = "Animal Hides",
        color = Color(0xFF6DF2B0),
        icon = ProductIcons.AnimalHides,
    ),
    WOOD_PRODUCTS(
        sectionId = 9L,
        title = "Wood Products",
        color = Color(0xFFDD0E31),
        icon = ProductIcons.WoodProducts,
    ),
    PAPER_GOODS(
        sectionId = 10L,
        title = "Paper Goods",
        color = Color(0xFFEFDC81),
        icon = ProductIcons.PaperGoods,
    ),
    TEXTILES(
        sectionId = 11L,
        title = "Textiles",
        color = Color(0xFF02A347),
        icon = ProductIcons.Textiles,
    ),
    FOOTWEAR_AND_HEADWEAR(
        sectionId = 12L,
        title = "Footwear and Headwear",
        color = Color(0xFF2CBA0F),
        icon = ProductIcons.FootwearAndHeadwear,
    ),
    STONE_AND_GLASS(
        sectionId = 13L,
        title = "Stone and Glass",
        color = Color(0xFFF46D2A),
        icon = ProductIcons.StoneAndGlass,
    ),
    PRECIOUS_METALS(
        sectionId = 14L,
        title = "Precious Metals",
        color = Color(0xFF892EFF),
        icon = ProductIcons.PreciousMetals,
    ),
    METALS(
        sectionId = 15L,
        title = "Metals",
        color = Color(0xFFAA7329),
        icon = ProductIcons.Metals,
    ),
    MACHINES(
        sectionId = 16L,
        title = "Machines",
        color = Color(0xFF2E97FF),
        icon = ProductIcons.Machines,
    ),
    TRANSPORTATION(
        sectionId = 17L,
        title = "Transportation",
        color = Color(0xFF69C8ED),
        icon = ProductIcons.Transportation,
    ),
    INSTRUMENTS(
        sectionId = 18L,
        title = "Instruments",
        color = Color(0xFF9E0071),
        icon = ProductIcons.Instruments,
    ),
    WEAPONS(
        sectionId = 19L,
        title = "Weapons",
        color = Color(0xFF9CF2CF),
        icon = ProductIcons.Weapons,
    ),
    MISCELLANEOUS(
        sectionId = 20L,
        title = "Miscellaneous",
        color = Color(0xFF9C9FB2),
        icon = ProductIcons.Miscellaneous,
    ),
    ARTS_AND_ANTIQUES(
        sectionId = 21L,
        title = "Arts and Antiques",
        color = Color(0xFF847290),
        icon = ProductIcons.ArtsAndAntiques,
    ),
    UNSPECIFIED(
        sectionId = 22L,
        title = "Unspecified",
        color = Color(0xFFFA5656),
        icon = ProductIcons.Unspecified,
    );

    companion object {

        fun byId(id: Long): ProductSection =
            values()
                .find { section -> section.sectionId == id }
                ?: UNSPECIFIED
    }
}
