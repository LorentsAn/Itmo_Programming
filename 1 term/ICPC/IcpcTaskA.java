import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
 
public class IcpcTaskA {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int n = scan.nextInt();
        float m = n - b;
        float l = b - a;
        double k = m / l;
        int otv = (int) Math.ceil(k);
        System.out.print(2 * otv + 1);
        // посмотреть как делать окргдение до верхней границы
    }
}
