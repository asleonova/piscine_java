import java.util.LinkedList;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

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

    DoublyLinkedList dl_List = new DoublyLinkedList();

    @Override
    public void addTransaction(Transaction transaction) {
        dl_List.addNode(transaction);
    }

    @Override
    public void removeTransaction(UUID identifier) throws TransactionNotFoundException {

        DoublyLinkedList tmpList = dl_List;

        while (tmpList.head != null)
        {
            if (tmpList.head.value.getIdentifier().equals(identifier)) {

                if (tmpList.head == dl_List.head) {
                    dl_List.head = tmpList.head.next;
                }

                if (tmpList.head.next != null) {
                    tmpList.head.next.prev = tmpList.head.prev;
                }
                if (tmpList.head.prev != null) {
                    tmpList.head.prev.next = tmpList.head.next;
                }
                return ;
            }
            tmpList.head = tmpList.head.next;
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray(Transaction transaction) {
        return new Transaction[0];
    }

//    public void printTransactionList()
//    {
//        while (dl_List.head != null)
//        {
//            dl_List.head.value.printTransaction();
//            dl_List.head = dl_List.head.next;
//        }
//    }

    public void printTransactionList()
    {

        DoublyLinkedList tmp = dl_List;
        while (tmp.head != null)
        {
            tmp.head.value.printTransaction();
            tmp.head = tmp.head.next;
        }
    }

    public void printTransactionListFirstLast()
    {

        DoublyLinkedList tmp = dl_List;
        while (tmp.tail != null)
        {
            tmp.tail.value.printTransaction();
            tmp.tail = tmp.tail.prev;
        }
    }
}
