import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
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
    
	public static long gcd(long a, long b){
        if(b==0)
            return a;
        return gcd(b,a%b);
    } 
    public static void main(String[] args) throws IOException {
        File file=new File("input.txt");
        long startTime=0;
        if(file.exists()){
            System.setIn(new FileInputStream("input.txt"));
            startTime=System.currentTimeMillis();
        }
        BufferedWriter output = new BufferedWriter(
            new OutputStreamWriter(System.out));
        FastReader sc=new FastReader();
		int t = sc.nextInt();
		while(t-- >0)
        {
			long a=sc.nextLong();
            long b=sc.nextLong();
            long g=gcd(a,b);
            long l=((a*b)/g);
            System.out.println(g+" "+l);
		}
        output.flush();
        if(file.exists()){
            long endTime=System.currentTimeMillis();
            System.out.println("Time Taken by program "+(endTime-startTime)+" miliseconds");
        }
    }
}