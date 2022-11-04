package project3;

public class LL<E> { // still needs to be changed to fit 2048

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LL(){ // uses other LinkedList constructor
        this(0);
    }

    public LL(int initial){ // LinkedList constructor
        if (initial != 0 && initial > 0){ // create the initial Node
            head = new Node<E>();
            tail = head;
            size++;
            Node<E> current = head;
            while(size < initial){ // create a new Node and have the last one point to it
                current.next = new Node<E>(); // new Node
                current = current.next; // have the last one point to it
                size++;
            }
            tail = current;
        }
    }

    public int getSize(){
        return size;
    }

    public void set(int index, int data){ // changes the data at the index to inputted 'data'
        if(index < 0 || index >= size){
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

    public void add(E data){ // allows us to add Nodes to the pre-existing LinkedList
        Node<E> newNode = new Node<E>((Integer) data);
        if(head == null){ // if LinkedList was empty before creating this Node^
            head = newNode;
            tail = newNode;
        } else{ // if LinkedList already had previous Nodes
            tail.next = newNode; // next Node will be the tail
            tail = newNode; // LinkedList' tail IS newNode
        }
        size++;
    }

    public Node remove(int index){ // resets Node data and returns specified Node
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        } else {
            Node newNode = get(index); // creates a temporary Node newNode with data from OG Node
            set(index, 0); // resets OG Node data to 0
            return newNode; // returns newNode with data from OG Node before the reset
        }
    }

    public Node get(int index){ // returns the specified Node from the desired LinkedList
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        } else {
            int count = 0;
            Node<E> current = head;
            while(count < index){
                current = current.next;
                count++;
            }
            return current;
        }
    }


    private class Node<E>{ // defines what is inside a Node ('data', pointer to next Node)
        int data; // stores the data
        Node<E> next; // stores the next Node

        public Node(int data){ // create a Node with a specified value
            this.data = data;
        }

        public Node(){ // allows us to create an empty Node (no data, still has pointer)

        }

        public String toString(){ // toString for Nodes

            return "" + data;
        }
    }

    public String toString(){ // toString for LinkedList
        String str = "";
        // how to retrieve a specific Node's data?
        return str;
    }

}
