
public class IDLListTestCase {
    public static void main(String[] args){
        IDLList list= new IDLList();
        System.out.println("adding elements to the list in the first position");
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list.toString());
        System.out.println("adding element at the particular index");
        list.add(1,5);
        list.add(2,7);
        list.add(3,8);
        list.append(9);
        System.out.println(list.toString());
        System.out.println("retrieve data at index: "+list.get(2));
        System.out.println("retrieve data at head: "+list.getHead());
        System.out.println("retrieve data at tail: "+list.getLast());
        System.out.println("total size of the list: "+list.size());
        System.out.println("after removing first element");
        list.remove();
        System.out.println(list.toString());
        list.removeLast();
        System.out.println("after removing last element");
        System.out.println(list.toString());
        System.out.println("after removing element at particular index");
        list.removeAt(2);
        System.out.println(list.toString());
        System.out.println("after removing given element");
        list.remove(2);
        System.out.println(list.toString());
        System.out.println("total size of the list: "+list.size());

    }
}
