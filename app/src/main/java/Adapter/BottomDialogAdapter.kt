package Adapter

import Data_Class.BottomDialogItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cona_android.R

class BottomDialogAdapter(val items: Array<BottomDialogItem>): RecyclerView.Adapter<BottomDialogAdapter.viewHolder>() {
    class viewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.Name)
        val address = itemView.findViewById<TextView>(R.id.address)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val comment = itemView.findViewById<TextView>(R.id.Comment)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_card,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.name.text = items.get(position).name
        holder.address.text = items.get(position).address
        holder.imageView.setImageResource(items.get(position).imageView)
        holder.comment.text = items.get(position).comment
    }



    override fun getItemCount(): Int {
        return items.size
    }




}