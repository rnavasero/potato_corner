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
                    "",
                    "Regular",
                    "",
                    33.00,
                    R.drawable.pcregular,
                    1
            ))
            product.add(Product(
                    "2100",
                    "",
                    "",
                    "",
                    "Large",
                    "",
                    55.00,
                    R.drawable.pclarge,
                    0
            ))
            product.add(Product(
                    "2200",
                    "",
                    "",
                    "",
                    "Jumbo",
                    "",
                    79.00,
                    R.drawable.pcjumbo,
                    0
            ))
            product.add(Product(
                    "2300",
                    "",
                    "",
                    "",
                    "",
                    "",
                    99.00,
                    R.drawable.pcmega,
                    0
            ))
            product.add(Product(
                    "2400",
                    "",
                    "",
                   "",
                    "",
                    "",
                    149.00,
                    R.drawable.pcgiga,
                    0
            ))
            product.add(Product(
                    "2500",
                    "",
                    "",
                    "",
                    "",
                    "",
                    199.00,
                    R.drawable.pcterra,
                    0
            ))
            return product


        }

    }
}