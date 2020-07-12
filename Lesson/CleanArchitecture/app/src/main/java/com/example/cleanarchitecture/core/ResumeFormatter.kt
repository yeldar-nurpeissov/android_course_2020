package com.example.cleanarchitecture.core

import com.example.cleanarchitecture.feature.resume.data.entity.Resume

class ResumeFormatter {

    companion object {

        fun stringResume(resume: Resume, separator: String = "\n"): String {
            return resume.firstName + separator +
                    resume.lastName + separator +
                    resume.birthday + separator +
                    "${resume.weight}$separator" +
                    "${resume.height}$separator" +
                    resume.aboutMe
        }

        fun formatResume(resumeString: String, separator: String = "\n"): Resume {
            val resumeParts = resumeString.split(separator)
            return Resume(
                firstName = resumeParts[0],
                lastName = resumeParts[1],
                birthday = resumeParts[2],
                weight = resumeParts[3].toInt(),
                height = resumeParts[4].toInt(),
                aboutMe = resumeParts[5]
            )
        }

    }
}