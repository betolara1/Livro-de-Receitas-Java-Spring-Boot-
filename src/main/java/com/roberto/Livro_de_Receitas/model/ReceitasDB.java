package com.roberto.Livro_de_Receitas.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "receitas")
public class ReceitasDB {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable // <-- ESSA ANOTAÇÃO PARA JPA USADO PARA CRIAR UM ARRAY DE OBJETOS
    public static class Ingredient{
        private String item;
        private String quantity;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String prepTime;
    private String difficulty;
    private String temperature;

    @ElementCollection // <-- ESSA ANOTAÇÃO PARA USAR ARRAYs
    private List<Ingredient> ingredients;//: array de objetos { item: string; quantity: string }
    
    @ElementCollection
    private List<String> instructions;

    //private String cookTime;
    //private String servings;
    //private String category;
    //private String imageUrl;
    //private int rating;
    //private int favorites;

    /*
    @ElementCollection
    private List<String> tags;
    
    @ElementCollection
    private List<String> images;
    */
}
