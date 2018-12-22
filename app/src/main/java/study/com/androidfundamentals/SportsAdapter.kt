package study.com.androidfundamentals

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class SportsAdapter(private val mSportsData: ArrayList<Sport>, private val mContext: Context,
                    private val listener: (Sport) -> Unit)
    : RecyclerView.Adapter<SportsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, p0, false))
    }

    override fun getItemCount(): Int {
        return mSportsData.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(mSportsData[p1], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mTitleText: TextView = itemView.findViewById(R.id.title)
        private val mNewsText: TextView = itemView.findViewById(R.id.newsTitle)
        private val mImageView: ImageView = itemView.findViewById(R.id.sportsImage)

        fun bind(currentSport: Sport, listener: (Sport) -> Unit){
            mTitleText.text = currentSport.title
            mNewsText.text = currentSport.news
            itemView.setOnClickListener { listener(currentSport) }
            Glide.with(itemView.context).load(currentSport.image).into(mImageView)
        }
    }
}