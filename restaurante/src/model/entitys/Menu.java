/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entitys;

import java.sql.ResultSet;


public class Menu {
    
    private int iditem_menu;
    private String nome_produto;
    private String ingredientes;
    private Categoria categoria;
    private float preco;
    private float preco_producao;
    private boolean disponibilidade;

    
    public Menu() {
        this.iditem_menu = 0;
        this.nome_produto = "";
        this.ingredientes = "";
        this.categoria = new Categoria();
        this.preco = 0;
        this.preco_producao = 0;
        this.disponibilidade = true;
    }
    
    public Menu(int iditem_menu, String nome_produto, String ingredientes, Categoria categoria, float preco, float preco_producao, boolean disponibilidade) {
        this.iditem_menu = iditem_menu;
        this.nome_produto = nome_produto;
        this.ingredientes = ingredientes;
        this.categoria = categoria;
        this.preco = preco;
        this.preco_producao = preco_producao;
        this.disponibilidade = disponibilidade;
    }
    
    public Menu(String nome_produto, String ingredientes, Categoria categoria, float preco, float preco_producao, boolean disponibilidade) {
        this.iditem_menu = 0;
        this.nome_produto = nome_produto;
        this.ingredientes = ingredientes;
        this.categoria = categoria;
        this.preco = preco;
        this.preco_producao = preco_producao;
        this.disponibilidade = disponibilidade;
    }

    public int getIditem_menu() {
        return iditem_menu;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public float getPreco() {
        return preco;
    }

    public float getPreco_producao() {
        return preco_producao;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setIditem_menu(int iditem_menu) {
        this.iditem_menu = iditem_menu;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public void setPreco_producao(float preco_producao) {
        this.preco_producao = preco_producao;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    
    
}
