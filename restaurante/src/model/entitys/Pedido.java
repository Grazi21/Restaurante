/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entitys;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author pet
 */
public class Pedido {
    
    private int idpedido;
    private Cliente cliente;
    private Funcionario funcionario;
    private ArrayList<Mesa> mesas;
    private ArrayList<Itens_pedidos> itens_pedios;
    private Date data;
    private float pagamento;
    private float troco;

    public Pedido() {
        this.idpedido = 0;
        this.cliente = new Cliente();
        this.funcionario = new Funcionario();
        this.mesas = new ArrayList<>();
        this.data = new Date();
        this.itens_pedios = new ArrayList<>();
        this.pagamento = 0;
        this.troco = 0;
    }
    
    public Pedido(int idpedido, Cliente cliente, Funcionario funcionario, ArrayList<Mesa> mesas, Date data, ArrayList<Itens_pedidos> itens,float pagamento, float troco) {
        this.idpedido = idpedido;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.mesas = mesas;
        this.data = data;
        this.itens_pedios = itens;
        this.pagamento = pagamento;
        this.troco = troco;
    }
    
    public Pedido(Cliente cliente, Funcionario funcionario, ArrayList<Mesa> mesas, Date data, ArrayList<Itens_pedidos> itens,float pagamento, float troco) {
        this.idpedido = 0;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.mesas = mesas;
        this.data = data;
        this.itens_pedios = itens;
        this.pagamento = pagamento;
        this.troco = troco;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public ArrayList<Itens_pedidos> getItens() {
        return itens_pedios;
    }

    public Date getData() {
        return data;
    }

    public float getPagamento() {
        return pagamento;
    }

    public float getTroco() {
        return troco;
    }
    
    public String getMesasString(){
        String str = "";
        for(int i = 0; i < mesas.size(); i++){
            str += mesas.get(i).getIdmesa()+";";
        }
        
        return str;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setMesas(ArrayList<Mesa> mesas) {
        this.mesas = mesas;
    }

    public void setItens(ArrayList<Itens_pedidos> itens) {
        this.itens_pedios = itens;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setPagamento(float pagamento) {
        this.pagamento = pagamento;
    }

    public void setTroco(float troco) {
        this.troco = troco;
    }

    
}
