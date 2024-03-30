package com.cs4520.assignment5.ui.products.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cs4520.assignment1.data.Product
import com.cs4520.assignment5.R

@Composable
fun ProductItem(product: Product) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .then(
                when (product.type) {
                    "Equipment" -> Modifier.background(Color(0xFFE06666))
                    "Food" -> Modifier.background(Color(0xFFFFD965))
                    else -> Modifier.background(MaterialTheme.colors.surface)
                }
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = product.name,
            modifier = Modifier
                .width(140.dp)
                .height(40.dp)
                .padding(start = 10.dp),
            color = Color.Black,
            textAlign = TextAlign.Start,
            fontSize = 12.sp
        )

        val image = when (product.type) {
            "Equipment" -> painterResource(R.drawable.equipment)
            "Food" -> painterResource(R.drawable.food)
            else -> throw IllegalArgumentException("Invalid type: ${product.type}")
        }

        Image(
            painter = image,
            contentDescription = "Type Image",
            modifier = Modifier
                .width(80.dp)
                .height(80.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = product.expiryDate ?: "",
            modifier = Modifier
                .width(80.dp)
                .height(40.dp),
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )

        Text(
            text = "$" + product.price.toString(),
            modifier = Modifier
                .width(50.dp)
                .height(40.dp)
                .padding(end = 10.dp),
            color = Color.Black,
            textAlign = TextAlign.End,
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
fun ProductPreview() {
    ProductItem(Product("Milk", "Food", "2022-10-01", 2.99f))
}