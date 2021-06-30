import java.util.UUID;

public interface TransactionsList {

    void addTransaction(Transaction transaction);
    MyLinkedList<Transaction> removeTransaction(UUID identifier) throws TransactionNotFoundException;
    Transaction[] toArray();
}
