import java.util.*;
public class IcpcTaskM {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        int n = scan.nextInt(); // кол-во тестов
        for (int m = 0; m < n; m++) {
            int l = scan.nextInt(); // кол-во входящих задач
            int in[] = new int[l];
            int otv = 0;
            for (int h = 0; h < l; h++) {
                in[h] = scan.nextInt();
            }
            for (int j = l - 1; j > 0; j--) {
                for (int i = 0; i < j; i++) {
 
                    if (hashMap.containsKey(2*in[j] - in[i])){
                        otv += hashMap.get(2*in[j] - in[i]);
                    }
                }
                if (hashMap.containsKey(in[j])) {
                    hashMap.put(in[j], hashMap.get(in[j]) + 1);
                } else {
                    hashMap.put(in[j], 1);
                }
            }
            System.out.println(otv);
            hashMap.clear();
        }
    }
 
}
