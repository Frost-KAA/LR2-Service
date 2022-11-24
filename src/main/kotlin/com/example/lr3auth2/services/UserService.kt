package com.example.lr3auth2.services

import com.example.lr3auth2.models.User
import com.example.lr3auth2.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService{

    fun save(user: User): User {
        return this.userRepository.save(user)
    }

    fun findByEmail(email: String): User? {
        return this.userRepository.findByEmail(email)
    }

    fun getById(id: Int): User {
        return this.userRepository.getById(id)
    }

    override fun loadUserByUsername(username: String?): User? {
        val user: User? = username?.let { userRepository.findByEmail(it) }
        return user
    }
}