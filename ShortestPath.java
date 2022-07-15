package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ShortestPath {
    static final int MAX = 100000;
    static final int INF = 1000000000;
    static int[] dist = new int[MAX];
    static int[] parent = new int[MAX];
    static boolean[] found = new boolean[MAX];
    static int numberOfVertices, numberOfEdges;

    //------------------------------------------------------------------------------------------------------------------
    static class d_aryHeap {
        public int d; // number of children each node can have.
        public int heapSize; // equals to the number of nodes we insert
        public int[] mainHeap; // Heap using array.
        public int[] distance; // distance from each node to next node
        public int[] weight;

        // -------------------------------------------------------------------------------------------------------------
        // Building constructor
        public d_aryHeap(int capacity) {
            //d = numberOfVertices/numberOfEdges;
            mainHeap = new int[capacity]; // Building a heap with 'MAX' size.
            distance = new int[capacity];
            weight = new int[capacity];
            Arrays.fill(mainHeap, INF); // All elements in heap are now infinity
//            Arrays.fill(distance, INF); // all distances sets to INF
//            Arrays.fill(weight, INF); // all weights to INF
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
        private int minChild(int index) {
            int bestChild = kthChild(index, 1);
            int k = 2; // setting child to 2
            int KthChildofindex = kthChild(index, k);
            while ((k <= d) && KthChildofindex < heapSize) {
                if (mainHeap[KthChildofindex] < mainHeap[bestChild])
                    bestChild = KthChildofindex;
                KthChildofindex = kthChild(index, k++);
            }
            return bestChild;
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
        public void downwardHeapify(int index) { ////// need to be done
            int child;
            int temp = mainHeap[index];
            while (kthChild(index, 1) < heapSize) {
                child = minChild(index);
                if (mainHeap[child] < temp) {
                    mainHeap[index] = mainHeap[child];
                } else {
                    break;
                }
                index = child;
            }
            mainHeap[index] = temp;
        }

        //--------------------------------------------------------------------------------------------------------------
        //Function to add elements to heap
        public void insertToHeap(int x, int y, int z) {
            int heapIndex = 0;
            while (heapIndex != MAX) {
                if (mainHeap[heapIndex] == INF) {
                    mainHeap[heapIndex] = x;
                    upwardHeapify(numberOfVertices); // reomoved -1
                    distance[heapIndex] = y;
                    weight[heapIndex] = z;
                    heapSize++;
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
            if (heapSize == 0) {
                throw new NoSuchElementException("Empty heap");
            }
            return mainHeap[0];
        }
    }

    public class pair {
        int first;
        int second;
    }

    //------------------------------------------------------------------------------------------------------------------
    public static void dijkstra(int s , int v) {
        //------------------------------------------
        for (int i = 0; i < numberOfVertices; i++) {
            dist[i] = INF;
            parent[i] = -1;
            found[i] = false;
        }
        //------------------------------------------
        dist[s] = 0;
        for (int i = 0; i < numberOfVertices; i++) {
            //int v = -1;
            found[v] = true;
            for (pair edge : adj[v]) {
                int to = edge.first;
                int len = edge.second;

                if (dist[v] + len < dist[to]) {
                    dist[to] = dist[v] + len;
                    parent[to] = v;
                }
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //vector<pair<int,int> > adj[MAX]; needs to be translated to java
    static ArrayList<pair>[] adj = new ArrayList[MAX];

    //static ArrayList<Pair> [] adj = new ArrayList<>;
    public static void main(String[] args) throws FileNotFoundException {
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


        File file = new File("/Users/shayansmacbook/Downloads/input1.txt");
        Scanner scanner = new Scanner(file);
        String firstLine = scanner.nextLine();
        firstLine.trim();
        String[] dataFirstLine = firstLine.split(" ");
        int numberOfVertices = Integer.parseInt(dataFirstLine[0]);
        int numberOfEdges = Integer.parseInt(dataFirstLine[1]);
        int source = Integer.parseInt(dataFirstLine[2]);
        int d = numberOfEdges / numberOfVertices;
//        System.out.println(numberOfVertices);
//        System.out.println(numberOfEdges);
//        System.out.println(source);
//        System.out.println(d);
        d_aryHeap DaryHeap = new d_aryHeap(numberOfEdges);

        int counter = 0;
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String[] splinted = data.split(" ");
            int src = Integer.parseInt(splinted[0]);
            int dist = Integer.parseInt(splinted[1]);
            int w = Integer.parseInt(splinted[2]);
//            System.out.println(src);
//            System.out.println(dist);
//            System.out.println(w);
            DaryHeap.insertToHeap(src,dist,w);
            DaryHeap.distance[counter] = dist;
            DaryHeap.weight[counter] = w;
            counter++;
        }
        int minimum = DaryHeap.findMinimum();
        //System.out.println(minimum);
        dijkstra(source,minimum);
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
    }
}
