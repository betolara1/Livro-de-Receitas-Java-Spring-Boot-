package com.roberto.Livro_de_Receitas.repository;

import org.springframework.stereotype.Repository;
import com.roberto.Livro_de_Receitas.model.UsuariosDB;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosDB, Long> {
    Optional<UsuariosDB> findByUsername(String username);
}
