package teoria5;

import java.io.Serializable;
import java.util.Arrays;

public class ArrayContainer implements Serializable {
    private int[] array;

    public ArrayContainer(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayContainer container = (ArrayContainer) o;

        return Arrays.equals(array, container.array);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
