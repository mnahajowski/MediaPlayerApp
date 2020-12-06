package pl.pwr.andz1.i00000.bmi

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.andz1.i00000.R
import pl.pwr.andz1.i00000.databinding.SingleResultLayoutBinding
import androidx.databinding.DataBindingUtil

class HistoryRecyclerAdapter(private val myDataset: ArrayList<BmiResultObject>) :
    RecyclerView.Adapter<HistoryRecyclerAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: SingleResultLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {
        val itemLayoutBinding : SingleResultLayoutBinding = itemView
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MyViewHolder {

        val itemLayoutBinding = DataBindingUtil.inflate<SingleResultLayoutBinding>(
                LayoutInflater.from(parent.context), R.layout.single_result_layout, parent, false)
        return MyViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val myObject = myDataset[position]
        val index = position + 1
        holder.itemLayoutBinding.numberTextView.text = "$index."
        holder.itemLayoutBinding.bmiTextView.text = myObject.bmi_result.toString()
        holder.itemLayoutBinding.heightTv.text = myObject.height.toString()
        holder.itemLayoutBinding.massTextView.text = myObject.weight.toString()
        holder.itemLayoutBinding.unitTextView.text = myObject.unit
        holder.itemLayoutBinding.dateTextView.text = myObject.date.toString()

    }

    override fun getItemCount() = myDataset.size
}