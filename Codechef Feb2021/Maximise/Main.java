import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

class Main{
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
    public static void main(String[] args) throws IOException {
        Reader sc=new Reader();
        int t=sc.nextInt();
        
        for(int p=0;p<t;p++){
            long max=-1,min1=1000000001,min2=1000000001;
            int n=sc.nextInt();
            for(int i=0;i<1;i++){
                long x=sc.nextLong();
                long y=sc.nextLong();
                long z=sc.nextLong();
                if(x>=y && x>=z){
                    max=x;
                    if(y>=z){
                        min2=y;
                        min1=z;
                    }
                    else{
                        min2=z;
                        min1=y;
                    }
                }
                else if(y>=x && y>=z){
                    max=y;
                    if(x>=z){
                        min2=x;
                        min1=z;
                    }
                    else{
                        min2=z;
                        min1=x;
                    }
                }
                else{
                    max=z;
                    if(x>=y){
                        min2=x;
                        min1=y;
                    }
                    else{
                        min2=y;
                        min1=x;
                    }
                }

            }
            for(int i=3;i<n;i++){
                
                long x=sc.nextLong();
                if(x>max){
                    max=x;
                    continue;
                }
                if(x<min1 ){
                    min2=min1;
                    min1=x;
                    continue;
                }
                if(x<min2){
                    min2=x;
                }
            }
            //System.out.println(max+" "+min1+" "+min2);
            if(min1>min2)
                System.out.println(((max-min1)+(max-min2)+(min1-min2)));
            else
            System.out.println(((max-min1)+(max-min2)+(min2-min1)));
        }
    }
    
}