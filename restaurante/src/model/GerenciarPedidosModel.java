/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.GerenciarPedidos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entitys.Categoria;
import model.entitys.Cliente;
import model.entitys.Funcionario;
import model.entitys.Itens_pedidos;
import model.entitys.Menu;
import model.entitys.Mesa;
import model.entitys.Pedido;

/**
 *
 * @author gabryel
 */
public class GerenciarPedidosModel {
    private final Conexao db;

    public GerenciarPedidosModel() {
        this.db = Conexao.getConnection();
    }
    
    /******************************************
    *                                         *
    *           Criação de Classes            *       
    *                                         *
    *******************************************/
    
    public Categoria novaCategoria(int id){
        ResultSet query = db.query("SELECT * FROM categoria WHERE idcategoria = "+ id);
        
        try {
            if(query.next()){
                return new Categoria(id, query.getString("nome"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Categoria();
    }
    
    public Cliente novoCliente(int id){
        ResultSet query = db.query("SELECT * FROM cliente INNER JOIN pessoa ON  cliente.idpessoa = pessoa.idpessoa WHERE idcliente = "+ id);
        
        try {
            if(query.next()){
                return new Cliente(id,
                                   query.getDate("data_cadastro"),
                                   query.getInt("idpessoa"),
                                   query.getString("nome"),
                                   query.getString("cpf"),
                                   query.getString("telefone"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Cliente();
    }
    
    public Funcionario novoFuncionario(int id){
        ResultSet query = db.query("SELECT * FROM funcionario INNER JOIN pessoa ON  funcionario.idpessoa = pessoa.idpessoa WHERE idfuncionario = "+ id);
        
        try {
            if(query.next()){
                return new Funcionario(id,
                                       query.getDate("data_de_admissao"),
                                       query.getString("cargo"),
                                       query.getString("usuario_sistema"),
                                       query.getString("senha_sistema"),
                                       query.getBoolean("ativo"),
                                       query.getInt("idpessoa"),
                                       query.getString("nome"),
                                       query.getString("cpf"),
                                       query.getString("telefone"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Funcionario();
    }
    
    public Mesa novaMesa(int id){
        ResultSet query = db.query("SELECT * FROM mesa WHERE idmesa = "+ id);
        
        try {
            if(query.next()){
                return new Mesa(id, query.getString("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Mesa();
    }
    
    public Menu novoMenuItem(int id){
        ResultSet query = db.query("SELECT * FROM menu WHERE iditem_menu = "+ id);
        
        try {
            if(query.next()){
                return new Menu(id,
                                query.getString("nome_produto"),
                                query.getString("ingredientes"),
                                novaCategoria(query.getInt("idcategoria")),
                                query.getFloat("preco"),
                                query.getFloat("preco_producao"),
                                query.getBoolean("disponibilidade"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Menu();
    }
    
    public ArrayList<Mesa> mesasToArray(String mesa_str){
        ArrayList<Mesa> mesas_array = new ArrayList<>();
        String[] mesas = mesa_str.split(";");
        
        for (String mesa : mesas) {
            mesas_array.add(novaMesa(Integer.parseInt(mesa)));
        }
        
        return mesas_array;
    }
    
    
    /******************************************
    *                                         *
    *           Listas de Classes             *       
    *                                         *
    *******************************************/
    public ArrayList<Menu> listaItensMenu(String pesquisa){
        String where = "";
        ArrayList<Menu> itens_menu = new ArrayList<>();
        
        if(!"".equals(pesquisa)){
            where = "WHERE nome_produto like '%"+pesquisa+"%' or iditem_menu LIKE '%"+pesquisa+"%'";
        }
        
        ResultSet query = db.query("SELECT * FROM menu "+ where);
        
        try {
            
            while(query.next()){                
                Menu current_item = new Menu(query.getInt("iditem_menu"),
                                             query.getString("nome_produto"),
                                             query.getString("ingredientes"),
                                             novaCategoria((query.getInt("idcategoria"))),
                                             query.getFloat("preco"),
                                             query.getFloat("preco_producao"),
                                             query.getBoolean("disponibilidade"));
                
                itens_menu.add(current_item);  
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarPedidosModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return itens_menu;
    }
    
    public ArrayList<Cliente> listaCliente(String pesquisa){
        String where = "";
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        if(!"".equals(pesquisa)){
            where = "WHERE nome like '%"+pesquisa+"%' or cpf LIKE '%"+pesquisa+"%'";
        }
        
        ResultSet query = db.query("SELECT * FROM cliente INNER JOIN pessoa ON  cliente.idpessoa = pessoa.idpessoa "+ where);
        
        try {
            
            while(query.next()){                
                Cliente current_cliente = new Cliente(query.getInt("idcliente"),
                                                      query.getDate("data_cadastro"),
                                                      query.getInt("idpessoa"),
                                                      query.getString("nome"),
                                                      query.getString("cpf"),
                                                      query.getString("telefone"));
                
                clientes.add(current_cliente);  
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarPedidosModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return clientes;
    }
    
    public ArrayList<Pedido> listaPedidos(String pesquisa){
        String where = "";
        ArrayList<Pedido> pedidos = new ArrayList<>();
        
        if(!"".equals(pesquisa)){
            where = "WHERE idmesa like '%"+pesquisa+"%' or idcliente LIKE '%"+pesquisa+"%'";
        }
        
        ResultSet query = db.query("SELECT * FROM pedido "+ where);
        
        try {
            
            while(query.next()){
                
                Pedido current_pedido = new Pedido();
                
                current_pedido.setIdpedido(query.getInt("idpedido"));
                current_pedido.setCliente(novoCliente(query.getInt("idcliente")));
                current_pedido.setData(query.getDate("data"));
                current_pedido.setFuncionario(novoFuncionario(query.getInt("idfuncionario")));
                current_pedido.setItens(listaItensPedidos(current_pedido));
                current_pedido.setMesas(mesasToArray(query.getString("idmesa")));
                current_pedido.setPagamento(Float.parseFloat("0"));
                current_pedido.setTroco(query.getFloat("troco"));
                
                pedidos.add(current_pedido);  
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarPedidosModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return pedidos;
    }
    
    public ArrayList<Itens_pedidos> listaItensPedidos(Pedido pedido){

        ArrayList<Itens_pedidos> itens = new ArrayList<>();
       
        ResultSet query = db.query("SELECT * FROM itens_pedidos WHERE idpedido = "+ pedido.getIdpedido());
        
        try {
            
            while(query.next()){                
               Itens_pedidos current_menu_item = new Itens_pedidos(pedido, novoMenuItem(query.getInt("iditem_menu")));
                
                itens.add(current_menu_item);  
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarPedidosModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return itens;
    }
}
