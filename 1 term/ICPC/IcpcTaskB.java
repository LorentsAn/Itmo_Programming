import java.util.Scanner;
 
public class IcpcTaskB {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int otv = -710 * 25000 + 710 * i;
            System.out.println(otv);
        }
    }
}
