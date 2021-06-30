import java.util.LinkedList;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private MyLinkedList<Transaction> head;
    private MyLinkedList<Transaction> tail;
    private int size;


    public TransactionsLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
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
        size++;
    }

    @Override
    public MyLinkedList<Transaction> removeTransaction(UUID identifier) throws TransactionNotFoundException {

        MyLinkedList<Transaction> tmp = head;

        while (tmp != null)
        {
            if (tmp.getValue().getIdentifier().equals(identifier)) {
                if (tmp == head) {
                    head = tmp.next;
                }
                if (tmp.next != null) {
                    tmp.next.prev = tmp.prev;
                }
                if (tmp.prev != null) {
                    tmp.prev.next = tmp.next;
                }
                size--;
                return tmp;
            }
            tmp = tmp.next;
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray() {
        Transaction transactionArr[] = new Transaction[size];
        MyLinkedList<Transaction> tmp = head;

        for(int i = 0; i < size; ++i) {
            transactionArr[i] = tmp.getValue();
            tmp = tmp.next;
        }
        return transactionArr;
    }

    public void printTransactionList()
    {

        MyLinkedList<Transaction> tmp = head;
        while (tmp != null)
        {
            tmp.getValue().printTransaction();
            tmp = tmp.next;
        }
    }

    public void printTransactionListFirstLast()
    {

        MyLinkedList<Transaction> tmp = tail;
        while (tmp != null)
        {
            tmp.getValue().printTransaction();
            tmp = tmp.prev;
        }
    }
}
