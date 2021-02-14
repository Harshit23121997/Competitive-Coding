package BinaryExponential.ParkingLot;
import java.util.Scanner;

public class Solution {
    static long binpow(long a,long b){
        if(b==0)
            return 1;
        long res=binpow(a,b/2);
        if(b%2==1)
            res=res*res*a;
        else
            res=res*res;
        
        return res;

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        long ncr=4*3*2*binpow(4, n-3)+3*4*3*binpow(4, n-4)*(n-3);
        System.out.println(ncr);
        sc.close();
    }
    
}
