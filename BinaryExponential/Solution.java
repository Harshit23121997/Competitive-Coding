package BinaryExponential;

import java.util.Scanner;

class Solution{
    static long binpow(long a,long b,long m){
        a%=m;
        if(b==0)
            return 1;
        long res=binpow(a,b>>1,m);
        if((b&1)==1){
            res=((res*res)%m)*(a);
        }
        else
            res=(res*res)%m;
        
        return res;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //System.out.println("Enter a,b, m to calculate (a^b)%m");
        int t=sc.nextInt();
        for(int i=0;i<t;i++){
            long a=sc.nextLong();
            long b=sc.nextLong();
            long m=sc.nextLong();
            System.out.println(binpow(a,b,m));
        }
        sc.close();
    }
    
}