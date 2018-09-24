/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entitys;


public class Reserva {
    
    private final Cliente idcliente;
    private final Funcionario idfuncionario;
    private final Mesa idmesa;
    private final int idreserva;
    private final String horario;
    private final int quantidade_pessoas;

    public Reserva(Cliente idcliente, Funcionario idfuncionario, Mesa idmesa, int idreserva, String horario, int quantidade_pessoas) {
        this.idcliente = idcliente;
        this.idfuncionario = idfuncionario;
        this.idmesa = idmesa;
        this.idreserva = idreserva;
        this.horario = horario;
        this.quantidade_pessoas = quantidade_pessoas;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public Funcionario getIdfuncionario() {
        return idfuncionario;
    }

    public Mesa getIdmesa() {
        return idmesa;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public String getHorario() {
        return horario;
    }

    public int getQuantidade_pessoas() {
        return quantidade_pessoas;
    }
    
}
