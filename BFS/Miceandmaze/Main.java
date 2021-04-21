package Miceandmaze;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
    static class Pair{
        int first,second,chances;
        Pair(int f,int s,int c){
            this.first=f;
            this.second=s;
            this.chances=c;
        }
    }
	static class Graph{
		int V;
		ArrayList adj[];
		int distance[];
		boolean sptSet[];
		Graph(int V,int e){
			this.V=V;
			distance=new int[V];
			adj=new ArrayList[V];
			sptSet=new boolean[V];
			Arrays.fill(sptSet,false);
			Arrays.fill(distance,Integer.MAX_VALUE);
			distance[e]=0;
			
		}
		void addEdge(int u,int v,int w){
			adj[v-1][u-1]=w;
		}
		void djiskshtra(){
			for(int i=0;i<V-1;i++){
				int u=minKey();
				for(int v=0;v<V;v++){
					if(!sptSet[v] && adj[u][v]!=0 && distance[v]>distance[u]+adj[u][v]){
						distance[v]=distance[u]+adj[u][v];
					}
				}
			}
		}
		int minKey(){
			int min=Integer.MAX_VALUE;
			int index=-1;
			for(int i=0;i<V;i++){
				if(!sptSet[i] && min>distance[i]){
					min=distance[i];
					index=i;
				}
			}
			return index;
		}
		int countMice(int t){
			int count=0;
			for(int i=0;i<V;i++){
				if(distance[i]<=t){
					count++;
				}
			}
			return count;
		}
	}

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("Miceandmaze\\input.txt"));
        Reader sc=new Reader();
        int n=sc.nextInt();
		int e=sc.nextInt();
		int t=sc.nextInt();
		int m=sc.nextInt();
		Graph g=new Graph(n,e);
		while(m-- >0){
			int u=sc.nextInt();
			int v=sc.nextInt();
			int w=sc.nextInt();
			g.addEdge(u,v,w);

		}
		
		g.djiskshtra();
		System.out.println(g.countMice(t));
        
    }
    
}
