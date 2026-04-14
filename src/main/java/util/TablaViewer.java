package util;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

/**
 * Clase utilitaria encargada de mostrar datos en formato tabla mediante una interfaz gráfica.
 */
public class TablaViewer {

    /**
     * Crea y muestra una tabla en una ventana emergente.
     * <p>
     * Los datos se representan en forma de matriz bidimensional de Strings,
     * donde cada fila corresponde a un registro y cada columna a un atributo.
     * </p>
     *
     * @param datos matriz de datos a mostrar en la tabla (filas y columnas)
     * @param columnas nombres de las columnas de la tabla
     * @param titulo título de la ventana emergente
     * @param width ancho preferido de la tabla
     * @param height alto preferido de la tabla
     *
     * @throws NullPointerException si los datos o columnas son nulos
     */
    public static void crearTabla(String [][] datos, String [] columnas, String titulo, int width, int height){

        JTable tabla = new JTable(datos, columnas);
        JScrollPane scroll = new JScrollPane(tabla);

        tabla.setPreferredScrollableViewportSize(new java.awt.Dimension(width, height));
        tabla.setFillsViewportHeight(true);

        JOptionPane.showMessageDialog( null,  scroll,  titulo,  JOptionPane.INFORMATION_MESSAGE );
    }
}