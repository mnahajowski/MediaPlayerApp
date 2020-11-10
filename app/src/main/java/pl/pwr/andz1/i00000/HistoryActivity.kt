package pl.pwr.andz1.i00000

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.andz1.i00000.bmi.BmiHistory
import pl.pwr.andz1.i00000.bmi.BmiResultObject
import pl.pwr.andz1.i00000.bmi.HistoryRecyclerAdapter

class HistoryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val title = intent.getBundleExtra("Bundle")
        val resultList = title?.getSerializable("mytext") as BmiHistory

        findViewById<TextView>(R.id.label_result).text = "${getString(R.string.tab)}" +
                "${getString(R.string.tab)} " +
                "BMI  Height        Mass ${getString(R.string.tab)} Unit "

        viewManager = LinearLayoutManager(this)
        viewAdapter = HistoryRecyclerAdapter(resultList.get())

        recyclerView = findViewById<RecyclerView>(R.id.myRecyclerView).apply {

            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }
    }
}