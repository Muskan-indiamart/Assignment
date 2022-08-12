package com.example.navigationdrawerapp


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso



import com.bumptech.glide.Glide
import retrofit2.Callback

class ListAdapter(private val listener: ListBtnClicked,
                  val context: Context, private val Users:List<UserInfo>) : RecyclerView.Adapter<ListViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items,parent,false)

        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val userinfo:UserInfo=Users[position]

        holder.name.text=userinfo.firstName
        holder.email.text=userinfo.email
        holder.number.text=userinfo.phone

        Picasso.get().load(userinfo.image).into(holder.image)
        holder.call.setOnClickListener { listener.onBtnClicked(userinfo) }

    }

    override fun getItemCount(): Int {
        return Users.size
    }
}
class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val image : ImageView =itemView.findViewById(R.id.imageView)
    val name : TextView = itemView.findViewById(R.id.name)
    val email : TextView = itemView.findViewById(R.id.email)
    val number : TextView = itemView.findViewById(R.id.phone)
    val call : Button =itemView.findViewById(R.id.call_btn)
}
interface  ListBtnClicked {
    fun onBtnClicked(userInfo: UserInfo)

}