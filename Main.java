package com.company;


import java.util.Scanner;

public class Main {
    static final int MAX = 100000;
    static final int INF = 1000000000;
    static int[] dist = new int[MAX];
    static int[] parent = new int[MAX];
    static boolean[] found = new boolean[MAX];
    //vector<pair<int,int> > adj[MAX]; needs to be translated to java
    static int n, m;

    public static int main(String[] args) {
        /////////////////////////////////////////
        /////////////////
        //Sample Input (CLRS-Page 659):
        //5 10 0
        //0 1 10
        //0 4 5
        //1 2 1
        //1 4 2
        //2 3 4
        //3 0 7
        //3 2 6
        //4 1 3
        //4 2 9
        //4 3 2
        /////////////////

        int source;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.next();
        m = scanner.nextInt();
        scanner.next();
        source = scanner.nextInt();
        // cin >> n >> m >> source;

        int x, y, w;
        for (int i = 0; i < m; i++) {
            x = scanner.nextInt();
            scanner.next();
            y = scanner.nextInt();
            scanner.next();
            w = scanner.nextInt();
            //cin >> x >> y >> w; // edge x->y with length w
            adj[x].push_back({y, w});
        }

        dijkstra(source);
        for (int i = 0; i < n; i++) {
           // cout << i << "\t" << ((dist[i] == INF) ? -1 : dist[i]) << endl;
            System.out.println(i + "\t" + ((dist[i] == INF) ? -1 : dist[i]));
        }
        /////////////////
        //Sample Output:
        //0       0
        //1       8
        //2       9
        //3       7
        //4       5
        /////////////////

        return 0;
    }

    public static void dijkstra(int s) {
        //------------------------------------------
        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            parent[i] = -1;
            found[i] = false;
        }
        //------------------------------------------
        dist[s] = 0;
        for (int i = 0; i < n; i++) {
            /////////////////////////////////////////////////////
            //Replace this part with
            //	int v = Extract Minimum from a (d-ary)-Heap (CLRS-Page 167)
            //and change the required parts of the program accordingly.
            //Notes: (1) Implement (d-ary)-Heap with array
            //		 (2) Keep the childs of each node in the (d-ary)-Heap in regular Heap
            //		 (3) d = [m / n]
            /////////////////////////////////////////////////////
            int v = -1;
            for (int j = 0; j < n; j++) {
                if (!found[j] && (v == -1 || dist[j] < dist[v]))
                    v = j;
            }
            if (dist[v] == INF)
                break;
            found[v] = true;

            for (Object edge : adj[v]) {
                int to = edge[0];
                int len = edge.[1];

                if (dist[v] + len < dist[to]) {
                    dist[to] = dist[v] + len;
                    parent[to] = v;
                }
            }
        }
    }
}
