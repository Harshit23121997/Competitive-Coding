import java.util.Iterator;
import java.util.LinkedList;

public class Main {
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
            
            System.out.print(i+" ");
            Iterator<Integer> it=this.adj[i].listIterator();
            while(it.hasNext()){
                System.out.println("Here");
                int n=it.next();
                if(!visited[n])
                    DFSUtil(n, visited);
            }
        }
        void DFS(int i){
            boolean visited[]=new boolean[this.V];
            DFSUtil(i,visited);
        }
    }
    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        g.DFS(0);
        System.out.println();

    }
}
