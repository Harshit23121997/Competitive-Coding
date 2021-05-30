import java.util.*;
class Main
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- >0){
            int a=sc.nextInt();
            long b=sc.nextLong();
            int last=a%10;
            if(b==0){
                System.out.println(1);
                continue;
            }
            b%=4;
            b=(b==0)?4:b;
            for(int i=1;i<b;i++)
                last*=(a%10);
            System.out.println(last%10);
        }
    }
} 