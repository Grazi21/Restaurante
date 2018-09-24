/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entitys;


public class Itens_pedidos_finalizados {
    
    private final int iditens_pedido_finalizado;
    private final Pedidos_finalizados idpedido_finalizado;
    private final Itens_pedidos iditens_pedidos;
    private final String nome_produto;
    private final String ingredientes;
    private final String categoria;
    private final float preco;
    private final float preco_producao;

    public Itens_pedidos_finalizados(int iditens_pedido_finalizado, Pedidos_finalizados idpedido_finalizado, Itens_pedidos iditens_pedidos, String nome_produto, String ingredientes, String categoria, float preco, float preco_producao) {
        this.iditens_pedido_finalizado = iditens_pedido_finalizado;
        this.idpedido_finalizado = idpedido_finalizado;
        this.iditens_pedidos = iditens_pedidos;
        this.nome_produto = nome_produto;
        this.ingredientes = ingredientes;
        this.categoria = categoria;
        this.preco = preco;
        this.preco_producao = preco_producao;
    }

    public int getIditens_pedido_finalizado() {
        return iditens_pedido_finalizado;
    }

    public Pedidos_finalizados getIdpedido_finalizado() {
        return idpedido_finalizado;
    }

    public Itens_pedidos getIditens_pedidos() {
        return iditens_pedidos;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getCategoria() {
        return categoria;
    }

    public float getPreco() {
        return preco;
    }

    public float getPreco_producao() {
        return preco_producao;
    }
    
}
