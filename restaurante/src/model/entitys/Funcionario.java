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
public class Funcionario extends Pessoa{
    
    private int idfuncionario;
    private Date data_admissao;
    private String cargo;
    private String usuario_sistema;
    private String senha_sistema;
    private boolean ativo;
    
    public Funcionario() {
        super();
        this.idfuncionario = 0;
        this.data_admissao = new Date();
        this.cargo = "";
        this.usuario_sistema = "";
        this.senha_sistema = "";
        this.ativo = true;
    }
    
    public Funcionario(int idfuncionario, Date data_admissao, String cargo, String usuario_sistema, String senha_sistema, boolean ativo, int idpessoa, String nome, String cpf, String telefone) {
        super(idpessoa, nome, cpf, telefone);
        this.idfuncionario = idfuncionario;
        this.data_admissao = data_admissao;
        this.cargo = cargo;
        this.usuario_sistema = usuario_sistema;
        this.senha_sistema = senha_sistema;
        this.ativo = ativo;
    }
    
    public Funcionario(Date data_admissao, String cargo, String usuario_sistema, String senha_sistema, boolean ativo, String nome, String cpf, String telefone) {
        super(0, nome, cpf, telefone);
        this.idfuncionario = 0;
        this.data_admissao = data_admissao;
        this.cargo = cargo;
        this.usuario_sistema = usuario_sistema;
        this.senha_sistema = senha_sistema;
        this.ativo = ativo;
    }

    public int getIdfuncionario() {
        return idfuncionario;
    }

    public Date getData_admissao() {
        return data_admissao;
    }

    public String getCargo() {
        return cargo;
    }

    public String getUsuario_sistema() {
        return usuario_sistema;
    }

    public String getSenha_sistema() {
        return senha_sistema;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setIdfuncionario(int idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public void setData_admissao(Date data_admissao) {
        this.data_admissao = data_admissao;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setUsuario_sistema(String usuario_sistema) {
        this.usuario_sistema = usuario_sistema;
    }

    public void setSenha_sistema(String senha_sistema) {
        this.senha_sistema = senha_sistema;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
