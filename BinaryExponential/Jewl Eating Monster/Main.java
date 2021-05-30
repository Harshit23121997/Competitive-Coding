import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
    
	public static long binpow(long a,long b,long m){
        a%=m;
        long res=1;
        while(b>0){
            if((b&1)==1)
                res=(res*a)%m;
            a=(a*a)%m;
            b>>=1;
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        File file=new File("input.txt");
        long startTime=0;
        if(file.exists()){
            System.setIn(new FileInputStream("input.txt"));
            startTime=System.currentTimeMillis();
        }
        FastReader sc=new FastReader();
		//int t = sc.nextInt();
		while(true)
        {
			long x=sc.nextLong();
            long a=sc.nextLong();
            long n=sc.nextLong();
            long c=sc.nextLong();
            if(x==0 && a==0 && n==0 && c==0)
                break;
            long res=x;
            for(long i=0; i<n; i++) {
                res=(res-1)*a;
                res%=c;
            }
            res=(res+c)%c;
            System.out.println(res);
		}
        if(file.exists()){
            long endTime=System.currentTimeMillis();
            System.out.println("Time Taken by program "+(endTime-startTime)+" miliseconds");
        }
    }
}