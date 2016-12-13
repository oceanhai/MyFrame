package com.example.mianshi;

/**
 1	2	3	4
 5	6	7	8
 9	10	11	12
 13	14	15	16
 m行n列数组，要求输出结果1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10一圈一圈由外到内输出结果
 */
public class TestYiXia {

    public static void main(String[] args){
        method01(4,5);
    }

    public static void method01(int m, int n){
        int[][] data = initData(m,n);
        for (int x=0;x<data.length;x++){
            for (int y=0;y<data[x].length;y++){
                System.out.print(data[x][y]+"\t");
            }
            System.out.println();
        }

        print(data);
    }

    private static void print(int[][] data) {
        int m = data.length;
        int n = data[0].length;
        int quan = 0;
        while (true){
            for(int a=quan;a<n-quan;a++){
                System.out.print(data[quan][a]+"\t");
            }
            for(int b=1+quan;b<m-quan;b++){
                System.out.print(data[b][n-1-quan]+"\t");
            }
            for(int c=n-1-1-quan;c>=quan;c--){
                System.out.print(data[m - quan - 1][c] + "\t");
            }
            for (int d=m-1-1-quan;d>=quan+1;d--){
                System.out.print(data[d][quan]+"\t");
            }
            quan++;
            if(m%2==0 || n%2==0){//行或列 为偶数
                if(quan >= Math.min(m,n)/2){
                    break;
                }
            }else{//5*5 这种行列都是奇数的
                if(quan > Math.min(m,n)/2){
                    break;
                }
            }

        }
    }

    private static int[][] initData(int m, int n) {
        int num = 1;
        int[][] erwei = new int[m][n];
        for(int x=0;x<m;x++){
            int[] yiwei = new int[n];
            for(int y=0;y<n;y++){
                yiwei[y] = num++;
            }
            erwei[x] = yiwei;
        }

        return erwei;
    }

}
