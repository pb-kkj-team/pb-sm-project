package pl.suwalki.zs2.thebestiilo.model

import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName(value = "id")
    val id: String,
    @SerializedName(value = "url")
    val url: String,
    @SerializedName(value = "title")
    val title: String?,
    @SerializedName(value = "content_text")
    val content_text: String,
    @SerializedName(value = "content_html")
    val content_html: String,
    @SerializedName(value = "image")
    val image: String?,
    @SerializedName(value = "date_published")
    val date: String
)