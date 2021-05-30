import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
    static long m=1000000007;
    static long binpow(long a,long b){
        a%=m;
        long res=1;
        while(b>0){
            if((b&1)==1){
                res=(res*a)%m;
            }
            a=(a*a)%m;
            b>>=1;
        }
        return res;
    }
	
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        FastReader sc=new FastReader();
        BufferedWriter output = new BufferedWriter(
            new OutputStreamWriter(System.out));
		int t = sc.nextInt();
		while(t-- >0)
        {
			long n=sc.nextLong();
            if(n<2){
                output.write(1+"\n");
                continue;
            }
            
            long d=n/3;
            long rem=n%3;
            if(rem==1){
                d--;
                rem+=3;
            }
            else if(rem==0)
                rem=1;
            output.write(((binpow(3, d)*rem)%m)+"\n");
		}
        output.flush();
    }
}