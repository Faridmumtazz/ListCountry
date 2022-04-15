package mumtaz.binar.listcountry.network

import mumtaz.binar.listcountry.model.GetAllCountryResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("all")
    fun getAllData(
    ) : Call<List<GetAllCountryResponseItem>>
}