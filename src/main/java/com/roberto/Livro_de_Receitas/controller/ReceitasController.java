package com.roberto.Livro_de_Receitas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roberto.Livro_de_Receitas.DTO.ReceitasDTO;
import com.roberto.Livro_de_Receitas.model.ReceitasDB;
import com.roberto.Livro_de_Receitas.service.ReceitasService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


//GET É PARA RETORNAR AS INFORMAÇÕES (CONSULTA)
//POST É PARA INSERIR AS INFORMAÇÕES (SALVAR)
//PUT É PARA ALTERAR AS INFORMAÇÕES (ALTERAR)
//DELETE É PARA DELETAR AS INFORMAÇÕES (DELETAR)

@RestController
@RequestMapping("/api/receitas")
public class ReceitasController {

    @Autowired // ANOTAÇÃO QUE SERVE NO LUGAR DE CRIAR CONSTRUTOR 
    private ReceitasService receitasService;


    //CLASSE PARA LISTAR AS RECEITAS (RETORNA SOMENTE OS CAMPOS DA CLASSE receitaDTO)
    @GetMapping
    public ResponseEntity<List<ReceitasDTO>> listarReceitas() {
        List<ReceitasDTO> listaDtos = receitasService.listarReceitas();
        return ResponseEntity.ok(listaDtos);
    }


    // CLASSE QUE BUSCA OS DADOS POR ID
    // MÉTODO PARA BUSCAR DO DTO PARA MOSTRAR APENAS O NECESSÁRIO ("O QUE EU REALMENTE QUERO MOSTRAR AO USUARIO FINAL")
    // LOGICA FEITA NO SERVICE DO QUE RETORNAR AO USUARIO
    @GetMapping("/{id}")
    public ResponseEntity<ReceitasDTO> pegarReceita(@PathVariable Long id) {
        // O controller pede pro service um DTO, não um DB
        ReceitasDTO receitaPronta = receitasService.buscarPorId(id);
        return ResponseEntity.ok(receitaPronta);
    }
    
    //CLASSE PARA CRIAR AS RECEITAS
    @PostMapping
    public ResponseEntity<ReceitasDTO> criarReceita(@RequestBody ReceitasDB receita) {
        ReceitasDB receitasDB = receitasService.salvarReceita(receita);

        ReceitasDTO receitasDTO = new ReceitasDTO(receitasDB);
        return ResponseEntity.status(HttpStatus.CREATED).body(receitasDTO);
    }
    
    //CLASSE PARA ATUALIZAR RECEITA POR ID
    @PutMapping("/{id}")
    public ResponseEntity<ReceitasDTO> atualizarReceita(@PathVariable Long id, @RequestBody ReceitasDB receitaAtualizada) {
        ReceitasDB receitaDB = receitasService.atualizarReceita(id, receitaAtualizada);
        ReceitasDTO receitasDTO = new ReceitasDTO(receitaDB);
        return ResponseEntity.ok(receitasDTO);
    }
    
    //CLASSE PARA DELETAR RECEITAS POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReceita(@PathVariable Long id){
        receitasService.deletarReceita(id);
        return ResponseEntity.noContent().build(); //USADO PORQUE O DELETARRECEITA FOI CRIADO COMO VOID 
    }
}
