package project3;

public class LL<E> { // still needs to be changed to fit 2048

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LL() { // uses other LinkedList constructor
        this(0);
    }

    public LL(int initial) { // LinkedList constructor
        if (initial != 0 && initial > 0) { // create the initial Node
            head = new Node<E>();
            tail = head;
            size++;
            Node<E> current = head;
            while (size < initial) { // create a new Node and have the last one point to it
                current.next = new Node<E>(); // new Node
                current = current.next; // have the last one point to it
                size++;
            }
            tail = current;
        }
    }

    public int getSize() {
        return size;
    }

    public void set(int index, E data) { // changes the data at the index to inputted 'data'
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            int count = 0;
            Node<E> current = head;
            while (count < index) { // traverses through the Nodes until at the specified Node
                current = current.next;
                count++;
            }
            current.data = data; // sets the current Node data equal to the data passed in
        }
    }

    public void add(E data) { // allows us to add Nodes to the pre-existing LinkedList
        Node<E> newNode = new Node<E>(data);
        if (head == null) { // if LinkedList was empty before creating this Node^
            head = newNode;
            tail = newNode;
        } else { // if LinkedList already had previous Nodes
            tail.next = newNode; // next Node will be the tail
            tail = newNode; // LinkedList' tail IS newNode
        }
        size++;
    }

    // make special cases for first and last
    // set the "next" Node for the Node before 'index' equal to the one after 'index' and
    // Java deletes the Node on its own
    public E remove(int index) { // points Node before 'index' to Node after 'index' and Java erases the Node at 'index' since there's nothing pointing to it
        if (index < 0 || index >= size) { // ensures inputted 'index' is possible
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) { // if we're removing the first Node
            Node<E> copy = head; // makes copy of Node at 'index'
            head = head.next; // erases Node
            return copy.data; // returns copy of data at 'index'
        }
        if (index == size - 1) { // if we're removing the last Node
            int count = 0;
            Node<E> current = head;
            while (count < size - 2) {
                current = current.next;
                count++;
            } // current should be equal to the Node before the last Node
            Node<E> copy = tail; // makes copy of Node at 'index'
            current.next = null; // point one before index to null
            tail = current; // set Tail equal to our new last Node
            return copy.data; // returns copy of data at 'index'
        }
        // if we're removing any other Node besides first or last
        int count = 0;
        Node<E> current = head;
        while (count < index - 1) { // traverses up to one before the specified index
            current = current.next;
            count++;
        } // 'current' should now be equal to the Node preceding 'index'
        Node<E> before = current; // saves the Node before 'index'
        current = current.next; // goes to Node at 'index'
        Node<E> copy = current; // makes a copy of Node at 'index' before it gets deleted
        current = current.next; // goes to Node 1 after 'index'
        Node<E> after = current; // saves the Node after 'index'

        before.next = after; // sets the previous Node's "next Node" equal to the one after 'index'
        // -, effectively erasing the Node in between since there is nothing
        // -pointing to it
        return copy.data; // returns data from the Node at 'index' before it was erased
    }

    public E get(int index) { // returns the specified Node from the desired LinkedList
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            int count = 0;
            Node<E> current = head;
            while (count < index) {
                current = current.next;
                count++;
            }
            return current.data;
        }
    }

    public void clear() {
        head = null; //head points to nothing
        tail = null; //tail points to nothing
        size = 0; //size is now 0
    }


    private class Node<E> { // defines what is inside a Node ('data', pointer to next Node)
        E data; // stores the data
        Node<E> next; // stores the next Node

        public Node(E data) { // create a Node with a specified value
            this.data = data;
        }

        public Node() { // allows us to create an empty Node (no data, still has pointer)

        }

        public String toString() { // toString for Nodes

            return "" + data;
        }
    }

    public String toString() { // toString for LinkedList
        String str = "";
        // how to retrieve a specific Node's data?
        return str;
    }

    public E[] toArray() {
        Object[] arr = new Object[size];
        Node<E> current = head;
        int i = 0;
        while (current != null) {
            arr[i] = current.data;
            current = current.next;
            i++;
        }
        return (E[])arr;
    }
}

