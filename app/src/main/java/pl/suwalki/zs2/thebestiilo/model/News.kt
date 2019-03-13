package pl.suwalki.zs2.thebestiilo.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val desc: String,
    @SerializedName("items")
    val items: List<Items>

)