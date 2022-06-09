package br.com.devLearn.application.model

import java.time.LocalDate
import javax.persistence.*

@Entity
data class Videos (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    val description: String,
    var date: LocalDate,
    var url: String,
    @ManyToOne
    val curso: Courses,
)