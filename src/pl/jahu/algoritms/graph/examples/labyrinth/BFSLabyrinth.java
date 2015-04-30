package pl.jahu.algoritms.graph.examples.labyrinth;

import pl.jahu.algoritms.graph.bfs.BFS;
import pl.jahu.algoritms.graph.model.Graph;
import pl.jahu.algoritms.graph.model.Vertex;

import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.Set;

/**
 * Example usage of BFS algorithm for searching of shortest path in labyrinth
 */
public class BFSLabyrinth {

    public static void main(String[] args) {
        ConsoleGraphProvider graphProvider = new ConsoleGraphProvider();
        Graph graph = graphProvider.getGraph();
        Vertex startVertex = graphProvider.getStartVertex();
        Set<Vertex> possibleEndVertexes = graphProvider.getPossibleEndVertexes();

        BFS bfs = new BFS();
        bfs.traversGraph(graph, startVertex);

        Vertex endVertex = getEndVertex(bfs.getDistanceMap(), possibleEndVertexes);

        System.out.println("Shortest path length: " + bfs.getDistanceMap().get(endVertex));
        System.out.println("Path: ");
        displayPath(bfs.getParentsMap(), endVertex, startVertex);

    }

    private static void displayPath(Map<Vertex, Vertex> parentsMap, Vertex endVertex, Vertex startVertex) {
        Vertex currentVertex = endVertex;
        while (currentVertex != startVertex) {
            System.out.println(currentVertex);
            currentVertex = parentsMap.get(currentVertex);
        }
        System.out.println(currentVertex);
    }


    private static Vertex getEndVertex(Map<Vertex, Double> distanceMap, Set<Vertex> possibleEndVertexes) {
        Vertex endVertex = null;
        Double distance = Double.MAX_VALUE;
        for (Vertex possibleEndVertex: possibleEndVertexes) {
            if (distanceMap.get(possibleEndVertex) < distance) {
                distance = distanceMap.get(possibleEndVertex);
                endVertex = possibleEndVertex;
            }
        }
        return endVertex;
    }


}
