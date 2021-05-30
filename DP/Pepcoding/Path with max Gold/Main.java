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
	static int getMaxGold(int gold[][],int m,int n){
		int dp[][]=new int[m][n];
		for(int i=n-1;i>=0;i--){
			for(int j=m-1;j>=0;j--){
				if(i==n-1 || gold[j][i]==0)
					dp[j][i]=gold[j][i];
				else{
					int right=dp[j][i+1];
					int right_up=0;
					if(j-1>=0)
						right_up=dp[j-1][i+1];
					int right_down=0;
					if(j+1<n)
						right_down=dp[j+1][i+1];
					int ma=Math.max(right_down, Math.max(right, right_up));
					dp[j][i]=gold[j][i]+ma;
				}
			}
		}
		for(int i=01;i<m;i++){
			dp[0][0]=Math.max(dp[0][0],dp[i][0]);
		}
		return dp[0][0];
	}
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Reader sc=new Reader();
		int t=sc.nextInt();
		while(t-- >0)
        {
			int gold[][]= { {10, 33, 13, 15},
                        {22, 21, 4, 1},
                        {5, 0, 2, 3},
                        {0, 6, 14, 2} };
                         
        int m = 4, n = 4;
         
        System.out.print(getMaxGold(gold, m, n));	
		}
    }
}