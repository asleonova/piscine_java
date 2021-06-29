public class TransactionNotFoundException extends RuntimeException {
    TransactionNotFoundException() {
        super("user not found");
    }
}
