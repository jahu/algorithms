package pl.jahu.algoritms.graph.examples.labyrinth;

import pl.jahu.algoritms.graph.model.Edge;
import pl.jahu.algoritms.graph.model.Graph;
import pl.jahu.algoritms.graph.model.Vertex;
import pl.jahu.algoritms.graph.model.provider.GraphProvider;

import java.util.*;


/**
 * Graph provider for labyrinth exercise. Input example:
 *
 *  6 5
 *  X X X 0 X
 *  0 0 0 0 X
 *  X X 0 0 X
 *  X 0 X 0 X
 *  X 0 0 0 X
 *  X X 0 X X
 *  5 2
 *
 */
public class ConsoleGraphProvider implements GraphProvider {

    private int n;
    private int m;
    private int a;
    private int b;

    private Vertex startVertex;
    private Vertex[][] vertexMatrix;

    private Set<Vertex> possibleEndVertexes;

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public ConsoleGraphProvider() {
        possibleEndVertexes = new HashSet<>();
    }

    @Override
    public Graph getGraph() {
        char[][] flatGraph = getFlatGraphFromConsole();
        Graph graph = generateGraphFromFlatGraph(flatGraph);
        return graph;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Set<Vertex> getPossibleEndVertexes() {
        return possibleEndVertexes;
    }

    public void setPossibleEndVertexes(Set<Vertex> possibleEndVertexes) {
        this.possibleEndVertexes = possibleEndVertexes;
    }

    private Graph generateGraphFromFlatGraph(char[][] flatGraph) {
        Graph graph = new Graph();
        Set<Vertex> vertexes = new HashSet<>();

        for (int i=0; i<flatGraph.length; i++) {
            for (int j=0; j<flatGraph[i].length; j++) {
                if (flatGraph[i][j] == '0') {
                    Vertex currentVertex = vertexMatrix[i][j];
                    if (i == a && j == b) {
                        // start vertex found
                        startVertex = currentVertex;
                    } else if (i == 0 || i == n-1 || j == 0 || j == m-1) {
                        // possible end vertex found
                        possibleEndVertexes.add(currentVertex);
                    }
                    List<Edge> edges = getEdges(flatGraph, i, j);
                    currentVertex.setEdges(edges);
                    vertexes.add(currentVertex);
                }
            }
        }
        graph.setVertexes(vertexes);
        return graph;
    }

    private List<Edge> getEdges(char[][] flatGraph, int i, int j) {
        List<Edge> edges = new ArrayList<>();

        if (i-1 >=0 && flatGraph[i-1][j] == '0') {
            Edge edge = new Edge();
            edge.setWeight(1.0);
            edge.setTargetVertex(vertexMatrix[i-1][j]);
            edges.add(edge);
        }

        if (i+1 <n && flatGraph[i+1][j] == '0') {
            Edge edge = new Edge();
            edge.setWeight(1.0);
            edge.setTargetVertex(vertexMatrix[i+1][j]);
            edges.add(edge);
        }

        if (j-1 >=0 && flatGraph[i][j-1] == '0') {
            Edge edge = new Edge();
            edge.setWeight(1.0);
            edge.setTargetVertex(vertexMatrix[i][j-1]);
            edges.add(edge);
        }

        if (j+1 <m && flatGraph[i][j+1] == '0') {
            Edge edge = new Edge();
            edge.setWeight(1.0);
            edge.setTargetVertex(vertexMatrix[i][j+1]);
            edges.add(edge);
        }

        return edges;
    }

    private char[][] getFlatGraphFromConsole() {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        m = scanner.nextInt();

        vertexMatrix = new Vertex[n][m];

        scanner.nextLine();

        char[][] flatGraph = new char[n][m];

        for (int i=0; i<n; i++) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(" ");
            flatGraph[i] = new char[m];
            for (int j=0; j<splitLine.length; j++) {
                flatGraph[i][j] = splitLine[j].charAt(0);
                vertexMatrix[i][j] = new Vertex(i + " " + j);
            }
        }

        a = scanner.nextInt();
        b = scanner.nextInt();

        scanner.close();

        return flatGraph;
    }
}
