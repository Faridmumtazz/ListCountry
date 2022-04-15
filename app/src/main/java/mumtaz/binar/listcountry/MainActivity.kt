package mumtaz.binar.listcountry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import mumtaz.binar.listcountry.adapter.AdapterCountry
import mumtaz.binar.listcountry.model.GetAllCountryResponseItem
import mumtaz.binar.listcountry.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataCountry()
    }

    fun getDataCountry(){
        ApiClient.instance.getAllData()
            .enqueue(object : Callback<List<GetAllCountryResponseItem>> {
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
                        Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_LONG).show()
                    }
                    Log.i("datacountry", response.body().toString())
                }

                override fun onFailure(call: Call<List<GetAllCountryResponseItem>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                }

            })
    }
}