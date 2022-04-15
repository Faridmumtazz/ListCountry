package mumtaz.binar.listcountry.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main_data.*
import mumtaz.binar.listcountry.R
import mumtaz.binar.listcountry.adapter.AdapterCountry
import mumtaz.binar.listcountry.model.GetAllCountryResponseItem
import mumtaz.binar.listcountry.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_data)

        getDataCountry()
    }

    fun getDataCountry(){
        ApiClient.instance.getAllData()
            .enqueue(object : Callback<List<GetAllCountryResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetAllCountryResponseItem>>,
                    response: Response<List<GetAllCountryResponseItem>>
                ) {
                    if (response.isSuccessful){
                        val datacountry = response.body()
                        val adaptercountry = AdapterCountry(datacountry!!)
                        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                        rv_country.layoutManager = layoutManager
                        rv_country.adapter = adaptercountry
                    }else {
                        Toast.makeText(this@MainDataActivity, response.message(), Toast.LENGTH_LONG).show()
                    }
                    Log.i("datacountry", response.body().toString())
                }

                override fun onFailure(call: Call<List<GetAllCountryResponseItem>>, t: Throwable) {
                    Toast.makeText(this@MainDataActivity, t.message, Toast.LENGTH_LONG).show()
                }

            })
    }
}