package com.example.mediwithu

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import retrofit2.Retrofit

class RetrofitConnection {

    companion object{
        //private const val BASE_URL = "https://apis.data.go.kr/B551182/hospInfoServicev2"

        var hospitalNetworkService : NetworkService
        val parser = TikXml.Builder().exceptionOnUnreadXml(false).build()
        val hospitalRetrofit : Retrofit
            get() = Retrofit.Builder()
                .baseUrl("https://apis.data.go.kr/B551182/hospInfoServicev2/")
                .addConverterFactory(TikXmlConverterFactory.create(parser))
                .build()

        init{
            hospitalNetworkService = hospitalRetrofit.create(NetworkService::class.java)
        }

        var pharmNetworkService : NetworkService
        //val parser = TikXml.Builder().exceptionOnUnreadXml(false).build()
        val pharmRetrofit : Retrofit
            get() = Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/")
                .addConverterFactory(TikXmlConverterFactory.create(parser))
                .build()

        init{
            pharmNetworkService = pharmRetrofit.create(NetworkService::class.java)
        }

    }

}