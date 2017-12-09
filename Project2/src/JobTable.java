import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;

public class JobTable extends AbstractTableModel {
    private String[] header;
    private ArrayList<String[]> rows;

    public JobTable(String[] header) {
        this.header = header;
        rows = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows.get(rowIndex)[columnIndex];
    }

    public void add(Ship ship, Job jobToAdd){
        String[] row = new String[2];
        row[0] = ship.getName();
        row[1] = jobToAdd.getName();
        rows.add(row);
        fireTableDataChanged();
    }

    public void remove(String name){
        for(String[] arr : rows){
            if(arr[1].equals(name)){
                rows.remove(arr);
                fireTableDataChanged();
                break;
            }
        }
    }
}
