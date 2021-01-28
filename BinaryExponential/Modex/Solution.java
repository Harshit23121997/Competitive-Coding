package BinaryExponential.Modex;

import java.util.Scanner;

public class Solution {
    public static long binpow(long x,long y,long m){
        x%=m;
        if(y==0)
            return 1;
        long res=binpow(x,y>>1,m);
        if((y&1)==1)
            res=((res*res)%m*x)%m;
        else
            res=(res*res)%m;
        return res;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        for(int i=0;i<t;i++){
            long x=sc.nextLong();
            long y=sc.nextLong();
            long m=sc.nextLong();
            System.out.println(binpow(x,y,m));
            
        }
        //System.out.println("Here");
        //sc.close();
    }
}
