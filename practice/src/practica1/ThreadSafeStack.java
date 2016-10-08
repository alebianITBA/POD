package practica1;

public class ThreadSafeStack extends Stack {
    @Override
    public synchronized void push(final int n) {
        if (top == MAX_SIZE) {
            throw new IllegalStateException("stack full");
        }
        values[top++] = n;
    }

    @Override
    public synchronized int pop() {
        if (top == 0) {
            throw new IllegalStateException("stack empty");
        }
        return values[--top];
    }

    @Override
    public int top() {
        return top;
    }
}
