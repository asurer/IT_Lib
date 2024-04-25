import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    // 边的构造函数
    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    // 实现 Comparable 接口，根据权重比较边的大小
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class Graph {
    int V, E; // 图的顶点数和边数
    Edge[] edges; // 图的边

    // 图的构造函数
    public Graph(int V, int E) {
        this.V = V;
        this.E = E;
        edges = new Edge[E];
        for (int i = 0; i < E; ++i)
            edges[i] = new Edge(0, 0, 0);
    }

    // 找到节点的根节点
    int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return find(parent, parent[i]);
    }

    // 合并两个节点的集合
    void union(int[] parent, int[] rank, int x, int y) {
        int xroot = find(parent, x);
        int yroot = find(parent, y);

        // 将 rank 较小的树连接到 rank 较大的树上
        if (rank[xroot] < rank[yroot])
            parent[xroot] = yroot;
        else if (rank[xroot] > rank[yroot])
            parent[yroot] = xroot;
        else {
            parent[yroot] = xroot;
            rank[xroot]++;
        }
    }

    // 执行 Kruskal 算法，生成最小生成树
    void kruskalMST() {
        Edge[] result = new Edge[V];
        int e = 0;
        int i = 0;

        // 将边按权重排序
        Arrays.sort(edges);

        int[] parent = new int[V];
        int[] rank = new int[V];

        // 初始化每个节点的父节点为自身
        for (int v = 0; v < V; ++v) {
            parent[v] = v;
            rank[v] = 0;
        }

        while (e < V - 1 && i < E) {
            Edge next_edge = edges[i++];

            int x = find(parent, next_edge.src);
            int y = find(parent, next_edge.dest);

            // 如果加入此边不会形成环路，则加入结果集
            if (x != y) {
                result[e++] = next_edge;
                union(parent, rank, x, y);
            }
        }

        // 打印最小生成树的边
        System.out.println("最小生成树的边为：");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " - " + result[i].dest + ": " + result[i].weight);
    }
}

public class kruskalAlgorithm {
    public static void main(String[] args) {
        int V = 4; // 图的顶点数
        int E = 5; // 图的边数
        Graph graph = new Graph(V, E);

        // 添加边
        graph.edges[0] = new Edge(0, 1, 10);
        graph.edges[1] = new Edge(0, 2, 6);
        graph.edges[2] = new Edge(0, 3, 5);
        graph.edges[3] = new Edge(1, 3, 15);
        graph.edges[4] = new Edge(2, 3, 4);

        // 执行 Kruskal 算法
        graph.kruskalMST();
    }
}
