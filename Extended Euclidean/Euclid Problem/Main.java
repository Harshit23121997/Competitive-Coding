import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main{
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(
                new InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() { return Integer.parseInt(next()); }
 
        long nextLong() { return Long.parseLong(next()); }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
	public static long gcd(long a, long b,Data x,Data y){
        if(b==0){
            x.x=1;
            y.x=0;
            return a;
        }
        Data x1=new Data(1);
        Data y1=new Data(1);
        long d=gcd(b,a%b,x1,y1);
        x.x=y1.x;
        y.x=x1.x-y1.x*(a/b);
        return d;
    } 
    static class Data{
        long x;
        Data(long x){
            this.x=x;
        }
    }
    public static void main(String[] args) throws IOException {
        
        Scanner sc=new Scanner(System.in);
		int t = 1;
		while(t-- >0)
        {
            Deque<Integer> q=new ArrayDeque<>();
            q.push(1);
            q.push(2);
            System.out.println(q.getLast());
            System.out.println(q.getLast());
		}
    }
}