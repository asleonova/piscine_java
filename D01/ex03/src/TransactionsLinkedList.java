import java.util.LinkedList;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private MyLinkedList<Transaction> head;
    private MyLinkedList<Transaction> tail;


    public TransactionsLinkedList() {
        this.head = null;
        this.tail = null;
    }
    private class DoublyLinkedList {
        class Node {
            Transaction value;
            Node prev;
            Node next;

            public Node(Transaction value) {
                this.value = value;
            }
        }

        Node head = null;
        Node tail = null;

        public Node getHead() {
            return head;
        }

        public Node getTail() {
            return tail;
        }

        public void addNode(Transaction value) {
            Node newNode = new Node(value);

            if (head == null) {
                head = tail = newNode;
                head.prev = null;
                tail.next = null;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
                tail.next = null;
            }
        }

        public void deleteNode(Node delNode) {

            // если только голова
            if (head == delNode)
                head = delNode.next;
            // апдейтим указатель next (связываем его prev с prev удаляемой ноды
            if (delNode.next != null) {
                delNode.next.prev = delNode.prev;
            }
            // next предыдущей ноды будет указывать на то, на что указывала удаляемая нода
            if (delNode.prev != null) {
                delNode.prev.next = delNode.next;
            }
        }
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (head == null) {
            head = tail = new MyLinkedList<Transaction>(transaction);
        }
        else {
            tail.next = new MyLinkedList<Transaction>(transaction, null, tail);
            tail = tail.next;
        }
    }

    @Override
    public void removeTransaction(UUID identifier) throws TransactionNotFoundException {

        MyLinkedList<Transaction> tmp = head;

        while (tmp != null)
        {
            if (tmp.getValue_type().getIdentifier().equals(identifier)) {
                if (tmp == head) {
                    head = tmp.next;
                }
                if (tmp.next != null) {
                    tmp.next.prev = tmp.prev;
                }
                if (tmp.prev != null) {
                    tmp.prev.next = tmp.next;
                }
                return;
            }
            tmp = tmp.next;
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray(Transaction transaction) {
        return new Transaction[0];
    }

    public void printTransactionList()
    {

        MyLinkedList<Transaction> tmp = head;
        while (tmp != null)
        {
            tmp.getValue_type().printTransaction();
            tmp = tmp.next;
        }
    }

    public void printTransactionListFirstLast()
    {

        MyLinkedList<Transaction> tmp = tail;
        while (tmp != null)
        {
            tmp.getValue_type().printTransaction();
            tmp = tmp.prev;
        }
    }
}
