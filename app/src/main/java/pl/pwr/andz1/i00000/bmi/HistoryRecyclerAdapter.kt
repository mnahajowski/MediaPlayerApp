package pl.pwr.andz1.i00000.bmi

import android.content.res.Resources
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class HistoryRecyclerAdapter(private val myDataset: ArrayList<BmiResultObject>) :
    RecyclerView.Adapter<HistoryRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): HistoryRecyclerAdapter.MyViewHolder {

        val tv = TextView(parent.context)
        tv.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
        )

        return MyViewHolder(tv)
    }
    val Int.px: Float
        get() = (this * Resources.getSystem().displayMetrics.density).toFloat()


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val myObject = myDataset[position] as BmiResultObject
        val index = position + 1
        holder.textView.text = java.lang.String.format("%3d.     ", index) + myObject.toString()
        holder.textView.textSize = 8.px

    }

    override fun getItemCount() = myDataset.size
}