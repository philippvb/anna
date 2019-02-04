package de.bfom.anna.gui;

import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.ReducedFileEntity;

import java.util.List;

public class ReducedFileEntityTable {
    private String[] columnNames = {"Name", "Type", "created"};
    private List<ReducedFileEntity> data;

    public ReducedFileEntityTable(List<ReducedFileEntity> data){
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
        ReducedFileEntity e = data.get(row);
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

    public void setvalues(List<ReducedFileEntity> l){
        this.data = l;
    }

    public ReducedFileEntity getEntity(int id){
        return data.get(id);
    }
}
