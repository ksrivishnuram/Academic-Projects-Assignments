import java.util.ArrayList;

public class IDLList<E>{
    Node<E> head;
    Node<E> tail;
    int size;
    ArrayList<Node<E>> indices =new ArrayList<>();
    private class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;
/*
 a constructor that creates a node holding element
 */

        public Node(E elem) {
            this.data = elem;
        }
/*
a constructor that creates a node holding elem, with
next as next and prev as prev
 */

        public Node(E elem, Node<E> next, Node<E> prev) {
            this.data = elem;
            this.next = next;
            this.prev = prev;
        }
    }

    /*
    to create an empty doubly linked list
    */
    public IDLList() {
        head = tail = null;
        size = 0;
    }
    /*
    to add element at first
    */
    public boolean add(E elem) {
        Node<E> newNode = new Node<>(elem);
        if(head ==null){
            head =tail =newNode;
            head.prev=null;
            head.next=null;
            tail.prev=null;
            tail.next=null;
            indices.add(newNode);
        }
        else{
            newNode.next=head;
            head.prev=newNode;
            head= newNode;
            indices.add(0,newNode);
        }
        size+=1;
        return true;
    }
    /*
        adding an element at given index
    */
    public boolean add(int index, E elem){
        if(0>index||index>=size){
            throw new IllegalArgumentException("Index must be between '0' to 'size-1'");
        }
        else{
            Node<E> currentNode=indices.get(index);
            Node<E> newNode=new Node<>(elem);
            currentNode.prev.next=newNode;
            newNode.next=currentNode;
            newNode.prev=currentNode.prev;
            currentNode.prev=newNode;
            indices.add(index,newNode);
            size+=1;
        }
        return true;
    }
    /*
    to add element at last
     */
    public boolean append(E elem) {
        Node<E> newNode = new Node<>(elem);
        tail.next=newNode;
        newNode.prev=tail;
        tail=newNode;
        indices.add(newNode);
        size += 1;
        return true;
    }
    /*
        to get the first data of the list
    */
    public E getHead(){
        return head.data;
    }
    /*
    to get last data of the tail
     */
    public E getLast(){
        return tail.data;
    }
    /*
    to get the total size of the list
     */
    public int size(){
        return size;
    }
    /*
    to retrieve data of the specified index
     */
    public E get(int index) {
        if (0 > index || index >= size) {
            throw new IllegalArgumentException("index must between the list boundary");
        }
        return indices.get(index).data;
    }
    /*
    to remove and return the element at head
    */
    public E remove(){
        head=head.next;
        head.prev=null;
        indices.remove(head);
        size-=1;
        return head.data;
    }
    /*
    to remove and return the element at the tail
     */
    public E removeLast(){
        tail=tail.prev;
        tail.next=null;
        indices.remove(tail);
        size-=1;
        return tail.data;
    }
    /*
    to remove and return the element at the index
     */
    public E removeAt(int index){
        if (0>index||index>=size){
            throw new IllegalArgumentException("index must between the list boundary");
        }
        if(index==0){
            remove();
            return indices.get(0).data;
        }else if(index==size-1){
            removeLast();
            return indices.get(size-1).data;
        }else {
            Node<E> currentNode = indices.get(index);
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
            indices.remove(currentNode);
            size -= 1;
            return currentNode.data;
        }
    }
    /*
    to remove the first occurrence of elem in the list
     */
    public boolean remove(E elem){
        Node<E> temp=head;
        int tempSize=0;
        while(temp!=null){
            if(temp.data==elem){
                removeAt(tempSize);
                return true;
            }
            temp=temp.next;
            tempSize+=1;
        }
        return false;
    }
    /*
    to present a string representation of the list
     */
    public String toString(){
        String result="";
        Node<E> current=head;
        while(current.next != null) {
            result+=current.data+ ",";
            current = current.next;
        }
        result+=current.data;
        return result;
    }

}