package com.example.gbookssample.com.example.gbookssample.data

import com.google.gson.annotations.SerializedName

data class Volume (
    @SerializedName("kind") val kind : String,
    @SerializedName("totalItems") val totalItems : Int,
    @SerializedName("items") val items : List<Item>?
)

data class Item (
    @SerializedName("kind") val kind : String,
    @SerializedName("id") val id : String,
    @SerializedName("etag") val etag : String,
    @SerializedName("selfLink") val selfLink : String,
    @SerializedName("volumeInfo") val volumeInfo : VolumeInfo,
    @SerializedName("saleInfo") val saleInfo : SaleInfo,
    @SerializedName("accessInfo") val accessInfo : AccessInfo
)

data class VolumeInfo (
    @SerializedName("title") val title : String,
    @SerializedName("subtitle") val subtitle : String,
    @SerializedName("authors") val authors : List<String>?,
    @SerializedName("publishedDate") val publishedDate : String,
    @SerializedName("printType") val printType : String,
    @SerializedName("averageRating") val averageRating : Double,
    @SerializedName("ratingsCount") val ratingsCount : Int,
    @SerializedName("maturityRating") val maturityRating : String,
    @SerializedName("allowAnonLogging") val allowAnonLogging : Boolean,
    @SerializedName("contentVersion") val contentVersion : String,
    @SerializedName("imageLinks") val imageLinks : ImageLinks,
    @SerializedName("language") val language : String,
    @SerializedName("previewLink") val previewLink : String,
    @SerializedName("infoLink") val infoLink : String,
    @SerializedName("canonicalVolumeLink") val canonicalVolumeLink : String
)

data class SaleInfo (
    @SerializedName("country") val country : String,
    @SerializedName("saleability") val saleability : String,
    @SerializedName("isEbook") val isEbook : Boolean,
    @SerializedName("buyLink") val buyLink : String
)

data class AccessInfo (
    @SerializedName("country") val country : String,
    @SerializedName("viewability") val viewability : String,
    @SerializedName("embeddable") val embeddable : Boolean,
    @SerializedName("publicDomain") val publicDomain : Boolean,
    @SerializedName("textToSpeechPermission") val textToSpeechPermission : String,
    @SerializedName("epub") val epub : Epub,
    @SerializedName("pdf") val pdf : Pdf,
    @SerializedName("webReaderLink") val webReaderLink : String,
    @SerializedName("accessViewStatus") val accessViewStatus : String,
    @SerializedName("quoteSharingAllowed") val quoteSharingAllowed : Boolean
)

data class Pdf (
    @SerializedName("isAvailable") val isAvailable : Boolean,
    @SerializedName("downloadLink") val downloadLink : String
)

data class Epub (
    @SerializedName("isAvailable") val isAvailable : Boolean,
    @SerializedName("downloadLink") val downloadLink : String
)

data class ImageLinks (
    @SerializedName("smallThumbnail") val smallThumbnail : String,
    @SerializedName("thumbnail") val thumbnail : String
)