

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiproject.Item
import com.example.apiproject.ItemWrapper
import com.example.apiproject.RetrofitHelper
import com.example.apiproject.VocaloidAdapter
import com.example.apiproject.VocaloidService
import com.example.apiproject.databinding.ActivityVocaloidListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VocaloidListActivity : AppCompatActivity() {

    val TAG = "VocaloidListActivity"

    private lateinit var adapter: VocaloidAdapter
    private lateinit var binding: ActivityVocaloidListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVocaloidListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = RetrofitHelper.getInstance()
        val vocaloidService = retrofit.create(VocaloidService::class.java)
        //query variables
        val vocaloidCall = vocaloidService.getSongs(
            "Original",
            "2020-01-01",
            false,
            false,
            false,
            false,
            false,
            "Finished",
            0,
            200,
            false,
            "RatingScore",
            false,
            "MainPicture,Lyrics",
            "English"
        )

        vocaloidCall.enqueue(object : Callback<ItemWrapper> {
            override fun onResponse(
                call: Call<ItemWrapper>, response: Response<ItemWrapper>
            ) {
                // this is where you get your data
                // this is where you will set up your adapter for recyclerview
                adapter = VocaloidAdapter(response.body()?.items ?: listOf())
                binding.recyclerViewVocaloidListList.adapter = adapter
                binding.recyclerViewVocaloidListList.layoutManager = LinearLayoutManager(this@VocaloidListActivity)
                // don't forget a null check before trying to use the data
                // response.body() contains the object in the <> after Response
                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<ItemWrapper>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }
}