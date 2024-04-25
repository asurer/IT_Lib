import java.util.*;

public class dijkstraAlgorithm {
    
    private static final int INF = Integer.MAX_VALUE; // 无穷大表示不可达
    
    public static void main(String[] args) {
        int[][] graph = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
                          { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
                          { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
                          { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
                          { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
                          { 0, 0, 4, 0, 10, 0, 2, 0, 0 }, 
                          { 0, 0, 0, 14, 0, 2, 0, 1, 6 }, 
                          { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
                          { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        dijkstra(graph, 0);
    }

    public static void dijkstra(int[][] graph, int src) {
        int n = graph.length;
        int[] dist = new int[n]; // 存储从源点到每个节点的最短距离
        boolean[] visited = new boolean[n]; // 标记节点是否已经被访问
        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = minDistance(dist, visited);
            visited[u] = true;
            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist);
    }

    public static int minDistance(int[] dist, boolean[] visited) {
        int min = INF;
        int minIndex = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void printSolution(int[] dist) {
        System.out.println("节点\t\t距离");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }
}
