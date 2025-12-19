/* 
    No Repository é onde eu pego os dados do banco de dados
    Crio minhas funções para retornar os dados que quiser
*/

package com.roberto.Livro_de_Receitas.repository;

import org.springframework.stereotype.Repository;
import com.roberto.Livro_de_Receitas.model.ReceitasDB;
import com.roberto.Livro_de_Receitas.model.UsuariosDB;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ReceitasRepository extends JpaRepository<ReceitasDB, Long> {

    // O SPRING CRIA O SQL AUTOMÁTICO: SELECT * FROM RECEITAS WHERE USUARIO_ID = ?
    List<ReceitasDB> findByUsuario(UsuariosDB usuario);
        
    // OPCIONAL: BUSCAR POR ID E USUÁRIO (PARA GARANTIR SEGURANÇA NA EDIÇÃO)
    Optional<ReceitasDB> findByIdAndUsuario(Long id, UsuariosDB usuario);
}
