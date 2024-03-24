import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class get's a list of items that the user wants to sort. After a series of questions, 
 * the output is an ordered list of said items. The order depends on the users answers.
 * 
 * @author Joshua Ramirez Sanchez
 * @date 1/12/2024
 */
public class SortingComparionsTwo
{
    private ArrayList<String> strings = new ArrayList<>();

    /**
     * Prompts the user to enter items to sort separated by a comma and a space.
     * 
     * @return An array of items entered by the user.
     * @throws IOException if an I/O error occurs.
     */
    public String[] getItems() throws IOException
    {
        System.out.println("Please enter items to sort seperared by a comma and a space: ");
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        String[] list = bi.readLine().split(", ");
        return randomize(list);
    }
    
    /**
     * Randomizes the order of items in the array.
     * 
     * @param arr The array of items to be randomized.
     * @return The array with randomized item order.
     */
    public String[] randomize(String[] arr)
    {
        int rand;
        String temp = "";
        for (int i = 0; i < arr.length; i++)
        {
            rand = (int)(Math.random()*arr.length);
            temp = arr[i];
            arr[i] = arr[rand];
            arr[rand] = temp;
        }
        return arr;
    }
    
     /**
     * Sorts the items based on the answers of the user.
     * 
     * @param items The array of items to be sorted.
     * @return The array of sorted items.
     */
    public String[] sort(String[] items)
    {
        String temp = "";
        @SuppressWarnings("resource")
        Scanner list = new Scanner(System.in);
        for (int j = 0; j < items.length; j++)
        {
            for (int i = 0; i < items.length - 1; i++)
            {
                if (hasCompared(items[i], items[i + 1]))
                {
                    System.out.println("Pick your favorite item using 1 or 2:" + '\n' 
                        + "1. " + items[i] + " or " + "2. " + items[i + 1]);
                    int num = list.nextInt();
                    if (num == 2)
                    {   
                        temp = items[i];
                        items[i] = items[i + 1];
                        items[i + 1] = temp;
                    }
                }
            }
        }
        return items;
    }

    /**
     * Checks if two items have been compared before.
     * 
     * @param a The first item to compare.
     * @param b The second item to compare.
     * @return True if the items have not been compared before, false otherwise.
     */
    public boolean hasCompared(String a, String b)
    {
        String combined = a + b;
        String combined2 = b + a;

        if (strings.contains(combined) || strings.contains(combined2))
        {
            return false;
        }
        strings.add(combined);
        strings.add(combined2);
        return true;
    }

     /**
     * Creates a string representation of the ordered list of items.
     * 
     * @param order The array of items in their ordered sequence.
     * @return The string representation of the ordered list of items.
     */
    public String toString(String[] order)
    {
        String list = "Ordered List of Items:" + '\n';
        for (int i = 0; i < order.length; i++)
        {
            list += (i + 1) + ". " + order[i] + '\n';
        }
        return list;
    }
    
    public static void main(String arg[]) throws IOException
    {
        SortingComparionsTwo obj = new SortingComparionsTwo();
        
        System.out.println(obj.toString(obj.sort(obj.getItems())));
    }
}