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

    /**
     * 99乘法表
     */
    public static void nineNineMultiplication(){
        for(int x=1; x<=9; x++){
            for(int y=1; y<=x; y++){
                System.out.print(y + "*" + x + "=" + y * x + "\t");
            }
            System.out.println();
        }
    }

    /**
     * num的阶乘
     * @param num
     */
    public static int factorial(int num){
        if(num == 1){
            return 1;
        }else{
            return num*factorial(num-1);
        }
    }

    /**
     * 判断数组arr2是否完全包含arr1
     * arr2含连续的arr1整体
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean judgeArrayContain(char[] arr1, char[] arr2){
        if(arr1==null || arr1.length==0 || arr2==null || arr2.length==0 || arr2.length<arr1.length){
            return false;
        }

        /**
         * 两个等长数组
         */
        if(arr1.length == arr2.length){
            for(int x=0;x<arr1.length;x++){
                if(arr1[x] != arr2[x]){
                    return false;
                }
            }
            return true;
        }

        int index = 0;
        boolean flag1 = false;
        for(int x=0;x<arr1.length;x++){
            char char1 = arr1[x];
            for(int y=0;y<arr2.length;y++){
                if(!flag1){
                    if(char1==arr2[y]){
                        index = y;
                        flag1 = true;
                        break;
                    }
                    if(y==arr2.length-1){//第一轮检测无
                        return false;
                    }
                }else{
                    if(index+1>arr2.length-1){//越界
                        return false;
                    }
                    if(char1==arr2[index+1]){
                        index++;
                        break;
                    }
                    return false;
                }
            }
        }

        return true;
    }
}
