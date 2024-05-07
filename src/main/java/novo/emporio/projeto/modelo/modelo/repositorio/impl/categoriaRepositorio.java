/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package novo.emporio.projeto.modelo.modelo.repositorio.impl;

import novo.emporio.projeto.modelo.modelo.entidade.produto;

/**
 *
 * @author arthu
 */
public class categoriaRepositorio extends crudrepositorioimplento {

    public categoriaRepositorio() {
        super(produto.class);
    }

    public static void main(String[] args) {
        produto usuario = produto.builder()
                .id(1L)
                .nome("ração")
                .descricao("eu sou muito bom")
                .build();

        categoriaRepositorio repositorio = new categoriaRepositorio();

       repositorio.salvar(usuario);

    }
}
