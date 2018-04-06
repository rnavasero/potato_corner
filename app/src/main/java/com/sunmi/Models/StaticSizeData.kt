package com.example.codemagnus.newproject.Models

import com.sunmi.printerhelper.R


/**
 * Created by codemagnus on 3/20/18.
 */
class StaticSizeData {
    companion object {
        fun getlists(): ArrayList<Product> {
            val product = ArrayList<Product>()
            product.add(Product(
                    "2000",
                    "",
                    "",
                    R.drawable.pcregular,
                    "",
                    "",
                    "Regular",
                    33.00,
                    1
            ))
            product.add(Product(
                    "2100",
                    "",
                    "",
                    R.drawable.pclarge,
                    "",
                    "",
                    "Large",
                    55.00,
                    0
            ))
            product.add(Product(
                    "2200",
                    "",
                    "",
                    R.drawable.pcjumbo,
                    "",
                    "",
                    "Jumbo",
                    79.00,
                    0
            ))
            product.add(Product(
                    "2300",
                    "",
                    "",
                    R.drawable.pcmega,
                    "",
                    "",
                    "Mega",
                    99.00,
                    0
            ))
            product.add(Product(
                    "2400",
                    "",
                    "",
                    R.drawable.pcgiga,
                    "",
                    "",
                    "Giga",
                    149.00,
                    0
            ))
            product.add(Product(
                    "2500",
                    "",
                    "",
                    R.drawable.pcterra,
                    "",
                    "",
                    "Terra",
                    199.00,
                    0
            ))
            return product


        }

    }
}