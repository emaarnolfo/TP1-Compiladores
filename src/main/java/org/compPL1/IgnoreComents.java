package org.compPL1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class IgnoreComents extends BufferedReader {

    public IgnoreComents(Reader in, int sz) {
        super(in, sz);
    }

    public IgnoreComents(Reader in) {
        super(in);
    }

    @Override
    public String readLine() throws IOException {
        String linea;
        // Leer hasta encontrar una línea que no sea un comentario o que no esté vacía
        do {
            linea = super.readLine();
            try {
                linea = linea.trim();
            } catch (Exception e){
                return null;
            }
        } while (linea.startsWith("#"));

        return linea;  // Devolver la línea válida
    }
}
