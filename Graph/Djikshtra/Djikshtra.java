import java.util.Arrays;

class Djikshtra{
    private static final int V = 9;
    static void primMST(int graph[][]){
        int key[]=new int[V];
        boolean mstSet[]=new boolean[V];
        Arrays.fill(mstSet,false);
        Arrays.fill(key,Integer.MAX_VALUE);
        key[0]=0;
        for(int i=0;i<V-1;i++){
            int u=minKey(key,mstSet);
            mstSet[u]=true;
            for(int v=0;v<V;v++){
                if(graph[u][v]!=0 && !mstSet[v] && key[v]> key[u]+graph[u][v]){
                    key[v]=key[u]+graph[u][v];
                }
            }
        }
        printMST(key);
    }
    static void printMST(int key[]){
        System.out.println("Vertex \t Distance");
        for (int i = 0; i < V; i++)
            System.out.println(i + "\t" + key[i]);
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
        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                                      { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                                      { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                                      { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                                      { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                                      { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                                      { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                                      { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                                      { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        primMST(graph);
    }
}