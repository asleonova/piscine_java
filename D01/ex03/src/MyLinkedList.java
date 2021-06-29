public class MyLinkedList<T> {
    T value_type;
    MyLinkedList<T> next;
    MyLinkedList<T> prev;

    public MyLinkedList(T value_type) {
        this.value_type = value_type;
        this.next = null;
        this.prev = null;
    }

    public MyLinkedList(T value_type, MyLinkedList<T> next, MyLinkedList<T> prev) {
        this.value_type = value_type;
        this.next = next;
        this.prev = prev;
    }

    public T getValue_type() {
        return value_type;
    }
}