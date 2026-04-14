package util;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class TablaViewer {

    public static void crearTabla(String [][] datos, String [] columnas, String titulo, int width, int height){

        JTable tabla = new JTable(datos, columnas);
        JScrollPane scroll = new JScrollPane(tabla);

        tabla.setPreferredScrollableViewportSize(new java.awt.Dimension(width, height));
        tabla.setFillsViewportHeight(true);

        JOptionPane.showMessageDialog( null,  scroll,  titulo,  JOptionPane.INFORMATION_MESSAGE );
    }
}