package com.example.lr3auth2.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Role (
    @Id
    var name: String = "",

    @ManyToMany(
        mappedBy = "roles",
        fetch = FetchType.EAGER,
        cascade = [ CascadeType.PERSIST],
    )
    @JsonIgnore
    var accounts: MutableList<User> = mutableListOf()
)