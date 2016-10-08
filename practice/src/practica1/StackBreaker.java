package practica1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StackBreaker {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Stack stack = new Stack();
        Stack stack = new ThreadSafeStack();
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> stack.push(1));
        }
        executor.awaitTermination(1, TimeUnit.SECONDS);
        if(stack.top() != 1000) {
            System.out.println("Fallo");
            System.out.println("Stack top: " + String.valueOf(stack.top()));
            return;
        }
        System.out.println("No fallo");
    }
}
