package com.devaruluis.exchanges.model

import com.google.gson.annotations.SerializedName

//@Entity(tableName = "exchanges")
//data class Exchange(
//    @PrimaryKey(autoGenerate = true)
//    val id: Long,
//
//    @ColumnInfo(name= "idStr")
//    val idStr: String,
//
//    @ColumnInfo(name = "name")
//    val name: String,
//
//    @ColumnInfo(name = "description")
//    val description: String?,
//
//    @ColumnInfo(name = "active")
//    val active:Boolean,
//)

data class Exchange(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("last_updated")
    val lastUpdated: String?
)
