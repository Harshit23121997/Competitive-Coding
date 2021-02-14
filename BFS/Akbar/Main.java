package BFS.Akbar;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
public class Main {
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
    static class Info{
        int city,str,par;
        Info(int city,int str,int par){
            this.city=city;
            this.str=str;
            this.par=par;
        }
    }
    static int maxn=1000009;
    static ArrayList<Integer> roads[]=new ArrayList[maxn];
    static int strength[]=new int[maxn];
    static int visited[]=new int[maxn];
    static int n,x,y,r,m;
    public static void main(String[] args) throws IOException {
        Reader sc=new Reader();
        int t=sc.nextInt();
        for(int i=0;i<t;i++){
            n=sc.nextInt();
            r=sc.nextInt();
            m=sc.nextInt();
            
            for(int j=1;j<=n;j++){
                roads[j]=new ArrayList<Integer>();
                strength[j]=0;
                visited[j]=0;
            }
            for(int j=1;j<=r;j++){
                int x=sc.nextInt();
                int y=sc.nextInt();
                roads[x].add(y);
                roads[y].add(x);
            }
            boolean isGood=true;
            
            for(int j=1;j<=m;j++){
                int c=sc.nextInt();
                int s=sc.nextInt();
                Queue<Info> q=new LinkedList<Info>();
                q.add(new Info(c, s, -1));
                visited[c]=j;
                strength[c]++;
                while(!q.isEmpty()){
                    Info in=q.remove();
                    if(in.str==0)
                        continue;
                    for(int nextCity:roads[in.city]){
                        //System.out.println("City "+nextCity+" Str "+ (in.str-1));
                        if(visited[nextCity]==j)
                            continue;
                        visited[nextCity]=j;
                        strength[nextCity]++;
                        q.add(new Info(nextCity, in.str-1, in.city));
                    }
                }

            }
            if(isGood){
                for(int k=1;k<=n;k++){
                    if(strength[k]!=1){
                        isGood=false;
                        break;
                    }
                }
            }
            if(isGood){
                System.out.println("Yes");
            }
            else
                System.out.println("No");
        }
        sc.close();
    }
    
}
