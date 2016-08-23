package practica1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StackBreaker {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Stack stack = new Stack();
        ExecutorService executor = Executors.newCachedThreadPool();

//        executor.submit(() -> {
//            for (int i = 0; i < 500; i++) {
//                stack.push(1);
//            }
//        }).get();
//
//        executor.submit(() -> {
//            for (int i = 0; i < 500; i++) {
//                stack.pop();
//            }
//        }).get();
        int times = 0;
        while(true) {
            for (int i = 0; i < 1000; i++) {
                executor.submit(() -> stack.push(1));
                executor.submit(() -> stack.pop());
            }
            times++;
            executor.shutdown();
            if(stack.top() != 0) {
                break;
            }
        }

        System.out.println("Fallo");
        System.out.println("Stack top: " + String.valueOf(stack.top()));
        System.out.println("Times: " + String.valueOf(times));

    }
}
