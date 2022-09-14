// This class implements Kosaraju-Sharir algorithm, which does:
// A- Run dfs on G^r (G reversed) to compute reverse postorder
// B- Run dfs on G, considering vertices in order given by first dfs

// Vertices v and w are strongly connected iff exists a direct path from v to w
// and a direct path from w to v

// This is done in O(V + E)

package chapter4.digraphs;

public class StrongComponents {
    private final TopologicalSort sort;
    private final boolean[] marked;
    private int[] scc; // As we did with connected componets

    public StrongComponents(Digraph g) {
		sort = new TopologicalSort(g.reverse());
		marked = new boolean[g.V()];
		int scc = 0;
		for (int v : sort.reversePost()) {
			if (!marked[v]) dfs(g, v, scc++);
		}
    }

    public boolean belongToSameSCC(int v, int w) {
		return scc[v] == scc[w];
    }

    private void dfs(Digraph g, int s, int id) {
		marked[s] = true;
		scc[id] = id;
		for (int w : g.adj(s)) {
			if (!marked[w]) {
			dfs(g, s, id);
			}
		}
    }
}
