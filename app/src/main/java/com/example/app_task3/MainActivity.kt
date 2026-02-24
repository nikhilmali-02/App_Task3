package com.example.app_task3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.getPosts().enqueue(object : Callback<List<post>> {
            override fun onResponse(
                call: Call<List<post>>,
                response: Response<List<post>>
            ) {
                if (response.isSuccessful) {
                    recyclerView.adapter = PostAdapter(response.body() ?: emptyList())
                }
            }

            override fun onFailure(call: Call<List<post>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}