package com.example.mediwithu

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class HospitalResponse(
    @Element(name = "body")
    val body: MyBody
)

@Xml(name = "body")
data class MyBody(
    @Element(name = "items")
    val items: Items
)

@Xml(name = "items")
data class Items(
    @Element(name = "item")
    val item: MutableList<myHospitalItem>
)

@Xml(name = "item")
data class myHospitalItem(
    @PropertyElement(name = "cmdcResdntCnt") val cmdcResdntCnt: String?,
    @PropertyElement(name = "cmdcSdrCnt") val cmdcSdrCnt: String?,
    @PropertyElement(name = "pnursCnt") val pnursCnt: String?,
    @PropertyElement(name = "XPos") val xPos: String?,
    @PropertyElement(name = "YPos") val yPos: String?,
    @PropertyElement(name = "distance") val distance: String?,
    @PropertyElement(name = "detyGdrCnt") val detyGdrCnt: String?,
    @PropertyElement(name = "detyIntnCnt") val detyIntnCnt: String?,
    @PropertyElement(name = "detyResdntCnt") val detyResdntCnt: String?,
    @PropertyElement(name = "detySdrCnt") val detySdrCnt: String?,
    @PropertyElement(name = "cmdcGdrCnt") val cmdcGdrCnt: String?,
    @PropertyElement(name = "cmdcIntnCnt") val cmdcIntnCnt: String?,
    @PropertyElement(name = "mdeptResdntCnt") val mdeptResdntCnt: String?,
    @PropertyElement(name = "drTotCnt") val drTotCnt: String?,
    @PropertyElement(name = "mdeptGdrCnt") val mdeptGdrCnt: String?,
    @PropertyElement(name = "mdeptIntnCnt") val mdeptIntnCnt: String?,
    @PropertyElement(name = "telno") val telno: String?,
    @PropertyElement(name = "hospUrl") val hospUrl: String?,
    @PropertyElement(name = "estbDd") val estbDd: String?,
    @PropertyElement(name = "sgguCdNm") val sgguCdNm: String?,
    @PropertyElement(name = "emdongNm") val emdongNm: String?,
    @PropertyElement(name = "postNo") val postNo: String?,
    @PropertyElement(name = "addr") val addr: String?,
    @PropertyElement(name = "sidoCdNm") val sidoCdNm: String?,
    @PropertyElement(name = "sgguCd") val sgguCd: String?,
    @PropertyElement(name = "ykiho") val ykiho: String?,
    @PropertyElement(name = "yadmNm") val yadmNm: String?,
    @PropertyElement(name = "clCd") val clCd: String?,
    @PropertyElement(name = "clCdNm") val clCdNm: String?,
    @PropertyElement(name = "sidoCd") val sidoCd: String?,
    @PropertyElement(name = "mdeptSdrCnt") val mdeptSdrCnt: String?
)
