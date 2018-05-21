package com.example.codemagnus.newproject.Models

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
                    "Flavored Fries Cheddar",
                    "FLAVORED FRIES",
                    "",
                    "Cheddar",
                    0.00,
                    R.drawable.cheese,
                    0

            ))
            product.add(Product(
                    "200",
                    "f12",
                    "Flavored Fries Barbeque",
                    "FLAVORED FRIES",
                    "",
                    "Barbeque",
                    0.00,
                    R.drawable.cheese,
                    0
            ))
            product.add(Product(
                    "300",
                    "f13",
                    "Flavored Fries Salted Caramel",
                    "FLAVORED FRIES",
                    "",
                    "Salted Caramel",
                    0.00,
                    R.drawable.cheese,
                    0
            ))
            product.add(Product(
                    "400",
                    "f14",
                    "Flavored Fries Wasabi",
                    "FLAVORED FRIES",
                    "",
                    "Wasabi",
                    0.00,
                    R.drawable.cheese,
                    0
            ))
            product.add(Product(
                    "500",
                    "f15",
                    "Flavored Fries Sour & Cream",
                    "FLAVORED FRIES",
                    "",
                    "Sour & Cream",
                    0.00,
                    R.drawable.cheese,
                    0
            ))
            product.add(Product(
                    "600",
                    "f16",
                    "Flavored Fries Chili Barbeque",
                    "FLAVORED FRIES",
                    " Fries",
                    "Chili Barbeque",
                    0.00,
                    R.drawable.cheese,
                    0
            ))
            product.add(Product(
                    "700",
                    "f17",
                    "Flavored Fries Garlic Parmesan",
                    "FLAVORED FRIES",
                    "",
                    "Garlic Parmesan",
                    0.00,
                    R.drawable.cheese,
                    0
            ))
            product.add(Product(
                    "800",
                    "f18",
                    "Flavored Fries Cinnamon & Sugar",
                    "FLAVORED FRIES",
                    "Flavored Fries",
                    "Cinnamon $ Sugar",
                    0.00,
                    R.drawable.cheese,
                    0
            ))
            return product


        }

        fun getlists2(): ArrayList<Product> {
            var product = ArrayList<Product>()
            product.add(Product(
                    "900",
                    "f19",
                    "Softdrinks",
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
                    "Iced Tea",
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
                    "Bottled Water",
                    "DRINKS",
                    "Beverages",
                    "Cinnamon $ Sugar",
                    0.00,
                    R.drawable.water,
                    0
            ))

            product.add(Product(
                    "1200",
                    "f22",
                    "Minute Maid",
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
                    "Loopy Fries",
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
                    "Hash Brown",
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
                    "Jojos",
                    "FANCY FRIES",
                    "Fancy Fries",
                    "",
                    0.00,
                    R.drawable.jojos,
                    0
            ))

            product.add(Product(
                    "1600",
                    "f26",
                    "Cheezy Fries",
                    "FANCY FRIES",
                    "Fancy Fries",
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
                    0
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
                    0
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
                    0
            ))

            return product


        }

    }
}