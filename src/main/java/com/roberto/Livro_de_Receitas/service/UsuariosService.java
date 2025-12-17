package com.roberto.Livro_de_Receitas.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.roberto.Livro_de_Receitas.model.UsuariosDB;
import com.roberto.Livro_de_Receitas.repository.UsuariosRepository;

@Service
public class UsuariosService {
    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuariosService(UsuariosRepository usuariosRepository){
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UsuariosDB registrarUsuarios(String username, String password){
        String senhaCriptografada = passwordEncoder.encode(password);
        UsuariosDB usuarios = new UsuariosDB(username, senhaCriptografada);
        return usuariosRepository.save(usuarios);
    }

    public Optional<UsuariosDB> buscarUsername(String username){
        return usuariosRepository.findByUsername(username);
    }
}
