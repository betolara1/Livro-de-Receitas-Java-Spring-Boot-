package com.roberto.Livro_de_Receitas.DTO;

import lombok.Data; // O @Data do Lombok já inclui Getter, Setter, ToString, etc.
import java.util.List;

import com.roberto.Livro_de_Receitas.model.ReceitasDB;
import com.roberto.Livro_de_Receitas.model.ReceitasDB.Ingredient;

// Usamos DTO para devolver o JSON para o App/Front
@Data
public class ReceitasDTO {
    
    private Long id; 
    private String title;
    private String description;

    private List<Ingredient> ingredients; 
    private List<String> instructions;

    private String usuario;

    // NÃO COLOQUEI 'PREPTIME' OU 'DIFFICULTY' DE PROPÓSITO
    // SÓ PARA MOSTRAR QUE O DTO PODE FILTRAR DADOS. 
    // DEPOIS DA PRA INSERIR OS RESTANTE DOS CAMPOS QUE PODEM SER EXIBIDOS PARA O USUARIO FINAL



    // CRIA OS CONTRUTORES PARA QUE NO SERVICE BUSQUE SOMENTE O QUE QUERO QUE RETORNE
    // CONSTRUTOR VAZIO
    public ReceitasDTO() {}

    // CONSTRUTOR QUE CONVERTE!
    public ReceitasDTO(ReceitasDB receita) {
        this.id = receita.getId();
        this.title = receita.getTitle();
        this.ingredients = receita.getIngredients();
        this.description = receita.getDescription();
        this.instructions = receita.getInstructions();

        if (receita.getUsuario() != null){
            this.usuario = receita.getUsuario().getUsername();
        }
    }
}