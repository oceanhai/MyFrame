package com.example;

/**
 * 算法utils
 */
public final class AlgorithmUtils {

    /**
     * 递归算法 一次输出每一位对应的数字
     * @param num
     */
    public static void printOut(int num){
        if(num >= 10){
            printOut(num/10);
        }
        System.out.println(num%10);
    }

    /**
     * 洗牌算法
     * @param data
     */
    public static void shuffleSort(int[] data) {
        if(data == null || data.length == 0){
            return;
        }

        for (int i = 0; i < data.length - 1; i++) {
            int j = (int) (data.length * Math.random());
            swap(data, i, j);
        }
    }

    /**
     * 洗牌算法 - 交换
     * @param data
     * @param i
     * @param j
     */
    private static void swap(int[] data, int i, int j) {
        if (i == j) {
            return;
        }
        //交换 方式1
//        data[i] = data[i] + data[j];
//        data[j] = data[i] - data[j];
//        data[i] = data[i] - data[j];
        //交换 方式2
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
