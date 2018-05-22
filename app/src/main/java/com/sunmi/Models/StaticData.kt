package com.sunmi.Models

import com.sunmi.Models.Product
import com.sunmi.printerhelper.R


/**
 * Created by codemagnus on 3/20/18.
 */
class StaticData {
    companion object {
        fun getlists(): ArrayList<Product> {
            var product = ArrayList<Product>()
            product.add(Product(
                    "100",
                    "f11",
                    "CHEDDAR",
                    "FLAVORED FRIES",
                    "",
                    "CHEDDAR",
                    0.00,
                    R.drawable.cheese,
                    1

            ))
            product.add(Product(
                    "200",
                    "f12",
                    "BARBEQUE",
                    "FLAVORED FRIES",
                    "",
                    "BARBEQUE",
                    0.00,
                    R.drawable.cheese,
                    1
            ))
            product.add(Product(
                    "300",
                    "f13",
                    "SALTED CARAMEL",
                    "FLAVORED FRIES",
                    "",
                    "SALTED CARAMEL",
                    0.00,
                    R.drawable.cheese,
                    1
            ))
            product.add(Product(
                    "400",
                    "f14",
                    "WASABI",
                    "FLAVORED FRIES",
                    "",
                    "WASABI",
                    0.00,
                    R.drawable.cheese,
                    1
            ))
            product.add(Product(
                    "500",
                    "f15",
                    "SOUR CREAM",
                    "FLAVORED FRIES",
                    "",
                    "SOUR CREAM",
                    0.00,
                    R.drawable.cheese,
                    1
            ))
            product.add(Product(
                    "600",
                    "f16",
                    "CHILI BARBEQUE",
                    "FLAVORED FRIES",
                    " Fries",
                    "CHILI BARBEQUE",
                    0.00,
                    R.drawable.cheese,
                    1
            ))
            product.add(Product(
                    "700",
                    "f17",
                    "GARLIC PARMESAN",
                    "FLAVORED FRIES",
                    "",
                    "GARLIC PARMESAN",
                    0.00,
                    R.drawable.cheese,
                    1
            ))
            product.add(Product(
                    "800",
                    "f18",
                    "CINNAMON & SUGAR",
                    "FLAVORED FRIES",
                    "",
                    "CINNAMON & SUGAR",
                    0.00,
                    R.drawable.cheese,
                    1
            ))
            return product


        }

        fun getlists2(): ArrayList<Product> {
            var product = ArrayList<Product>()
            product.add(Product(
                    "900",
                    "f19",
                    "SOFTDRINKS",
                    "DRINKS",
                    "",
                    "",
                    0.00,
                    R.drawable.softdrinks,
                    0

            ))


            product.add(Product(
                    "1000",
                    "f20",
                    "ICED TEA",
                    "DRINKS",
                    "",
                    "",
                    0.00,
                    R.drawable.icedtead,
                    0
            ))


            product.add(Product(
                    "1100",
                    "f21",
                    "BOTTLED WATER",
                    "DRINKS",
                    "",
                    "",
                    0.00,
                    R.drawable.water,
                    0
            ))

            product.add(Product(
                    "1200",
                    "f22",
                    "MINUTE MAID",
                    "DRINKS",
                    "",
                    "",
                    0.00,
                    R.drawable.minutemaid,
                    0
            ))

            return product


        }

        fun getlists3(): ArrayList<Product> {
            var product = ArrayList<Product>()
            product.add(Product(
                    "1300",
                    "f23",
                    "LOOPY FRIES",
                    "FANCY FRIES",
                    "",
                    "",
                    0.00,
                    R.drawable.loopyfries,
                    0
            ))


            product.add(Product(
                    "1400",
                    "f24",
                    "HASH BROWN",
                    "FANCY FRIES",
                    "",
                    "",
                    0.00,
                    R.drawable.hashbrown,
                    0
            ))


            product.add(Product(
                    "1500",
                    "f25",
                    "JOJOS",
                    "FANCY FRIES",
                    "",
                    "",
                    0.00,
                    R.drawable.jojos,
                    0
            ))

            product.add(Product(
                    "1600",
                    "f26",
                    "CHEEZY FRIES",
                    "FANCY FRIES",
                    "",
                    "",
                    0.00,
                    R.drawable.cheese,
                    0
            ))

            return product


        }

        fun getlists4(): ArrayList<Product> {
            val product = ArrayList<Product>()
            product.add(Product(
                    "1700",
                    "",
                    "",
                    "",
                    "Small",
                    "",
                    0.00,
                    0,
                    1
            ))


            product.add(Product(
                    "1800",
                    "",
                    "",
                    "",
                    "Medium",
                    "",
                    0.00,
                    0,
                    1
            ))


            product.add(Product(
                    "1900",
                    "",
                    "",
                    "",
                    "Large",
                    "",
                    0.00,
                    0,
                    1
            ))

            return product


        }

    }
}