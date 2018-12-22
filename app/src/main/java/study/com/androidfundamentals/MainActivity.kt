package study.com.androidfundamentals

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mSportData: ArrayList<Sport> = mutableListOf<Sport>() as ArrayList<Sport>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SportsAdapter(mSportData, this.applicationContext) {
            val i = Intent(this, DetailActivity::class.java)
            i.putExtra("title", it.title)
            i.putExtra("imageResource", it.image)
            startActivity(i)
        }

        initializeData()

        initSwipe()

        floatingActionButton.setOnClickListener {
            initializeData()
        }
    }

    @SuppressLint("Recycle")
    private fun initializeData() {
        val sportListTitle = resources.getStringArray(R.array.sports_titles)
        val sportListNews = resources.getStringArray(R.array.sports_info)
        val sportListImageTypedArray = resources.obtainTypedArray(R.array.sports_images)

        mSportData.clear()

        for (i in 0 until sportListTitle.size){
            mSportData.add(Sport(sportListTitle[i], sportListNews[i], sportListImageTypedArray.getResourceId(i,0)))
        }

        sportListImageTypedArray.recycle()

        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun initSwipe() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT
                or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP, ItemTouchHelper.LEFT
                or ItemTouchHelper.RIGHT){
            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                val from = p1.adapterPosition
                val to = p2.adapterPosition
                Collections.swap(mSportData, from, to)
                recyclerView.adapter?.notifyItemMoved(from, to)

                return true
            }

            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                mSportData.removeAt(p0.adapterPosition)
                recyclerView.adapter?.notifyItemRemoved(p0.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
