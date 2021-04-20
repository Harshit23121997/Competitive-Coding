import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

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
    static class Hex{
        int x,y,height;
        Hex(int x, int y,int h){
            this.x=x;
            this.y=y;
            this.height=h;
        }
    }
    static class HexComparator implements Comparator<Hex>{
        public int compare(Hex h1,Hex h2){
            if(h1.height>h2.height)
                return 1;
            if(h1.height<h2.height)
                return -1;
            return 0;
        }
    }
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BFS\\Water\\input.txt"));
        Reader sc=new Reader();
        int t=sc.nextInt();
        while(t-- >0){
            PriorityQueue<Hex> queue= new PriorityQueue<Hex>(10,new HexComparator());
            int n=sc.nextInt();
            int m=sc.nextInt();
            boolean visited[][]=new boolean[n][m];
            int mat[][]=new int[n][m];
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    mat[i][j]=sc.nextInt();
                    if(i==0 || j==0 || i==(n-1) || j==(m-1)){
                        visited[i][j]=true;
                        queue.add(new Hex(i,j,mat[i][j]));
                    }
                }
            }
            int level=Integer.MIN_VALUE;
            int water=0;
            int[][] dir = new int[][]{{0, 1},{0, -1},{1, 0},{-1, 0}};
            while(!queue.isEmpty()){
                Hex hex=queue.remove();
                int x=hex.x,y=hex.y,h=hex.height;
				//System.out.println("Popped is "+x+" "+y+" "+h);
                if(h<level)
                    water+=(level-h);
                level=Math.max(level,h);
                for(int i=0;i<dir.length;i++){
                    int nx=x+dir[i][0];
                    int ny=y+dir[i][1];
                    if(nx<0 || ny<0 || nx>=n || ny>=m || visited[nx][ny] )
                        continue;
                    queue.add(new Hex(nx,ny,mat[nx][ny]));
                    visited[nx][ny]=true;
                }
            }
            System.out.println(water);
        }

    }
}
