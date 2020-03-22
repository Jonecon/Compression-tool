public class LZWStack{

    //FList class Fields
    private Node head;
    private int count = 0;

    //Private Node class
     private class Node{ 
        private char Data;
        Node next;

        //Constructor
        public Node(char newData){
        this.Data = newData;
        }
    }

    // Whatever properties are needed to implement the stack
    public void push(char item){
        //Node currStackNode = head;
        Node newNode = new Node(item);//Creating new Node object
        newNode.next = head;
        //Assigning newNode to the head of the linked list
        head = newNode;
        //Increasing the count
        count++;
        return;
    }

    public char pop() {
         Node currStackNode = head;
         head = head.next;
         count--;
        return currStackNode.Data;
    }

    public boolean empty() {
        // Return true if stack is empty, false otherwise
        if(head == null){
            return true;
        }
        else{
            return false;
        }
    }

    public void PrintStack(){
        Node currNode = head;
        //Goes through the linked list and prints elements
        while(currNode != null)
        {
            System.out.println(currNode.Data);
            currNode = currNode.next;
        }
    }

    //Testing Method
    public Node returnHead(){
        return head;
    }

    public int returnStackCount(){
        return count;
    }

}