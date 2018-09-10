/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author pet
 */
public class CustomJTable extends JTable {
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    
    public void setTabelaCliente(){
        this.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "CPF"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        this.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        
    }   

    public CustomJTable() {
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        setTabelaCliente();
        this.getTableHeader().setReorderingAllowed(false);
        this.setRowHeight(30);
        this.setFont(new java.awt.Font("Tahoma", 0, 14));
    }
    
    public void setTabelaProduto(){
        this.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Preço", "Disponivel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        this.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        this.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
    }
    
    public void setTabelaPedidos(){
        this.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pedido","Cliente", "Mesas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
               false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        this.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        this.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
    }
   
    
}
