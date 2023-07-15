package `by`.overpass.treemapchart.sample.shared.complex.icons

import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.AnimalAndVegetableBiProducts
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.AnimalHides
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.AnimalProducts
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.ArtsAndAntiques
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.ChemicalProducts
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.Foodstuffs
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.FootwearAndHeadwear
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.Instruments
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.Machines
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.Metals
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.MineralProducts
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.Miscellaneous
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.PaperGoods
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.PlasticsAndRubbers
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.PreciousMetals
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.StoneAndGlass
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.Textiles
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.Transportation
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.Unspecified
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.VegetableProducts
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.Weapons
import `by`.overpass.treemapchart.sample.shared.complex.icons.producticons.WoodProducts
import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.collections.List as ____KtList

public object ProductIcons

private var allIcons: ____KtList<ImageVector>? = null

public val ProductIcons.AllIcons: ____KtList<ImageVector>
  get() {
    if (allIcons != null) {
      return allIcons!!
    }
    allIcons= listOf(FootwearAndHeadwear, PreciousMetals, Unspecified, AnimalHides,
        ArtsAndAntiques, Transportation, Instruments, Weapons, AnimalAndVegetableBiProducts,
        WoodProducts, MineralProducts, Metals, AnimalProducts, Textiles, PaperGoods, Miscellaneous,
        Machines, ChemicalProducts, PlasticsAndRubbers, VegetableProducts, StoneAndGlass,
        Foodstuffs)
    return allIcons!!
  }
