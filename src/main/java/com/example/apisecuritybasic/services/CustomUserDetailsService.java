package com.example.apisecuritybasic.services;

import com.example.apisecuritybasic.models.Users;
import com.example.apisecuritybasic.repository.UsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository ;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws  UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuario não existe"));
        return new User(
                users.getUsername(),
                users.getPassword(),
                users.isEnabled(),
                true,
                true,
                true,
                users.getRoles().stream(role -> new SimpleGrantedAuthority(role.getRoleName())).toList());
    }
}
