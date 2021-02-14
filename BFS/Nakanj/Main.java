package BFS.Nakanj;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


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
    public static void main(String[] args) throws IOException {
        Reader sc=new Reader();
        int t=sc.nextInt();
        for(int i=0;i<t;i++){
            String start=sc.readLine();
            String str[]=start.split(" ");
            int st2=str[0].charAt(0)-'a';
            int st=str[0].charAt(1)-'1';
            int ds2=str[1].charAt(0)-'a';
            int ds=str[1].charAt(1)-'1';
            //System.out.println(st+" "+st2+" "+ds+" "+ds2);
            boolean visited[][]=new boolean[8][8];
            Queue<Pair> q=new LinkedList<>();
            q.add(new Pair(st, st2,0));
            while(!q.isEmpty()){
                Pair pair=q.remove();
                visited[pair.first][pair.second]=true;
                if(pair.first==ds && pair.second==ds2){
                    System.out.printf("%d \n",pair.chances);
                    break;
                }
                int f=pair.first;
                int s=pair.second;
                int c=pair.chances;
                if(f-1>=0){
                    if(s-2>=0 && !visited[f-1][s-2]){
                        q.add(new Pair(f-1,s-2,c+1));
                    }
                    if(s+2<8 && !visited[f-1][s+2]){
                        q.add(new Pair(f-1, s+2, c+1));
                    }
                }
                if(f-2>=0){
                    if(s-1>=0 && !visited[f-2][s-1]){
                        q.add(new Pair(f-2,s-1,c+1));
                    }
                    if(s+1<8 && !visited[f-2][s+1]){
                        q.add(new Pair(f-2, s+1, c+1));
                    }
                }
                if(f+1<8){
                    if(s-2>=0 && !visited[f+1][s-2]){
                        q.add(new Pair(f+1,s-2,c+1));
                    }
                    if(s+2<8 && !visited[f+1][s+2]){
                        q.add(new Pair(f+1, s+2, c+1));
                    }
                }
                if(f+2<8){
                    if(s-1>=0 && !visited[f+2][s-1]){
                        q.add(new Pair(f+2,s-1,c+1));
                    }
                    if(s+1<8 && !visited[f+2][s+1]){
                        q.add(new Pair(f+2, s+1, c+1));
                    }
                }
            }
            
        }
    }
    
}
