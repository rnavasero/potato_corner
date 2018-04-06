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
                    "Cheddar",
                    "Description",
                    R.drawable.cheese,
                    "Flavored Fries",
                    "Cheddar",
                    "",
                    0.00,
                    0

            ))
            product.add(Product(
                    "200",
                    "Barbeque",
                    "Description",
                    R.drawable.cheese,
                    "Flavored Fries",
                    "Barbeque",
                    "",
                    0.00,
                    0
            ))
            product.add(Product(
                    "300",
                    "Salted Caramel",
                    "Description",
                    R.drawable.cheese,
                    "Flavored Fries",
                    "Salted Caramel",
                    "",
                    0.00,
                    0
            ))
            product.add(Product(
                    "400",
                    "Wasabi",
                    "Description",
                    R.drawable.cheese,
                    "Flavored Fries",
                    "Wasabi",
                    "",
                    0.00,
                    0
            ))
            product.add(Product(
                    "500",
                    "Sour and Cream",
                    "Description",
                    R.drawable.cheese,
                    "Flavored Fries",
                    "Sour & Cream",
                    "",
                    0.00,
                    0
            ))
            product.add(Product(
                    "600",
                    "Chili Barbeque",
                    "Description",
                    R.drawable.cheese,
                    "Flavored Fries",
                    "Chili Barbeque",
                    "",
                    0.00,
                    0
            ))
            product.add(Product(
                    "700",
                    "Garlic Parmesan",
                    "Description",
                    R.drawable.cheese,
                    "Flavored Fries",
                    "Garlic Parmesan",
                    "",
                    0.00,
                    0
            ))
            product.add(Product(
                    "800",
                    "Cinnamon & Sugar",
                    "Description",
                    R.drawable.cheese,
                    "Flavored Fries",
                    "Cinnamon $ Sugar",
                    "",
                    0.00,
                    0
            ))
            return product


        }

        fun getlists2(): ArrayList<Product> {
            var product = ArrayList<Product>()
            product.add(Product(
                    "900",
                    "Softdrinks",
                    "Description",
                    R.drawable.softdrinks,
                    "Beverages",
                    "Cinnamon $ Sugar",
                    "",
                    0.00,
                    0

            ))


            product.add(Product(
                    "1000",
                    "Iced Tea",
                    "Description",
                    R.drawable.icedtead,
                    "Beverages",
                    "Cinnamon $ Sugar",
                    "",
                    0.00,
                    0
            ))


            product.add(Product(
                    "1100",
                    "Bottled Water",
                    "Description",
                    R.drawable.water,
                    "Beverages",
                    "Cinnamon $ Sugar",
                    "",
                    0.00,
                    0
            ))

            product.add(Product(
                    "1200",
                    "Minute Maid",
                    "Description",
                    R.drawable.minutemaid,
                    "Beverages",
                    "Cinnamon $ Sugar",
                    "",
                    0.00,
                    0
            ))

            return product


        }

        fun getlists3(): ArrayList<Product> {
            var product = ArrayList<Product>()
            product.add(Product(
                    "1300",
                    "Loopy Fries",
                    "Description",
                    R.drawable.loopyfries,
                    "Fancy Fries",
                    "",
                    "",
                    0.00,
                    0
            ))


            product.add(Product(
                    "1400",
                    "Hash Brown",
                    "Description",
                    R.drawable.hashbrown,
                    "Fancy Fries",
                    "",
                    "",
                    0.00,
                    0
            ))


            product.add(Product(
                    "1500",
                    "Jojos",
                    "Description",
                    R.drawable.jojos,
                    "Fancy Fries",
                    "",
                    "",
                    0.00,
                    0
            ))

            product.add(Product(
                    "1600",
                    "Cheezy Fries",
                    "Description",
                    R.drawable.cheese,
                    "Fancy Fries",
                    "",
                    "",
                    0.00,
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
                    R.drawable.cheese,
                    "",
                    "",
                    "Small",
                    0.00,
                    0
            ))


            product.add(Product(
                    "1800",
                    "",
                    "",
                    R.drawable.cheese,
                    "",
                    "",
                    "Medium",
                    0.00,
                    0
            ))


            product.add(Product(
                    "1900",
                    "",
                    "",
                    R.drawable.cheese,
                    "",
                    "",
                    "Large",
                    0.00,
                    0
            ))

            return product


        }

    }
}