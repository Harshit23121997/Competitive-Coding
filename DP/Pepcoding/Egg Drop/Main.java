//https://codeforces.com/problemset/problem/1472/B

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main{
    static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader()
		{
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException
		{
			din = new DataInputStream(
				new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException
		{
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n') {
					if (cnt != 0) {
						break;
					}
					else {
						continue;
					}
				}
				buf[cnt++] = (byte)c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException
		{
			int ret = 0;
			byte c = read();
			while (c <= ' ') {
				c = read();
			}
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException
		{
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException
		{
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException
		{
			bytesRead = din.read(buffer, bufferPointer = 0,
								BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException
		{
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException
		{
			if (din == null)
				return;
			din.close();
		}
	}
	static int eggDrop(int e,int f){
		int dp[][]=new int[e+1][f+1];
		for(int i=0;i<=e;i++){
			for(int j=0;j<=f;j++){
				if(j==0)
					dp[i][j]=0;
				else if(i==0){
					dp[i][j]=-1;
				}
				else if(i==1)
					dp[i][j]=j;
				else if(j==1)
					dp[i][j]=1;
				else{
					int m=Integer.MAX_VALUE;
					for(int k=1;k<=j;k++){
						m=Math.min(m,Math.max(dp[i-1][k-1],dp[i][j-k]));
					}
					dp[i][j]=m+1;
				}
			}
		}
		for(int i=0;i<=e;i++){
			for(int j=0;j<=f;j++){
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		return dp[e][f];

	}
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Reader sc=new Reader();
		int t=sc.nextInt();
		while(t-- >0)
        {
			int e=sc.nextInt();
			int f=sc.nextInt();
			System.out.println(eggDrop(e,f));
		}
    }
}