package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    static final int MAX = 100000;
    static final int INF = 1000000000;
    static int[] dist = new int[MAX];
    static int[] parent = new int[MAX];
    static boolean[] found = new boolean[MAX];
    static int numberOfVertices, numberOfEdges;
    //------------------------------------------------------------------------------------------------------------------
    static class d_aryHeap {
        private int d; // number of children each node can have.
        private int heapSize; // equals to the number of nodes we insert
        private int[] mainHeap; // Heap using array.
        private int[] distance; // dstance from each node to next node

        // -------------------------------------------------------------------------------------------------------------
        // Building constructor
        public d_aryHeap(int numberOfChildren, int numberOfVertices) {
            d = numberOfVertices/numberOfEdges;
            mainHeap = new int[numberOfVertices]; // Building a heap with 'MAX' size.
            Arrays.fill(mainHeap, INF); // All elements in heap are now infinity
            Arrays.fill(distance, INF); // all distances sets to INF
        }

        //--------------------------------------------------------------------------------------------------------------
        // Function to find index 'i' parent
        private int parent(int i) {
            return (i - 1) / d; // Formula based on the d_ary heap definition.
        }

        //--------------------------------------------------------------------------------------------------------------
        // Function to find k'th children of node 'i'
        private int kthChild(int i, int k) {
            return (d * i) + k; // Based on d_ary heap definition.
        }

        //--------------------------------------------------------------------------------------------------------------
        private int minChild(int index){
            int bestChild = kthChild((index,1));
            int k = 2; // setting child to 2
            int KthChildofindex = kthChild((index,k));
            while((k <= d) && KthChildofindex < heapSize){
                if (mainHeap[KthChildofindex] < mainHeap[bestChild])
                    bestChild = KthChildofindex;
                KthChildofindex = kthChild(index,k++)
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        // Upward heapify
        private void upwardHeapify(int childIndex) {
            int temp = mainHeap[childIndex]; // saving the value saved in the node in the heap
            while (childIndex > 0 && temp < mainHeap[parent(childIndex)]) {
                // changing parent and child with each other
                mainHeap[childIndex] = mainHeap[parent(childIndex)];
                childIndex = parent(childIndex);
            }
            mainHeap[childIndex] = temp; // updating value
        }

        //--------------------------------------------------------------------------------------------------------------
        // Downward heapify
        private void downwardHeapify(int index) { ////// need to be done
            int child;
            int temp = mainHeap[index];
            while(kthChild(index,1) < heapSize){
                child = minChild(index);
                if (mainHeap[child] < temp)
                    mainHeap[index] = mainHeap[child];
                else
                    break
                index = child;
            }
            mainHeap[index] = temp;
        }

        //--------------------------------------------------------------------------------------------------------------
        //Function to add elements to heap
        public void insertToHeap(int x) {
            int heapIndex = 0;
            while (heapIndex != MAX) {
                if (mainHeap[heapIndex] == INF) {
                    mainHeap[heapIndex] = x;
                    upwardHeapify(numberOfVertices - 1);
                    break;
                } else
                    heapIndex++;
            }
        }

        //---------------------------------------------------------------------------------------------------------------
        // Function to delete element from heap
        public int deleteFromHeap(int index) {
            int temp = mainHeap[index];
            mainHeap[index] = mainHeap[heapSize - 1];
            heapSize--;
            downwardHeapify(index);
            return temp;
        }


        //------------------------------------------------------------------------------------------------------------------
        public int findMinimum() {
            if(heapSize == 0){
                throw new NoSuchElementException("Empty heap");
            }
            return mainHeap[0];
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    //vector<pair<int,int> > adj[MAX]; needs to be translated to java
    ArrayList<Map<int,int>> adj[MAX];
    //static ArrayList<Pair> [] adj = new ArrayList<>;
    static int numberOfVertices, numberOfEdges;

    public static int main(String[] args) {
        d = numberOfEdges / numberOfVertices;

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
        numberOfVertices = scanner.nextInt();
        //scanner.next();
        numberOfEdges = scanner.nextInt();
        //scanner.next();
        source = scanner.nextInt();
        // cin >> numberOfVertices >> numberOfEdges >> source;
        //int dimension = numberOfEdges / numberOfVertices;
        scanner.nextLine();
        // edge x to y with weight of 'weight'
        int x, y, weight;
        for (int i = 0; i < numberOfEdges; i++) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            weight = scanner.nextInt();
            //cin >> x >> y >> weight; // edge x->y with length weight

        }

        dijkstra(source);
        for (int i = 0; i < numberOfVertices; i++) {
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
        for (int i = 0; i < numberOfVertices; i++) {
            dist[i] = INF;
            parent[i] = -1;
            found[i] = false;
        }
        //------------------------------------------
        dist[s] = 0;
        for (int i = 0; i < numberOfVertices; i++) {
            v = dAryHeap.findMinimum();
            found[v] = true;
            for (int edge : adj[v]) {
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
