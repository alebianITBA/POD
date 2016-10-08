package practica1.ejercicio6;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountLinesInFiles {
    private static final String DEFAULT_PATH = "/Users/alebian/Desktop/Cursando/Programacion de Objetos Distribuidos/github/practice/files/";

    public static void main(String[] args) throws InterruptedException, IOException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Path rootPath;

        if (args.length > 0) {
            rootPath = Paths.get(args[0]);
        } else {
            rootPath = Paths.get(DEFAULT_PATH);
        }

        List<Path> filePaths = PathsFinder.findPaths(rootPath);

        for (Path path : filePaths) {
            executor.submit(() -> System.out.printf("File %s: %d lines\n", path.toString(), FileLinesCounter.countLines(path)));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
    }
}
