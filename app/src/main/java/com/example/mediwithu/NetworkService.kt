package com.example.mediwithu


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("getHospBasisList")
    fun getHospitalList(
        @Query("serviceKey", encoded = true) serviceKey: String,
        @Query("sidoCd") sidoCd: String? = null,
        @Query("numOfRows") numOfRows: Int = 10,
        @Query("pageNo") pageNo: Int = 1,
    ) : Call<HospitalResponse>

    @GET("getParmacyListInfoInqire")
    fun getPharmacyList(
        @Query("serviceKey", encoded = true) serviceKey: String,
        @Query("Q0") sido: String? = null,
        @Query("Q1") sggu: String? = null,
        @Query("QT") dayOfWeek: String? = null,
        @Query("QN") institutionName: String? = null,
        @Query("ORD") order: String? = null,
        @Query("pageNo") pageNo: Int = 1,
        @Query("numOfRows") numOfRows: Int = 20
    ) : Call<PharmResponse>
}