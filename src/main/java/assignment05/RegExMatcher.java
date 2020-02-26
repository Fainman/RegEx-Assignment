package assignment05;

/* Adapted from the textbook's site at
 *   https://algs4.cs.princeton.edu/54regexp/NFA.java.html
 */
import java.util.LinkedList;
import java.util.Stack;

public class RegExMatcher {
  private static Graph constructNFA(final String pattern, final int M) {
    Stack<Integer> ops = new Stack<Integer>();
    Graph g = new Graph(M + 1); // Using a directed graph
    for (int i = 0; i < M; i++) {
      int lp = i;
      if (pattern.charAt(i) == '(' || pattern.charAt(i) == '|') {
        ops.push(i);
      } else if (pattern.charAt(i) == ')') {
        int or = ops.pop();

        // 2-way OR
        if (pattern.charAt(or) == '|') {
          lp = ops.pop();
          g.addEdge(lp, or + 1);
          g.addEdge(or, i);
        } else if (pattern.charAt(or) == '(') {
          lp = or;
        } else {
          assert false;
        }
      }

      // Closure *
      if (i < M - 1 && pattern.charAt(i + 1) == '*') {
        g.addEdge(lp, i + 1);
        g.addEdge(i + 1, lp);
      }

      if (pattern.charAt(i) == '(' || pattern.charAt(i) == '*' || pattern.charAt(i) == ')') {
        g.addEdge(i, i + 1);
      }
    }

    if (ops.size() != 0) {
      throw new IllegalArgumentException("Invalid regular expression");
    }

    return g;
  }

  public static boolean recognizes(String pattern, String text) {
    final int M = pattern.length();
    Graph g = constructNFA(pattern, M);
    DepthFirstSearch dfs = new DepthFirstSearch(g, 0);

    LinkedList<Integer> pc = new LinkedList<Integer>();
    for (int v = 0; v < g.V(); v++) {
      if (dfs.reachable(v)) {
        pc.add(v);
      }
    }

    for (int i = 0; i < text.length(); i++) {
      // Don't allow metacharacters (used in specifying patterns) in text
      if (text.charAt(i) == '*' || text.charAt(i) == '|' ||
          text.charAt(i) == '(' || text.charAt(i) == ')') {
        throw new IllegalArgumentException("Metacharacters (, *, |, and ) not allowed.");
      }

      LinkedList<Integer> match = new LinkedList<Integer>();
      for (int v : pc) {
        if (v == M) continue;
        if ((pattern.charAt(v) == text.charAt(i)) || pattern.charAt(v) == '.')
          match.add(v + 1);
      }

      dfs = new DepthFirstSearch(g, match);
      pc = new LinkedList<Integer>();
      for (int v = 0; v < g.V(); v++) {
        if (dfs.reachable(v)) pc.add(v);
      }

      // Return if no states reachable
      if (pc.size() == 0) {
        return false;
      }
    }

    // Check for accept state
    for (int v : pc) {
      if (v == M) {
        return true;
      }
    }

    return false;
  }
}
