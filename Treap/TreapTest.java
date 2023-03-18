public class TreapTest<E> {
    public static void main(String[] args)
    {
        Treap testTree  =  new Treap < Integer >();
        testTree.add(4 ,19);
        testTree.add (2 ,31);
        testTree.add (6 ,70);
        testTree.add (1 ,84);
        testTree.add (3 ,12);
        testTree.add (5 ,83);
        testTree.add (7 ,26);
        System.out.println(testTree);
        testTree.delete(7);
        System.out.println(testTree.find(1));
        System.out.println(testTree);
    }
}
