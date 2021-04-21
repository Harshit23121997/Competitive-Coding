import java.util.Iterator;
import java.util.LinkedList;

public class Main  {
    static class Graph{
        private int V;
        LinkedList<Integer> adj[];
        Graph(int v){
            this.V=v;
            this.adj=new LinkedList[v];
            for(int i=0;i<V;i++)
                this.adj[i]=new LinkedList<>();
        }
        void addEdge(int v, int w){
            adj[v].add(w); // Add w to v's list.
        }
        void DFSUtil(int i,boolean visited[]){
            visited[i]=true;
            
            //System.out.print(i+" ");
            Iterator<Integer> it=this.adj[i].listIterator();
            while(it.hasNext()){
                //System.out.println("Here");
                int n=it.next();
                if(!visited[n])
                    DFSUtil(n, visited);
            }
        }
    }
    public static void main(String[] args) {
        Graph g=new Graph(6);

        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(2, 1);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 5);
        boolean visited[]=new boolean[6];
        int possible=-1;
        for(int i=0;i<6;i++){
            if(!visited[i]){
                g.DFSUtil(i, visited);
                possible=i;
            }
        }
        visited=new boolean[6];
        g.DFSUtil(possible, visited);
        for(int i=0;i<6;i++){
            if(!visited[i]){
                possible=-1;
            }
        }
        System.out.println("Mother Vertex is "+possible);

    }
}
