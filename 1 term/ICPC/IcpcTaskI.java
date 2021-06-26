import java.util.Scanner;
 
public class IcpcTaskI {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x = scan.nextInt();
        int y = scan.nextInt();
        int h = scan.nextInt();
        int xl = x - h;
        int yl = y - h;
        int xr = x + h;
        int yr = y + h;
 
        if (n == 1) {
            System.out.print(x + " " + y + " " + h);
        } else {
            for (int i = 0; i < n - 1; i++) {
                x = scan.nextInt();
                y = scan.nextInt();
                h = scan.nextInt();
                xl = Math.min(x - h, xl);
                yl = Math.min(y - h, yl);
                xr = Math.max(x + h, xr);
                yr = Math.max(y + h, yr);
            }
            double k = Math.ceil(Math.max(xr - xl, yr - yl) / 2);
            x = (xl + xr) / 2;
            y = (yl + yr) / 2;
            System.out.print(x);
            System.out.print(" ");
            System.out.print(y);
            System.out.print(" ");
            System.out.print((int) Math.ceil(((Math.max((double) xr - xl, (double) yr - yl)) / 2)));
        }
 
    }
 
}
