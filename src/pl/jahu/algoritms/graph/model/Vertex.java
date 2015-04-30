package pl.jahu.algoritms.graph.model;

import java.util.List;
import java.util.UUID;

public class Vertex {

    private final UUID uuid;
    private List<Edge> edges;
    private String name;

    public Vertex() {
        this.uuid = UUID.randomUUID();
        this.name = uuid.toString();
    }

    public Vertex(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (uuid != null ? !uuid.equals(vertex.uuid) : vertex.uuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                '}';
    }
}
