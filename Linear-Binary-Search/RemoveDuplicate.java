import java.util.*;
public class RemoveDuplicate {
    public static String removeDuplicate(String str){
        // Initialize an empty StringBuilder to store the result

        StringBuilder sb = new StringBuilder();

        // using hashset to hs  charcter
        HashSet<Character> hs  = new HashSet<>();

        // itertae each charcter in the String

        for(char c:str.toCharArray()){
            // if charcter not already in the hashset
            if(!hs.contains(c)){
                // Append the result and add set
                sb.append(c);
                hs.add(c);

            }
        }
        // return the result as a String
        return sb.toString();
    }

    public static void main(String[] args) {
        // create a Scanner object for user input

        Scanner sc = new Scanner(System.in);

        // user to enter a String
        System.out.print("Enter a String: ");
        String str = sc.nextLine();

        // Remove duplicates from the str String

        String ans = removeDuplicate(str);

        // Display the result
        System.out.println("String Without Duplicates: " + ans);
    }
}
