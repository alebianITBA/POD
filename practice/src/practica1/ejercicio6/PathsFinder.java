package practica1.ejercicio6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PathsFinder {
    public static List<Path> findPaths(Path rootPath) throws IOException {
        List<Path> paths = new ArrayList<>();
        DirectoryStream<Path> stream = Files.newDirectoryStream(rootPath);
        for (Path path : stream) {
            paths.add(path);
        }
        return paths;
    }
}
