/* 
    No Repository é onde eu pego os dados do banco de dados
    Crio minhas funções para retornar os dados que quiser
*/

package com.roberto.Livro_de_Receitas.repository;

import org.springframework.stereotype.Repository;

@Repository
public class ReceitasRepository {
    public String obterReceitas(){
        return "Recebeu as receitas";
    }
}
