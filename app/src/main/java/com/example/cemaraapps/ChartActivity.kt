package com.example.cemaraapps

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cemaraapps.databinding.ActivityChartBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class ChartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChartBinding
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backChart.setOnClickListener{
            super.onBackPressed()
        }
        pieChart = binding.PieChart
        setupPieChart()
        loadPieChart()

    }

    private fun setupPieChart(){
        pieChart.isDrawHoleEnabled = true
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelTextSize(15F)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.setCenterText("Task \nDistribution")
        pieChart.setCenterTextSize(20F)
        pieChart.getDescription().setEnabled(false)


        val l = pieChart.getLegend()
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP)
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
        l.setOrientation(Legend.LegendOrientation.VERTICAL)
        l.setDrawInside(false)
        l.isEnabled = true
    }

    private fun loadPieChart(){
        val entries = mutableListOf<PieEntry>()
        entries.add(PieEntry(0.1f,"Ibu"))
        entries.add(PieEntry(0.1f,"Raka"))
        entries.add(PieEntry(0.1f,"Ayah"))
        entries.add(PieEntry(0.1f,"Ibu"))
        entries.add(PieEntry(0.1f,"Raka"))

        val colors = mutableListOf<Int>()
        for(color in ColorTemplate.MATERIAL_COLORS){
            colors.add(color)
        }
        for(color in ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color)
        }

        val dataset = PieDataSet(entries, "Keterangan")
        dataset.setColors(colors)

        val data = PieData(dataset)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(15f)
        data.setValueTextColor(Color.BLACK)

        pieChart.setData(data)
        pieChart.invalidate()

    }
}