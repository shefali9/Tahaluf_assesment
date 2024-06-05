package com.shefali.tahalufassessment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shefali.tahalufassessment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UniversityViewModel
    private lateinit var adapter: UniversityAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[UniversityViewModel::class.java]
        adapter = UniversityAdapter { university ->
            val intent = Intent(this, UniversityDetailActivity::class.java)
            intent.putExtra("university", university)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.universities.observe(this) { universities ->
            Log.e("universities",universities.toString())
            adapter.submitList(universities)
        }

        viewModel.fetchUniversities(this@MainActivity)
        viewModel.error.observe(this) { errorMessage ->
            Log.e("error",errorMessage)
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
    }
