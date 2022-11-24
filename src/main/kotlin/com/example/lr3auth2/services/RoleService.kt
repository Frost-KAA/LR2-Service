package com.example.lr3auth2.services

import com.example.lr3auth2.models.Role
import com.example.lr3auth2.models.User
import com.example.lr3auth2.repositories.RoleRepository
import com.example.lr3auth2.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class RoleService(private val roleRepository: RoleRepository) {

    fun save(role: Role): Role {
        return this.roleRepository.save(role)
    }

    fun getById(id: String): Role {
        return this.roleRepository.getById(id)
    }
}