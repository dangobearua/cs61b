public class ArrayDeque<T> {
    private T[] items;
    private int capacity = 8;
    private int first;
    private int last;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        first = -1;
        last = -1;
        size = 0;
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize(size * 2);
        }
        if (isEmpty()) {
            first = 0;
            last = 0;
        } else if (first == 0) {
            first = capacity - 1;
        } else {
            first -= 1;
        }
        items[first] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (isFull()) {
            resize(size * 2);
        }
        if (isEmpty()) {
            first = 0;
            last = 0;
        } else if (last == 0) {
            last += 1;
        } else {
            last = (last + 1) % capacity;
        }
        items[last] = item;
        size += 1;
    }

    private void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        int j = first;
        for (int i = 0;  i < size; i += 1) {
            newItems[i] = items[j];
            j = (j + 1) % capacity;
        }
        capacity = newCapacity;
        first = 0;
        last = size - 1;
        items = newItems;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i += 1) {
            int correctedIndex = (first + i) % capacity;
            if (i == size - 1) {
                System.out.println(items[correctedIndex]);
                break;
            }
            System.out.print(items[correctedIndex] + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T itemRemoved = items[first];
        if (first == last) {
            first = -1;
            last = -1;
        } else {
            first = (first + 1) % capacity;
        }
        size -= 1;
        checkUsage();
        return itemRemoved;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removedItem = items[last];
        if (first == last) {
            first = -1;
            last = -1;
        } else if (last == 0) {
            last = capacity - 1;
        } else {
            last -= 1;
        }
        size -= 1;
        checkUsage();
        return removedItem;
    }

    private void checkUsage() {
        if (capacity < 16) {
            return;
        }
        if (size < (capacity / 4)) {
            resize(capacity / 2);
        }
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int correctedIndex = (first + index) % capacity;
        return items[correctedIndex];
    }
}
