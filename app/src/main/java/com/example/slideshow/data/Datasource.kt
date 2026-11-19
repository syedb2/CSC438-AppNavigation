package com.example.appnavigation.data
import com.example.appnavigation.R
import com.example.appnavigation.model.GalleryItem
class Datasource {
 fun loadItems(): List<GalleryItem> = listOf(
  GalleryItem(R.string.name_mercury, R.drawable.mercury, R.string.description_mercury),
  GalleryItem(R.string.name_venus, R.drawable.venus, R.string.description_venus),
  GalleryItem(R.string.name_earth, R.drawable.earth, R.string.description_earth),
  GalleryItem(R.string.name_mars, R.drawable.mars, R.string.description_mars),
  GalleryItem(R.string.name_jupiter, R.drawable.jupiter, R.string.description_jupiter),
  GalleryItem(R.string.name_saturn, R.drawable.saturn, R.string.description_saturn),
  GalleryItem(R.string.name_uranus, R.drawable.uranus, R.string.description_uranus),
  GalleryItem(R.string.name_neptune, R.drawable.neptune, R.string.description_neptune),
  GalleryItem(R.string.name_earth_detail, R.drawable.earth_detail, R.string.description_earth_detail),
  GalleryItem(R.string.name_jupiter_detail, R.drawable.jupiter_detail, R.string.description_jupiter_detail)
 )
}
