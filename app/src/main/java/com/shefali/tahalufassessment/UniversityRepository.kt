package com.shefali.tahalufassessment

import android.app.ProgressDialog
import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UniversityRepository(private val dao: UniversityDao, private val api: UniversityApiService) {
    private var progressDialog: ProgressDialog? = null
    suspend fun getUniversitiesFromDb(): List<University> {
        return withContext(Dispatchers.IO) {
            dao.getAllUniversities()
        }
    }
    suspend fun fetchUniversities(context:Context): List<University> {
        showLoader(context,"Fetching data...")

        return try {
            val universities = api.getUniversities()
            dao.insertAll(universities)
            universities

        } catch (e: Exception) {
            val cachedUniversities = dao.getAllUniversities()
            cachedUniversities.ifEmpty {
                throw FetchDataException("Failed to fetch data. No data available.")
            }
        }
        finally {
            hideLoader()
        }
    }
    class FetchDataException(message: String) : Exception(message)

    private fun showLoader(context: Context,message: String) {
        progressDialog = ProgressDialog(context)
        progressDialog?.setMessage(message)
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }

    private fun hideLoader() {
        progressDialog?.dismiss()
    }
}
