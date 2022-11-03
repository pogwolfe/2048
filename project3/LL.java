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

    public void set(int index, E number){ // changes the data at the index to inputted 'number'
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        } else {
            int count = 0;
            Node<E> current = head;
            while (count < index) { // traverses through the Nodes until at the specified Node
                current = current.next;
                count++;
            }
            current.number = number; // sets the current Node data equal to the number passed in
        }
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

    /**
     * returns Node data cast as an int which is okay for our purposes because we know
     * that 'E' is always going to be an int
     * @param node is the desired Node to retrieve the data from
     * @returns the integer held in the node
     */
    public int getNum(Node node){ // returns Node data in the form of an int

        return (int) node.number;
    }

    private class Node<E>{ // defines what is inside a Node ('number', pointer to next Node)
        E number; // stores the number
        Node<E> next; // stores the next Node

        public Node(E number){ // create a Node with a specified value
            this.number = number;
        }

        public Node(){ // allows us to create an empty Node (no data, still has pointer)

        }

        public String toString(){ // toString for Nodes

            return "" + number;
        }
    }

    public String toString(){ // toString for LinkedList
        String str = "";
        // how to retrieve a specific Node's data?
        return str;
    }

}
