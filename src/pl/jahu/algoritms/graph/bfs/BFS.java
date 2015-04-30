package pl.jahu.algoritms.graph.bfs;

import pl.jahu.algoritms.graph.model.Edge;
import pl.jahu.algoritms.graph.model.Graph;
import pl.jahu.algoritms.graph.model.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BFS {

    private Map<Vertex, VertexColor> vertexColorMap;
    private Map<Vertex, Double> vertexDistanceMap;
    private Map<Vertex, Vertex> vertexParentMap;

    public BFS() {
        vertexColorMap = new HashMap<>();
        vertexDistanceMap = new HashMap<>();
        vertexParentMap = new HashMap<>();
    }

    public void traversGraph(Graph graph, Vertex startVertex) {
        clearMaps();
        Queue<Vertex> vertexQueue = new LinkedList<>();

        for (Vertex vertex : graph.getVertexes()) {
            vertexColorMap.put(vertex, VertexColor.WHITE);
            vertexDistanceMap.put(vertex, Double.MAX_VALUE);
            vertexParentMap.put(vertex, null);
        }

        vertexColorMap.put(startVertex, VertexColor.GREY);
        vertexDistanceMap.put(startVertex, 0.0);
        vertexQueue.add(startVertex);
        while (!vertexQueue.isEmpty()) {
            Vertex currentVertex = vertexQueue.poll();
            for (Edge edge: currentVertex.getEdges()) {
                Vertex edgeVertex = edge.getTargetVertex();
                if (vertexColorMap.get(edgeVertex) == VertexColor.WHITE) {
                    vertexColorMap.put(edgeVertex, VertexColor.GREY);
                    vertexDistanceMap.put(edgeVertex, vertexDistanceMap.get(currentVertex) + 1.0);
                    vertexParentMap.put(edgeVertex, currentVertex);
                    vertexQueue.add(edgeVertex);
                }
            }
            vertexColorMap.put(currentVertex, VertexColor.BLACK);
        }

    }

    public Map<Vertex, Double> getDistanceMap() {
        return vertexDistanceMap;
    }

    public Map<Vertex, Vertex> getParentsMap() {
        return vertexParentMap;
    }

    private void clearMaps() {
        vertexColorMap.clear();
        vertexDistanceMap.clear();
        vertexParentMap.clear();
    }

}
