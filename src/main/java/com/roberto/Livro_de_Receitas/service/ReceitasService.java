package com.roberto.Livro_de_Receitas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.roberto.Livro_de_Receitas.DTO.ReceitasDTO;
import com.roberto.Livro_de_Receitas.exception.RecursoNaoEncontradoException;
import com.roberto.Livro_de_Receitas.model.ReceitasDB;
import com.roberto.Livro_de_Receitas.model.UsuariosDB;
import com.roberto.Livro_de_Receitas.repository.ReceitasRepository;
import com.roberto.Livro_de_Receitas.repository.UsuariosRepository;

@Service
public class ReceitasService {

    @Autowired
    private ReceitasRepository receitasRepository;
    @Autowired
    private UsuariosRepository usuariosRepository;


    private UsuariosDB getUsuarioLogado() {
        // PEGA O EMAIL/USERNAME QUE ESTAVA NO TOKEN JWT
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // BUSCA NO BANCO OS DADOS COMPLETOS DESSE USUÁRIO
        return usuariosRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }


    // 2. CRIAR RECEITA (AUTOMATICAMENTE VINCULADA)
    public ReceitasDB salvarReceita(ReceitasDB novaReceita) {
        UsuariosDB usuario = getUsuarioLogado(); // DESCOBRE QUEM TÁ MANDANDO A REQUISIÇÃO
        novaReceita.setUsuario(usuario); // CARIMBA A RECEITA COM O USUARIO
        return receitasRepository.save(novaReceita);
    }


    // 3. LISTAR (APENAS AS MINHAS)
    public List<ReceitasDTO> listarReceitas() {
        UsuariosDB usuario = getUsuarioLogado();
        List<ReceitasDB> receitasDB = receitasRepository.findByUsuario(usuario);

        return receitasDB.stream()
        .map(ReceitasDTO::new)
        .collect(Collectors.toList()); // TRAZ SÓ AS DO USUARIO
    }

    public ReceitasDTO buscarPorId(Long id) {
        UsuariosDB usuariosDB = getUsuarioLogado();

        ReceitasDB receitaDB = receitasRepository.findByIdAndUsuario(id, usuariosDB)
                .orElseThrow(() -> new RecursoNaoEncontradoException
                ("Receita não encontrada! ID: " + id)); // EXCEÇÃO ESPECIFICA CASO NÃO ENCONTRA O ID
    
        return new ReceitasDTO(receitaDB);
    }

    //CLASSE PARA DELETAR UMA RECEITA
    public void deletarReceita(Long id){

        if(!receitasRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Receita com o ID "+id+" não encontrado!");
        }

        receitasRepository.deleteById(id);
    }


}
