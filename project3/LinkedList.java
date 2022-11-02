package project3;

public class LinkedList<E> { // still needs to be changed to fit 2048

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedList(){ // uses other LinkedList constructor
        this(0);
    }

    public LinkedList(int initial){ // LinkedList constructor
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

    public void set(int index, E number){ // changes the data at the index to 'number'
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        Node<E> current = head;
        while(count < index){ // traverses through the Nodes until we're at the specified Node
            current = current.next;
            count++;
        }
        current.number = number; // sets the current Node data equal to the number passed in
    }

    public void add(E number){ // allows us to add Nodes to the pre-existing LinkedList
        Node<E> newNode = new Node<E>(number);
        if(head == null){ // if LinkedList was empty before creating this Node^
            head = newNode;
            tail = newNode;
        } else{ // if LinkedList already had previous Nodes
            tail.next = newNode; // next Node will be the tail
            tail = newNode; // LinkedList' tail IS newNode
        }
        size++;
    }

    private class Node<E>{ // defines what is inside a Node ('number', pointer to next Node)
        E number; // stores the number
        Node<E> next; // stores the next Node

        public Node(E number){ // create a Node with a specified value
            this.number = number;
        }

        public Node(){ // allows us to create an empty Node (no data, still has pointer)

        }
    }

    public String toString(){
        String str = "";
        // how to retrieve a specific Node's data?
        return str;
    }

}
