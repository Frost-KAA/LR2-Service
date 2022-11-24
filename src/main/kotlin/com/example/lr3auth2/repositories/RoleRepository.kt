package com.example.lr3auth2.repositories

import com.example.lr3auth2.models.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository: JpaRepository<Role, String> {
    fun findByName(id: String): Role
}