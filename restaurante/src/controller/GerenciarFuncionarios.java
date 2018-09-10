/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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
import view.FuncionarioView;

/**
 *
 * @author 201319040195
 */
public class GerenciarFuncionarios implements ActionListener{

    protected FuncionarioView view;
    private final Conexao db;
    private final DecimalFormat floatFormat = new DecimalFormat("0.00");
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public GerenciarFuncionarios(FuncionarioView view) {
        this.view = view;
        this.db = Conexao.getConnection();
        Date date = new Date();
        view.getInputFuncionario_Data().setText(dateFormat.format(date));
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand() ){
            case "Limpar":
                LimparCamposFuncionario();
                break;
            case "Salvar":
                salva_funcionario();
                break;
            case "Pesquisar":
                lista_funcionario();
                break;
            default:
                System.out.println(e.getActionCommand() );
                break;
        }
    }

    private void LimparCamposFuncionario() {
        view.getInputFuncionario_Nome().setText("");
        view.getInputFuncionario_Cpf().setText("");
        view.getInputFuncionario_Telefone().setText("");
        view.getInputFuncionario_Cargo().setText("");
        view.getInputFuncionario_Salario().setValue(new BigDecimal("0.00"));
        view.getInputFuncionario_Data().setText("");
        view.getInputFuncionario_Usuario().setText("");
        view.getInputFuncionario_Senha().setText("");
        view.getInputFuncionario_Codigo().setText("");
        Date date = new Date();
        view.getInputFuncionario_Data().setText(dateFormat.format(date));
        clear_row((DefaultTableModel) view.getjTable1().getModel());
    }

    private void salva_funcionario() {
        String nome, cpf, telefone, cargo, data, usuario, senha;
        int codigo;
        float salario;
        boolean ativo;
        
        if(   "".equals(view.getInputFuncionario_Nome().getText())
            || "".equals(view.getInputFuncionario_Cpf().getText())
            || "".equals(view.getInputFuncionario_Telefone().getText())
            || "".equals(view.getInputFuncionario_Cargo().getText())
            || "".equals(view.getInputFuncionario_Salario().getText())
            || "".equals(view.getInputFuncionario_Data().getText())
            || "".equals(view.getInputFuncionario_Usuario().getText())
            || "".equals(view.getInputFuncionario_Senha().getText())){
            return;
        }
        
        nome = view.getInputFuncionario_Nome().getText();
        cpf = view.getInputFuncionario_Cpf().getText();
        telefone = view.getInputFuncionario_Telefone().getText();
        cargo = view.getInputFuncionario_Cargo().getText();
        salario = view.getInputFuncionario_Salario().getValue().floatValue();
        data = view.getInputFuncionario_Data().getText();
        usuario = view.getInputFuncionario_Usuario().getText();
        senha = view.getInputFuncionario_Senha().getText();
        ativo = view.getFuncionario_Ativo().isSelected();
        
        SimpleDateFormat from = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        
        if("".equals(view.getInputFuncionario_Codigo().getText())){
            try {
                date = from.parse(data); // 01/02/2014
                String mysqlString = to.format(date); // 2014-02-01
                     
                codigo = db.query_insert("pessoa", "nome, cpf, telefone", "'"+nome+"','" +cpf+"','" +telefone+"'");
            
                //Erro na inserção
                if(codigo == 0){
                    return;
                }
        
                db.query_insert("funcionario", "idpessoa, data_de_admissao, cargo, salario, usuario_sistema, senha_sistema, ativo", codigo+", '" +mysqlString+"', '" +cargo+"', '"+salario+"', '" +usuario+"', '" +senha+"'," +ativo);
            
                LimparCamposFuncionario();
            } catch (ParseException ex) {
                Logger.getLogger(GerenciarFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        
        else{
            codigo = Integer.parseInt(view.getInputFuncionario_Codigo().getText());
            ResultSet query = db.query("SELECT idpessoa FROM funcionario WHERE idfuncionario =" +codigo);
            
            try {
                if(query.next()){
                    db.query_update("pessoa", "nome ='" +nome+ "' ,cpf = '" +cpf+"' , telefone = '" +telefone +"'", "idpessoa ="+query.getInt("idpessoa"));
                    db.query_update("funcionario", "cargo ='" +cargo+ "' ,salario = '" +salario+"' ,usuario_sistema = '"+usuario+"', senha_sistema = '"+senha+"', ativo = "+ativo , "idpessoa ="+query.getInt("idpessoa"));
                    
                    LimparCamposFuncionario();
                }   
            } catch (SQLException ex) {
                Logger.getLogger(GerenciarFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    private void lista_funcionario() {
        String where = "";
        DefaultTableModel row = (DefaultTableModel) view.getjTable1().getModel();
        int rowCount = row.getRowCount();

        clear_row(row);
        
        String pesquisa = view.getInputPesquisa_Funcionario().getText();
        
        where = "WHERE nome like '%"+pesquisa+"%' or cpf LIKE '%"+pesquisa+"%'";
        
        ResultSet query = db.query("SELECT * FROM funcionario INNER JOIN pessoa ON  funcionario.idpessoa = pessoa.idpessoa "+ where);
        
        try {
            while(query.next()){
                row.addRow(new Object[]{query.getInt("idfuncionario"), query.getString("nome"), query.getString("cpf")});
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
    
    public void setFuncionario_values(int index){
        String where = "";
        
        DefaultTableModel row = (DefaultTableModel) view.getjTable1().getModel();
        
        where = "WHERE idfuncionario = "+row.getValueAt(index, 0).toString();
        
        ResultSet query = db.query("SELECT * FROM funcionario INNER JOIN pessoa ON  funcionario.idpessoa = pessoa.idpessoa "+ where);
        
        try {
            if(query.next()){
                view.getInputFuncionario_Nome().setText(query.getString("nome"));
                view.getInputFuncionario_Cpf().setText(query.getString("cpf"));
                view.getInputFuncionario_Telefone().setText(query.getString("telefone"));
                view.getInputFuncionario_Data().setText(dateFormat.format(query.getDate("data_de_admissao")));
                view.getInputFuncionario_Codigo().setText(query.getString("idfuncionario"));
                view.getInputFuncionario_Cargo().setText(query.getString("cargo"));
                view.getInputFuncionario_Salario().setText(query.getString("salario"));
                view.getInputFuncionario_Usuario().setText(query.getString("usuario_sistema"));
                view.getInputFuncionario_Senha().setText(query.getString("senha_sistema"));
                view.getFuncionario_Ativo().setSelected(query.getBoolean("ativo"));
                
            }     
        } catch (SQLException ex) {
            Logger.getLogger(GerenciarPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
