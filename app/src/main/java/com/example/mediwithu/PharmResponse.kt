package com.example.mediwithu

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class PharmResponse(
    @Element(name = "body")
    val body: PharmBody
)

@Xml(name = "body")
data class PharmBody(
    @Element(name = "items")
    val items: PharmItems,
    @PropertyElement(name = "numOfRows")
    val numOfRows: Int,
    @PropertyElement(name = "pageNo")
    val pageNo: Int,
    @PropertyElement(name = "totalCount")
    val totalCount: Int
)

@Xml(name = "items")
data class PharmItems(
    @Element(name = "item")
    val item: List<PharmItem>
)

@Xml(name = "item")
data class PharmItem(
    @PropertyElement(name = "dutyName")
    val institutionName: String? = null,

    @PropertyElement(name = "dutyAddr")
    val address: String? = null,

    @PropertyElement(name = "dutyTel1")
    val phoneNumber: String? = null
)