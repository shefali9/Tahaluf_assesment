package com.shefali.tahalufassessment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shefali.tahalufassessment.databinding.ActivityUniversityDetailBinding

class UniversityDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUniversityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUniversityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val university: University = intent.getParcelableExtra("university")!!

        binding.refresh.setOnClickListener {  val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) }
        binding.apply {
            textViewUniversityName.text = university.name
            binding.textViewUniversityCountryCode.text = university.alpha_two_code
            binding.textViewUniversityCountry.text = university.country
            binding.textViewWebPages.text = university.web_pages!!.joinToString("\n")
            textViewUniversityState.apply {
                text = university.state_province
                visibility = if (university.state_province.isNullOrEmpty()) View.GONE else View.VISIBLE
            }
        }


    }
}