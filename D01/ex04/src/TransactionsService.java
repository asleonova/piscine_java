import java.util.UUID;

public class TransactionsService {

    private UsersArrayList usersList;
    private UnpairedTransactions unpairedTransactions;

    public TransactionsService() {
        usersList = new UsersArrayList();
        unpairedTransactions = new UnpairedTransactions();
    }

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public int retrieveUserBalance(int userId) throws UserNotFoundException {
        User user = usersList.RetrieveUserById(userId);
        return user.getBalance();
    }

    public void transferTransaction(int recipientId, int senderId, int transferAmount) throws UserNotFoundException, IllegalTransactionException {
        User recipient = usersList.RetrieveUserById(recipientId);
        User sender = usersList.RetrieveUserById(senderId);

        if (sender.getBalance() - transferAmount < 0) {throw new IllegalTransactionException();}

        Transaction credit = new Transaction(sender, recipient, Category.credit, -transferAmount);
        Transaction debit = new Transaction(credit.getIdentifier(), sender, recipient, Category.debit, transferAmount);

        sender.getTransactionsList().addTransaction(credit);
        recipient.getTransactionsList().addTransaction(debit);

        sender.setBalance(sender.getBalance() + credit.getTransferAmount());
        recipient.setBalance(recipient.getBalance() + debit.getTransferAmount());

    }

    public Transaction[] retrieveTransfer(int userId) throws UserNotFoundException {
        User user = usersList.RetrieveUserById(userId);
        return user.getTransactionsList().toArray();
    }

    public void  removeTransaction(int userId, UUID transactionId) throws UserNotFoundException {
        User user = usersList.RetrieveUserById(userId);
        MyLinkedList<Transaction> transaction = user.getTransactionsList().removeTransaction(transactionId);
        unpairedTransactions.addTransaction(transaction.getValue());
    }

    public Transaction[] checkValidityOfTransaction() {
        return unpairedTransactions.toArray();
    }

}
