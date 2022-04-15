package mumtaz.binar.listcountry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_country.view.*
import mumtaz.binar.listcountry.R
import mumtaz.binar.listcountry.model.GetAllCountryResponseItem

class AdapterCountry (private var datacountry : List<GetAllCountryResponseItem>) : RecyclerView.Adapter<AdapterCountry.ViewHolder> (){
    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCountry.ViewHolder {
        val itemview = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: AdapterCountry.ViewHolder, position: Int) {
        holder.itemView.tv_namacountry.text = datacountry[position].name
        holder.itemView.tv_capital.text = datacountry[position].capital
        holder.itemView.tv_region.text = datacountry[position].region

        Glide.with(holder.itemView.context).load(datacountry[position].flags.png).into(holder.itemView.img_country)
    }

    override fun getItemCount(): Int {
        return datacountry.size
    }
}