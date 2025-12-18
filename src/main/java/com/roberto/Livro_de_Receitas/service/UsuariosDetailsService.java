package com.roberto.Livro_de_Receitas.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.roberto.Livro_de_Receitas.model.UsuariosDB;
import com.roberto.Livro_de_Receitas.repository.UsuariosRepository;

@Service
public class UsuariosDetailsService implements UserDetailsService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosDetailsService(UsuariosRepository usuariosRepository){
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuariosDB usuariosDB = usuariosRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));

        return User.builder()
                .username(usuariosDB.getUsername())
                .password(usuariosDB.getPassword())
                .roles("USER")
                .build();
    }

}
