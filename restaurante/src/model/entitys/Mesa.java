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
public class Mesa {
    
    private int idmesa;
    private String status;

    public Mesa() {
        this.idmesa = 0;
        this.status = "";
    }
    
    public Mesa(int idmesa, String status) {
        this.idmesa = idmesa;
        this.status = status;
    }
    
    public Mesa(String status) {
        this.idmesa = 0;
        this.status = status;
    }

    public int getIdmesa() {
        return idmesa;
    }

    public String getStatus() {
        return status;
    }

    public void setIdmesa(int idmesa) {
        this.idmesa = idmesa;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
