package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Clase genérica encargada de exportar listas de entidades a archivos JSON.
 */
public class ExportardorJSON<T> {

    private static final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation() // Excluye atributos sin anotacion @Expose
            .setPrettyPrinting()
            .create();

    /**
     * Exporta una lista de entidades a un archivo JSON dentro de la ruta: {@code src/main/resources/data/}
     *
     * @param entidad Lista de objetos que se desean exportar
     * @param archivo Nombre del archivo JSON de destino
     */
    public void exportarJSON(List<T> entidad, String archivo) {
        String ARCHIVO = "src/main/resources/data/" + archivo;

        try (Writer writer = new OutputStreamWriter( new FileOutputStream(ARCHIVO), StandardCharsets.UTF_8)) {
            gson.toJson(entidad, writer);

        } catch (Exception e) { System.out.println(e.getMessage()); }
    }
}