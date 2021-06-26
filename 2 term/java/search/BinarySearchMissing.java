package search;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BinarySearchMissing {

    private static int ans;

    // pre: mas: для любого i принадлежащего [0; len(mas) - 1] mas[i] <= mas[i - 1] && elem принадлежит Z
    public static int binaryIterativeSearch(int elem, int[] mas) {
        // pre: для любого i принадлежащего [0; len(mas) - 1] mas[i] <= mas[i - 1]
        if (mas.length == 0 || mas[0] < elem) {
            // pre: mas.length == 0 || mas[0] < elem
            // => i = 1 => R = -1
            return -1;
            // post: R = -1 && Immutable
        } else if (mas[mas.length - 1] > elem) {
            // pre: mas[mas.length - 1] > elem => i = mas.length => R = -1 * mas.length - 1
            return -1 * mas.length - 1;
            // post: R = -1 * mas.length - 1 && Immutable
        } else {
            // pre: для любого i принадлежащего [0; len(mas) - 1] mas[i] <= mas[i - 1]
            int left = -1;
            // post: left = -1 && для любого i принадлежащего [0; len(mas) - 1] mas[i] <= mas[i - 1]

            // pre: left = -1 && для любого i принадлежащего [0; len(mas) - 1] mas[i] <= mas[i - 1]
            int right = mas.length;
            // post: right = mas.length && left = -1 && для любого i принадлежащего [0; len(mas) - 1] mas[i] <= mas[i - 1]

            // pre: right = mas.length && mas.length >= 0 && left = -1 && для любого i принадлежащего [0; len(mas) - 1] mas[i] <= mas[i - 1]
            while (left < right - 1) {

                // pre: left < right - 1 && min i: mas[i] <= elem принадлежит [left; right] && right = right' && left = left'
                int middle = (left + right) / 2;
                // post: middle = (left + right) / 2 && middle >= 0 && min i: mas[i] <= elem принадлежит [left; right] &&
                // && left < right - 1 && right = right' && left = left'

                //pre: min i: mas[i] <= elem принадлежит [left; right] && middle = (left + right) / 2 && && left < right - 1 &&
                // && right = right' && left = left'
                if (mas[middle] > elem) {

                    // pre: mas[middle] > elem && min i: mas[i] <= elem принадлежит [middle; right] && right = right'
                    left = middle;
                    // post: min i: mas[i] <= elem принадлежит [left; right] && right = right' && left' = middle && left = left'

                } else {

                    // pre: mas[middle] <= elem && min i: mas[i] <= elem принадлежит [left; middle]  && right = right' && left = left'
                    right = middle;
                    // post: right' = middle && right = right' && min i: mas[i] <= elem принадлежит [left; right] && left = left'

                }
                // post: min i: mas[i] <= elem принадлежит [left'; right'] && mas[left'] > elem >= mas[right']
            }
            // post: 1 >= right' - left' && min i: mas[i] <= elem принадлежит [left'; right'] && mas[left'] > elem >= mas[right']
            // т е мы определили минимальные границы => min i (mas[i] <= elem) = right'
            // pre: min i: mas[i] <= elem == right && immutable
            if (mas[right] != elem) {
                // pre: min i: mas[i] < elem == right && immutable
                right = -1 * right - 1;
                // post: right' = -1 * right -1 && min i = right' && immutable
            }
            // post: min i: mas[i] <= elem == right && immutable
            return right;
        }
        // post: R = i: mas[i] = elem || R = -i - 1: min i: mas[i] < elem && Immutable
    }

    // pre: mas: для любого i принадлежащего [0; len(mas) - 1] mas[i] <= mas[i - 1] && elem принадлежит Z
    public static int binaryRecursionSearch(int elem, int[] mas, int left, int right) {

        // pre: для любого i принадлежащего [0; len(mas) - 1] mas[i] <= mas[i - 1] && (изначально) left = - 1 && right = mas.length &&
        // right = right' && left = left'

        // pre: min i: mas[i] <= elem принадлежит [left'; right'] && mas[left'] > elem >= mas[right'] && для любого i принадлежащего [0; len(mas) - 1] mas[i] <= mas[i - 1]
        if (right - left == 1) {
            // pre: right - left == 1 && min i: mas[i] <= elem принадлежит [left'; right'] && mas[left'] > elem >= mas[right']
            // => минимальные границы => min i (mas[i] <= elem) = right'
            return right;
            // post: min i (mas[i] <= elem) = right' == ans
        }
        // post: ans = min(i) (mas[i] <= elem)

        else {
            // pre: right - left != 1 && min i: mas[i] <= elem принадлежит [left'; right'] && mas[left'] > elem >= mas[right']
            int middle = (left + right) / 2;
            // post: middle = (left + right) / 2 && right - left != 1 && min i: mas[i] <= elem принадлежит [left'; right'] && mas[left'] > elem >= mas[right']
            if (mas[middle] == elem) {
                // pre: middle = min(i): mas[i] <= elem && middle = (left + right) / 2 && right - left != 1
                return middle;
                // post: min i (mas[i] <= elem) = middle == ans
            } else if (mas[middle] < elem) {
                // pre: mas[middle] < elem && min i: mas[i] <= elem принадлежит [left; middle] && right = right' && mas[left] > elem >= mas[right]
                binaryRecursionSearch(elem, mas, left, middle);
            } else if (mas[middle] > elem) {
                // pre: mas[middle] > elem && min i: mas[i] <= elem принадлежит [middle; right] && right = right' && mas[middle] > elem >= mas[right]
                binaryRecursionSearch(elem, mas, middle, right);
                // post: right' = middle && right = right' && min i: mas[i] <= elem принадлежит [left; right] && left = left' && mas[left] > elem >= mas[right]
            }
        }
        // post: min i: mas[i] <= elem && Immutable
        return right;
        //post: R = i : mas[i] <= elem && Immutable

    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int a[] = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            a[i - 1] = Integer.parseInt(args[i]);
        }

        System.out.println(binaryIterativeSearch(x, a));
    }

}