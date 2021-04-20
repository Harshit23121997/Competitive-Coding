import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

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
    static int returnMinutes(String time){
        int t=0;
        //System.out.println("Time is "+time);
        String str[]=new String[2];
        str=time.split(" ");
        //System.out.println(str[0]+ " this is zone");
        if(time.charAt(6)=='P'){
            t=t+12*60;
        }
        str=str[0].split(":");
        int hrs=Integer.parseInt(str[0]);
        int mins=Integer.parseInt(str[1]);
        t=t+(hrs%12)*60+mins;
        return t;
    }
	static int returnResult(String times,int req){
		String str[]=times.split(" ");
		int t1=0,t2=0;
		if(str[1].equals("PM")){
			t1+=720;
		}
		if(times.charAt(15)=='P'){
			t2+=720;
		}
		String first[]=str[0].split(":");
		int hrs=Integer.parseInt(first[0]);
        int mins=Integer.parseInt(first[1]);
		t1=t1+(hrs%12)*60+mins;
		first=str[2].split(":");
		hrs=Integer.parseInt(first[0]);
        mins=Integer.parseInt(first[1]);
        t2=t2+(hrs%12)*60+mins;
		//System.out.println("TIme is "+t1+"  "+t2);
		if(t1<=req && t2>=req)
			return 1;
		return 0;
	}
    public static void main(String[] args) throws IOException {
        Reader sc=new Reader();
        int t=sc.nextInt();
		//returnResult("12:00 PM 11:42 PM");
        for(int i=0;i<t;i++){
            String time=sc.readLine();
            int reqTime=returnMinutes(time);
            //System.out.println("Req time "+reqTime);
            int n=sc.nextInt();
            for(int j=0;j<n;j++){
				int re=returnResult(sc.readLine(),reqTime);
                System.out.print(re);
            }
			System.out.println();
        }
    }
}