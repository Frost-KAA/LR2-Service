package com.example.lr3auth2.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.stream.Collectors
import javax.persistence.*


@Entity(name = "account")
class User: UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var name = ""

    @Column(unique = true)
    var email = ""

    @Column
    var passw = ""
        @JsonIgnore
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = [ CascadeType.PERSIST],
    )
    @JoinTable(
        name = "ACCOUNT_ROLE",
        joinColumns = [JoinColumn(name = "id")],
        inverseJoinColumns = [JoinColumn(name="name")]
    )
    var roles: MutableList<Role> = mutableListOf()

    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.passw)
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles
            .stream()
            .map { role ->
                SimpleGrantedAuthority(role.name)
            }.collect(Collectors.toList())
    }

    override fun getPassword(): String {
        return passw
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}