import java.util.Arrays;

class Main{
    private static final int V = 5;
    static void primMST(int graph[][]){
        int parent[]=new int[V];
        int key[]=new int[V];
        boolean mstSet[]=new boolean[V];
        Arrays.fill(mstSet,false);
        Arrays.fill(key,Integer.MAX_VALUE);
        key[0]=0;
        parent[0]=-1;
        for(int i=0;i<V-1;i++){
            int u=minKey(key,mstSet);
            mstSet[u]=true;
            for(int v=0;v<V;v++){
                if(graph[u][v]!=0 && !mstSet[v] && key[v]> graph[u][v]){
                    key[v]=graph[u][v];
                    parent[v]=u;
                }
            }
        }
        printMST(parent,graph);
    }
    static void printMST(int parent[],int graph[][]){
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }
    static int minKey(int key[],boolean mstSet[]){
        int min=Integer.MAX_VALUE;
        int index=-1;
        for(int i=0;i<V;i++){
            if(!mstSet[i] && key[i]<min){
                min=key[i];
                index=i;
            }
        }
        return index;
    }
    public static void main(String[] args) {
        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 },
                                      { 2, 0, 3, 8, 5 },
                                      { 0, 3, 0, 0, 7 },
                                      { 6, 8, 0, 0, 9 },
                                      { 0, 5, 7, 9, 0 } };
        primMST(graph);
    }
}