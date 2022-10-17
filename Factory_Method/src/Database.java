import javax.swing.table.*;
import java.util.*;
class Database extends AbstractTableModel {
    private List<TableHeader> headers;
    private List<List<TableData>> data;
    
    public Database() {
        headers = new ArrayList<TableHeader>();
        data = new ArrayList<List<TableData>>();
    }
    public void addRow() {
        List<TableData> row = new ArrayList<TableData>();
        for(TableHeader col:headers)
            row.add(col.create()); // wywołanie metody fabrykującej
        data.add(row);
        fireTableStructureChanged();
    }
    public void addCol(TableHeader type) {
        headers.add(type);
        for(List<TableData> row:data)
            row.add(type.create()); // wywołanie metody fabrykującej
        fireTableStructureChanged();
    }

    public int getRowCount() { return data.size(); }
    public int getColumnCount() { return headers.size(); }
    public String getColumnName(int column) {
        return headers.get(column).toString();
    }
    public Object getValueAt(int row, int column) {
        return data.get(row).get(column);
    }
}

interface TableData {
    final static Random rnd = new Random();
}

class TableDataInt implements TableData
{
    private int data;
    public TableDataInt() { data = rnd.nextInt(100); }
    public String toString() { return Integer.toString(data); }
}
class TableDataDouble implements TableData
{
    private double data;
    public TableDataDouble(){data=rnd.nextDouble();}
    public String toString(){return Double.toString(data);}
}
class TableDataChar implements TableData
{
    private char data;
    public TableDataChar(){data=(char)rnd.nextInt(100);}
    public String toString(){return String.valueOf(data);}
}
class TableDataBoolean implements TableData
{
    private boolean data;
    public TableDataBoolean(){data=false;}
    public String toString(){return Boolean.toString(data);}
}
abstract class TableHeader
{
    public abstract TableData create();
} 
class TableHeaderInt extends TableHeader
{
    public TableData create()
    {
        return new TableDataInt();
    }
    public String toString()
    {
        return "INT";
    }
}
class TableHeaderDouble extends TableHeader
{
    public TableData create()
    {
        return new TableDataDouble();
    }
    public String toString()
    {
        return "DOUBLE";
    }
}
class TableHeaderChar extends TableHeader
{
    public TableData create()
    {
        return new TableDataChar();
    }
    public String toString()
    {
        return "CHAR";
    }
}
class TableHeaderBoolean extends TableHeader
{
    public TableData create()
    {
        return new TableDataBoolean();
    }
    public String toString()
    {
        return "BOOLEAN";
    }
}