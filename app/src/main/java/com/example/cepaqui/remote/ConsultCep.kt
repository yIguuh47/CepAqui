package com.example.cepaqui.remote

import com.example.cepaqui.model.CepModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ConsultCep {

    @GET("/json/{cep}")
    fun getDiceCity(@Path("cep") cep: String): Call<CepModel>

}