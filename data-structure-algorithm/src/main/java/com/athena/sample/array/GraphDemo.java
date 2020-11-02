package com.athena.sample.array;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yusheng
 */
public class GraphDemo {


    public static void main(String[] args) {
        Graph graph = new Graph(65535);
        graph.add(1, 2);
        graph.add(1, 3);
        graph.add(1, 4);
        graph.dfs(1, 4);

        List<String> imgList = JSON.parseArray("[321321]",String.class);
        System.out.println(imgList);
    }


    private static class Graph {

        private int v;

        private LinkedList<Integer> adj[];

        private boolean found;

        public Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void add(int s, int t) {
            if (s >= v || t >= v) {
                return;
            }
            adj[s].add(t);
            adj[t].add(s);
        }

        public void dfs(int s, int t) {
            found = false;
            boolean[] visited = new boolean[v];
            int[] prev = new int[v];
            for (int i = 0; i < v; i++) {
                prev[i] = -1;
            }
            recursiveDfs(prev, visited, s, t);

            print(prev, s, t);
        }

        private void recursiveDfs(int[] prev, boolean[] visited, int s, int t) {
            visited[s] = true;
            if(found){
                return;
            }
            if(s == t){
                found = true;
                return;
            }
            for(int i = 0; i < adj[s].size(); i++){
                int current = adj[s].get(i);
                if(!visited[current]){
                    prev[current] = s;
                    recursiveDfs(prev, visited, current, t);
                    if(found){
                        return;
                    }
                }
            }
        }

        private void print(int[] prev, int s, int t) {
            // 递归打印s->t的路径
            if (prev[t] != -1 && t != s) {
                print(prev, s, prev[t]);
            }
            System.out.print(t + " ");
        }
    }
}
