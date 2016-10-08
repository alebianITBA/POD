package practica1.ejercicio6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileLinesCounter {
    public static long countLines(Path file) throws IOException {
        if (file.toFile().isFile()) {
            return Files.lines(file, StandardCharsets.ISO_8859_1).count();
        }
        return 0;
    }
}
