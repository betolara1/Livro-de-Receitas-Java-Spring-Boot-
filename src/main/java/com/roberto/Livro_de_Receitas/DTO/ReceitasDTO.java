package com.roberto.Livro_de_Receitas.DTO;

import lombok.Data; // O @Data do Lombok já inclui Getter, Setter, ToString, etc.
import java.util.List;

import com.roberto.Livro_de_Receitas.model.ReceitasDB;

// Usamos DTO para devolver o JSON para o App/Front
@Data
public class ReceitasDTO {
    
    private Long id; 
    private String title;
    private String description;

    private List<ReceitasDB.Ingredient> ingredients; 
    private List<String> instructions;

    // NÃO coloquei 'prepTime' ou 'difficulty' de propósito
    // Só para mostrar que o DTO pode filtrar dados. 
    // DEpois da pra inserir os restante dos campos que podem ser exibidos para o usuario final



    // CRIA OS CONTRUTORES PARA QUE NO SERVICE BUSQUE SOMENTE O QUE QUERO QUE RETORNE
    // Construtor vazio
    public ReceitasDTO() {}

    // Construtor que converte!
    public ReceitasDTO(ReceitasDB receita) {
        this.id = receita.getId();
        this.title = receita.getTitle();
        this.ingredients = receita.getIngredients();
        this.description = receita.getDescription();
        this.instructions = receita.getInstructions();
    }
}