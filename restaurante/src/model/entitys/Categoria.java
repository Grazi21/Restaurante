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
public class Categoria {
    
    private int idcategoria;
    private String nome;

    public Categoria() {
        this.idcategoria = 0;
        this.nome = "";
    }
    
    public Categoria(int idcategoria, String nome) {
        this.idcategoria = idcategoria;
        this.nome = nome;
    }
    
    public Categoria(String nome) {
        this.idcategoria = 0;
        this.nome = nome;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
