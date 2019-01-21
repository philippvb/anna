package de.bfom.anna.gui;


import de.bfom.anna.business.file.entity.FileEntity;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EntityTable extends AbstractTableModel {
    private String[] columnNames = {"Name", "Type", "created"};
    private List<FileEntity> data;

    public EntityTable(List<FileEntity> data){
        this.data = data;
    }

   public int getColumnCount(){
       return columnNames.length;
   }

   public int getRowCount(){
       return data.size();
   }

    public String getColumnName(int col) {
        return columnNames[col];
    }


   public Object getValueAt(int row, int col){
       FileEntity e = data.get(row);
       if(col == 0){
           return e.getName();
       }
       else if(col == 1){
           return e.getMime();
       }
       else {
           return e.getCreated();
       }
   }

   public void setvalues(List<FileEntity> l){
       this.data = l;
   }
}
