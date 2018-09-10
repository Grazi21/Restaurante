/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entitys;

import java.util.Date;

/**
 *
 * @author pet
 */
public class Cliente extends Pessoa{
    
    private int idcliente;
    private Date data_cadastro;

    public Cliente() {
        super();
        this.idcliente = 0;
        this.data_cadastro = new Date();
    }
    
    public Cliente(int idcliente, Date data_cadastro, int idpessoa, String nome, String cpf, String telefone) {
        super(idpessoa, nome, cpf, telefone);
        this.idcliente = idcliente;
        this.data_cadastro = data_cadastro;
    }
    
    public Cliente(Date data_cadastro, String nome, String cpf, String telefone) {
        super(0, nome, cpf, telefone);
        this.idcliente = 0;
        this.data_cadastro = data_cadastro;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }   
}
