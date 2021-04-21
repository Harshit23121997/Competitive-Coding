
import java.util.ArrayList;

public class Kcores {
    static class Graph{
        private int V;
        ArrayList<Integer> adj[];
        Graph(int v){
            this.V=v;
            this.adj=new ArrayList[v];
            for(int i=0;i<V;i++)
                this.adj[i]=new ArrayList<Integer>();
        }
        void addEdge(int v, int w){
            adj[w].add(v);
            adj[v].add(w); // Add w to v's list.
        }
        boolean DFSUtil(int v,boolean visited[],int vDegrees[],int k){
            visited[v]=true;
            for(int i:adj[v]){
                if(vDegrees[v]<k){
                    vDegrees[i]--;
                }
                if(!visited[i]){
                    DFSUtil(i, visited, vDegrees, k);
                }
            }
            return vDegrees[v]<k;
        }
        // void DFS(int i){
        //     boolean visited[]=new boolean[this.V];
        //     DFSUtil(i,visited);
        // }
        void printKCores(int k){
            int vDegrees[]=new int[this.V];
            boolean visited[]=new boolean[this.V];
            int minDegree=Integer.MAX_VALUE;
            int minVertex=-1;
            for(int i=0;i<V;i++){
                vDegrees[i]=this.adj[i].size();
                if(vDegrees[i]<minDegree){
                    minDegree=vDegrees[i];
                    minVertex=i;
                }
            }
            DFSUtil(minVertex, visited,vDegrees,k);
            for(int i=0;i<V;i++){
                if(!visited[i])
                    DFSUtil(i, visited, vDegrees, k);
            
            }
            System.out.println("K-Cores");
            for(int i=0;i<V;i++){
                if(vDegrees[i]>=k){
                    System.out.printf("\n[%d]",i);
                    for(int j:adj[i]){
                        if(vDegrees[j]>=k)
                            System.out.printf(" -> %d ",j);
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        Graph g1 = new Graph(9);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(1, 5);
        g1.addEdge(2, 3);
        g1.addEdge(2, 4);
        g1.addEdge(2, 5);
        g1.addEdge(2, 6);
        g1.addEdge(3, 4);
        g1.addEdge(3, 6);
        g1.addEdge(3, 7);
        g1.addEdge(4, 6);
        g1.addEdge(4, 7);
        g1.addEdge(5, 6);
        g1.addEdge(5, 8);
        g1.addEdge(6, 7);
        g1.addEdge(6, 8);
        g1.printKCores(3);
    }
}
