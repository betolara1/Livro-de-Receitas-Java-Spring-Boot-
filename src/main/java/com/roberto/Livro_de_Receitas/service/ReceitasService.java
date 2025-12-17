package com.roberto.Livro_de_Receitas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.roberto.Livro_de_Receitas.DTO.ReceitasDTO;
import com.roberto.Livro_de_Receitas.exception.RecursoNaoEncontradoException;
import com.roberto.Livro_de_Receitas.model.ReceitasDB;
import com.roberto.Livro_de_Receitas.repository.ReceitasRepository;

@Service
public class ReceitasService {

    // INSTANCIAR A CLASSE DE RECEITASREPOSITORY
    private final ReceitasRepository receitasRepository;
    public ReceitasService (ReceitasRepository receitasRepository){
        this.receitasRepository = receitasRepository;
    }


    // CLASSE PARA LISTAR TODOS OS DADOS DA TABELA RECEITAS
    // 
    public List<ReceitasDTO> listarReceitas(){
        List<ReceitasDB> receitasDoBanco = receitasRepository.findAll();

        // 2. Converte usando o SEU construtor e Java Streams
        return receitasDoBanco.stream()
                .map(ReceitasDTO::new) // <--- Aqui ele chama seu construtor automaticamente para cada item
                .collect(Collectors.toList());
    }


    // CLASSE QUE BUSCA OS DADOS POR ID
    // MÉTODO PARA BUSCAR DO DTO PARA MOSTRAR APENAS O NECESSÁRIO ("O QUE EU REALMENTE QUERO MOSTRAR AO USUARIO FINAL")
    public ReceitasDTO buscarPorId(Long id) {
        // BUSCA O ID NO BANCO DE DADOS
        ReceitasDB receita = receitasRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException
                ("Receita não encontrada! ID: " + id)); // EXCEÇÃO ESPECIFICA CASO NÃO ENCONTRA O ID

                
        // O SERVICE JÁ SABE O QUE PRECISA RETORNAR POR CAUSA DO CONSTRUTOR FEITO NO DTO
        return new ReceitasDTO(receita); 
    }

    
    //CLASSE PARA SALVAR AS RECEITAS
    public ReceitasDB salvarReceita(ReceitasDB receita){
        return receitasRepository.save(receita);
    }

    //CLASSE PARA DELETAR UMA RECEITA
    public void deletarReceita(Long id){

        if(!receitasRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Receita com o ID "+id+" não encontrado!");
        }

        receitasRepository.deleteById(id);
    }

}


    /*
    // ESSE METODO É PARA BUSCAR DIRETO DO RECEITASDB
    // CLASSE QUE BUSCA OS DADOS POR ID
    public ReceitasDB buscarPorId(Long id){
        return receitasRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException
                ("Receita com ID "+id+" não encontrada!")); //CRIO A EXCEÇÃO SE NÃO ENCONTRAR A RECEITA
    }*/