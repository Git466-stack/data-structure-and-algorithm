import java.util.*;
public class ReverseString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.reverse();
        System.out.println(sb);
    }
}
