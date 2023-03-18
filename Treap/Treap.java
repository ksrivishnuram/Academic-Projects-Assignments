
import java.util.Random;
import java.util.Stack;


public class Treap<E extends Comparable<E>>
{
    private Random priorityGenerator;
    private Node<E> root;

    public Treap()
    {

        priorityGenerator =new Random();
    }

    public Treap(long seed)
            
    {
        priorityGenerator =new Random(seed);
    }

    /**
     * a method to add the given key
     * @param key
     * @param priority
     * @return
     */
    public boolean add(E key,int priority)
    {

        Node<E> node = new Node<E>(key,priority);
        if(root ==null)
        {
            root =node;
            return true;
        }
        else if(find(key))
        {
            return false;
        }
        else
        {
            Node<E> tempRoot = root;
            Stack<Node<E>> stack = new Stack<>();
            while(tempRoot != null)
            {
                int compNode = key.compareTo(tempRoot.data);
                stack.push(tempRoot);
                switch (compNode)
                {
                    case -1:

                        tempRoot = tempRoot.left;
                        break;
                    case 0:
                        return false;
                    case 1:
                        tempRoot = tempRoot.right;
                        break;
                }
            }
            Node<E> nextStack = stack.peek();
             int addNode = key.compareTo(nextStack.data);
            if(addNode == -1)
            {
                nextStack.left = node;
            }
            else if(addNode == 1)
            {
                nextStack.right =node;
            }
            return reHeap(node,stack);
            }
    }
    public boolean add(E key)
    {
         add(key,(int)priorityGenerator.nextInt());
        return true;
    }

    /**
     * helper function to arrange the treap in descending order (Max Heap)
     * @param node
     * @param stack
     * @return
     */
    public boolean reHeap(Node<E> node, Stack<Node<E>> stack)
    {
        while (stack.peek().priority < node.priority && !stack.empty())
        {
            Node<E> currentNode = stack.pop();
            if (currentNode == root)
            {
                if (root.left == node)
                {
                    root.rotateRight();
                    root = node;
                    return true;
                } else
                {
                    root.rotateLeft();
                    root = node;
                    return true;
                }
            } else if (node == currentNode.left)
            {
                currentNode.rotateRight();
            } else
            {
                currentNode.rotateLeft();
            }
            if (stack.peek().left == currentNode)
            {
                stack.peek().left = node;
            } else
            {
                stack.peek().right = node;
            }
        }
        return true;
    }

    /**
     * a method to delete when key is given
     * @param key
     * @return
     */
    public boolean delete(E key)
    {
        if(find(key)==true)
        {
            root = deleteHelper(root,key);
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * helper function to find and delete the given node
     * @param root1
     * @param keyToDelete
     * @return
     */

    public Node<E> deleteHelper(Node<E> root1,E keyToDelete)
    {
       if(root1==null) return null;
       Node<E> n = null;
       if(root1.data.compareTo(keyToDelete)==0)
       {
           if(root1.right==null || root1.left ==null)
           {
               return (root1.right==null) ? root1.left : root1.right;
           }

           if(root1.right.priority<root1.left.priority)
           {
               root1=root1.rotateRight();
               root1.right=deleteHelper(root1.right,keyToDelete);
           }
           else
           {
               root1=root1.rotateLeft();
               root1.left=deleteHelper(root1.left,keyToDelete);
           }
           return root1;
       }
       n=(root1.data.compareTo(keyToDelete)<0) ? deleteHelper(root1.right,keyToDelete) :
               deleteHelper(root1.left,keyToDelete);
       if(root1.data.compareTo(keyToDelete)<0)
       {
           root1.right=n;
       }
       else {
           root1.left=n;
       }
       return root1;

    }

    /**
     * helper method to check if the node is present in the treap recursively
     * @param root
     * @param key
     * @return
     */

private boolean find(Node<E> root, E key){
    if(root == null){
        return false;
    }
    return (root.data == key) ? true : (find(root.left,key) || find(root.right,key));
}

    public boolean find(E key){
        return find(root,key);
    }

    /**
     * method to perform preOrder traversal and add values in StringBuilder
     * @param current - initial value is rootValue of Treap
     * @return
     */
    private StringBuilder toString(Node<E> current)
{
    StringBuilder str = new StringBuilder();
    if (current==null)
    {
        return str.append(" null ,");
    }
    else
    {
        str.append(" ("+current.data.toString()+","+current.priority+") , ");
        str.append(toString(current.left));
        str.append(toString(current.right));
        return str;
    }
}

    /**
     * method to print the Treap values in String
     * @return
     */
    public String toString()
    {
        String result =  toString(root).toString();
        if(result.length() < 0){
            return "";
        }
        return result.substring(0, result.length() - 1);
    }

    /**
     * Node - a private static inner class
     * data , priority , right, left are class member variables
     * @param <E>
     */
    private static class Node<E>
    {
        public E data;
        public int priority;
        public Node<E> right;
        public Node<E> left;

        /**
         * Constructor with the following parameters
         * @param data
         * @param priority
         */
        public Node(E data, int priority)
        {
            if(data==null)
            {
                throw new NullPointerException("Data cannot be null");
            }
            else
            {
                this.data = data;
                this.priority = priority;
                left=right=null;
            }


        }

        /**
         * method to perform the right rotation
         * @return
         */
        public Node<E> rotateRight()
        {
            Node<E> newRoot = this.left;
            if(newRoot==null)
            {
                return this;
            }
            Node<E> obj = this.left.right;
            newRoot.right = this;
            this.left = obj;
            return newRoot;

        }

        /**
         * method to perform the left rotation
         * @return
         */
        public Node<E> rotateLeft()
        {
            Node<E> newRoot = this.right;
            if(newRoot==null)
            {
                return this;
            }
            Node<E> obj = this.right.left;
            newRoot.left = this;
            this.right = obj;

            return newRoot;

        }
    }
}
