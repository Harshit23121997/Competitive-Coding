//https://codeforces.com/problemset/problem/1472/B

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
	static boolean exist(String str, String pat,int m,int n,int visited[][]){
		if(m==0)
			return false;
		//System.out.println("m and n "+m+" "+n);
		if(n==1 && str.charAt(m-1)== pat.charAt(n-1) )
			return true;
		
		
		if(str.charAt(m-1)==pat.charAt(n-1) && n!=1){
			if(visited[m-1][n-1]==0){
				if(exist(str, pat, m-1, n-1, visited))
					visited[m-1][n-1]=1;
				else
					visited[m-1][n-1]=-1;
			}
			if(visited[m-1][n]==0){
				if(exist(str, pat, m-1, n, visited))
					visited[m-1][n]=1;
				else
					visited[m-1][n]=-1;
			}
			if(visited[m-1][n-1]==1 || visited[m-1][n]==1)
				return true;
			else
				return false;
		}
		else{
			if(visited[m-1][n]==0){
				if(exist(str, pat, m-1, n, visited))
					visited[m-1][n]=1;
				else
					visited[m-1][n]=-1;
			}
			if(visited[m-1][n]==1)
				return true;
			else
				return false;
		}
	}
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("input.txt"));
        Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		while(t-- >0)
        {
			int n=sc.nextInt();
			int q=sc.nextInt();
			String str=sc.next();
			
			while(q-- >0){
				boolean ans=false;
				int l=sc.nextInt();
				int r=sc.nextInt();
				int visited[][]=new int[n+2][n+2];
				int visited1[][]=new int[n+2][n+2];
				String pat=str.substring(l-1,r);
				String pat1=pat+str.charAt(r-1);
				String pat2=str.charAt(l-1)+pat;
				
				//System.out.println(pat1+" "+pat2);
					if( exist(str, pat2, n,pat1.length(),visited) || exist(str, pat1, n,pat2.length(),visited1)){
						//System.out.println("Here");
						ans=true;
					}
				
				if(ans)
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}
    }
}