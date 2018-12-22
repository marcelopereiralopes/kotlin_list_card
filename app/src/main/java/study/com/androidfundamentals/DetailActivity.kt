package study.com.androidfundamentals

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        titleDetail.text = intent.getStringExtra("title")
        Glide.with(this).load(intent.getIntExtra("imageResource", 0)).into(sportsImageDetail)
    }
}
