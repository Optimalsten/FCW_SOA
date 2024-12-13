package util;

// Счетчик для работы внутри блока try_with_resources
public class Counter implements AutoCloseable {
    private int counter;
    private boolean closed;

    public Counter() {
        counter = 0;
        closed = false;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public void close() {
        closed = true;
        counter = 0;
    }

    public void add() {
        if (closed) {
            throw new RuntimeException("Счетчик находится вне блока try_with_resources");
        }
        counter++;
    }
}