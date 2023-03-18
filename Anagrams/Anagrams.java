
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *Class Anagrams
 * Constant prime is initialized
 */
public class Anagrams {
    final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43,
            47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    Map<Character, Integer> letterTable;
    Map<Long, ArrayList<String>> anagramTable;

    public Anagrams() {
        buildLetterTable();
        anagramTable = new HashMap<Long, ArrayList<String>>();
    }

    /**
     * built the hash table letterTable which consists of the following entries
     */
    public void buildLetterTable() {
        letterTable = new HashMap<Character, Integer>();
        letterTable.put('a', 2);
        letterTable.put('b', 3);
        letterTable.put('c', 5);
        letterTable.put('d', 7);
        letterTable.put('e', 11);
        letterTable.put('f', 13);
        letterTable.put('g', 17);
        letterTable.put('h', 19);
        letterTable.put('i', 23);
        letterTable.put('j', 29);
        letterTable.put('k', 31);
        letterTable.put('l', 37);
        letterTable.put('m', 41);
        letterTable.put('n', 43);
        letterTable.put('o', 47);
        letterTable.put('p', 53);
        letterTable.put('q', 59);
        letterTable.put('r', 61);
        letterTable.put('s', 67);
        letterTable.put('t', 71);
        letterTable.put('u', 73);
        letterTable.put('v', 79);
        letterTable.put('w', 83);
        letterTable.put('x', 89);
        letterTable.put('y', 97);
        letterTable.put('z', 101);
    }

    /**
     * method to add word in the hashTable
     * @param s
     * @throws IllegalArgumentException
     */

    public void addWord(String s) throws IllegalArgumentException {
        if (s == null) {

            throw new IllegalArgumentException();
        }
        if (s.length() == 0) {
            throw new IllegalArgumentException();
        }

        long aWord = myHashCode(s);
        if (!anagramTable.containsKey(aWord)) {
            ArrayList<String> aList = new ArrayList<>();
            aList.add(s);
            anagramTable.put(aWord, aList);
        }
        else
        {
            anagramTable.get(aWord).add(s);
        }

}

    /**
     * method to compute the hashCode
     * @param s
     * @return
     */
    private Long myHashCode(String s)
    {
        long uft = 1;
        int length = 0;
        while(length < s.length())
        {
            uft = uft * (long)letterTable.get(s.charAt(length));
            length += 1;
        }
//        System.out.println(uft);
        return uft;
    }

    private void processFile(String s) throws IOException
    {
        FileInputStream fstream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while((strLine = br.readLine()) != null)
        {
            this.addWord(strLine);
        }
        br.close();
    }

    /**
     * returns  the entries in the  anagramTable  that have the largest number of anagrams.
     * @return
     */
    private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries()
    {
        int maxSize = 0;
        long result = 0;
        ArrayList<Map.Entry<Long,ArrayList<String>>> resultMap = new ArrayList<>();
        for (Map.Entry<Long,ArrayList<String>> maxEnt : anagramTable.entrySet())
        {
            int key = maxEnt.getValue().size();
            if(key > maxSize)
            {
                resultMap.clear();
                resultMap.add(maxEnt);
                maxSize = key;
            }
            else if(key == maxSize)
            {
                resultMap.add(maxEnt);
            }
        }
        return resultMap;
    }

    public static void main(String[] args)
    {
        Anagrams a =  new Anagrams();
        final long startTime = System.nanoTime();
        try
        {
            a.processFile("/Users/srivishnuramkaruppanasamy/IdeaProjects/hw6/src/words_alpha.txt");
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        ArrayList<Map.Entry<Long,ArrayList<String>>>maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double) estimatedTime/1000000000);
        System.out.println("Time:" +seconds);
        System.out.println("List of max anagrams:" +maxEntries);
        for(int i=0;i<maxEntries.size();i++)
        {
            System.out.println("Key  of  max  anagrams : "+maxEntries.get(i).getKey());
            System.out.println("List  of  max  anagrams : "+maxEntries.get(i).getValue());
            System.out.println("Length  of  list  of  max  anagrams :  "+maxEntries.get(i).getValue().size());
        }
    }
}
