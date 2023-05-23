import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

class Main {

    private static class Edge {
        int src;
        int dest;
        double weight;

        public Edge(int src, int dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    private static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];

            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY)
                return;

            if (rank[rootX] < rank[rootY])
                parent[rootX] = rootY;
            else if (rank[rootX] > rank[rootY])
                parent[rootY] = rootX;
            else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
    public static String sumWeight(Integer n, List<Edge> edges) {
        UnionFind uf = new UnionFind(n + 1);
        double sum = 0;

        Collections.sort(edges, Comparator.comparingDouble(e -> e.weight));

        for (Edge edge : edges) {
            int src = edge.src;
            int dest = edge.dest;

            if (uf.find(src) != uf.find(dest)) {
                uf.union(src, dest);
                sum += edge.weight;
            }
        }

        return String.format("%.3f", sum).replace(",", ".");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> splitStringByLines(String input) {
        String[] inputSplited = input.split("\n");
        List<String> lines = new ArrayList<>(inputSplited.length);
        Collections.addAll(lines, inputSplited);
        return lines;
    }

    public static List<List<String>> splitStringBySpaces(List<String> input) {
        List<List<String>> result = new ArrayList<>(input.size());

        for (String line : input) {
            String[] inputSplited = line.split(" ");
            List<String> edge = new LinkedList<>();

            for (String element : inputSplited) {
                edge.add(element);
            }

            result.add(edge);
        }

        return result;
    }

    public static List<Edge> buildEdges(List<List<String>> input){
        List<Edge> edges = new ArrayList<>(input.size());
        for (List<String> edge :input) {
            edges.add(new Edge(Integer.parseInt(edge.get(0)), Integer.parseInt(edge.get(1)), Double.parseDouble(edge.get(2))));
        }
        return edges;
    }

    public static String receiveInput(Scanner scanner) {
        StringBuilder builder = new StringBuilder();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            builder.append(line).append("\n");

            if (line.trim().isEmpty()) {
                return builder.toString().replaceAll("\\n\\n$", "");
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String input = receiveInput(scanner);
        List<String> edgesBeforeSplitBySpaces = splitStringByLines(input);
        edgesBeforeSplitBySpaces.remove(0);
        edgesBeforeSplitBySpaces.remove(0);
        edgesBeforeSplitBySpaces.remove(1);

        String n = edgesBeforeSplitBySpaces.remove(0).split("n=")[1];
        List<List<String>> edgesString = splitStringBySpaces(edgesBeforeSplitBySpaces);
        List<Edge> edges = buildEdges(edgesString);

        System.out.println(sumWeight(Integer.valueOf(n), edges));
    }
}
