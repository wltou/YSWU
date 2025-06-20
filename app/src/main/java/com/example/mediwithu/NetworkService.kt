package com.example.mediwithu


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("getHospList")
    fun getHospitalList(
        @Query("serviceKey", encoded = false) serviceKey: String,
        @Query("sidoCd") sidoCd: String? = null,
        @Query("numOfRows") numOfRows: Int = 10,
        @Query("pageNo") pageNo: Int = 1,
    ) : Call<HospitalResponse>

    @GET("getParmacyListInfoInqire")
    fun getPharmacyList(
        @Query("serviceKey", encoded = true) serviceKey: String,
        @Query("Q0") sido: String? = null,          // 시도
        @Query("Q1") sggu: String? = null,          // 시군구
        @Query("QT") dayOfWeek: String? = null,     // 진료요일 (1~8)
        @Query("QN") institutionName: String? = null, // 기관명 (약국 이름)
        @Query("ORD") order: String? = null,        // 정렬 기준 (ex: NAME)
        @Query("pageNo") pageNo: Int = 1,           // 페이지 번호
        @Query("numOfRows") numOfRows: Int = 20     // 목록 건수
    ) : Call<PharmResponse>
}