package com.example.cleanarchitecture.feature.resume.data.datasource

import android.content.SharedPreferences
import com.example.cleanarchitecture.core.ResumeFormatter
import com.example.cleanarchitecture.feature.resume.data.entity.Resume

interface ResumeLocalDataSource {
    fun saveResume(resume: Resume)
    fun getResume(): Resume?
    fun deleteResume()
}

class SharedPrefLocalDataSource(
    private val sharedPref: SharedPreferences
): ResumeLocalDataSource{

    private val KEY_RESUME = "key_resume"
    private val separator = "####"

    override fun saveResume(resume: Resume) {
        sharedPref.edit()
            .putString(KEY_RESUME, ResumeFormatter.stringResume(resume, separator))
            .apply()
    }

    override fun getResume(): Resume? {
        val resumeString = sharedPref.getString(KEY_RESUME, null)

        if(resumeString?.contains(separator) == true){
            return ResumeFormatter.formatResume(resumeString, separator)
        }
        return null
    }

    override fun deleteResume() {
        sharedPref.edit().remove(KEY_RESUME).apply()
    }

}