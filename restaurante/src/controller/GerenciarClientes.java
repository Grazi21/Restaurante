/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.Conexao;
import view.ClienteView;


public class GerenciarClientes implements ActionListener{
    
    protected ClienteView view;
    private final Conexao db;
    private final DecimalFormat floatFormat = new DecimalFormat("0.00");
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public GerenciarClientes(ClienteView view) {
        this.view = view;
        this.db = Conexao.getConnection();
        Date date = new Date();
        view.getInputCliente_Data().setText(dateFormat.format(date));
    }
    @Override
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand() ){
            case "Limpar":
                LimparCamposCliente();
                break;
            case "Salvar":
                salva_cliente();
                break;
            case "Pesquisar":
                lista_clientes();
                break;
            default:
                System.out.println(e.getActionCommand() );
                break;
        }
    }

    private void LimparCamposCliente() {
        view.getInputCliente_Nome().setText("");
        view.getInputCliente_Cpf().setText("");
        view.getInputCliente_Telefone().setText("");
        view.getInputCliente_Data().setText("");
        view.getInputCliente_Codigo().setText("");
        view.getInputPesquisa_Cliente().setText("");
        Date date = new Date();
        view.getInputCliente_Data().setText(dateFormat.format(date));
        clear_row((DefaultTableModel) view.getjTable1().getModel());
    }

    private void salva_cliente() {
        String nome, cpf, telefone, data;
        int codigo;
        
        if(   "".equals(view.getInputCliente_Nome().getText())
            || "".equals(view.getInputCliente_Cpf().getText())
            || "".equals(view.getInputCliente_Telefone().getText())
            || "".equals(view.getInputCliente_Data().getText())){
            return;
        }
        
        nome = view.getInputCliente_Nome().getText();
        cpf = view.getInputCliente_Cpf().getText();
        telefone = view.getInputCliente_Telefone().getText();
        data = view.getInputCliente_Data().getText();
        
        SimpleDateFormat from = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        
        if("".equals(view.getInputCliente_Codigo().getText())){
            try {
                date = from.parse(data); // 01/02/2014
                String mysqlString = to.format(date); // 2014-02-01
                     
                codigo = db.query_insert("pessoa", "nome, cpf, telefone", "'"+nome+"','" +cpf+"','" +telefone+"'");
            
                //Erro na inserção
                if(codigo == 0){
                    return;
                }
        
                db.query_insert("cliente", "idpessoa, data_cadastro", codigo+", '" +mysqlString+"'");
            
                LimparCamposCliente();
            } catch (ParseException ex) {
                Logger.getLogger(GerenciarClientes.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        
        else{
            codigo = Integer.parseInt(view.getInputCliente_Codigo().getText());
            ResultSet query = db.query("SELECT idpessoa FROM cliente WHERE idcliente =" +codigo);
            
            try {
                if(query.next()){
                    db.query_update("pessoa", "nome ='" +nome+ "' ,cpf = '" +cpf+"' , telefone = '" +telefone +"'", "idpessoa ="+query.getInt("idpessoa"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }

    private void lista_clientes() {
        String where = "";
        DefaultTableModel row = (DefaultTableModel) view.getjTable1().getModel();
        int rowCount = row.getRowCount();

        clear_row(row);
        
        String pesquisa = view.getInputPesquisa_Cliente().getText();
        if(!"".equals(pesquisa)){
            where = "WHERE nome like '%"+pesquisa+"%' or cpf LIKE '%"+pesquisa+"%'";
        }
        ResultSet query = db.query("SELECT * FROM cliente INNER JOIN pessoa ON  cliente.idpessoa = pessoa.idpessoa "+ where);
        
        try {
            while(query.next()){
                row.addRow(new Object[]{query.getInt("idcliente"), query.getString("nome"), query.getString("cpf")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarPedidos.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private void clear_row(DefaultTableModel table) {
        int rowCount = table.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            table.removeRow(i);
        }
    }
    
    public void setCliente_values(int index){
        String where = "";
        String pesquisa = view.getInputPesquisa_Cliente().getText();
        
        DefaultTableModel row = (DefaultTableModel) view.getjTable1().getModel();
        
        ResultSet query = db.query("SELECT * FROM cliente INNER JOIN pessoa ON  cliente.idpessoa = pessoa.idpessoa WHERE idcliente = "+row.getValueAt(index, 0).toString());
        
        try {
            if(query.next()){
                view.getInputCliente_Nome().setText(query.getString("nome"));
                view.getInputCliente_Cpf().setText(query.getString("cpf"));
                view.getInputCliente_Telefone().setText(query.getString("telefone"));
                view.getInputCliente_Data().setText(dateFormat.format(query.getDate("data_cadastro")));
                view.getInputCliente_Codigo().setText(query.getString("idcliente"));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
