import java.util.*;

public class topologicalSort {
    private int V; // 顶点的数量
    private linkedList<Integer> adj[]; // 邻接表

    // 构造函数
    topologicalSort(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    // 添加边
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // 拓扑排序的递归函数
    void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
        visited[v] = true;
        Integer i;

        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        stack.push(new Integer(v));
    }

    // 执行拓扑排序
    void topologicalSort() {
        Stack<Integer> stack = new Stack<>();

        // 标记所有顶点为未访问
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // 递归地进行拓扑排序
        for (int i = 0; i < V; i++)
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);

        // 打印排序结果
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
    }

    public static void main(String args[]) {
        topologicalSort g = new topologicalSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println("拓扑排序结果为：");
        g.topologicalSort();
    }
}
