package by.overpass.treemapchart.sample.shared.complex.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val japan2021ExportsJson = """
    {
      "data": [
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 101,
          "HS2": "Live animals",
          "HS4 ID": 10101,
          "HS4": "Horses",
          "Trade Value": 13523554
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 101,
          "HS2": "Live animals",
          "HS4 ID": 10106,
          "HS4": "Other Animals",
          "Trade Value": 11373386
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 102,
          "HS2": "Meat & edible offal",
          "HS4 ID": 10201,
          "HS4": "Bovine Meat",
          "Trade Value": 241072388
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 102,
          "HS2": "Meat & edible offal",
          "HS4 ID": 10202,
          "HS4": "Frozen Bovine Meat",
          "Trade Value": 255655522
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 102,
          "HS2": "Meat & edible offal",
          "HS4 ID": 10203,
          "HS4": "Pig Meat",
          "Trade Value": 18218703
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 102,
          "HS2": "Meat & edible offal",
          "HS4 ID": 10204,
          "HS4": "Sheep and Goat Meat",
          "Trade Value": 119255
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 102,
          "HS2": "Meat & edible offal",
          "HS4 ID": 10205,
          "HS4": "Horse Meat",
          "Trade Value": 2698
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 102,
          "HS2": "Meat & edible offal",
          "HS4 ID": 10206,
          "HS4": "Edible Offal",
          "Trade Value": 1298061
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 102,
          "HS2": "Meat & edible offal",
          "HS4 ID": 10207,
          "HS4": "Poultry Meat",
          "Trade Value": 12418603
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 102,
          "HS2": "Meat & edible offal",
          "HS4 ID": 10208,
          "HS4": "Other Meat",
          "Trade Value": 166
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 102,
          "HS2": "Meat & edible offal",
          "HS4 ID": 10209,
          "HS4": "Animal Fat",
          "Trade Value": 54315
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 102,
          "HS2": "Meat & edible offal",
          "HS4 ID": 10210,
          "HS4": "Preserved Meat",
          "Trade Value": 2018807
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 103,
          "HS2": "Fish, crustaceans, & molluscs",
          "HS4 ID": 10301,
          "HS4": "Live Fish",
          "Trade Value": 126607174
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 103,
          "HS2": "Fish, crustaceans, & molluscs",
          "HS4 ID": 10302,
          "HS4": "Non-fillet Fresh Fish",
          "Trade Value": 132576732
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 103,
          "HS2": "Fish, crustaceans, & molluscs",
          "HS4 ID": 10303,
          "HS4": "Non-fillet Frozen Fish",
          "Trade Value": 563247120
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 103,
          "HS2": "Fish, crustaceans, & molluscs",
          "HS4 ID": 10304,
          "HS4": "Fish Fillets",
          "Trade Value": 253846445
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 103,
          "HS2": "Fish, crustaceans, & molluscs",
          "HS4 ID": 10305,
          "HS4": "Fish: dried, salted, smoked or in brine",
          "Trade Value": 14297757
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 103,
          "HS2": "Fish, crustaceans, & molluscs",
          "HS4 ID": 10306,
          "HS4": "Crustaceans",
          "Trade Value": 23689161
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 103,
          "HS2": "Fish, crustaceans, & molluscs",
          "HS4 ID": 10307,
          "HS4": "Molluscs",
          "Trade Value": 639367941
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 104,
          "HS2": "Edible products of animal origin, n.e.s.",
          "HS4 ID": 10401,
          "HS4": "Milk",
          "Trade Value": 18375272
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 104,
          "HS2": "Edible products of animal origin, n.e.s.",
          "HS4 ID": 10402,
          "HS4": "Concentrated Milk",
          "Trade Value": 16960429
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 104,
          "HS2": "Edible products of animal origin, n.e.s.",
          "HS4 ID": 10403,
          "HS4": "Fermented Milk Products",
          "Trade Value": 2709037
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 104,
          "HS2": "Edible products of animal origin, n.e.s.",
          "HS4 ID": 10404,
          "HS4": "Whey and other milk products",
          "Trade Value": 4950242
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 104,
          "HS2": "Edible products of animal origin, n.e.s.",
          "HS4 ID": 10405,
          "HS4": "Butter",
          "Trade Value": 1105254
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 104,
          "HS2": "Edible products of animal origin, n.e.s.",
          "HS4 ID": 10406,
          "HS4": "Cheese",
          "Trade Value": 18443589
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 104,
          "HS2": "Edible products of animal origin, n.e.s.",
          "HS4 ID": 10407,
          "HS4": "Eggs",
          "Trade Value": 52901468
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 104,
          "HS2": "Edible products of animal origin, n.e.s.",
          "HS4 ID": 10408,
          "HS4": "Processed Egg Products",
          "Trade Value": 1949945
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 104,
          "HS2": "Edible products of animal origin, n.e.s.",
          "HS4 ID": 10409,
          "HS4": "Honey",
          "Trade Value": 968237
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 104,
          "HS2": "Edible products of animal origin, n.e.s.",
          "HS4 ID": 10410,
          "HS4": "Other Edible Animal Products",
          "Trade Value": 229701
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 105,
          "HS2": "Animal originated products, n.e.s.",
          "HS4 ID": 10501,
          "HS4": "Human Hair",
          "Trade Value": 508008.00000000006
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 105,
          "HS2": "Animal originated products, n.e.s.",
          "HS4 ID": 10502,
          "HS4": "Pig Hair",
          "Trade Value": 122409
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 105,
          "HS2": "Animal originated products, n.e.s.",
          "HS4 ID": 10504,
          "HS4": "Animal Organs",
          "Trade Value": 157230
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 105,
          "HS2": "Animal originated products, n.e.s.",
          "HS4 ID": 10505,
          "HS4": "Bird Feathers and Skins",
          "Trade Value": 12285832
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 105,
          "HS2": "Animal originated products, n.e.s.",
          "HS4 ID": 10506,
          "HS4": "Processed Bones",
          "Trade Value": 122105.00000000001
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 105,
          "HS2": "Animal originated products, n.e.s.",
          "HS4 ID": 10507,
          "HS4": "Raw Bones",
          "Trade Value": 34239
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 105,
          "HS2": "Animal originated products, n.e.s.",
          "HS4 ID": 10508,
          "HS4": "Coral and Shells",
          "Trade Value": 38135938
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 105,
          "HS2": "Animal originated products, n.e.s.",
          "HS4 ID": 10510,
          "HS4": "Pharmaceutical Animal Products",
          "Trade Value": 982626.0000000001
        },
        {
          "Section ID": 1,
          "Section": "Animal Products",
          "HS2 ID": 105,
          "HS2": "Animal originated products, n.e.s.",
          "HS4 ID": 10511,
          "HS4": "Other Inedible Animal Products",
          "Trade Value": 14541598
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 206,
          "HS2": "Live trees, plants, bulbs, cut flowers, & ornamental foliage",
          "HS4 ID": 20601,
          "HS4": "Bulbs and Roots",
          "Trade Value": 618690
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 206,
          "HS2": "Live trees, plants, bulbs, cut flowers, & ornamental foliage",
          "HS4 ID": 20602,
          "HS4": "Other live plants, cuttings and slips;\nmushroom spawn",
          "Trade Value": 66181176
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 206,
          "HS2": "Live trees, plants, bulbs, cut flowers, & ornamental foliage",
          "HS4 ID": 20603,
          "HS4": "Cut Flowers",
          "Trade Value": 8655042
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 206,
          "HS2": "Live trees, plants, bulbs, cut flowers, & ornamental foliage",
          "HS4 ID": 20604,
          "HS4": "Plant foliage",
          "Trade Value": 2178769
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20701,
          "HS4": "Potatoes",
          "Trade Value": 512922
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20702,
          "HS4": "Tomatoes",
          "Trade Value": 1089411
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20703,
          "HS4": "Onions",
          "Trade Value": 4762838
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20704,
          "HS4": "Cabbages",
          "Trade Value": 3517704
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20705,
          "HS4": "Lettuce",
          "Trade Value": 434282
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20706,
          "HS4": "Root Vegetables",
          "Trade Value": 2981343
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20707,
          "HS4": "Cucumbers",
          "Trade Value": 291890
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20708,
          "HS4": "Legumes",
          "Trade Value": 233419
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20709,
          "HS4": "Other Vegetables",
          "Trade Value": 12990671
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20710,
          "HS4": "Frozen Vegetables",
          "Trade Value": 1523657
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20711,
          "HS4": "Preserved Vegetables",
          "Trade Value": 1483259
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20712,
          "HS4": "Dried Vegetables",
          "Trade Value": 5526393
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20713,
          "HS4": "Dried Legumes",
          "Trade Value": 1205248
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 207,
          "HS2": "Edible vegetables, roots, & tubers",
          "HS4 ID": 20714,
          "HS4": "Cassava",
          "Trade Value": 22775854
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20801,
          "HS4": "Coconuts, Brazil Nuts, and Cashews",
          "Trade Value": 132696
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20802,
          "HS4": "Other Nuts",
          "Trade Value": 3902279.0000000005
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20803,
          "HS4": "Bananas",
          "Trade Value": 6081
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20804,
          "HS4": "Tropical Fruits",
          "Trade Value": 999863
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20805,
          "HS4": "Citrus",
          "Trade Value": 11256768
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20806,
          "HS4": "Grapes",
          "Trade Value": 41475183
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20807,
          "HS4": "Melons",
          "Trade Value": 10912926
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20808,
          "HS4": "Apples and Pears",
          "Trade Value": 150442737
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20809,
          "HS4": "Pitted Fruits",
          "Trade Value": 23022424
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20810,
          "HS4": "Other Fruits",
          "Trade Value": 46167037
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20811,
          "HS4": "Frozen Fruits and Nuts",
          "Trade Value": 490536
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20812,
          "HS4": "Preserved Fruits and Nuts",
          "Trade Value": 4924
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20813,
          "HS4": "Dried Fruits",
          "Trade Value": 2775982
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 208,
          "HS2": "Edible fruits, nuts & fruit peels",
          "HS4 ID": 20814,
          "HS4": "Citrus and Melon Peels",
          "Trade Value": 670822
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 209,
          "HS2": "Coffee, tea, mate & spices",
          "HS4 ID": 20901,
          "HS4": "Coffee",
          "Trade Value": 41525799
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 209,
          "HS2": "Coffee, tea, mate & spices",
          "HS4 ID": 20902,
          "HS4": "Tea",
          "Trade Value": 195762866
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 209,
          "HS2": "Coffee, tea, mate & spices",
          "HS4 ID": 20903,
          "HS4": "Maté",
          "Trade Value": 29393
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 209,
          "HS2": "Coffee, tea, mate & spices",
          "HS4 ID": 20904,
          "HS4": "Pepper",
          "Trade Value": 3178482
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 209,
          "HS2": "Coffee, tea, mate & spices",
          "HS4 ID": 20905,
          "HS4": "Vanilla",
          "Trade Value": 43887
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 209,
          "HS2": "Coffee, tea, mate & spices",
          "HS4 ID": 20906,
          "HS4": "Cinnamon",
          "Trade Value": 23356
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 209,
          "HS2": "Coffee, tea, mate & spices",
          "HS4 ID": 20907,
          "HS4": "Cloves",
          "Trade Value": 12038.000000000002
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 209,
          "HS2": "Coffee, tea, mate & spices",
          "HS4 ID": 20908,
          "HS4": "Nutmeg, mace and cardamons",
          "Trade Value": 857193.9999999999
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 209,
          "HS2": "Coffee, tea, mate & spices",
          "HS4 ID": 20909,
          "HS4": "Spice Seeds",
          "Trade Value": 144627
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 209,
          "HS2": "Coffee, tea, mate & spices",
          "HS4 ID": 20910,
          "HS4": "Spices",
          "Trade Value": 15009615
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 210,
          "HS2": "Cereals",
          "HS4 ID": 21001,
          "HS4": "Wheat",
          "Trade Value": 660698
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 210,
          "HS2": "Cereals",
          "HS4 ID": 21002,
          "HS4": "Rye",
          "Trade Value": 452
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 210,
          "HS2": "Cereals",
          "HS4 ID": 21003,
          "HS4": "Barley",
          "Trade Value": 141706
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 210,
          "HS2": "Cereals",
          "HS4 ID": 21004,
          "HS4": "Oats",
          "Trade Value": 234144
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 210,
          "HS2": "Cereals",
          "HS4 ID": 21005,
          "HS4": "Corn",
          "Trade Value": 129938
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 210,
          "HS2": "Cereals",
          "HS4 ID": 21006,
          "HS4": "Rice",
          "Trade Value": 75445184
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 210,
          "HS2": "Cereals",
          "HS4 ID": 21007,
          "HS4": "Sorghum",
          "Trade Value": 12859
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 210,
          "HS2": "Cereals",
          "HS4 ID": 21008,
          "HS4": "Buckwheat",
          "Trade Value": 420776
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 211,
          "HS2": "Products of the milling industry",
          "HS4 ID": 21101,
          "HS4": "Wheat Flours",
          "Trade Value": 90680125
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 211,
          "HS2": "Products of the milling industry",
          "HS4 ID": 21102,
          "HS4": "Cereal Flours",
          "Trade Value": 1481631
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 211,
          "HS2": "Products of the milling industry",
          "HS4 ID": 21103,
          "HS4": "Cereal Meal and Pellets",
          "Trade Value": 520163
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 211,
          "HS2": "Products of the milling industry",
          "HS4 ID": 21104,
          "HS4": "Processed Cereals",
          "Trade Value": 1368543
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 211,
          "HS2": "Products of the milling industry",
          "HS4 ID": 21105,
          "HS4": "Potato Flours",
          "Trade Value": 306131
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 211,
          "HS2": "Products of the milling industry",
          "HS4 ID": 21106,
          "HS4": "Legume Flours",
          "Trade Value": 1189944
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 211,
          "HS2": "Products of the milling industry",
          "HS4 ID": 21107,
          "HS4": "Malt",
          "Trade Value": 24086
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 211,
          "HS2": "Products of the milling industry",
          "HS4 ID": 21108,
          "HS4": "Starches",
          "Trade Value": 6619173
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 211,
          "HS2": "Products of the milling industry",
          "HS4 ID": 21109,
          "HS4": "Wheat Gluten",
          "Trade Value": 258994.99999999997
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 212,
          "HS2": "Oils seeds, oleaginous fruits, grains, straw & fodder",
          "HS4 ID": 21201,
          "HS4": "Soybeans",
          "Trade Value": 973137
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 212,
          "HS2": "Oils seeds, oleaginous fruits, grains, straw & fodder",
          "HS4 ID": 21202,
          "HS4": "Ground Nuts",
          "Trade Value": 33135
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 212,
          "HS2": "Oils seeds, oleaginous fruits, grains, straw & fodder",
          "HS4 ID": 21204,
          "HS4": "Linseed",
          "Trade Value": 2254.9999999999995
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 212,
          "HS2": "Oils seeds, oleaginous fruits, grains, straw & fodder",
          "HS4 ID": 21205,
          "HS4": "Rapeseed",
          "Trade Value": 11181391
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 212,
          "HS2": "Oils seeds, oleaginous fruits, grains, straw & fodder",
          "HS4 ID": 21206,
          "HS4": "Sunflower Seeds",
          "Trade Value": 6679590
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 212,
          "HS2": "Oils seeds, oleaginous fruits, grains, straw & fodder",
          "HS4 ID": 21207,
          "HS4": "Other Oily Seeds",
          "Trade Value": 5767734
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 212,
          "HS2": "Oils seeds, oleaginous fruits, grains, straw & fodder",
          "HS4 ID": 21208,
          "HS4": "Oil Seed Flower",
          "Trade Value": 1782420
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 212,
          "HS2": "Oils seeds, oleaginous fruits, grains, straw & fodder",
          "HS4 ID": 21209,
          "HS4": "Sowing Seeds",
          "Trade Value": 138449417
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 212,
          "HS2": "Oils seeds, oleaginous fruits, grains, straw & fodder",
          "HS4 ID": 21210,
          "HS4": "Hops",
          "Trade Value": 7580
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 212,
          "HS2": "Oils seeds, oleaginous fruits, grains, straw & fodder",
          "HS4 ID": 21211,
          "HS4": "Perfume Plants",
          "Trade Value": 3694070
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 212,
          "HS2": "Oils seeds, oleaginous fruits, grains, straw & fodder",
          "HS4 ID": 21212,
          "HS4": "Locust beans, seaweed, sugar beet, cane, for food",
          "Trade Value": 9031401
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 212,
          "HS2": "Oils seeds, oleaginous fruits, grains, straw & fodder",
          "HS4 ID": 21213,
          "HS4": "Cereal Straws",
          "Trade Value": 417011
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 212,
          "HS2": "Oils seeds, oleaginous fruits, grains, straw & fodder",
          "HS4 ID": 21214,
          "HS4": "Forage Crops",
          "Trade Value": 147732
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 213,
          "HS2": "Natural gums, resins & other vegetable extracts",
          "HS4 ID": 21301,
          "HS4": "Insect Resins",
          "Trade Value": 1260501
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 213,
          "HS2": "Natural gums, resins & other vegetable extracts",
          "HS4 ID": 21302,
          "HS4": "Vegetable Saps",
          "Trade Value": 48336848
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 214,
          "HS2": "Vegetable plaiting materials & products, n.e.s.",
          "HS4 ID": 21401,
          "HS4": "Vegetable Plaiting Materials",
          "Trade Value": 263314
        },
        {
          "Section ID": 2,
          "Section": "Vegetable Products",
          "HS2 ID": 214,
          "HS2": "Vegetable plaiting materials & products, n.e.s.",
          "HS4 ID": 21404,
          "HS4": "Other Vegetable Products",
          "Trade Value": 2254462
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31501,
          "HS4": "Pig and Poultry Fat",
          "Trade Value": 3170340
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31502,
          "HS4": "Bovine, Sheep, and Goat Fat",
          "Trade Value": 3094684.0000000005
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31503,
          "HS4": "Lard stearin and oils",
          "Trade Value": 165750
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31504,
          "HS4": "Fish oil",
          "Trade Value": 30493836
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31505,
          "HS4": "Wool Grease",
          "Trade Value": 23567140
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31506,
          "HS4": "Other Animal Fats",
          "Trade Value": 1821544
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31507,
          "HS4": "Soybean Oil",
          "Trade Value": 961829
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31508,
          "HS4": "Ground Nut Oil",
          "Trade Value": 45536
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31509,
          "HS4": "Pure Olive Oil",
          "Trade Value": 1019434
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31510,
          "HS4": "Olive Oil",
          "Trade Value": 163150
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31511,
          "HS4": "Palm Oil",
          "Trade Value": 336802
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31512,
          "HS4": "Seed Oils",
          "Trade Value": 825104
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31513,
          "HS4": "Coconut Oil",
          "Trade Value": 293692
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31514,
          "HS4": "Rapeseed Oil",
          "Trade Value": 8257588
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31515,
          "HS4": "Other Pure Vegetable Oils",
          "Trade Value": 85901048
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31516,
          "HS4": "Other Vegetable Oils",
          "Trade Value": 10050222
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31517,
          "HS4": "Margarine",
          "Trade Value": 17795535
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31518,
          "HS4": "Inedible Fats and Oils",
          "Trade Value": 126432414
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31519,
          "HS4": "Stearic Acid",
          "Trade Value": 32580220
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31520,
          "HS4": "Glycerol",
          "Trade Value": 3948116
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31521,
          "HS4": "Vegetable waxes and beeswax",
          "Trade Value": 8175212
        },
        {
          "Section ID": 3,
          "Section": "Animal and Vegetable Bi-Products",
          "HS2 ID": 315,
          "HS2": "Animal or vegetable fats, oils, & waxes",
          "HS4 ID": 31522,
          "HS4": "Fat and Oil Residues",
          "Trade Value": 5311.000000000001
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 416,
          "HS2": "Preparations of fish, crustacenas, & molluscs",
          "HS4 ID": 41601,
          "HS4": "Sausages",
          "Trade Value": 1207904
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 416,
          "HS2": "Preparations of fish, crustacenas, & molluscs",
          "HS4 ID": 41602,
          "HS4": "Other Prepared Meat",
          "Trade Value": 15866250
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 416,
          "HS2": "Preparations of fish, crustacenas, & molluscs",
          "HS4 ID": 41603,
          "HS4": "Animal Extracts",
          "Trade Value": 3553797
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 416,
          "HS2": "Preparations of fish, crustacenas, & molluscs",
          "HS4 ID": 41604,
          "HS4": "Processed Fish",
          "Trade Value": 221420325
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 416,
          "HS2": "Preparations of fish, crustacenas, & molluscs",
          "HS4 ID": 41605,
          "HS4": "Processed Crustaceans",
          "Trade Value": 534649740
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 417,
          "HS2": "Sugars & confectioneries",
          "HS4 ID": 41701,
          "HS4": "Raw Sugar",
          "Trade Value": 4516851
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 417,
          "HS2": "Sugars & confectioneries",
          "HS4 ID": 41702,
          "HS4": "Other Sugars",
          "Trade Value": 19407659
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 417,
          "HS2": "Sugars & confectioneries",
          "HS4 ID": 41703,
          "HS4": "Molasses",
          "Trade Value": 69925
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 417,
          "HS2": "Sugars & confectioneries",
          "HS4 ID": 41704,
          "HS4": "Confectionery Sugar",
          "Trade Value": 147015747
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 418,
          "HS2": "Cocoa & cocoa preparations",
          "HS4 ID": 41801,
          "HS4": "Cocoa Beans",
          "Trade Value": 4766
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 418,
          "HS2": "Cocoa & cocoa preparations",
          "HS4 ID": 41802,
          "HS4": "Cocoa Shells",
          "Trade Value": 208283
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 418,
          "HS2": "Cocoa & cocoa preparations",
          "HS4 ID": 41803,
          "HS4": "Cocoa Paste",
          "Trade Value": 1323290
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 418,
          "HS2": "Cocoa & cocoa preparations",
          "HS4 ID": 41804,
          "HS4": "Cocoa Butter",
          "Trade Value": 806698
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 418,
          "HS2": "Cocoa & cocoa preparations",
          "HS4 ID": 41805,
          "HS4": "Cocoa Powder",
          "Trade Value": 939467
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 418,
          "HS2": "Cocoa & cocoa preparations",
          "HS4 ID": 41806,
          "HS4": "Chocolate",
          "Trade Value": 94266615
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 419,
          "HS2": "Preparations of cereals, flour, starch or milk",
          "HS4 ID": 41901,
          "HS4": "Malt Extract",
          "Trade Value": 180954933
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 419,
          "HS2": "Preparations of cereals, flour, starch or milk",
          "HS4 ID": 41902,
          "HS4": "Pasta",
          "Trade Value": 185312970
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 419,
          "HS2": "Preparations of cereals, flour, starch or milk",
          "HS4 ID": 41903,
          "HS4": "Tapioca",
          "Trade Value": 62421
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 419,
          "HS2": "Preparations of cereals, flour, starch or milk",
          "HS4 ID": 41904,
          "HS4": "Prepared Cereals",
          "Trade Value": 83998683
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 419,
          "HS2": "Preparations of cereals, flour, starch or milk",
          "HS4 ID": 41905,
          "HS4": "Baked Goods",
          "Trade Value": 438369188
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 420,
          "HS2": "Preparations of vegetables, fruit, nuts or other plant parts",
          "HS4 ID": 42001,
          "HS4": "Pickled Foods",
          "Trade Value": 4454840
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 420,
          "HS2": "Preparations of vegetables, fruit, nuts or other plant parts",
          "HS4 ID": 42002,
          "HS4": "Processed Tomatoes",
          "Trade Value": 940998.0000000001
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 420,
          "HS2": "Preparations of vegetables, fruit, nuts or other plant parts",
          "HS4 ID": 42003,
          "HS4": "Processed Mushrooms",
          "Trade Value": 1218352
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 420,
          "HS2": "Preparations of vegetables, fruit, nuts or other plant parts",
          "HS4 ID": 42004,
          "HS4": "Other Frozen Vegetables",
          "Trade Value": 7118656
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 420,
          "HS2": "Preparations of vegetables, fruit, nuts or other plant parts",
          "HS4 ID": 42005,
          "HS4": "Other Processed Vegetables",
          "Trade Value": 49741414
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 420,
          "HS2": "Preparations of vegetables, fruit, nuts or other plant parts",
          "HS4 ID": 42006,
          "HS4": "Sugar Preserved Foods",
          "Trade Value": 660195
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 420,
          "HS2": "Preparations of vegetables, fruit, nuts or other plant parts",
          "HS4 ID": 42007,
          "HS4": "Jams",
          "Trade Value": 5788666
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 420,
          "HS2": "Preparations of vegetables, fruit, nuts or other plant parts",
          "HS4 ID": 42008,
          "HS4": "Other Processed Fruits and Nuts",
          "Trade Value": 130010247
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 420,
          "HS2": "Preparations of vegetables, fruit, nuts or other plant parts",
          "HS4 ID": 42009,
          "HS4": "Fruit Juice",
          "Trade Value": 41886769
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 421,
          "HS2": "Miscellaneous edible preparations",
          "HS4 ID": 42101,
          "HS4": "Coffee and Tea Extracts",
          "Trade Value": 140755203
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 421,
          "HS2": "Miscellaneous edible preparations",
          "HS4 ID": 42102,
          "HS4": "Yeast",
          "Trade Value": 14839517
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 421,
          "HS2": "Miscellaneous edible preparations",
          "HS4 ID": 42103,
          "HS4": "Sauces and Seasonings",
          "Trade Value": 539216249
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 421,
          "HS2": "Miscellaneous edible preparations",
          "HS4 ID": 42104,
          "HS4": "Soups and Broths",
          "Trade Value": 106416685
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 421,
          "HS2": "Miscellaneous edible preparations",
          "HS4 ID": 42105,
          "HS4": "Ice Cream",
          "Trade Value": 49109087
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 421,
          "HS2": "Miscellaneous edible preparations",
          "HS4 ID": 42106,
          "HS4": "Other Edible Preparations",
          "Trade Value": 1141685347
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 422,
          "HS2": "Beverages, spirits, & vinegar",
          "HS4 ID": 42201,
          "HS4": "Water",
          "Trade Value": 11819553
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 422,
          "HS2": "Beverages, spirits, & vinegar",
          "HS4 ID": 42202,
          "HS4": "Flavored Water",
          "Trade Value": 346959138
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 422,
          "HS2": "Beverages, spirits, & vinegar",
          "HS4 ID": 42203,
          "HS4": "Beer",
          "Trade Value": 73319381
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 422,
          "HS2": "Beverages, spirits, & vinegar",
          "HS4 ID": 42204,
          "HS4": "Wine",
          "Trade Value": 8382237
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 422,
          "HS2": "Beverages, spirits, & vinegar",
          "HS4 ID": 42205,
          "HS4": "Vermouth",
          "Trade Value": 932051
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 422,
          "HS2": "Beverages, spirits, & vinegar",
          "HS4 ID": 42206,
          "HS4": "Other Fermented Beverages",
          "Trade Value": 379219790
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 422,
          "HS2": "Beverages, spirits, & vinegar",
          "HS4 ID": 42207,
          "HS4": "Alcohol > 80% ABV",
          "Trade Value": 1689058
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 422,
          "HS2": "Beverages, spirits, & vinegar",
          "HS4 ID": 42208,
          "HS4": "Hard Liquor",
          "Trade Value": 609763361
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 422,
          "HS2": "Beverages, spirits, & vinegar",
          "HS4 ID": 42209,
          "HS4": "Vinegar",
          "Trade Value": 27715361
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 423,
          "HS2": "Food residues & wastes; animal fodder",
          "HS4 ID": 42301,
          "HS4": "Animal Meal and Pellets",
          "Trade Value": 15965473.999999998
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 423,
          "HS2": "Food residues & wastes; animal fodder",
          "HS4 ID": 42302,
          "HS4": "Bran",
          "Trade Value": 27380730
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 423,
          "HS2": "Food residues & wastes; animal fodder",
          "HS4 ID": 42303,
          "HS4": "Starch Residue",
          "Trade Value": 179030
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 423,
          "HS2": "Food residues & wastes; animal fodder",
          "HS4 ID": 42304,
          "HS4": "Soybean Meal",
          "Trade Value": 997953
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 423,
          "HS2": "Food residues & wastes; animal fodder",
          "HS4 ID": 42306,
          "HS4": "Other Vegetable Residues",
          "Trade Value": 3244454
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 423,
          "HS2": "Food residues & wastes; animal fodder",
          "HS4 ID": 42307,
          "HS4": "Wine Lees",
          "Trade Value": 11200
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 423,
          "HS2": "Food residues & wastes; animal fodder",
          "HS4 ID": 42308,
          "HS4": "Other Vegetable Residues and Waste",
          "Trade Value": 713821
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 423,
          "HS2": "Food residues & wastes; animal fodder",
          "HS4 ID": 42309,
          "HS4": "Animal Food",
          "Trade Value": 133418818
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 424,
          "HS2": "Tobacco & substitutes",
          "HS4 ID": 42401,
          "HS4": "Raw Tobacco",
          "Trade Value": 13731197
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 424,
          "HS2": "Tobacco & substitutes",
          "HS4 ID": 42402,
          "HS4": "Rolled Tobacco",
          "Trade Value": 103439747
        },
        {
          "Section ID": 4,
          "Section": "Foodstuffs",
          "HS2 ID": 424,
          "HS2": "Tobacco & substitutes",
          "HS4 ID": 42403,
          "HS4": "Processed Tobacco",
          "Trade Value": 19885031
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52501,
          "HS4": "Salt",
          "Trade Value": 5062758
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52502,
          "HS4": "Iron Pyrites",
          "Trade Value": 54331.99999999999
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52503,
          "HS4": "Sulphur",
          "Trade Value": 113632636
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52504,
          "HS4": "Graphite",
          "Trade Value": 34790118
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52505,
          "HS4": "Sand",
          "Trade Value": 7034566
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52506,
          "HS4": "Quartz",
          "Trade Value": 4736149
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52507,
          "HS4": "Kaolin",
          "Trade Value": 10183950
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52508,
          "HS4": "Clays",
          "Trade Value": 47728430
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52509,
          "HS4": "Chalk",
          "Trade Value": 1971138
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52510,
          "HS4": "Calcium Phosphates",
          "Trade Value": 341937
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52511,
          "HS4": "Barium Sulphate",
          "Trade Value": 1039035
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52512,
          "HS4": "Siliceous Fossil Meals",
          "Trade Value": 5127049
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52513,
          "HS4": "Pumice",
          "Trade Value": 25115369
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52514,
          "HS4": "Slate",
          "Trade Value": 203703
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52515,
          "HS4": "Marble, Travertine and Alabaster",
          "Trade Value": 22451
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52516,
          "HS4": "Granite",
          "Trade Value": 7836072
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52517,
          "HS4": "Gravel and Crushed Stone",
          "Trade Value": 2208038
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52518,
          "HS4": "Dolomite",
          "Trade Value": 569558
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52519,
          "HS4": "Magnesium Carbonate",
          "Trade Value": 83450272
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52520,
          "HS4": "Gypsum",
          "Trade Value": 3071454
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52521,
          "HS4": "Limestone",
          "Trade Value": 75560628
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52522,
          "HS4": "Quicklime",
          "Trade Value": 5951727
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52523,
          "HS4": "Cement",
          "Trade Value": 454791071
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52524,
          "HS4": "Asbestos",
          "Trade Value": 35254.00000000001
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52525,
          "HS4": "Mica",
          "Trade Value": 9324302
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52526,
          "HS4": "Soapstone",
          "Trade Value": 11113071
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52528,
          "HS4": "Borax",
          "Trade Value": 246052
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52529,
          "HS4": "Feldspar",
          "Trade Value": 3445466
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 525,
          "HS2": "Salt, sulphur, cement, lime, stone, & plaster",
          "HS4 ID": 52530,
          "HS4": "Other Mineral",
          "Trade Value": 32160086
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52601,
          "HS4": "Iron Ore",
          "Trade Value": 543510
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52602,
          "HS4": "Manganese Ore",
          "Trade Value": 43772
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52603,
          "HS4": "Copper Ore",
          "Trade Value": 1602265
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52604,
          "HS4": "Nickel Ore",
          "Trade Value": 333490
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52605,
          "HS4": "Cobalt Ore",
          "Trade Value": 41878
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52606,
          "HS4": "Aluminium Ore",
          "Trade Value": 437323
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52607,
          "HS4": "Lead Ore",
          "Trade Value": 63349.99999999999
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52608,
          "HS4": "Zinc Ore",
          "Trade Value": 189199
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52609,
          "HS4": "Tin Ores",
          "Trade Value": 4097511.9999999995
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52610,
          "HS4": "Chromium Ore",
          "Trade Value": 211618
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52611,
          "HS4": "Tungsten Ore",
          "Trade Value": 844557
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52612,
          "HS4": "Uranium and Thorium Ore",
          "Trade Value": 1511
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52613,
          "HS4": "Molybdenum Ore",
          "Trade Value": 4503913
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52614,
          "HS4": "Titanium Ore",
          "Trade Value": 164898.99999999997
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52615,
          "HS4": "Niobium, Tantalum, Vanadium and Zirconium Ore",
          "Trade Value": 735771
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52616,
          "HS4": "Precious Metal Ore",
          "Trade Value": 106781.99999999999
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52617,
          "HS4": "Other Ores",
          "Trade Value": 510518
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52618,
          "HS4": "Granulated Slag",
          "Trade Value": 145331415
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52619,
          "HS4": "Slag Dross",
          "Trade Value": 10845512
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52620,
          "HS4": "Non-Iron and Steel Slag, Ash and Residues",
          "Trade Value": 638012180
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 526,
          "HS2": "Ores, slag and ash",
          "HS4 ID": 52621,
          "HS4": "Other Slag and Ash",
          "Trade Value": 28565079
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52701,
          "HS4": "Coal Briquettes",
          "Trade Value": 4453287
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52703,
          "HS4": "Peat",
          "Trade Value": 140659
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52704,
          "HS4": "Coke",
          "Trade Value": 980022847
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52705,
          "HS4": "Non-Petroleum Gas",
          "Trade Value": 2687.0000000000005
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52706,
          "HS4": "Tar",
          "Trade Value": 9613747
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52707,
          "HS4": "Coal Tar Oil",
          "Trade Value": 1535460658
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52708,
          "HS4": "Pitch Coke",
          "Trade Value": 63479944
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52709,
          "HS4": "Crude Petroleum",
          "Trade Value": 224088448
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52710,
          "HS4": "Refined Petroleum",
          "Trade Value": 7131744920
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52711,
          "HS4": "Petroleum Gas",
          "Trade Value": 165350072
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52712,
          "HS4": "Petroleum Jelly",
          "Trade Value": 76224880
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52713,
          "HS4": "Petroleum Coke",
          "Trade Value": 289243958
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52714,
          "HS4": "Bitumen and asphalt",
          "Trade Value": 168746
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52715,
          "HS4": "Asphalt Mixtures",
          "Trade Value": 65676614
        },
        {
          "Section ID": 5,
          "Section": "Mineral Products",
          "HS2 ID": 527,
          "HS2": "Mineral fuels, mineral oils and products of their distillation",
          "HS4 ID": 52716,
          "HS4": "Electricity",
          "Trade Value": 2941.0000000000005
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62801,
          "HS4": "Halogens",
          "Trade Value": 185457615
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62802,
          "HS4": "Sulfur",
          "Trade Value": 9546851
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62803,
          "HS4": "Carbon",
          "Trade Value": 218282398
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62804,
          "HS4": "Hydrogen",
          "Trade Value": 674279207.0000001
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62805,
          "HS4": "Alkaline Metals",
          "Trade Value": 20712451
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62806,
          "HS4": "Hydrochloric Acid",
          "Trade Value": 42644260
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62807,
          "HS4": "Sulfuric Acid",
          "Trade Value": 122652496
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62808,
          "HS4": "Nitric Acids",
          "Trade Value": 4909631
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62809,
          "HS4": "Phosphoric Acid",
          "Trade Value": 35326773
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62810,
          "HS4": "Boron",
          "Trade Value": 1980212
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62811,
          "HS4": "Other Inorganic Acids",
          "Trade Value": 451735352
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62812,
          "HS4": "Halides",
          "Trade Value": 139690113
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62813,
          "HS4": "Nonmetal Sulfides",
          "Trade Value": 14087271
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62814,
          "HS4": "Ammonia",
          "Trade Value": 7381580
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62815,
          "HS4": "Sodium or Potassium Peroxides",
          "Trade Value": 357346834
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62816,
          "HS4": "Magnesium Hydroxide and Peroxide",
          "Trade Value": 21862645
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62817,
          "HS4": "Zinc Oxide and Peroxide",
          "Trade Value": 19261727
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62818,
          "HS4": "Aluminium Oxide",
          "Trade Value": 279098895
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62819,
          "HS4": "Chromium Oxides and Hydroxides",
          "Trade Value": 9674067
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62820,
          "HS4": "Manganese Oxides",
          "Trade Value": 34719352
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62821,
          "HS4": "Iron Oxides and Hydroxides",
          "Trade Value": 41295317
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62822,
          "HS4": "Cobalt Oxides and Hydroxides",
          "Trade Value": 1923011
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62823,
          "HS4": "Titanium Oxides",
          "Trade Value": 86450267
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62824,
          "HS4": "Lead Oxides",
          "Trade Value": 2558664
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62825,
          "HS4": "Inorganic Salts",
          "Trade Value": 253338624
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62826,
          "HS4": "Fluorides",
          "Trade Value": 128951512
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62827,
          "HS4": "Chlorides",
          "Trade Value": 118656955
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62828,
          "HS4": "Hypochlorites",
          "Trade Value": 55867646
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62829,
          "HS4": "Chlorates and Perchlorates",
          "Trade Value": 885714
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62830,
          "HS4": "Sulfides",
          "Trade Value": 7254283
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62831,
          "HS4": "Dithionites and Sulfoxylates",
          "Trade Value": 525263
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62832,
          "HS4": "Sulfites",
          "Trade Value": 15972377
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62833,
          "HS4": "Sulfates",
          "Trade Value": 112234762
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62834,
          "HS4": "Nitrites and Nitrates",
          "Trade Value": 8957631
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62835,
          "HS4": "Phosphinates (hypophosphites) and phosphonates (phosphites)",
          "Trade Value": 83872215
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62836,
          "HS4": "Carbonates",
          "Trade Value": 63098856
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62837,
          "HS4": "Cyanides",
          "Trade Value": 16915676
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62839,
          "HS4": "Silicates",
          "Trade Value": 27634198
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62840,
          "HS4": "Borates",
          "Trade Value": 1587935
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62841,
          "HS4": "Oxometallic or Peroxometallic Acid Salts",
          "Trade Value": 452978179
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62842,
          "HS4": "Other Inorganic Acids Salts",
          "Trade Value": 156445424
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62843,
          "HS4": "Precious Metal Compounds",
          "Trade Value": 2845930997
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62844,
          "HS4": "Radioactive Chemicals",
          "Trade Value": 34857072
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62845,
          "HS4": "Other Isotopes",
          "Trade Value": 17431778
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62846,
          "HS4": "Rare-Earth Metal Compounds",
          "Trade Value": 200588887
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62847,
          "HS4": "Hydrogen peroxide",
          "Trade Value": 36277233
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62849,
          "HS4": "Carbides",
          "Trade Value": 51779546
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62850,
          "HS4": "Hydrides and other anions",
          "Trade Value": 64467836
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 628,
          "HS2": "Inorganic chemicals",
          "HS4 ID": 62851,
          "HS4": "Inorganic Compounds",
          "Trade Value": 371451999
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62901,
          "HS4": "Acyclic Hydrocarbons",
          "Trade Value": 1334157162
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62902,
          "HS4": "Cyclic Hydrocarbons",
          "Trade Value": 3540226754
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62903,
          "HS4": "Halogenated Hydrocarbons",
          "Trade Value": 1719385473
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62904,
          "HS4": "Sulfonated, Nitrated or Nitrosated Hydrocarbons",
          "Trade Value": 31624805
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62905,
          "HS4": "Acyclic Alcohols",
          "Trade Value": 692807414
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62906,
          "HS4": "Cyclic Alcohols",
          "Trade Value": 197236635
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62907,
          "HS4": "Phenols",
          "Trade Value": 934210002
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62908,
          "HS4": "Phenol Derivatives",
          "Trade Value": 53862413
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62909,
          "HS4": "Ethers",
          "Trade Value": 321283610
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62910,
          "HS4": "Epoxides",
          "Trade Value": 206334764
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62911,
          "HS4": "Acetals and Hemiacetals",
          "Trade Value": 1170801
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62912,
          "HS4": "Aldehydes",
          "Trade Value": 128356264
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62913,
          "HS4": "Aldehyde Derivatives",
          "Trade Value": 5755501
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62914,
          "HS4": "Ketones and Quinones",
          "Trade Value": 432067887
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62915,
          "HS4": "Saturated Acyclic Monocarboxylic Acids",
          "Trade Value": 433456418
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62916,
          "HS4": "Unsaturated Acyclic Monocarboxylic Acids",
          "Trade Value": 794949741
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62917,
          "HS4": "Polycarboxylic Acids",
          "Trade Value": 260624173
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62918,
          "HS4": "Carboxylic Acids",
          "Trade Value": 329557212
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62919,
          "HS4": "Phosphoric Esters and Salts",
          "Trade Value": 93370783
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62920,
          "HS4": "Other Esters",
          "Trade Value": 137775768
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62921,
          "HS4": "Amine Compounds",
          "Trade Value": 482827685
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62922,
          "HS4": "Oxygen Amino Compounds",
          "Trade Value": 311750954
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62923,
          "HS4": "Quaternary Ammonium Salts and Hydroxides",
          "Trade Value": 83893368
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62924,
          "HS4": "Carboxyamide Compounds",
          "Trade Value": 371903063
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62925,
          "HS4": "Carboxyimide Compounds",
          "Trade Value": 131038111
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62926,
          "HS4": "Nitrile Compounds",
          "Trade Value": 201539397
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62927,
          "HS4": "Diazo, Azo or Aoxy Compounds",
          "Trade Value": 67611482
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62928,
          "HS4": "Hydrazine or Hydroxylamine Derivatives",
          "Trade Value": 48849150
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62929,
          "HS4": "Other Nitrogen Compounds",
          "Trade Value": 600809130
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62930,
          "HS4": "Organo-Sulfur Compounds",
          "Trade Value": 1138992652
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62931,
          "HS4": "Other Organo-Inorganic Compounds",
          "Trade Value": 359630829
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62932,
          "HS4": "Oxygen Heterocyclic Compounds",
          "Trade Value": 470967017
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62933,
          "HS4": "Nitrogen Heterocyclic Compounds",
          "Trade Value": 1452133796
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62934,
          "HS4": "Nucleic Acids",
          "Trade Value": 1617432805
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62935,
          "HS4": "Sulfonamides",
          "Trade Value": 78259429
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62936,
          "HS4": "Vitamins",
          "Trade Value": 104726562
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62937,
          "HS4": "Hormones",
          "Trade Value": 35334479
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62938,
          "HS4": "Glycosides",
          "Trade Value": 26626109
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62939,
          "HS4": "Vegetable Alkaloids",
          "Trade Value": 26182837
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62940,
          "HS4": "Chemically Pure Sugars",
          "Trade Value": 80074373
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62941,
          "HS4": "Antibiotics",
          "Trade Value": 101546735
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 629,
          "HS2": "Organic chemicals",
          "HS4 ID": 62942,
          "HS4": "Other Organic Compounds",
          "Trade Value": 40707398
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 630,
          "HS2": "Pharmaceutical products",
          "HS4 ID": 63001,
          "HS4": "Glands and Other Organs",
          "Trade Value": 25166022
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 630,
          "HS2": "Pharmaceutical products",
          "HS4 ID": 63002,
          "HS4": "Vaccines, blood, antisera, toxins and cultures",
          "Trade Value": 3656690920
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 630,
          "HS2": "Pharmaceutical products",
          "HS4 ID": 63003,
          "HS4": "Unpackaged Medicaments",
          "Trade Value": 195055902
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 630,
          "HS2": "Pharmaceutical products",
          "HS4 ID": 63004,
          "HS4": "Packaged Medicaments",
          "Trade Value": 5710335236
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 630,
          "HS2": "Pharmaceutical products",
          "HS4 ID": 63005,
          "HS4": "Bandages",
          "Trade Value": 332180052
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 630,
          "HS2": "Pharmaceutical products",
          "HS4 ID": 63006,
          "HS4": "Special Pharmaceuticals",
          "Trade Value": 301050710
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 631,
          "HS2": "Fertilizers",
          "HS4 ID": 63101,
          "HS4": "Animal or Vegetable Fertilizers",
          "Trade Value": 23415830
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 631,
          "HS2": "Fertilizers",
          "HS4 ID": 63102,
          "HS4": "Nitrogenous Fertilizers",
          "Trade Value": 106167503
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 631,
          "HS2": "Fertilizers",
          "HS4 ID": 63103,
          "HS4": "Phosphatic Fertilizers",
          "Trade Value": 688091
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 631,
          "HS2": "Fertilizers",
          "HS4 ID": 63104,
          "HS4": "Potassic Fertilizers",
          "Trade Value": 3167472
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 631,
          "HS2": "Fertilizers",
          "HS4 ID": 63105,
          "HS4": "Mixed Mineral or Chemical Fertilizers",
          "Trade Value": 49431736
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63201,
          "HS4": "Vegetable Tanning Extracts",
          "Trade Value": 1358797
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63202,
          "HS4": "Synthetic Tanning Extracts",
          "Trade Value": 2170922
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63203,
          "HS4": "Vegetable or Animal Dyes",
          "Trade Value": 22259969
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63204,
          "HS4": "Synthetic Coloring Matter",
          "Trade Value": 638616736
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63205,
          "HS4": "Lake Pigments",
          "Trade Value": 2739066
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63206,
          "HS4": "Other Coloring Matter",
          "Trade Value": 505889432
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63207,
          "HS4": "Prepared Pigments",
          "Trade Value": 673998904
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63208,
          "HS4": "Nonaqueous Paints",
          "Trade Value": 1850532269
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63209,
          "HS4": "Aqueous Paints",
          "Trade Value": 167167110
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63210,
          "HS4": "Other Paints",
          "Trade Value": 36050539
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63211,
          "HS4": "Prepared Paint Driers",
          "Trade Value": 1394064
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63212,
          "HS4": "Nonaqueous Pigments",
          "Trade Value": 151085833
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63213,
          "HS4": "Artistry Paints",
          "Trade Value": 9885674
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63214,
          "HS4": "Glaziers Putty",
          "Trade Value": 347291450
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 632,
          "HS2": "Paints, dyes, & varnishes",
          "HS4 ID": 63215,
          "HS4": "Ink",
          "Trade Value": 956631582
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 633,
          "HS2": "Perfumery & cosmetics",
          "HS4 ID": 63301,
          "HS4": "Essential Oils",
          "Trade Value": 10162981
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 633,
          "HS2": "Perfumery & cosmetics",
          "HS4 ID": 63302,
          "HS4": "Scented Mixtures",
          "Trade Value": 267612092
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 633,
          "HS2": "Perfumery & cosmetics",
          "HS4 ID": 63303,
          "HS4": "Perfumes",
          "Trade Value": 25501444
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 633,
          "HS2": "Perfumery & cosmetics",
          "HS4 ID": 63304,
          "HS4": "Beauty Products",
          "Trade Value": 5830722432
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 633,
          "HS2": "Perfumery & cosmetics",
          "HS4 ID": 63305,
          "HS4": "Hair Products",
          "Trade Value": 627703503
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 633,
          "HS2": "Perfumery & cosmetics",
          "HS4 ID": 63306,
          "HS4": "Dental Products",
          "Trade Value": 117848873
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 633,
          "HS2": "Perfumery & cosmetics",
          "HS4 ID": 63307,
          "HS4": "Shaving Products",
          "Trade Value": 222985545
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 634,
          "HS2": "Soap, cleaners, candles, dental, modelling pastes",
          "HS4 ID": 63401,
          "HS4": "Soap",
          "Trade Value": 94381820
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 634,
          "HS2": "Soap, cleaners, candles, dental, modelling pastes",
          "HS4 ID": 63402,
          "HS4": "Cleaning Products",
          "Trade Value": 1535499353
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 634,
          "HS2": "Soap, cleaners, candles, dental, modelling pastes",
          "HS4 ID": 63403,
          "HS4": "Lubricating Products",
          "Trade Value": 1341590623
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 634,
          "HS2": "Soap, cleaners, candles, dental, modelling pastes",
          "HS4 ID": 63404,
          "HS4": "Waxes",
          "Trade Value": 61690400
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 634,
          "HS2": "Soap, cleaners, candles, dental, modelling pastes",
          "HS4 ID": 63405,
          "HS4": "Polishes and Creams",
          "Trade Value": 484590665
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 634,
          "HS2": "Soap, cleaners, candles, dental, modelling pastes",
          "HS4 ID": 63406,
          "HS4": "Candles",
          "Trade Value": 1209985
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 634,
          "HS2": "Soap, cleaners, candles, dental, modelling pastes",
          "HS4 ID": 63407,
          "HS4": "Pastes and Waxes",
          "Trade Value": 37116012
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 635,
          "HS2": "Glues, starches, & albuminoid products",
          "HS4 ID": 63501,
          "HS4": "Casein",
          "Trade Value": 712908
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 635,
          "HS2": "Glues, starches, & albuminoid products",
          "HS4 ID": 63502,
          "HS4": "Water Soluble Proteins",
          "Trade Value": 1543457
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 635,
          "HS2": "Glues, starches, & albuminoid products",
          "HS4 ID": 63503,
          "HS4": "Gelatin",
          "Trade Value": 22552094
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 635,
          "HS2": "Glues, starches, & albuminoid products",
          "HS4 ID": 63504,
          "HS4": "Peptones",
          "Trade Value": 59853864
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 635,
          "HS2": "Glues, starches, & albuminoid products",
          "HS4 ID": 63505,
          "HS4": "Dextrins",
          "Trade Value": 48817472
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 635,
          "HS2": "Glues, starches, & albuminoid products",
          "HS4 ID": 63506,
          "HS4": "Glues",
          "Trade Value": 706806440
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 635,
          "HS2": "Glues, starches, & albuminoid products",
          "HS4 ID": 63507,
          "HS4": "Enzymes",
          "Trade Value": 307000895
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 636,
          "HS2": "Explosives, matches, & fireworks",
          "HS4 ID": 63601,
          "HS4": "Propellant Powders",
          "Trade Value": 7191145
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 636,
          "HS2": "Explosives, matches, & fireworks",
          "HS4 ID": 63602,
          "HS4": "Prepared Explosives",
          "Trade Value": 33382
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 636,
          "HS2": "Explosives, matches, & fireworks",
          "HS4 ID": 63603,
          "HS4": "Detonating Fuses",
          "Trade Value": 14279537
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 636,
          "HS2": "Explosives, matches, & fireworks",
          "HS4 ID": 63604,
          "HS4": "Fireworks",
          "Trade Value": 352903
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 636,
          "HS2": "Explosives, matches, & fireworks",
          "HS4 ID": 63605,
          "HS4": "Matches",
          "Trade Value": 413035
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 636,
          "HS2": "Explosives, matches, & fireworks",
          "HS4 ID": 63606,
          "HS4": "Pyrophoric Alloys",
          "Trade Value": 681060.0000000001
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 637,
          "HS2": "Photo & movie goods",
          "HS4 ID": 63701,
          "HS4": "Photographic Plates",
          "Trade Value": 1196923142
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 637,
          "HS2": "Photo & movie goods",
          "HS4 ID": 63702,
          "HS4": "Photographic Film",
          "Trade Value": 429645361
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 637,
          "HS2": "Photo & movie goods",
          "HS4 ID": 63703,
          "HS4": "Photographic Paper",
          "Trade Value": 8433194
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 637,
          "HS2": "Photo & movie goods",
          "HS4 ID": 63704,
          "HS4": "Undeveloped Exposed Photographic Material",
          "Trade Value": 12593652.999999998
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 637,
          "HS2": "Photo & movie goods",
          "HS4 ID": 63705,
          "HS4": "Developed Exposed Photographic Material",
          "Trade Value": 299818143
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 637,
          "HS2": "Photo & movie goods",
          "HS4 ID": 63706,
          "HS4": "Motion-picture film, exposed and developed",
          "Trade Value": 93396
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 637,
          "HS2": "Photo & movie goods",
          "HS4 ID": 63707,
          "HS4": "Photographic Chemicals",
          "Trade Value": 3615704545
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63801,
          "HS4": "Artificial Graphite",
          "Trade Value": 261574253
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63802,
          "HS4": "Activated Carbon",
          "Trade Value": 117032921
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63803,
          "HS4": "Tall Oil",
          "Trade Value": 624483
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63804,
          "HS4": "Wood Pulp Lyes",
          "Trade Value": 4183723
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63805,
          "HS4": "Turpentine",
          "Trade Value": 892073
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63806,
          "HS4": "Rosin",
          "Trade Value": 17685679
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63807,
          "HS4": "Wood Tar, Oils and Pitch",
          "Trade Value": 193048
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63808,
          "HS4": "Pesticides",
          "Trade Value": 705016728
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63809,
          "HS4": "Dyeing Finishing Agents",
          "Trade Value": 186541700
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63810,
          "HS4": "Metal Pickling Preparations",
          "Trade Value": 389328237
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63811,
          "HS4": "Antiknock",
          "Trade Value": 232753366
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63812,
          "HS4": "Prepared Rubber Accelerators",
          "Trade Value": 128830898
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63813,
          "HS4": "Fire Extinguishers Preparations",
          "Trade Value": 1541403
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63814,
          "HS4": "Organic Composite Solvents",
          "Trade Value": 68687961
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63815,
          "HS4": "Reaction and Catalytic Products",
          "Trade Value": 2562305213
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63816,
          "HS4": "Refractory Cements",
          "Trade Value": 46419895
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63817,
          "HS4": "Alkylbenzenes and Alkylnaphthalenes",
          "Trade Value": 2088369
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63818,
          "HS4": "Disc Chemicals for Electronics",
          "Trade Value": 4766464207
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63819,
          "HS4": "Hydraulic Brake Fluid",
          "Trade Value": 26907589
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63820,
          "HS4": "Antifreeze",
          "Trade Value": 18309092
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63821,
          "HS4": "Micro-Organism Culture Preparations",
          "Trade Value": 67750123
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63822,
          "HS4": "Laboratory Reagents",
          "Trade Value": 988079986
        },
        {
          "Section ID": 6,
          "Section": "Chemical Products",
          "HS2 ID": 638,
          "HS2": "Chemical products n.e.s.",
          "HS4 ID": 63823,
          "HS4": "Industrial Fatty Acids, Oils and Alcohols",
          "Trade Value": 4861572726
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73901,
          "HS4": "Ethylene Polymers",
          "Trade Value": 1230476634
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73902,
          "HS4": "Propylene Polymers",
          "Trade Value": 1000583024
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73903,
          "HS4": "Styrene Polymers",
          "Trade Value": 882818975
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73904,
          "HS4": "Vinyl Chloride Polymers",
          "Trade Value": 1801715459
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73905,
          "HS4": "Other Vinyl Polymers",
          "Trade Value": 489260655
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73906,
          "HS4": "Acrylic Polymers",
          "Trade Value": 1478514759
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73907,
          "HS4": "Polyacetals",
          "Trade Value": 3241712370
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73908,
          "HS4": "Polyamides",
          "Trade Value": 675873220
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73909,
          "HS4": "Amino-resins",
          "Trade Value": 1034078581
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73910,
          "HS4": "Silicone",
          "Trade Value": 903602010
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73911,
          "HS4": "Petroleum Resins",
          "Trade Value": 859534080
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73912,
          "HS4": "Cellulose",
          "Trade Value": 362983669
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73913,
          "HS4": "Natural Polymers",
          "Trade Value": 95378157
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73914,
          "HS4": "Polymer Ion-Exchangers",
          "Trade Value": 130660504
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73915,
          "HS4": "Scrap Plastic",
          "Trade Value": 290276277
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73916,
          "HS4": "Monofilament",
          "Trade Value": 48746041
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73917,
          "HS4": "Plastic Pipes",
          "Trade Value": 763766368
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73918,
          "HS4": "Plastic Floor Coverings",
          "Trade Value": 32419680
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73919,
          "HS4": "Self-adhesive Plastics",
          "Trade Value": 3155159741
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73920,
          "HS4": "Raw Plastic Sheeting",
          "Trade Value": 6519423529
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73921,
          "HS4": "Other Plastic Sheetings",
          "Trade Value": 1958321571
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73922,
          "HS4": "Plastic Wash Basins",
          "Trade Value": 21411528
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73923,
          "HS4": "Plastic Lids",
          "Trade Value": 786005995
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73924,
          "HS4": "Plastic Housewares",
          "Trade Value": 115678932
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73925,
          "HS4": "Plastic Building Materials",
          "Trade Value": 46031943
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 739,
          "HS2": "Plastics & articles thereof",
          "HS4 ID": 73926,
          "HS4": "Other Plastic Products",
          "Trade Value": 2740616242
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74001,
          "HS4": "Rubber",
          "Trade Value": 6587799
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74002,
          "HS4": "Synthetic Rubber",
          "Trade Value": 2476330750
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74003,
          "HS4": "Reclaimed Rubber",
          "Trade Value": 1459285
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74004,
          "HS4": "Scrap Rubber",
          "Trade Value": 2957998
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74005,
          "HS4": "Compounded Unvulcanised Rubber",
          "Trade Value": 218204346
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74006,
          "HS4": "Unvulcanised Rubber Products",
          "Trade Value": 6218852
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74007,
          "HS4": "Rubber Thread",
          "Trade Value": 5223330
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74008,
          "HS4": "Rubber Sheets",
          "Trade Value": 151032138
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74009,
          "HS4": "Rubber Pipes",
          "Trade Value": 556595944
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74010,
          "HS4": "Rubber Belting",
          "Trade Value": 390737675
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74011,
          "HS4": "Rubber Tires",
          "Trade Value": 5158612636
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74012,
          "HS4": "Used Rubber Tires",
          "Trade Value": 106352474
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74013,
          "HS4": "Rubber Inner Tubes",
          "Trade Value": 6109818
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74014,
          "HS4": "Pharmaceutical Rubber Products",
          "Trade Value": 50381775
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74015,
          "HS4": "Rubber Apparel",
          "Trade Value": 8997208
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74016,
          "HS4": "Other Rubber Products",
          "Trade Value": 1720003196
        },
        {
          "Section ID": 7,
          "Section": "Plastics and Rubbers",
          "HS2 ID": 740,
          "HS2": "Rubber & articles thereof",
          "HS4 ID": 74017,
          "HS4": "Hard Rubber",
          "Trade Value": 4865930
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 841,
          "HS2": "Raw hides & skin (non-fur)",
          "HS4 ID": 84101,
          "HS4": "Equine and Bovine Hides",
          "Trade Value": 1143567
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 841,
          "HS2": "Raw hides & skin (non-fur)",
          "HS4 ID": 84102,
          "HS4": "Sheep Hides",
          "Trade Value": 4310
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 841,
          "HS2": "Raw hides & skin (non-fur)",
          "HS4 ID": 84103,
          "HS4": "Other Hides and Skins",
          "Trade Value": 27030
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 841,
          "HS2": "Raw hides & skin (non-fur)",
          "HS4 ID": 84104,
          "HS4": "Tanned Equine and Bovine Hides",
          "Trade Value": 80025103
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 841,
          "HS2": "Raw hides & skin (non-fur)",
          "HS4 ID": 84105,
          "HS4": "Tanned Sheep Hides",
          "Trade Value": 1075367
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 841,
          "HS2": "Raw hides & skin (non-fur)",
          "HS4 ID": 84106,
          "HS4": "Tanned Goat Hides",
          "Trade Value": 1059109
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 841,
          "HS2": "Raw hides & skin (non-fur)",
          "HS4 ID": 84107,
          "HS4": "Leather of Other Animals",
          "Trade Value": 69327535
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 841,
          "HS2": "Raw hides & skin (non-fur)",
          "HS4 ID": 84108,
          "HS4": "Chamois Leather",
          "Trade Value": 191041
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 841,
          "HS2": "Raw hides & skin (non-fur)",
          "HS4 ID": 84109,
          "HS4": "Patent Leather",
          "Trade Value": 546609
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 841,
          "HS2": "Raw hides & skin (non-fur)",
          "HS4 ID": 84110,
          "HS4": "Leather Waste",
          "Trade Value": 44350
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 841,
          "HS2": "Raw hides & skin (non-fur)",
          "HS4 ID": 84111,
          "HS4": "Leather Sheets",
          "Trade Value": 4484398
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 842,
          "HS2": "Leather articles",
          "HS4 ID": 84201,
          "HS4": "Saddlery",
          "Trade Value": 1314038
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 842,
          "HS2": "Leather articles",
          "HS4 ID": 84202,
          "HS4": "Trunks and Cases",
          "Trade Value": 157753909
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 842,
          "HS2": "Leather articles",
          "HS4 ID": 84203,
          "HS4": "Leather Apparel",
          "Trade Value": 14339696
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 842,
          "HS2": "Leather articles",
          "HS4 ID": 84205,
          "HS4": "Other Leather Articles",
          "Trade Value": 6700517
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 842,
          "HS2": "Leather articles",
          "HS4 ID": 84206,
          "HS4": "Articles of Gut",
          "Trade Value": 8647
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 843,
          "HS2": "Furskins, articifial fur, & articles",
          "HS4 ID": 84301,
          "HS4": "Raw Furskins",
          "Trade Value": 318379
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 843,
          "HS2": "Furskins, articifial fur, & articles",
          "HS4 ID": 84302,
          "HS4": "Tanned Furskins",
          "Trade Value": 299575
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 843,
          "HS2": "Furskins, articifial fur, & articles",
          "HS4 ID": 84303,
          "HS4": "Furskin Apparel",
          "Trade Value": 1319672
        },
        {
          "Section ID": 8,
          "Section": "Animal Hides",
          "HS2 ID": 843,
          "HS2": "Furskins, articifial fur, & articles",
          "HS4 ID": 84304,
          "HS4": "Artificial Fur",
          "Trade Value": 4167290
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94401,
          "HS4": "Fuel Wood",
          "Trade Value": 1404486
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94402,
          "HS4": "Wood Charcoal",
          "Trade Value": 2206730
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94403,
          "HS4": "Rough Wood",
          "Trade Value": 194094687
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94404,
          "HS4": "Wood Stakes",
          "Trade Value": 70466
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94405,
          "HS4": "Wood Wool",
          "Trade Value": 75294
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94406,
          "HS4": "Railroad Ties",
          "Trade Value": 291962.99999999994
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94407,
          "HS4": "Sawn Wood",
          "Trade Value": 70131787
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94408,
          "HS4": "Veneer Sheets",
          "Trade Value": 3588093
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94409,
          "HS4": "Shaped Wood",
          "Trade Value": 5652516
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94410,
          "HS4": "Particle Board",
          "Trade Value": 7362809
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94411,
          "HS4": "Wood Fiberboard",
          "Trade Value": 2946487
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94412,
          "HS4": "Plywood",
          "Trade Value": 74352500
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94413,
          "HS4": "Densified Wood",
          "Trade Value": 253765
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94414,
          "HS4": "Wood Frames",
          "Trade Value": 800436
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94415,
          "HS4": "Wood Crates",
          "Trade Value": 5088521
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94416,
          "HS4": "Wood Barrels",
          "Trade Value": 2511341
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94417,
          "HS4": "Wooden Tool Handles",
          "Trade Value": 2608233
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94418,
          "HS4": "Wood Carpentry",
          "Trade Value": 19722507
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94419,
          "HS4": "Wood Kitchenware",
          "Trade Value": 13043083
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94420,
          "HS4": "Wood Ornaments",
          "Trade Value": 7579211
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 944,
          "HS2": "Wood, wood articles, & charcoal",
          "HS4 ID": 94421,
          "HS4": "Other Wood Articles",
          "Trade Value": 20577804
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 945,
          "HS2": "Cork & cork articles",
          "HS4 ID": 94501,
          "HS4": "Raw Cork",
          "Trade Value": 198548
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 945,
          "HS2": "Cork & cork articles",
          "HS4 ID": 94502,
          "HS4": "Debacked Cork",
          "Trade Value": 337974.00000000006
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 945,
          "HS2": "Cork & cork articles",
          "HS4 ID": 94503,
          "HS4": "Natural Cork Articles",
          "Trade Value": 794510
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 945,
          "HS2": "Cork & cork articles",
          "HS4 ID": 94504,
          "HS4": "Agglomerated Cork",
          "Trade Value": 2433869
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 946,
          "HS2": "Straw & esparto manufactures",
          "HS4 ID": 94601,
          "HS4": "Plaiting Products",
          "Trade Value": 2368476
        },
        {
          "Section ID": 9,
          "Section": "Wood Products",
          "HS2 ID": 946,
          "HS2": "Straw & esparto manufactures",
          "HS4 ID": 94602,
          "HS4": "Basketwork",
          "Trade Value": 1621163
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1047,
          "HS2": "Wood pulp & paper scrap",
          "HS4 ID": 104701,
          "HS4": "Mechanical Wood Pulp",
          "Trade Value": 71867
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1047,
          "HS2": "Wood pulp & paper scrap",
          "HS4 ID": 104702,
          "HS4": "Dissolving Grades Chemical Woodpulp",
          "Trade Value": 100035165
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1047,
          "HS2": "Wood pulp & paper scrap",
          "HS4 ID": 104703,
          "HS4": "Sulfate Chemical Woodpulp",
          "Trade Value": 161156142
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1047,
          "HS2": "Wood pulp & paper scrap",
          "HS4 ID": 104704,
          "HS4": "Sulfite Chemical Woodpulp",
          "Trade Value": 193950
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1047,
          "HS2": "Wood pulp & paper scrap",
          "HS4 ID": 104705,
          "HS4": "Semi chemical Woodpulp",
          "Trade Value": 98819
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1047,
          "HS2": "Wood pulp & paper scrap",
          "HS4 ID": 104706,
          "HS4": "Recovered Paper Pulp",
          "Trade Value": 2421826
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1047,
          "HS2": "Wood pulp & paper scrap",
          "HS4 ID": 104707,
          "HS4": "Recovered Paper",
          "Trade Value": 512679957
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104801,
          "HS4": "Newsprint",
          "Trade Value": 12706499
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104802,
          "HS4": "Uncoated Paper",
          "Trade Value": 176096975
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104803,
          "HS4": "Tissue",
          "Trade Value": 25157943
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104804,
          "HS4": "Kraft Paper",
          "Trade Value": 417897352
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104805,
          "HS4": "Other Uncoated Paper",
          "Trade Value": 600830471
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104806,
          "HS4": "Vegetable Parchment",
          "Trade Value": 23247851
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104807,
          "HS4": "Composite Paper",
          "Trade Value": 1362501
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104808,
          "HS4": "Corrugated Paper",
          "Trade Value": 12089649
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104809,
          "HS4": "Carbon Paper",
          "Trade Value": 18726073
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104810,
          "HS4": "Kaolin Coated Paper",
          "Trade Value": 491970477
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104811,
          "HS4": "Cellulose Fibers Paper",
          "Trade Value": 696584659
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104812,
          "HS4": "Paper Pulp Filter Blocks",
          "Trade Value": 1056272
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104813,
          "HS4": "Cigarette Paper",
          "Trade Value": 1242947
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104814,
          "HS4": "Wallpaper",
          "Trade Value": 25873534
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104816,
          "HS4": "Other Carbon Paper",
          "Trade Value": 20480215
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104817,
          "HS4": "Letter Stock",
          "Trade Value": 3333508
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104818,
          "HS4": "Toilet Paper",
          "Trade Value": 903185591
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104819,
          "HS4": "Paper Containers",
          "Trade Value": 74743257
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104820,
          "HS4": "Paper Notebooks",
          "Trade Value": 31118022
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104821,
          "HS4": "Paper Labels",
          "Trade Value": 70987879
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104822,
          "HS4": "Paper Spools",
          "Trade Value": 3380436
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1048,
          "HS2": "Paper articles",
          "HS4 ID": 104823,
          "HS4": "Shaped Paper",
          "Trade Value": 282681049
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1049,
          "HS2": "Books, newspapers, & other prints",
          "HS4 ID": 104901,
          "HS4": "Brochures",
          "Trade Value": 126469755
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1049,
          "HS2": "Books, newspapers, & other prints",
          "HS4 ID": 104902,
          "HS4": "Newspapers",
          "Trade Value": 16770504
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1049,
          "HS2": "Books, newspapers, & other prints",
          "HS4 ID": 104903,
          "HS4": "Children's Picture Books",
          "Trade Value": 2474552
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1049,
          "HS2": "Books, newspapers, & other prints",
          "HS4 ID": 104904,
          "HS4": "Sheet Music",
          "Trade Value": 1925341
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1049,
          "HS2": "Books, newspapers, & other prints",
          "HS4 ID": 104905,
          "HS4": "Maps",
          "Trade Value": 796736
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1049,
          "HS2": "Books, newspapers, & other prints",
          "HS4 ID": 104906,
          "HS4": "Architectural Plans",
          "Trade Value": 3375007
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1049,
          "HS2": "Books, newspapers, & other prints",
          "HS4 ID": 104907,
          "HS4": "Documents of title (bonds etc) and unused stamps",
          "Trade Value": 7701673
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1049,
          "HS2": "Books, newspapers, & other prints",
          "HS4 ID": 104908,
          "HS4": "Decals",
          "Trade Value": 114120994
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1049,
          "HS2": "Books, newspapers, & other prints",
          "HS4 ID": 104909,
          "HS4": "Postcards",
          "Trade Value": 3241038
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1049,
          "HS2": "Books, newspapers, & other prints",
          "HS4 ID": 104910,
          "HS4": "Calendars",
          "Trade Value": 3653544
        },
        {
          "Section ID": 10,
          "Section": "Paper Goods",
          "HS2 ID": 1049,
          "HS2": "Books, newspapers, & other prints",
          "HS4 ID": 104911,
          "HS4": "Other Printed Material",
          "Trade Value": 123455212
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1150,
          "HS2": "Silk",
          "HS4 ID": 115001,
          "HS4": "Silk-worm Cocoons",
          "Trade Value": 7271
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1150,
          "HS2": "Silk",
          "HS4 ID": 115002,
          "HS4": "Raw Silk",
          "Trade Value": 147182.99999999997
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1150,
          "HS2": "Silk",
          "HS4 ID": 115003,
          "HS4": "Silk Waste",
          "Trade Value": 250821
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1150,
          "HS2": "Silk",
          "HS4 ID": 115004,
          "HS4": "Non-Retail Silk Yarn",
          "Trade Value": 1104193
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1150,
          "HS2": "Silk",
          "HS4 ID": 115005,
          "HS4": "Silk Waste Yarn",
          "Trade Value": 1592682
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1150,
          "HS2": "Silk",
          "HS4 ID": 115006,
          "HS4": "Retail Silk Yarn",
          "Trade Value": 659678
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1150,
          "HS2": "Silk",
          "HS4 ID": 115007,
          "HS4": "Silk Fabrics",
          "Trade Value": 34866306
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1151,
          "HS2": "Wool & animal hair",
          "HS4 ID": 115101,
          "HS4": "Wool",
          "Trade Value": 94721
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1151,
          "HS2": "Wool & animal hair",
          "HS4 ID": 115102,
          "HS4": "Animal Hair",
          "Trade Value": 4239
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1151,
          "HS2": "Wool & animal hair",
          "HS4 ID": 115104,
          "HS4": "Garnetted Wool or Animal Hair",
          "Trade Value": 3
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1151,
          "HS2": "Wool & animal hair",
          "HS4 ID": 115105,
          "HS4": "Prepared Wool or Animal Hair",
          "Trade Value": 686295
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1151,
          "HS2": "Wool & animal hair",
          "HS4 ID": 115106,
          "HS4": "Non-Retail Carded Wool Yarn",
          "Trade Value": 1400738
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1151,
          "HS2": "Wool & animal hair",
          "HS4 ID": 115107,
          "HS4": "Non-Retail Combed Wool Yarn",
          "Trade Value": 5417063
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1151,
          "HS2": "Wool & animal hair",
          "HS4 ID": 115108,
          "HS4": "Non-Retail Animal Hair Yarn",
          "Trade Value": 1978112
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1151,
          "HS2": "Wool & animal hair",
          "HS4 ID": 115109,
          "HS4": "Retail Wool or Animal Hair Yarn",
          "Trade Value": 2912545
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1151,
          "HS2": "Wool & animal hair",
          "HS4 ID": 115110,
          "HS4": "Horsehair Yarn",
          "Trade Value": 285567
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1151,
          "HS2": "Wool & animal hair",
          "HS4 ID": 115111,
          "HS4": "Carded Wool or Animal Hair Fabric",
          "Trade Value": 43801465
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1151,
          "HS2": "Wool & animal hair",
          "HS4 ID": 115112,
          "HS4": "Combed Wool or Animal Hair Fabric",
          "Trade Value": 53897085
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1151,
          "HS2": "Wool & animal hair",
          "HS4 ID": 115113,
          "HS4": "Horsehair Fabric",
          "Trade Value": 53871
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1152,
          "HS2": "Cotton",
          "HS4 ID": 115201,
          "HS4": "Raw Cotton",
          "Trade Value": 2224545
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1152,
          "HS2": "Cotton",
          "HS4 ID": 115202,
          "HS4": "Cotton Waste",
          "Trade Value": 2115937
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1152,
          "HS2": "Cotton",
          "HS4 ID": 115203,
          "HS4": "Prepared Cotton",
          "Trade Value": 5867102
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1152,
          "HS2": "Cotton",
          "HS4 ID": 115204,
          "HS4": "Cotton Sewing Thread",
          "Trade Value": 2750299
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1152,
          "HS2": "Cotton",
          "HS4 ID": 115205,
          "HS4": "Non-Retail Pure Cotton Yarn",
          "Trade Value": 9309987
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1152,
          "HS2": "Cotton",
          "HS4 ID": 115206,
          "HS4": "Non-Retail Mixed Cotton Yarn",
          "Trade Value": 4708633
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1152,
          "HS2": "Cotton",
          "HS4 ID": 115207,
          "HS4": "Retail Cotton Yarn",
          "Trade Value": 1533244
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1152,
          "HS2": "Cotton",
          "HS4 ID": 115208,
          "HS4": "Light Pure Woven Cotton",
          "Trade Value": 109411764
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1152,
          "HS2": "Cotton",
          "HS4 ID": 115209,
          "HS4": "Heavy Pure Woven Cotton",
          "Trade Value": 132024932
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1152,
          "HS2": "Cotton",
          "HS4 ID": 115210,
          "HS4": "Light Mixed Woven Cotton",
          "Trade Value": 30016806
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1152,
          "HS2": "Cotton",
          "HS4 ID": 115211,
          "HS4": "Heavy Mixed Woven Cotton",
          "Trade Value": 47585414
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1152,
          "HS2": "Cotton",
          "HS4 ID": 115212,
          "HS4": "Other Cotton Fabrics",
          "Trade Value": 9438115
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1153,
          "HS2": "Vegetable textile fibers, paper yarn & fabrics",
          "HS4 ID": 115301,
          "HS4": "Flax Fibers",
          "Trade Value": 574306
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1153,
          "HS2": "Vegetable textile fibers, paper yarn & fabrics",
          "HS4 ID": 115302,
          "HS4": "Hemp Fibers",
          "Trade Value": 21814
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1153,
          "HS2": "Vegetable textile fibers, paper yarn & fabrics",
          "HS4 ID": 115303,
          "HS4": "Jute and Other Textile Fibers",
          "Trade Value": 640931
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1153,
          "HS2": "Vegetable textile fibers, paper yarn & fabrics",
          "HS4 ID": 115305,
          "HS4": "Coconut and Other Vegetable Fibers",
          "Trade Value": 67812
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1153,
          "HS2": "Vegetable textile fibers, paper yarn & fabrics",
          "HS4 ID": 115306,
          "HS4": "Flax Yarn",
          "Trade Value": 683598
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1153,
          "HS2": "Vegetable textile fibers, paper yarn & fabrics",
          "HS4 ID": 115307,
          "HS4": "Jute Yarn",
          "Trade Value": 14276
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1153,
          "HS2": "Vegetable textile fibers, paper yarn & fabrics",
          "HS4 ID": 115308,
          "HS4": "Other Vegetable Fibers Yarn",
          "Trade Value": 3677860
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1153,
          "HS2": "Vegetable textile fibers, paper yarn & fabrics",
          "HS4 ID": 115309,
          "HS4": "Flax Woven Fabric",
          "Trade Value": 8274449
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1153,
          "HS2": "Vegetable textile fibers, paper yarn & fabrics",
          "HS4 ID": 115310,
          "HS4": "Jute Woven Fabric",
          "Trade Value": 279583
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1153,
          "HS2": "Vegetable textile fibers, paper yarn & fabrics",
          "HS4 ID": 115311,
          "HS4": "Other Vegetable Fibers Fabric",
          "Trade Value": 5209880
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1154,
          "HS2": "Man-made filaments",
          "HS4 ID": 115401,
          "HS4": "Artificial Filament Sewing Thread",
          "Trade Value": 29457171
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1154,
          "HS2": "Man-made filaments",
          "HS4 ID": 115402,
          "HS4": "Non-Retail Synthetic Filament Yarn",
          "Trade Value": 666270851
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1154,
          "HS2": "Man-made filaments",
          "HS4 ID": 115403,
          "HS4": "Non-Retail Artificial Filament Yarn",
          "Trade Value": 176525520
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1154,
          "HS2": "Man-made filaments",
          "HS4 ID": 115404,
          "HS4": "Synthetic Monofilament",
          "Trade Value": 136072233
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1154,
          "HS2": "Man-made filaments",
          "HS4 ID": 115405,
          "HS4": "Artificial Monofilament",
          "Trade Value": 5136924
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1154,
          "HS2": "Man-made filaments",
          "HS4 ID": 115406,
          "HS4": "Retail Artificial Filament Yarn",
          "Trade Value": 7170166
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1154,
          "HS2": "Man-made filaments",
          "HS4 ID": 115407,
          "HS4": "Synthetic Filament Yarn Woven Fabric",
          "Trade Value": 842613012
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1154,
          "HS2": "Man-made filaments",
          "HS4 ID": 115408,
          "HS4": "Artificial Filament Yarn Woven Fabric",
          "Trade Value": 128063715
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115501,
          "HS4": "Synthetic Filament Tow",
          "Trade Value": 342291046
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115502,
          "HS4": "Artificial Filament Tow",
          "Trade Value": 440285765
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115503,
          "HS4": "Unprocessed Synthetic Staple Fibers",
          "Trade Value": 394067742
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115504,
          "HS4": "Unprocessed Artificial Staple Fibers",
          "Trade Value": 41250433
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115505,
          "HS4": "Artificial Fibers Waste",
          "Trade Value": 12028109.999999998
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115506,
          "HS4": "Processed Synthetic Staple Fibers",
          "Trade Value": 42809186
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115507,
          "HS4": "Processed Artificial Staple Fibers",
          "Trade Value": 495864.00000000006
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115508,
          "HS4": "Non-Retail Artificial Staple Fibers Sewing Thread",
          "Trade Value": 5131901
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115509,
          "HS4": "Non-Retail Synthetic Staple Fibers Yarn",
          "Trade Value": 23333576
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115510,
          "HS4": "Non-Retail Artificial Staple Fibers Yarn",
          "Trade Value": 2505373
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115511,
          "HS4": "Retail Artificial Staple Fibers Yarn",
          "Trade Value": 855110
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115512,
          "HS4": "Synthetic Fabrics",
          "Trade Value": 72684689
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115513,
          "HS4": "Light Synthetic Cotton Fabrics",
          "Trade Value": 40647851
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115514,
          "HS4": "Heavy Synthetic Cotton Fabrics",
          "Trade Value": 54430278
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115515,
          "HS4": "Other Synthetic Fabrics",
          "Trade Value": 145098765
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1155,
          "HS2": "Man-made staple fibres",
          "HS4 ID": 115516,
          "HS4": "Woven Fabric of Synthetic Staple Fibers",
          "Trade Value": 12935208
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1156,
          "HS2": "Wadding, felt, nonwovens, twin, ropes, & articles thereof",
          "HS4 ID": 115601,
          "HS4": "Wadding",
          "Trade Value": 146964970
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1156,
          "HS2": "Wadding, felt, nonwovens, twin, ropes, & articles thereof",
          "HS4 ID": 115602,
          "HS4": "Felt",
          "Trade Value": 24056197
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1156,
          "HS2": "Wadding, felt, nonwovens, twin, ropes, & articles thereof",
          "HS4 ID": 115603,
          "HS4": "Non-woven Textiles",
          "Trade Value": 912923810
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1156,
          "HS2": "Wadding, felt, nonwovens, twin, ropes, & articles thereof",
          "HS4 ID": 115604,
          "HS4": "Rubber Textiles",
          "Trade Value": 19945509
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1156,
          "HS2": "Wadding, felt, nonwovens, twin, ropes, & articles thereof",
          "HS4 ID": 115605,
          "HS4": "Metallic Yarn",
          "Trade Value": 13395139
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1156,
          "HS2": "Wadding, felt, nonwovens, twin, ropes, & articles thereof",
          "HS4 ID": 115606,
          "HS4": "Gimp Yarn",
          "Trade Value": 11521314
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1156,
          "HS2": "Wadding, felt, nonwovens, twin, ropes, & articles thereof",
          "HS4 ID": 115607,
          "HS4": "Twine and Rope",
          "Trade Value": 39174011
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1156,
          "HS2": "Wadding, felt, nonwovens, twin, ropes, & articles thereof",
          "HS4 ID": 115608,
          "HS4": "Netting",
          "Trade Value": 58979704
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1156,
          "HS2": "Wadding, felt, nonwovens, twin, ropes, & articles thereof",
          "HS4 ID": 115609,
          "HS4": "Other Articles of Twine and Rope",
          "Trade Value": 6862270
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1157,
          "HS2": "Carpets & textile floor covers",
          "HS4 ID": 115701,
          "HS4": "Knotted Carpets",
          "Trade Value": 238359
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1157,
          "HS2": "Carpets & textile floor covers",
          "HS4 ID": 115702,
          "HS4": "Hand-Woven Rugs",
          "Trade Value": 2388600
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1157,
          "HS2": "Carpets & textile floor covers",
          "HS4 ID": 115703,
          "HS4": "Tufted Carpets",
          "Trade Value": 34073231
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1157,
          "HS2": "Carpets & textile floor covers",
          "HS4 ID": 115704,
          "HS4": "Felt Carpets",
          "Trade Value": 1404137
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1157,
          "HS2": "Carpets & textile floor covers",
          "HS4 ID": 115705,
          "HS4": "Other Carpets",
          "Trade Value": 3819385
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1158,
          "HS2": "Special woven fabrics and tapestries",
          "HS4 ID": 115801,
          "HS4": "Woven Fabrics",
          "Trade Value": 22149610
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1158,
          "HS2": "Special woven fabrics and tapestries",
          "HS4 ID": 115802,
          "HS4": "Terry Fabric",
          "Trade Value": 2630984
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1158,
          "HS2": "Special woven fabrics and tapestries",
          "HS4 ID": 115803,
          "HS4": "Gauze",
          "Trade Value": 15286454
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1158,
          "HS2": "Special woven fabrics and tapestries",
          "HS4 ID": 115804,
          "HS4": "Tulles and Net Fabric",
          "Trade Value": 9784646
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1158,
          "HS2": "Special woven fabrics and tapestries",
          "HS4 ID": 115805,
          "HS4": "Hand-Woven Tapestries",
          "Trade Value": 117498
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1158,
          "HS2": "Special woven fabrics and tapestries",
          "HS4 ID": 115806,
          "HS4": "Narrow Woven Fabric",
          "Trade Value": 84896513
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1158,
          "HS2": "Special woven fabrics and tapestries",
          "HS4 ID": 115807,
          "HS4": "Labels",
          "Trade Value": 24844731
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1158,
          "HS2": "Special woven fabrics and tapestries",
          "HS4 ID": 115808,
          "HS4": "Ornamental Trimmings",
          "Trade Value": 9119305
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1158,
          "HS2": "Special woven fabrics and tapestries",
          "HS4 ID": 115809,
          "HS4": "Metallic Fabric",
          "Trade Value": 538354
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1158,
          "HS2": "Special woven fabrics and tapestries",
          "HS4 ID": 115810,
          "HS4": "Embroidery",
          "Trade Value": 13073546
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1158,
          "HS2": "Special woven fabrics and tapestries",
          "HS4 ID": 115811,
          "HS4": "Quilted Textiles",
          "Trade Value": 6898953
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1159,
          "HS2": "Coated, covered, & laminated fabrics for industrial use",
          "HS4 ID": 115901,
          "HS4": "Gum Coated Textile Fabric",
          "Trade Value": 7810214.000000001
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1159,
          "HS2": "Coated, covered, & laminated fabrics for industrial use",
          "HS4 ID": 115902,
          "HS4": "Polyamide Fabric",
          "Trade Value": 30434081
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1159,
          "HS2": "Coated, covered, & laminated fabrics for industrial use",
          "HS4 ID": 115903,
          "HS4": "Plastic Coated Textile Fabric",
          "Trade Value": 558923858
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1159,
          "HS2": "Coated, covered, & laminated fabrics for industrial use",
          "HS4 ID": 115904,
          "HS4": "Linoleum",
          "Trade Value": 40826
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1159,
          "HS2": "Coated, covered, & laminated fabrics for industrial use",
          "HS4 ID": 115905,
          "HS4": "Textile Wall Coverings",
          "Trade Value": 1579532
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1159,
          "HS2": "Coated, covered, & laminated fabrics for industrial use",
          "HS4 ID": 115906,
          "HS4": "Rubber Textile Fabric",
          "Trade Value": 58500758
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1159,
          "HS2": "Coated, covered, & laminated fabrics for industrial use",
          "HS4 ID": 115907,
          "HS4": "Coated Textile Fabric",
          "Trade Value": 10840872
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1159,
          "HS2": "Coated, covered, & laminated fabrics for industrial use",
          "HS4 ID": 115908,
          "HS4": "Textile Wicks",
          "Trade Value": 938866
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1159,
          "HS2": "Coated, covered, & laminated fabrics for industrial use",
          "HS4 ID": 115909,
          "HS4": "Hose Piping Textiles",
          "Trade Value": 4649041
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1159,
          "HS2": "Coated, covered, & laminated fabrics for industrial use",
          "HS4 ID": 115910,
          "HS4": "Conveyor Belt Textiles",
          "Trade Value": 37451647
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1159,
          "HS2": "Coated, covered, & laminated fabrics for industrial use",
          "HS4 ID": 115911,
          "HS4": "Textiles for technical uses",
          "Trade Value": 359665687
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1160,
          "HS2": "Knitted fabrics",
          "HS4 ID": 116001,
          "HS4": "Pile Fabric",
          "Trade Value": 18376585
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1160,
          "HS2": "Knitted fabrics",
          "HS4 ID": 116002,
          "HS4": "Light Rubberized Knitted Fabric",
          "Trade Value": 485055114
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116101,
          "HS4": "Knit Men's Coats",
          "Trade Value": 3874990
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116102,
          "HS4": "Knit Women's Coats",
          "Trade Value": 4736095
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116103,
          "HS4": "Knit Men's Suits",
          "Trade Value": 17869873
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116104,
          "HS4": "Knit Women's Suits",
          "Trade Value": 38423806
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116105,
          "HS4": "Knit Men's Shirts",
          "Trade Value": 9644717
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116106,
          "HS4": "Knit Women's Shirts",
          "Trade Value": 10232503
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116107,
          "HS4": "Knit Men's Undergarments",
          "Trade Value": 3219786
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116108,
          "HS4": "Knit Women's Undergarments",
          "Trade Value": 4707031
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116109,
          "HS4": "Knit T-shirts",
          "Trade Value": 90535736
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116110,
          "HS4": "Knit Sweaters",
          "Trade Value": 102482221
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116111,
          "HS4": "Knit Babies' Garments",
          "Trade Value": 2705698
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116112,
          "HS4": "Knit Active Wear",
          "Trade Value": 1328662
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116113,
          "HS4": "Garments of Impregnated Fabric",
          "Trade Value": 724773
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116114,
          "HS4": "Other Knit Garments",
          "Trade Value": 11743419
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116115,
          "HS4": "Knit Socks and Hosiery",
          "Trade Value": 29907314
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116116,
          "HS4": "Knit Gloves",
          "Trade Value": 3677047
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1161,
          "HS2": "Knitted clothing accesories",
          "HS4 ID": 116117,
          "HS4": "Other Knit Clothing Accessories",
          "Trade Value": 9255221
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116201,
          "HS4": "Non-Knit Men's Coats",
          "Trade Value": 28065397
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116202,
          "HS4": "Non-Knit Women's Coats",
          "Trade Value": 31296674
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116203,
          "HS4": "Non-Knit Men's Suits",
          "Trade Value": 76523022
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116204,
          "HS4": "Non-Knit Women's Suits",
          "Trade Value": 124035903
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116205,
          "HS4": "Non-Knit Men's Shirts",
          "Trade Value": 23187284
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116206,
          "HS4": "Non-Knit Women's Shirts",
          "Trade Value": 29195286
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116207,
          "HS4": "Non-Knit Men's Undergarments",
          "Trade Value": 2147054
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116208,
          "HS4": "Non-Knit Women's Undergarments",
          "Trade Value": 4541009
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116209,
          "HS4": "Non-Knit Babies' Garments",
          "Trade Value": 1855011
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116210,
          "HS4": "Felt or Coated Fabric Garments",
          "Trade Value": 8544544
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116211,
          "HS4": "Non-Knit Active Wear",
          "Trade Value": 27863753
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116212,
          "HS4": "Other Women's Undergarments",
          "Trade Value": 13132941
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116213,
          "HS4": "Handkerchiefs",
          "Trade Value": 1549049
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116214,
          "HS4": "Scarves",
          "Trade Value": 7038216
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116215,
          "HS4": "Neck Ties",
          "Trade Value": 780073
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116216,
          "HS4": "Non-Knit Gloves",
          "Trade Value": 1037923
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1162,
          "HS2": "Non-knitted clothing accesories",
          "HS4 ID": 116217,
          "HS4": "Other Non-Knit Clothing Accessories",
          "Trade Value": 8597631
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1163,
          "HS2": "Used clothes & textile articles",
          "HS4 ID": 116301,
          "HS4": "Blankets",
          "Trade Value": 6025188
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1163,
          "HS2": "Used clothes & textile articles",
          "HS4 ID": 116302,
          "HS4": "House Linens",
          "Trade Value": 18960534
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1163,
          "HS2": "Used clothes & textile articles",
          "HS4 ID": 116303,
          "HS4": "Window Dressings",
          "Trade Value": 1783018
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1163,
          "HS2": "Used clothes & textile articles",
          "HS4 ID": 116304,
          "HS4": "Bedspreads",
          "Trade Value": 3216766
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1163,
          "HS2": "Used clothes & textile articles",
          "HS4 ID": 116305,
          "HS4": "Packing Bags",
          "Trade Value": 4932938
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1163,
          "HS2": "Used clothes & textile articles",
          "HS4 ID": 116306,
          "HS4": "Awnings, Tents, and Sails",
          "Trade Value": 7485776
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1163,
          "HS2": "Used clothes & textile articles",
          "HS4 ID": 116307,
          "HS4": "Other Cloth Articles",
          "Trade Value": 170989140
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1163,
          "HS2": "Used clothes & textile articles",
          "HS4 ID": 116308,
          "HS4": "Packaged Sewing Sets",
          "Trade Value": 433948
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1163,
          "HS2": "Used clothes & textile articles",
          "HS4 ID": 116309,
          "HS4": "Used Clothing",
          "Trade Value": 94932716
        },
        {
          "Section ID": 11,
          "Section": "Textiles",
          "HS2 ID": 1163,
          "HS2": "Used clothes & textile articles",
          "HS4 ID": 116310,
          "HS4": "Textile Scraps",
          "Trade Value": 2985357
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1264,
          "HS2": "Footwear",
          "HS4 ID": 126401,
          "HS4": "Waterproof Footwear",
          "Trade Value": 2794403
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1264,
          "HS2": "Footwear",
          "HS4 ID": 126402,
          "HS4": "Rubber Footwear",
          "Trade Value": 27144430
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1264,
          "HS2": "Footwear",
          "HS4 ID": 126403,
          "HS4": "Leather Footwear",
          "Trade Value": 17523470
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1264,
          "HS2": "Footwear",
          "HS4 ID": 126404,
          "HS4": "Textile Footwear",
          "Trade Value": 30926313
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1264,
          "HS2": "Footwear",
          "HS4 ID": 126405,
          "HS4": "Other Footwear",
          "Trade Value": 4494989
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1264,
          "HS2": "Footwear",
          "HS4 ID": 126406,
          "HS4": "Footwear Parts",
          "Trade Value": 18480437
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1265,
          "HS2": "Headgear",
          "HS4 ID": 126501,
          "HS4": "Hat Forms",
          "Trade Value": 48415
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1265,
          "HS2": "Headgear",
          "HS4 ID": 126502,
          "HS4": "Hat Shapes",
          "Trade Value": 43685
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1265,
          "HS2": "Headgear",
          "HS4 ID": 126504,
          "HS4": "Hats",
          "Trade Value": 478813
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1265,
          "HS2": "Headgear",
          "HS4 ID": 126505,
          "HS4": "Knitted Hats",
          "Trade Value": 11913673
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1265,
          "HS2": "Headgear",
          "HS4 ID": 126506,
          "HS4": "Other Headwear",
          "Trade Value": 198401526
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1265,
          "HS2": "Headgear",
          "HS4 ID": 126507,
          "HS4": "Headbands and Linings",
          "Trade Value": 2689808
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1266,
          "HS2": "Umbrellas, walking sticks, & whips",
          "HS4 ID": 126601,
          "HS4": "Umbrellas",
          "Trade Value": 2016355
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1266,
          "HS2": "Umbrellas, walking sticks, & whips",
          "HS4 ID": 126602,
          "HS4": "Walking Sticks",
          "Trade Value": 251329.00000000003
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1266,
          "HS2": "Umbrellas, walking sticks, & whips",
          "HS4 ID": 126603,
          "HS4": "Umbrella and Walking Stick Accessories",
          "Trade Value": 113487
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1267,
          "HS2": "Feather articles, feathers, artificial flowers, & articles of human hair",
          "HS4 ID": 126701,
          "HS4": "Bird Skins and Feathers",
          "Trade Value": 1595079
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1267,
          "HS2": "Feather articles, feathers, artificial flowers, & articles of human hair",
          "HS4 ID": 126702,
          "HS4": "Artificial Vegetation",
          "Trade Value": 1186321
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1267,
          "HS2": "Feather articles, feathers, artificial flowers, & articles of human hair",
          "HS4 ID": 126703,
          "HS4": "Processed Hair",
          "Trade Value": 1832650
        },
        {
          "Section ID": 12,
          "Section": "Footwear and Headwear",
          "HS2 ID": 1267,
          "HS2": "Feather articles, feathers, artificial flowers, & articles of human hair",
          "HS4 ID": 126704,
          "HS4": "Fake Hair",
          "Trade Value": 2366203
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136801,
          "HS4": "Curbstones",
          "Trade Value": 271950
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136802,
          "HS4": "Building Stone",
          "Trade Value": 11660726
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136803,
          "HS4": "Worked Slate",
          "Trade Value": 157366
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136804,
          "HS4": "Milling Stones",
          "Trade Value": 484861543
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136805,
          "HS4": "Abrasive Powder",
          "Trade Value": 254726939
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136806,
          "HS4": "Rock Wool",
          "Trade Value": 407513843
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136807,
          "HS4": "Asphalt",
          "Trade Value": 1367138
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136808,
          "HS4": "Vegetable Fiber",
          "Trade Value": 39920909
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136809,
          "HS4": "Plaster Articles",
          "Trade Value": 12896873
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136810,
          "HS4": "Cement Articles",
          "Trade Value": 40087033
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136811,
          "HS4": "Asbestos Cement Articles",
          "Trade Value": 28717636
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136812,
          "HS4": "Asbestos Fibres",
          "Trade Value": 6777879
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136813,
          "HS4": "Friction Material",
          "Trade Value": 289044857
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136814,
          "HS4": "Processed Mica",
          "Trade Value": 10923717
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1368,
          "HS2": "Stone, plaster, cement, asbestos, mica.",
          "HS4 ID": 136815,
          "HS4": "Other Stone Articles",
          "Trade Value": 1032364999
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1369,
          "HS2": "Ceramic products",
          "HS4 ID": 136901,
          "HS4": "Bricks",
          "Trade Value": 1739061
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1369,
          "HS2": "Ceramic products",
          "HS4 ID": 136902,
          "HS4": "Refractory Bricks",
          "Trade Value": 122840472
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1369,
          "HS2": "Ceramic products",
          "HS4 ID": 136903,
          "HS4": "Refractory Ceramics",
          "Trade Value": 217135398
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1369,
          "HS2": "Ceramic products",
          "HS4 ID": 136904,
          "HS4": "Ceramic Bricks",
          "Trade Value": 1380515
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1369,
          "HS2": "Ceramic products",
          "HS4 ID": 136905,
          "HS4": "Roofing Tiles",
          "Trade Value": 9432221
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1369,
          "HS2": "Ceramic products",
          "HS4 ID": 136906,
          "HS4": "Ceramic Pipes",
          "Trade Value": 6566027
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1369,
          "HS2": "Ceramic products",
          "HS4 ID": 136907,
          "HS4": "Unglazed Ceramics",
          "Trade Value": 34537827
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1369,
          "HS2": "Ceramic products",
          "HS4 ID": 136909,
          "HS4": "Laboratory Ceramic Ware",
          "Trade Value": 1099161479
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1369,
          "HS2": "Ceramic products",
          "HS4 ID": 136910,
          "HS4": "Bathroom Ceramics",
          "Trade Value": 40489677
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1369,
          "HS2": "Ceramic products",
          "HS4 ID": 136911,
          "HS4": "Porcelain Tableware",
          "Trade Value": 99006316
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1369,
          "HS2": "Ceramic products",
          "HS4 ID": 136912,
          "HS4": "Ceramic Tableware",
          "Trade Value": 41208927
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1369,
          "HS2": "Ceramic products",
          "HS4 ID": 136913,
          "HS4": "Ornamental Ceramics",
          "Trade Value": 15769610
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1369,
          "HS2": "Ceramic products",
          "HS4 ID": 136914,
          "HS4": "Other Ceramic Articles",
          "Trade Value": 123470086
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137001,
          "HS4": "Glass Scraps",
          "Trade Value": 39158556
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137002,
          "HS4": "Glass Balls",
          "Trade Value": 185213056
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137003,
          "HS4": "Cast or Rolled Glass",
          "Trade Value": 122511355
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137004,
          "HS4": "Blown Glass",
          "Trade Value": 460119853
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137005,
          "HS4": "Float Glass",
          "Trade Value": 357995083
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137006,
          "HS4": "Glass with Edge Workings",
          "Trade Value": 624456374
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137007,
          "HS4": "Safety Glass",
          "Trade Value": 153602642
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137008,
          "HS4": "Insulating Glass",
          "Trade Value": 9360350
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137009,
          "HS4": "Glass Mirrors",
          "Trade Value": 90837507
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137010,
          "HS4": "Glass Bottles",
          "Trade Value": 71106573
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137011,
          "HS4": "Glass Bulbs",
          "Trade Value": 8255529
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137013,
          "HS4": "Interior Decorative Glassware",
          "Trade Value": 43144362
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137014,
          "HS4": "Signaling Glassware",
          "Trade Value": 97810706
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137015,
          "HS4": "Eyewear and Clock Glass",
          "Trade Value": 1714630
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137016,
          "HS4": "Glass Bricks",
          "Trade Value": 3120696
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137017,
          "HS4": "Laboratory Glassware",
          "Trade Value": 115547984
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137018,
          "HS4": "Glass Beads",
          "Trade Value": 38076572
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137019,
          "HS4": "Glass Fibers",
          "Trade Value": 444751509
        },
        {
          "Section ID": 13,
          "Section": "Stone And Glass",
          "HS2 ID": 1370,
          "HS2": "Glass & glassware",
          "HS4 ID": 137020,
          "HS4": "Other Glass Articles",
          "Trade Value": 505094919
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147101,
          "HS4": "Pearls",
          "Trade Value": 164478699
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147102,
          "HS4": "Diamonds",
          "Trade Value": 151770345
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147103,
          "HS4": "Precious Stones",
          "Trade Value": 54491035.00000001
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147104,
          "HS4": "Synthetic Reconstructed Jewellery Stones",
          "Trade Value": 49734330
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147105,
          "HS4": "Precious Stone Dust",
          "Trade Value": 5118701
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147106,
          "HS4": "Silver",
          "Trade Value": 2073000545
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147107,
          "HS4": "Silver Clad Metals",
          "Trade Value": 30112730
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147108,
          "HS4": "Gold",
          "Trade Value": 7967646255
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147109,
          "HS4": "Gold Clad Metals",
          "Trade Value": 8545564
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147110,
          "HS4": "Platinum",
          "Trade Value": 1808952530
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147111,
          "HS4": "Platinum Clad Metals",
          "Trade Value": 21187112
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147112,
          "HS4": "Precious Metal Scraps",
          "Trade Value": 382198928
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147113,
          "HS4": "Jewellery",
          "Trade Value": 953381311
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147114,
          "HS4": "Metal-Clad Products",
          "Trade Value": 75923598
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147115,
          "HS4": "Other Precious Metal Products",
          "Trade Value": 605067719
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147116,
          "HS4": "Pearl Products",
          "Trade Value": 48551089
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147117,
          "HS4": "Imitation Jewellery",
          "Trade Value": 37375616
        },
        {
          "Section ID": 14,
          "Section": "Precious Metals",
          "HS2 ID": 1471,
          "HS2": "Precious stones, metals, & pearls",
          "HS4 ID": 147118,
          "HS4": "Coin",
          "Trade Value": 282522
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157201,
          "HS4": "Pig Iron",
          "Trade Value": 26132770
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157202,
          "HS4": "Ferroalloys",
          "Trade Value": 414468429
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157203,
          "HS4": "Iron Reductions",
          "Trade Value": 1951440
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157204,
          "HS4": "Scrap Iron",
          "Trade Value": 3767199321
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157205,
          "HS4": "Iron Powder",
          "Trade Value": 276961580
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157206,
          "HS4": "Iron Ingots",
          "Trade Value": 1945602
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157207,
          "HS4": "Semi-Finished Iron",
          "Trade Value": 2050460765
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157208,
          "HS4": "Hot-Rolled Iron",
          "Trade Value": 10850115893
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157209,
          "HS4": "Cold-Rolled Iron",
          "Trade Value": 1757189654
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157210,
          "HS4": "Coated Flat-Rolled Iron",
          "Trade Value": 2864214685
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157211,
          "HS4": "Large Flat-Rolled Iron",
          "Trade Value": 97631429
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157212,
          "HS4": "Large Coated Flat-Rolled Iron",
          "Trade Value": 210057360
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157213,
          "HS4": "Hot-Rolled Iron Bars",
          "Trade Value": 709823069
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157214,
          "HS4": "Raw Iron Bars",
          "Trade Value": 725821845
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157215,
          "HS4": "Other Iron Bars",
          "Trade Value": 61944728
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157216,
          "HS4": "Iron Blocks",
          "Trade Value": 454724574
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157217,
          "HS4": "Iron Wire",
          "Trade Value": 187468677
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157218,
          "HS4": "Stainless Steel Ingots",
          "Trade Value": 6343842
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157219,
          "HS4": "Large Flat-Rolled Stainless Steel",
          "Trade Value": 1090900627
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157220,
          "HS4": "Flat-Rolled Stainless Steel",
          "Trade Value": 480748972
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157221,
          "HS4": "Hot-Rolled Stainless Steel Bars",
          "Trade Value": 280560683
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157222,
          "HS4": "Other Stainless Steel Bars",
          "Trade Value": 323489069
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157223,
          "HS4": "Stainless Steel Wire",
          "Trade Value": 176967338
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157224,
          "HS4": "Steel Ingots",
          "Trade Value": 266694687
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157225,
          "HS4": "Flat Flat-Rolled Steel",
          "Trade Value": 5584993736
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157226,
          "HS4": "Flat-Rolled Iron",
          "Trade Value": 359771277
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157227,
          "HS4": "Steel Bars",
          "Trade Value": 686903863
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157228,
          "HS4": "Other Steel Bars",
          "Trade Value": 1030691481
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1572,
          "HS2": "Iron & steel",
          "HS4 ID": 157229,
          "HS4": "Steel Wire",
          "Trade Value": 303058618
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157301,
          "HS4": "Iron Sheet Piling",
          "Trade Value": 90615020
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157302,
          "HS4": "Iron Railway Products",
          "Trade Value": 275155488
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157303,
          "HS4": "Cast Iron Pipes",
          "Trade Value": 6770383
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157304,
          "HS4": "Iron Pipes",
          "Trade Value": 2060616453
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157305,
          "HS4": "Other Large Iron Pipes",
          "Trade Value": 316893564
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157306,
          "HS4": "Other Small Iron Pipes",
          "Trade Value": 495662004
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157307,
          "HS4": "Iron Pipe Fittings",
          "Trade Value": 443962775
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157308,
          "HS4": "Iron Structures",
          "Trade Value": 213827472
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157309,
          "HS4": "Large Iron Containers",
          "Trade Value": 54334444
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157310,
          "HS4": "Small Iron Containers",
          "Trade Value": 67369365
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157311,
          "HS4": "Iron Gas Containers",
          "Trade Value": 47374211
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157312,
          "HS4": "Stranded Iron Wire",
          "Trade Value": 148492715
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157313,
          "HS4": "Barbed Wire",
          "Trade Value": 175607
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157314,
          "HS4": "Iron Cloth",
          "Trade Value": 104446290
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157315,
          "HS4": "Iron Chains",
          "Trade Value": 521465366
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157316,
          "HS4": "Iron Anchors",
          "Trade Value": 905012
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157317,
          "HS4": "Iron Nails",
          "Trade Value": 16972950
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157318,
          "HS4": "Iron Fasteners",
          "Trade Value": 3065610729
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157319,
          "HS4": "Iron Sewing Needles",
          "Trade Value": 28309015
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157320,
          "HS4": "Iron Springs",
          "Trade Value": 438743567
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157321,
          "HS4": "Iron Stovetops",
          "Trade Value": 113099827
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157322,
          "HS4": "Iron Radiators",
          "Trade Value": 42083470
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157323,
          "HS4": "Iron Housewares",
          "Trade Value": 67451741
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157324,
          "HS4": "Iron Toiletry",
          "Trade Value": 17382575
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157325,
          "HS4": "Other Cast Iron Products",
          "Trade Value": 46078570
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1573,
          "HS2": "Iron or steel articles",
          "HS4 ID": 157326,
          "HS4": "Other Iron Products",
          "Trade Value": 1563771320
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157401,
          "HS4": "Precipitated Copper",
          "Trade Value": 10867073
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157402,
          "HS4": "Raw Copper",
          "Trade Value": 4590687
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157403,
          "HS4": "Refined Copper",
          "Trade Value": 5711083651
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157404,
          "HS4": "Scrap Copper",
          "Trade Value": 2334901248
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157405,
          "HS4": "Copper Alloys",
          "Trade Value": 3725633
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157406,
          "HS4": "Copper Powder",
          "Trade Value": 88168655
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157407,
          "HS4": "Copper Bars",
          "Trade Value": 318196225
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157408,
          "HS4": "Copper Wire",
          "Trade Value": 488938671
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157409,
          "HS4": "Copper Plating",
          "Trade Value": 1575996393
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157410,
          "HS4": "Copper Foil",
          "Trade Value": 1593791925
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157411,
          "HS4": "Copper Pipes",
          "Trade Value": 83114383
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157412,
          "HS4": "Copper Pipe Fittings",
          "Trade Value": 94034683
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157413,
          "HS4": "Stranded Copper Wire",
          "Trade Value": 22284788
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157415,
          "HS4": "Copper Fasteners",
          "Trade Value": 31928034
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157418,
          "HS4": "Copper Housewares",
          "Trade Value": 6652083
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1574,
          "HS2": "Copper articles",
          "HS4 ID": 157419,
          "HS4": "Other Copper Products",
          "Trade Value": 300872496
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1575,
          "HS2": "Nickel articles",
          "HS4 ID": 157501,
          "HS4": "Nickel Mattes",
          "Trade Value": 13254846
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1575,
          "HS2": "Nickel articles",
          "HS4 ID": 157502,
          "HS4": "Raw Nickel",
          "Trade Value": 333645960
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1575,
          "HS2": "Nickel articles",
          "HS4 ID": 157503,
          "HS4": "Scrap Nickel",
          "Trade Value": 43342971
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1575,
          "HS2": "Nickel articles",
          "HS4 ID": 157504,
          "HS4": "Nickel Powder",
          "Trade Value": 152738968
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1575,
          "HS2": "Nickel articles",
          "HS4 ID": 157505,
          "HS4": "Nickel Bars",
          "Trade Value": 69833839
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1575,
          "HS2": "Nickel articles",
          "HS4 ID": 157506,
          "HS4": "Nickel Sheets",
          "Trade Value": 191234449
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1575,
          "HS2": "Nickel articles",
          "HS4 ID": 157507,
          "HS4": "Nickel Pipes",
          "Trade Value": 62226145
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1575,
          "HS2": "Nickel articles",
          "HS4 ID": 157508,
          "HS4": "Other Nickel Products",
          "Trade Value": 79156490
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157601,
          "HS4": "Raw Aluminium",
          "Trade Value": 99399147
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157602,
          "HS4": "Scrap Aluminium",
          "Trade Value": 574058526
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157603,
          "HS4": "Aluminium Powder",
          "Trade Value": 5769575
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157604,
          "HS4": "Aluminium Bars",
          "Trade Value": 120953484
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157605,
          "HS4": "Aluminium Wire",
          "Trade Value": 47324098
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157606,
          "HS4": "Aluminium Plating",
          "Trade Value": 785923069
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157607,
          "HS4": "Aluminium Foil",
          "Trade Value": 919904235
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157608,
          "HS4": "Aluminium Pipes",
          "Trade Value": 52553870
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157609,
          "HS4": "Aluminium Pipe Fittings",
          "Trade Value": 14085341
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157610,
          "HS4": "Aluminium Structures",
          "Trade Value": 30996106
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157611,
          "HS4": "Large Aluminium Containers",
          "Trade Value": 1517706
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157612,
          "HS4": "Aluminium Cans",
          "Trade Value": 24717034
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157613,
          "HS4": "Aluminium Gas Containers",
          "Trade Value": 15349793
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157614,
          "HS4": "Stranded Aluminium Wire",
          "Trade Value": 40736576
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157615,
          "HS4": "Aluminium Housewares",
          "Trade Value": 6640803
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1576,
          "HS2": "Aluminum articles",
          "HS4 ID": 157616,
          "HS4": "Other Aluminium Products",
          "Trade Value": 447163856
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1578,
          "HS2": "Lead articles",
          "HS4 ID": 157801,
          "HS4": "Raw Lead",
          "Trade Value": 118563098
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1578,
          "HS2": "Lead articles",
          "HS4 ID": 157802,
          "HS4": "Scrap Lead",
          "Trade Value": 7596165
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1578,
          "HS2": "Lead articles",
          "HS4 ID": 157804,
          "HS4": "Lead Sheets",
          "Trade Value": 11851362
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1578,
          "HS2": "Lead articles",
          "HS4 ID": 157806,
          "HS4": "Other Lead Products",
          "Trade Value": 5779196
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1579,
          "HS2": "Zinc articles",
          "HS4 ID": 157901,
          "HS4": "Raw Zinc",
          "Trade Value": 414661246
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1579,
          "HS2": "Zinc articles",
          "HS4 ID": 157902,
          "HS4": "Scrap Waste",
          "Trade Value": 7660472
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1579,
          "HS2": "Zinc articles",
          "HS4 ID": 157903,
          "HS4": "Zinc Powder",
          "Trade Value": 17200928
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1579,
          "HS2": "Zinc articles",
          "HS4 ID": 157904,
          "HS4": "Zinc Bars",
          "Trade Value": 1551934
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1579,
          "HS2": "Zinc articles",
          "HS4 ID": 157905,
          "HS4": "Zinc Sheets",
          "Trade Value": 65447932.99999999
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1579,
          "HS2": "Zinc articles",
          "HS4 ID": 157907,
          "HS4": "Other Zinc Products",
          "Trade Value": 8634770
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1580,
          "HS2": "Tin articles",
          "HS4 ID": 158001,
          "HS4": "Raw Tin",
          "Trade Value": 36620415
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1580,
          "HS2": "Tin articles",
          "HS4 ID": 158002,
          "HS4": "Scrap Tin",
          "Trade Value": 2263940
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1580,
          "HS2": "Tin articles",
          "HS4 ID": 158003,
          "HS4": "Tin Bars",
          "Trade Value": 43242401
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1580,
          "HS2": "Tin articles",
          "HS4 ID": 158007,
          "HS4": "Other Tin Products",
          "Trade Value": 68187148
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1581,
          "HS2": "Cermet articles",
          "HS4 ID": 158101,
          "HS4": "Tungsten",
          "Trade Value": 109901653
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1581,
          "HS2": "Cermet articles",
          "HS4 ID": 158102,
          "HS4": "Molybdenum",
          "Trade Value": 48737040
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1581,
          "HS2": "Cermet articles",
          "HS4 ID": 158103,
          "HS4": "Tantalum",
          "Trade Value": 113990771
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1581,
          "HS2": "Cermet articles",
          "HS4 ID": 158104,
          "HS4": "Magnesium",
          "Trade Value": 4520579
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1581,
          "HS2": "Cermet articles",
          "HS4 ID": 158105,
          "HS4": "Cobalt",
          "Trade Value": 150573906
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1581,
          "HS2": "Cermet articles",
          "HS4 ID": 158106,
          "HS4": "Bismuth",
          "Trade Value": 6020235
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1581,
          "HS2": "Cermet articles",
          "HS4 ID": 158107,
          "HS4": "Cadmium",
          "Trade Value": 4435534
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1581,
          "HS2": "Cermet articles",
          "HS4 ID": 158108,
          "HS4": "Titanium",
          "Trade Value": 527935770
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1581,
          "HS2": "Cermet articles",
          "HS4 ID": 158109,
          "HS4": "Zirconium",
          "Trade Value": 13809596
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1581,
          "HS2": "Cermet articles",
          "HS4 ID": 158110,
          "HS4": "Antimony",
          "Trade Value": 3389849
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1581,
          "HS2": "Cermet articles",
          "HS4 ID": 158111,
          "HS4": "Manganese",
          "Trade Value": 8296603
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1581,
          "HS2": "Cermet articles",
          "HS4 ID": 158112,
          "HS4": "Other Metals",
          "Trade Value": 38038207
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1581,
          "HS2": "Cermet articles",
          "HS4 ID": 158113,
          "HS4": "Cermets",
          "Trade Value": 35913034
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158201,
          "HS4": "Garden Tools",
          "Trade Value": 29615973
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158202,
          "HS4": "Hand Saws",
          "Trade Value": 204372113
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158203,
          "HS4": "Hand Tools",
          "Trade Value": 69907890
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158204,
          "HS4": "Wrenches",
          "Trade Value": 61627467
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158205,
          "HS4": "Other Hand Tools",
          "Trade Value": 114098974
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158206,
          "HS4": "Tool Sets",
          "Trade Value": 9531866
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158207,
          "HS4": "Interchangeable Tool Parts",
          "Trade Value": 2028937007
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158208,
          "HS4": "Cutting Blades",
          "Trade Value": 236918967
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158209,
          "HS4": "Tool Plates",
          "Trade Value": 957623211
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158210,
          "HS4": "Cooking Hand Tools",
          "Trade Value": 3561107
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158211,
          "HS4": "Knives",
          "Trade Value": 196989939
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158212,
          "HS4": "Razor Blades",
          "Trade Value": 35624578
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158213,
          "HS4": "Scissors",
          "Trade Value": 30412398
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158214,
          "HS4": "Other Cutlery",
          "Trade Value": 16821076
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1582,
          "HS2": "Tools & cutlery",
          "HS4 ID": 158215,
          "HS4": "Cutlery Sets",
          "Trade Value": 11020546
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1583,
          "HS2": "Miscellaneous metal products",
          "HS4 ID": 158301,
          "HS4": "Padlocks",
          "Trade Value": 169966794
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1583,
          "HS2": "Miscellaneous metal products",
          "HS4 ID": 158302,
          "HS4": "Metal Mountings",
          "Trade Value": 421769819
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1583,
          "HS2": "Miscellaneous metal products",
          "HS4 ID": 158303,
          "HS4": "Safes",
          "Trade Value": 3176233
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1583,
          "HS2": "Miscellaneous metal products",
          "HS4 ID": 158304,
          "HS4": "Filing Cabinets",
          "Trade Value": 1388568
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1583,
          "HS2": "Miscellaneous metal products",
          "HS4 ID": 158305,
          "HS4": "Metal Office Supplies",
          "Trade Value": 23524060
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1583,
          "HS2": "Miscellaneous metal products",
          "HS4 ID": 158306,
          "HS4": "Bells and Other Metal Ornaments",
          "Trade Value": 19593693
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1583,
          "HS2": "Miscellaneous metal products",
          "HS4 ID": 158307,
          "HS4": "Flexible Metal Tubing",
          "Trade Value": 54113134
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1583,
          "HS2": "Miscellaneous metal products",
          "HS4 ID": 158308,
          "HS4": "Other Metal Fasteners",
          "Trade Value": 35089879
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1583,
          "HS2": "Miscellaneous metal products",
          "HS4 ID": 158309,
          "HS4": "Metal Stoppers",
          "Trade Value": 34713938.00000001
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1583,
          "HS2": "Miscellaneous metal products",
          "HS4 ID": 158310,
          "HS4": "Metal Signs",
          "Trade Value": 27144499
        },
        {
          "Section ID": 15,
          "Section": "Metals",
          "HS2 ID": 1583,
          "HS2": "Miscellaneous metal products",
          "HS4 ID": 158311,
          "HS4": "Coated Metal Soldering Products",
          "Trade Value": 152718531
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168401,
          "HS4": "Nuclear Reactors",
          "Trade Value": 89004350
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168402,
          "HS4": "Steam Boilers",
          "Trade Value": 204949939
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168403,
          "HS4": "Central Heating Boilers",
          "Trade Value": 16236276
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168404,
          "HS4": "Boiler Plants",
          "Trade Value": 101910306
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168405,
          "HS4": "Water and Gas Generators",
          "Trade Value": 13918749
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168406,
          "HS4": "Steam Turbines",
          "Trade Value": 646766491
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168407,
          "HS4": "Spark-Ignition Engines",
          "Trade Value": 4985483442
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168408,
          "HS4": "Combustion Engines",
          "Trade Value": 4759039789
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168409,
          "HS4": "Engine Parts",
          "Trade Value": 6644671741
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168410,
          "HS4": "Hydraulic Turbines",
          "Trade Value": 2490453
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168411,
          "HS4": "Gas Turbines",
          "Trade Value": 4421661463
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168412,
          "HS4": "Other Engines",
          "Trade Value": 1631140004
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168413,
          "HS4": "Liquid Pumps",
          "Trade Value": 4254718706
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168414,
          "HS4": "Air Pumps",
          "Trade Value": 5650020919
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168415,
          "HS4": "Air Conditioners",
          "Trade Value": 1341405396
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168416,
          "HS4": "Liquid Fuel Furnaces",
          "Trade Value": 34702078
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168417,
          "HS4": "Industrial Furnaces",
          "Trade Value": 157036887
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168418,
          "HS4": "Refrigerators",
          "Trade Value": 860732432
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168419,
          "HS4": "Other Heating Machinery",
          "Trade Value": 1875394706
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168420,
          "HS4": "Rolling Machines",
          "Trade Value": 167713438
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168421,
          "HS4": "Centrifuges",
          "Trade Value": 3257867663
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168422,
          "HS4": "Washing and Bottling Machines",
          "Trade Value": 826320265
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168423,
          "HS4": "Scales",
          "Trade Value": 293650166
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168424,
          "HS4": "Liquid Dispersing Machines",
          "Trade Value": 487762714
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168425,
          "HS4": "Pulley Systems",
          "Trade Value": 217108372
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168426,
          "HS4": "Cranes",
          "Trade Value": 1050311055
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168427,
          "HS4": "Fork-Lifts",
          "Trade Value": 999704771
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168428,
          "HS4": "Lifting Machinery",
          "Trade Value": 1293217710
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168429,
          "HS4": "Large Construction Vehicles",
          "Trade Value": 11798132444
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168430,
          "HS4": "Other Construction Vehicles",
          "Trade Value": 283319805
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168431,
          "HS4": "Excavation Machinery",
          "Trade Value": 2621826925
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168432,
          "HS4": "Soil Preparation Machinery",
          "Trade Value": 125861744
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168433,
          "HS4": "Harvesting Machinery",
          "Trade Value": 374368214
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168434,
          "HS4": "Dairy Machinery",
          "Trade Value": 3655104
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168435,
          "HS4": "Fruit Pressing Machinery",
          "Trade Value": 3507556
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168436,
          "HS4": "Other Agricultural Machinery",
          "Trade Value": 29059961
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168437,
          "HS4": "Mill Machinery",
          "Trade Value": 13474322
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168438,
          "HS4": "Industrial Food Preperation Machinery",
          "Trade Value": 341681422
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168439,
          "HS4": "Papermaking Machines",
          "Trade Value": 84865587
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168440,
          "HS4": "Book-binding Machines",
          "Trade Value": 66633260
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168441,
          "HS4": "Other Paper Machinery",
          "Trade Value": 290887683
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168442,
          "HS4": "Print Production Machinery",
          "Trade Value": 57981254
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168443,
          "HS4": "Industrial Printers",
          "Trade Value": 9359997083
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168444,
          "HS4": "Artificial Textile Machinery",
          "Trade Value": 81126417
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168445,
          "HS4": "Textile Fiber Machinery",
          "Trade Value": 705128991
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168446,
          "HS4": "Looms",
          "Trade Value": 360545935
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168447,
          "HS4": "Knitting Machines",
          "Trade Value": 426148845
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168448,
          "HS4": "Knitting Machine Accessories",
          "Trade Value": 250452040
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168449,
          "HS4": "Felt Machinery",
          "Trade Value": 23655526
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168450,
          "HS4": "Household Washing Machines",
          "Trade Value": 46527394
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168451,
          "HS4": "Textile Processing Machines",
          "Trade Value": 64366437
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168452,
          "HS4": "Sewing Machines",
          "Trade Value": 450825644
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168453,
          "HS4": "Leather Machinery",
          "Trade Value": 2761342
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168454,
          "HS4": "Casting Machines",
          "Trade Value": 288404768
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168455,
          "HS4": "Metal-Rolling Mills",
          "Trade Value": 273252521
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168456,
          "HS4": "Non-Mechanical Removal Machinery",
          "Trade Value": 1348567005
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168457,
          "HS4": "Metalworking Transfer Machines",
          "Trade Value": 2914229201
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168458,
          "HS4": "Metal Lathes",
          "Trade Value": 1439742545
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168459,
          "HS4": "Drilling Machines",
          "Trade Value": 186430099
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168460,
          "HS4": "Metal Finishing Machines",
          "Trade Value": 570182372
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168461,
          "HS4": "Metalworking Machines",
          "Trade Value": 224570709
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168462,
          "HS4": "Forging Machines",
          "Trade Value": 839981697
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168463,
          "HS4": "Other Non-Metal Removal Machinery",
          "Trade Value": 131244673
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168464,
          "HS4": "Stone Working Machines",
          "Trade Value": 205026060.00000003
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168465,
          "HS4": "Woodworking machines",
          "Trade Value": 175876787
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168466,
          "HS4": "Metalworking Machine Parts",
          "Trade Value": 1615767679
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168467,
          "HS4": "Motor-working Tools",
          "Trade Value": 416777238
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168468,
          "HS4": "Soldering and Welding Machinery",
          "Trade Value": 41232003
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168470,
          "HS4": "Calculators",
          "Trade Value": 55037546
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168471,
          "HS4": "Computers",
          "Trade Value": 1413281005
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168472,
          "HS4": "Other Office Machines",
          "Trade Value": 131790224
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168473,
          "HS4": "Office Machine Parts",
          "Trade Value": 3794562141
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168474,
          "HS4": "Stone Processing Machines",
          "Trade Value": 197929196
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168475,
          "HS4": "Glass Working Machines",
          "Trade Value": 907389866
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168476,
          "HS4": "Vending Machines",
          "Trade Value": 11566444
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168477,
          "HS4": "Rubberworking Machinery",
          "Trade Value": 2590568718
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168478,
          "HS4": "Tobacco Processing Machines",
          "Trade Value": 5370606
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168479,
          "HS4": "Machinery Having Individual Functions",
          "Trade Value": 21984488865
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168480,
          "HS4": "Metal Molds",
          "Trade Value": 1049212929
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168481,
          "HS4": "Valves",
          "Trade Value": 5662717207
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168482,
          "HS4": "Ball Bearings",
          "Trade Value": 4749432797
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168483,
          "HS4": "Transmissions",
          "Trade Value": 5356857159
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168484,
          "HS4": "Gaskets",
          "Trade Value": 431570898
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1684,
          "HS2": "Machinery, mechanical appliances, & parts",
          "HS4 ID": 168485,
          "HS4": "Boat Propellers",
          "Trade Value": 814352314
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168501,
          "HS4": "Electric Motors",
          "Trade Value": 3862038534
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168502,
          "HS4": "Electric Generating Sets",
          "Trade Value": 612108039
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168503,
          "HS4": "Electric Motor Parts",
          "Trade Value": 1656751383
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168504,
          "HS4": "Electrical Transformers",
          "Trade Value": 5438536710
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168505,
          "HS4": "Electromagnets",
          "Trade Value": 1199402292
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168506,
          "HS4": "Batteries",
          "Trade Value": 482096389
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168507,
          "HS4": "Electric Batteries",
          "Trade Value": 5462924147
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168508,
          "HS4": "Vacuum Cleaners",
          "Trade Value": 419960664
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168509,
          "HS4": "Other Domestic Electric Housewares",
          "Trade Value": 90342383
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168510,
          "HS4": "Hair Trimmers",
          "Trade Value": 131851182
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168511,
          "HS4": "Electrical Ignitions",
          "Trade Value": 3602571312
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168512,
          "HS4": "Electrical Lighting and Signalling Equipment",
          "Trade Value": 1552489296
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168513,
          "HS4": "Portable Lighting",
          "Trade Value": 7444853
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168514,
          "HS4": "Electric Furnaces",
          "Trade Value": 482865128
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168515,
          "HS4": "Electric Soldering Equipment",
          "Trade Value": 1179000995
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168516,
          "HS4": "Electric Heaters",
          "Trade Value": 724860161
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168517,
          "HS4": "Telephones",
          "Trade Value": 1869616324
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168518,
          "HS4": "Microphones and Headphones",
          "Trade Value": 404861204
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168519,
          "HS4": "Sound Recording Equipment",
          "Trade Value": 12772275
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168520,
          "HS4": "Dictation Machines",
          "Trade Value": 27735889
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168521,
          "HS4": "Video Recording Equipment",
          "Trade Value": 2863292594
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168522,
          "HS4": "Audio and Video Recording Accessories",
          "Trade Value": 78799140
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168523,
          "HS4": "Blank Audio Media",
          "Trade Value": 2214957491
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168524,
          "HS4": "Sound Recordings",
          "Trade Value": 41657696
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168525,
          "HS4": "Broadcasting Equipment",
          "Trade Value": 1422816920
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168526,
          "HS4": "Navigation Equipment",
          "Trade Value": 755940501
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168527,
          "HS4": "Radio Receivers",
          "Trade Value": 48627147
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168528,
          "HS4": "Video Displays",
          "Trade Value": 836653340
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168529,
          "HS4": "Broadcasting Accessories",
          "Trade Value": 2217840309
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168530,
          "HS4": "Traffic Signals",
          "Trade Value": 89424027
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168531,
          "HS4": "Audio Alarms",
          "Trade Value": 255749614
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168532,
          "HS4": "Electrical Capacitors",
          "Trade Value": 8024595669
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168533,
          "HS4": "Electrical Resistors",
          "Trade Value": 1195847337
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168534,
          "HS4": "Printed Circuit Boards",
          "Trade Value": 3406156931
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168535,
          "HS4": "High-voltage Protection Equipment",
          "Trade Value": 307476003
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168536,
          "HS4": "Low-voltage Protection Equipment",
          "Trade Value": 8402262371
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168537,
          "HS4": "Electrical Control Boards",
          "Trade Value": 3573481801
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168538,
          "HS4": "Electrical Power Accessories",
          "Trade Value": 3084536366
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168539,
          "HS4": "Electric Filament",
          "Trade Value": 534263091
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168540,
          "HS4": "Cathode Tubes",
          "Trade Value": 302649032
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168541,
          "HS4": "Semiconductor Devices",
          "Trade Value": 11067040526
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168542,
          "HS4": "Integrated Circuits",
          "Trade Value": 36046294930
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168543,
          "HS4": "Other Electrical Machinery",
          "Trade Value": 3727475795
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168544,
          "HS4": "Insulated Wire",
          "Trade Value": 2302423649
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168545,
          "HS4": "Carbon-based Electronics",
          "Trade Value": 449619971
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168546,
          "HS4": "Electrical Insulators",
          "Trade Value": 67043279
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168547,
          "HS4": "Metal Insulating Fittings",
          "Trade Value": 841118302
        },
        {
          "Section ID": 16,
          "Section": "Machines",
          "HS2 ID": 1685,
          "HS2": "Electrical machinery and electronics",
          "HS4 ID": 168548,
          "HS4": "Electrical Parts",
          "Trade Value": 945870803
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1786,
          "HS2": "Railway and trams",
          "HS4 ID": 178601,
          "HS4": "Electric Locomotives",
          "Trade Value": 34844127
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1786,
          "HS2": "Railway and trams",
          "HS4 ID": 178602,
          "HS4": "Other Locomotives",
          "Trade Value": 151035
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1786,
          "HS2": "Railway and trams",
          "HS4 ID": 178603,
          "HS4": "Self-Propelled Rail Transport",
          "Trade Value": 241424142
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1786,
          "HS2": "Railway and trams",
          "HS4 ID": 178604,
          "HS4": "Railway Maintenance Vehicles",
          "Trade Value": 168806
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1786,
          "HS2": "Railway and trams",
          "HS4 ID": 178605,
          "HS4": "Railway Passenger Cars",
          "Trade Value": 125818173
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1786,
          "HS2": "Railway and trams",
          "HS4 ID": 178606,
          "HS4": "Railway Freight Cars",
          "Trade Value": 3318107.9999999995
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1786,
          "HS2": "Railway and trams",
          "HS4 ID": 178607,
          "HS4": "Locomotive Parts",
          "Trade Value": 199647144
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1786,
          "HS2": "Railway and trams",
          "HS4 ID": 178608,
          "HS4": "Railway Track Fixtures",
          "Trade Value": 21330258
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1786,
          "HS2": "Railway and trams",
          "HS4 ID": 178609,
          "HS4": "Railway Cargo Containers",
          "Trade Value": 28808809
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178701,
          "HS4": "Tractors",
          "Trade Value": 2374977887
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178702,
          "HS4": "Buses",
          "Trade Value": 1736862742
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178703,
          "HS4": "Cars",
          "Trade Value": 88580735511
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178704,
          "HS4": "Delivery Trucks",
          "Trade Value": 9965749015
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178705,
          "HS4": "Special purpose motor vehicles",
          "Trade Value": 145618715
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178706,
          "HS4": "Motor vehicle (8701 to 8705) chassis fitted with engine",
          "Trade Value": 233856124
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178707,
          "HS4": "Vehicle Bodies (including cabs) for the motor vehicles (8701 to 8705)",
          "Trade Value": 328901143
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178708,
          "HS4": "Motor vehicles; parts and accessories (8701 to 8705)",
          "Trade Value": 33418414897
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178709,
          "HS4": "Work Trucks",
          "Trade Value": 28631986
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178710,
          "HS4": "Tanks and Armored vehicles",
          "Trade Value": 2852454
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178711,
          "HS4": "Motorcycles and cycles",
          "Trade Value": 2874658277
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178712,
          "HS4": "Bicycles, delivery tricycles, other cycles",
          "Trade Value": 46026818
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178713,
          "HS4": "Wheelchairs",
          "Trade Value": 4051654
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178714,
          "HS4": "Bi-Wheel Vehicle Parts",
          "Trade Value": 2338417502
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178715,
          "HS4": "Baby Carriages",
          "Trade Value": 828009
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1787,
          "HS2": "Cars, tractors, trucks & parts thereof.",
          "HS4 ID": 178716,
          "HS4": "Trailers and semi-trailers, not mechanically propelled vehicles",
          "Trade Value": 59389658
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1788,
          "HS2": "Aircraft and spacecraft",
          "HS4 ID": 178801,
          "HS4": "Non-powered Aircraft",
          "Trade Value": 4745989
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1788,
          "HS2": "Aircraft and spacecraft",
          "HS4 ID": 178802,
          "HS4": "Planes, Helicopters, and/or Spacecraft",
          "Trade Value": 100213996
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1788,
          "HS2": "Aircraft and spacecraft",
          "HS4 ID": 178803,
          "HS4": "Aircraft Parts",
          "Trade Value": 1733914695
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1788,
          "HS2": "Aircraft and spacecraft",
          "HS4 ID": 178804,
          "HS4": "Parachutes",
          "Trade Value": 247024
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1788,
          "HS2": "Aircraft and spacecraft",
          "HS4 ID": 178805,
          "HS4": "Aircraft Launch Gear",
          "Trade Value": 935911
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1789,
          "HS2": "Ships, boats, & floating structures",
          "HS4 ID": 178901,
          "HS4": "Passenger and Cargo Ships",
          "Trade Value": 10165111111
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1789,
          "HS2": "Ships, boats, & floating structures",
          "HS4 ID": 178902,
          "HS4": "Fishing Ships",
          "Trade Value": 20731227
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1789,
          "HS2": "Ships, boats, & floating structures",
          "HS4 ID": 178903,
          "HS4": "Recreational Boats",
          "Trade Value": 33495549
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1789,
          "HS2": "Ships, boats, & floating structures",
          "HS4 ID": 178904,
          "HS4": "Tug Boats",
          "Trade Value": 76197808
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1789,
          "HS2": "Ships, boats, & floating structures",
          "HS4 ID": 178905,
          "HS4": "Special Purpose Ships",
          "Trade Value": 354693977.99999994
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1789,
          "HS2": "Ships, boats, & floating structures",
          "HS4 ID": 178906,
          "HS4": "Other Sea Vessels",
          "Trade Value": 108922354
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1789,
          "HS2": "Ships, boats, & floating structures",
          "HS4 ID": 178907,
          "HS4": "Other Floating Structures",
          "Trade Value": 2922118
        },
        {
          "Section ID": 17,
          "Section": "Transportation",
          "HS2 ID": 1789,
          "HS2": "Ships, boats, & floating structures",
          "HS4 ID": 178908,
          "HS4": "Scrap Vessels",
          "Trade Value": 230316752
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189001,
          "HS4": "Optical Fibers and optical fibre bundles",
          "Trade Value": 4189509617
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189002,
          "HS4": "Mirrors and Lenses",
          "Trade Value": 2161020874
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189003,
          "HS4": "Eyewear Frames",
          "Trade Value": 208908534
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189004,
          "HS4": "Eyewear",
          "Trade Value": 196146529
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189005,
          "HS4": "Binoculars and Telescopes",
          "Trade Value": 62702616
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189006,
          "HS4": "Cameras",
          "Trade Value": 74668350
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189007,
          "HS4": "Video Cameras",
          "Trade Value": 16349363
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189008,
          "HS4": "Image Projectors",
          "Trade Value": 10477195
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189009,
          "HS4": "Photocopiers",
          "Trade Value": 33650026
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189010,
          "HS4": "Photo Lab Equipment",
          "Trade Value": 18513445392
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189011,
          "HS4": "Microscopes",
          "Trade Value": 444962310
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189012,
          "HS4": "Non-optical Microscopes",
          "Trade Value": 592785148
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189013,
          "HS4": "LCDs",
          "Trade Value": 3492000979
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189014,
          "HS4": "Compasses",
          "Trade Value": 306635090
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189015,
          "HS4": "Surveying Equipment",
          "Trade Value": 247012723
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189016,
          "HS4": "Balances",
          "Trade Value": 20840551
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189017,
          "HS4": "Drafting Tools",
          "Trade Value": 213024195
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189018,
          "HS4": "Medical Instruments",
          "Trade Value": 6000225778
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189019,
          "HS4": "Therapeutic Appliances",
          "Trade Value": 97200906
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189020,
          "HS4": "Breathing Appliances",
          "Trade Value": 14827507
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189021,
          "HS4": "Orthopedic Appliances",
          "Trade Value": 262549108
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189022,
          "HS4": "X-Ray Equipment",
          "Trade Value": 2027005257
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189023,
          "HS4": "Instructional Models",
          "Trade Value": 68881926
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189024,
          "HS4": "Tensile Testing Machines",
          "Trade Value": 76052311
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189025,
          "HS4": "Hydrometers",
          "Trade Value": 288824611
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189026,
          "HS4": "Gas and Liquid Flow Measuring Instruments",
          "Trade Value": 1366475108
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189027,
          "HS4": "Chemical Analysis Instruments",
          "Trade Value": 5446997804
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189028,
          "HS4": "Utility Meters",
          "Trade Value": 87631595
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189029,
          "HS4": "Revolution Counters",
          "Trade Value": 353405873
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189030,
          "HS4": "Oscilloscopes",
          "Trade Value": 3654292259
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189031,
          "HS4": "Other Measuring Instruments",
          "Trade Value": 5306296433
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189032,
          "HS4": "Thermostats",
          "Trade Value": 5876394759
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1890,
          "HS2": "Optical, photo, & film equipment; medical instruments",
          "HS4 ID": 189033,
          "HS4": "Opto-Electric Instrument Parts",
          "Trade Value": 128041364
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189101,
          "HS4": "Precious Metal Watches",
          "Trade Value": 130624444
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189102,
          "HS4": "Base Metal Watches",
          "Trade Value": 584346242
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189103,
          "HS4": "Clocks with Watch Movements",
          "Trade Value": 412023
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189104,
          "HS4": "Dashboard Clocks",
          "Trade Value": 8641329
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189105,
          "HS4": "Other Clocks",
          "Trade Value": 10598476
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189106,
          "HS4": "Time Recording Instruments",
          "Trade Value": 2206953
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189107,
          "HS4": "Time Switches",
          "Trade Value": 2699177
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189108,
          "HS4": "Watch Movements",
          "Trade Value": 414358707
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189109,
          "HS4": "Clock Movements",
          "Trade Value": 1055746
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189110,
          "HS4": "Incomplete Movement Sets",
          "Trade Value": 78582100
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189111,
          "HS4": "Watch Cases and Parts",
          "Trade Value": 19758960
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189112,
          "HS4": "Clock Cases and Parts",
          "Trade Value": 434499
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189113,
          "HS4": "Watch Straps",
          "Trade Value": 7971425
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1891,
          "HS2": "Clocks, watches, & parts",
          "HS4 ID": 189114,
          "HS4": "Other Clocks and Watches",
          "Trade Value": 96907275
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1892,
          "HS2": "Musical instruments",
          "HS4 ID": 189201,
          "HS4": "Pianos",
          "Trade Value": 381835857
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1892,
          "HS2": "Musical instruments",
          "HS4 ID": 189202,
          "HS4": "String Instruments",
          "Trade Value": 11846994
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1892,
          "HS2": "Musical instruments",
          "HS4 ID": 189205,
          "HS4": "Wind Instruments",
          "Trade Value": 107037187
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1892,
          "HS2": "Musical instruments",
          "HS4 ID": 189206,
          "HS4": "Percussion",
          "Trade Value": 12577476
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1892,
          "HS2": "Musical instruments",
          "HS4 ID": 189207,
          "HS4": "Electric Musical Instruments",
          "Trade Value": 92379682
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1892,
          "HS2": "Musical instruments",
          "HS4 ID": 189208,
          "HS4": "Other Musical Instruments",
          "Trade Value": 1736999
        },
        {
          "Section ID": 18,
          "Section": "Instruments",
          "HS2 ID": 1892,
          "HS2": "Musical instruments",
          "HS4 ID": 189209,
          "HS4": "Musical Instrument Parts",
          "Trade Value": 94494110
        },
        {
          "Section ID": 19,
          "Section": "Weapons",
          "HS2 ID": 1993,
          "HS2": "Weapons",
          "HS4 ID": 199301,
          "HS4": "Military Weapons",
          "Trade Value": 54018
        },
        {
          "Section ID": 19,
          "Section": "Weapons",
          "HS2 ID": 1993,
          "HS2": "Weapons",
          "HS4 ID": 199302,
          "HS4": "Handguns",
          "Trade Value": 17554
        },
        {
          "Section ID": 19,
          "Section": "Weapons",
          "HS2 ID": 1993,
          "HS2": "Weapons",
          "HS4 ID": 199303,
          "HS4": "Other Firearms",
          "Trade Value": 57454330
        },
        {
          "Section ID": 19,
          "Section": "Weapons",
          "HS2 ID": 1993,
          "HS2": "Weapons",
          "HS4 ID": 199304,
          "HS4": "Spring, Air, and Gas Guns",
          "Trade Value": 18986113
        },
        {
          "Section ID": 19,
          "Section": "Weapons",
          "HS2 ID": 1993,
          "HS2": "Weapons",
          "HS4 ID": 199305,
          "HS4": "Weapons Parts and Accessories",
          "Trade Value": 49273269
        },
        {
          "Section ID": 19,
          "Section": "Weapons",
          "HS2 ID": 1993,
          "HS2": "Weapons",
          "HS4 ID": 199306,
          "HS4": "Explosive Ammunition",
          "Trade Value": 72405589
        },
        {
          "Section ID": 19,
          "Section": "Weapons",
          "HS2 ID": 1993,
          "HS2": "Weapons",
          "HS4 ID": 199307,
          "HS4": "Bladed Weapons and Accessories",
          "Trade Value": 5886914
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2094,
          "HS2": "Furniture, bedding, lamps, & prefab buildings",
          "HS4 ID": 209401,
          "HS4": "Seats",
          "Trade Value": 700205956
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2094,
          "HS2": "Furniture, bedding, lamps, & prefab buildings",
          "HS4 ID": 209402,
          "HS4": "Medical Furniture",
          "Trade Value": 63725579
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2094,
          "HS2": "Furniture, bedding, lamps, & prefab buildings",
          "HS4 ID": 209403,
          "HS4": "Other Furniture",
          "Trade Value": 108763037
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2094,
          "HS2": "Furniture, bedding, lamps, & prefab buildings",
          "HS4 ID": 209404,
          "HS4": "Mattresses",
          "Trade Value": 41114672
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2094,
          "HS2": "Furniture, bedding, lamps, & prefab buildings",
          "HS4 ID": 209405,
          "HS4": "Light Fixtures",
          "Trade Value": 109143080
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2094,
          "HS2": "Furniture, bedding, lamps, & prefab buildings",
          "HS4 ID": 209406,
          "HS4": "Prefabricated Buildings",
          "Trade Value": 14776289
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2095,
          "HS2": "Toys, games, & sports",
          "HS4 ID": 209503,
          "HS4": "Other toys",
          "Trade Value": 437584387
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2095,
          "HS2": "Toys, games, & sports",
          "HS4 ID": 209504,
          "HS4": "Video and Card Games",
          "Trade Value": 3071486739
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2095,
          "HS2": "Toys, games, & sports",
          "HS4 ID": 209505,
          "HS4": "Party Decorations",
          "Trade Value": 3702761
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2095,
          "HS2": "Toys, games, & sports",
          "HS4 ID": 209506,
          "HS4": "Sports Equipment",
          "Trade Value": 650818029
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2095,
          "HS2": "Toys, games, & sports",
          "HS4 ID": 209507,
          "HS4": "Fishing and Hunting Equipment",
          "Trade Value": 367845031
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2095,
          "HS2": "Toys, games, & sports",
          "HS4 ID": 209508,
          "HS4": "Fairground Amusements",
          "Trade Value": 6709988
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209601,
          "HS4": "Worked Ivory and Bone",
          "Trade Value": 2322330
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209602,
          "HS4": "Vegetable and Mineral Carvings",
          "Trade Value": 15634297
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209603,
          "HS4": "Brooms",
          "Trade Value": 157717293
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209604,
          "HS4": "Hand Sifters",
          "Trade Value": 543647
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209605,
          "HS4": "Travel Kits",
          "Trade Value": 250263
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209606,
          "HS4": "Buttons",
          "Trade Value": 133129375
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209607,
          "HS4": "Zippers",
          "Trade Value": 289324274
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209608,
          "HS4": "Pens",
          "Trade Value": 1033908054
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209609,
          "HS4": "Pencils and Crayons",
          "Trade Value": 28856876
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209610,
          "HS4": "Chalkboards",
          "Trade Value": 328484
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209611,
          "HS4": "Rubber Stamps",
          "Trade Value": 8183154
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209612,
          "HS4": "Ink Ribbons",
          "Trade Value": 269715720
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209613,
          "HS4": "Lighters",
          "Trade Value": 12762193
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209614,
          "HS4": "Smoking Pipes",
          "Trade Value": 3727740
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209615,
          "HS4": "Combs",
          "Trade Value": 13098221
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209616,
          "HS4": "Scent Sprays",
          "Trade Value": 15706954
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209617,
          "HS4": "Vacuum Flask",
          "Trade Value": 26773885
        },
        {
          "Section ID": 20,
          "Section": "Miscellaneous",
          "HS2 ID": 2096,
          "HS2": "Misc. manufactures",
          "HS4 ID": 209618,
          "HS4": "Mannequins",
          "Trade Value": 1168962
        },
        {
          "Section ID": 21,
          "Section": "Arts and Antiques",
          "HS2 ID": 2197,
          "HS2": "Art & antiques",
          "HS4 ID": 219701,
          "HS4": "Paintings",
          "Trade Value": 224366395
        },
        {
          "Section ID": 21,
          "Section": "Arts and Antiques",
          "HS2 ID": 2197,
          "HS2": "Art & antiques",
          "HS4 ID": 219702,
          "HS4": "Prints",
          "Trade Value": 18343998
        },
        {
          "Section ID": 21,
          "Section": "Arts and Antiques",
          "HS2 ID": 2197,
          "HS2": "Art & antiques",
          "HS4 ID": 219703,
          "HS4": "Sculptures",
          "Trade Value": 62018272
        },
        {
          "Section ID": 21,
          "Section": "Arts and Antiques",
          "HS2 ID": 2197,
          "HS2": "Art & antiques",
          "HS4 ID": 219704,
          "HS4": "Revenue Stamps",
          "Trade Value": 270407
        },
        {
          "Section ID": 21,
          "Section": "Arts and Antiques",
          "HS2 ID": 2197,
          "HS2": "Art & antiques",
          "HS4 ID": 219705,
          "HS4": "Collector's Items",
          "Trade Value": 19444995
        },
        {
          "Section ID": 21,
          "Section": "Arts and Antiques",
          "HS2 ID": 2197,
          "HS2": "Art & antiques",
          "HS4 ID": 219706,
          "HS4": "Antiques",
          "Trade Value": 20085710
        }
      ],
      "source": [
        {
          "name": "trade_i_baci_a_92",
          "measures": [
            "Trade Value",
            "Quantity"
          ],
          "annotations": {
            "source_name": "BACI",
            "dataset_link": "http://www.cepii.fr/CEPII/en/bdd_modele/presentation.asp?id=37",
            "topic": "International",
            "dataset_name": "HS6 REV. 1992 (1995 - 2021)",
            "subtopic": "Trade",
            "source_description": "Product Trade by Year and Country (HS 6-digit depth)",
            "table": "HS6 REV. 1992 (1995 - 2021)"
          }
        }
      ]
    }
""".trimIndent()

@Serializable
internal data class ExportsResponse(
    @SerialName("data")
    val data: List<ProductTrade>,
    @SerialName("source")
    val source: List<Source>,
)

@Serializable
internal data class ProductTrade(
    @SerialName("Section ID")
    val sectionId: Long,
    @SerialName("Section")
    val sectionName: String,
    @SerialName("HS2 ID")
    val hs2Id: Long,
    @SerialName("HS2")
    val hs2Name: String,
    @SerialName("HS4 ID")
    val hs4Id: Long,
    @SerialName("HS4")
    val hs4Name: String,
    @SerialName("Trade Value")
    val tradeValue: Double,
)

@Serializable
internal data class Source(
    @SerialName("name")
    val name: String,
    @SerialName("measures")
    val measures: List<String>,
    @SerialName("annotations")
    val annotations: Annotations,
)

@Serializable
internal data class Annotations(
    @SerialName("source_name")
    val sourceName: String,
    @SerialName("dataset_link")
    val datasetLink: String,
    @SerialName("topic")
    val topic: String,
    @SerialName("dataset_name")
    val datasetName: String,
    @SerialName("subtopic")
    val subtopic: String,
    @SerialName("source_description")
    val sourceDescription: String,
    @SerialName("table")
    val table: String,
)

internal object ProductTradesRepository {

    suspend fun getJapan2021Exports(): List<ProductTrade> = withContext(Dispatchers.Default) {
        val exportsResponse: ExportsResponse = Json.decodeFromString(japan2021ExportsJson)
        exportsResponse.data
    }
}
