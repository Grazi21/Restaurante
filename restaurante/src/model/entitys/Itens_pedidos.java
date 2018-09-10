/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entitys;

/**
 *
 * @author pet
 */
public class Itens_pedidos {
    
    private int iditens_pedidos;
    private Pedido idpedido;
    private Menu iditem_menu;

    public Itens_pedidos() {
        this.iditens_pedidos = 0;
        this.idpedido = new Pedido();
        this.iditem_menu = new Menu();
    }
    
    public Itens_pedidos(int iditens_pedidos, Pedido idpedido, Menu iditem_menu) {
        this.iditens_pedidos = iditens_pedidos;
        this.idpedido = idpedido;
        this.iditem_menu = iditem_menu;
    }
    
    public Itens_pedidos(Pedido idpedido, Menu iditem_menu) {
        this.iditens_pedidos = 0;
        this.idpedido = idpedido;
        this.iditem_menu = iditem_menu;
    }

    public int getIditens_pedidos() {
        return iditens_pedidos;
    }

    public Pedido getIdpedido() {
        return idpedido;
    }

    public Menu getIditem_menu() {
        return iditem_menu;
    }

    public void setIditens_pedidos(int iditens_pedidos) {
        this.iditens_pedidos = iditens_pedidos;
    }

    public void setIdpedido(Pedido idpedido) {
        this.idpedido = idpedido;
    }

    public void setIditem_menu(Menu iditem_menu) {
        this.iditem_menu = iditem_menu;
    }
    
    
    
}
