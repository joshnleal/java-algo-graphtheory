package wk8_Assignment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * UMGC CMSC 315
 * Programming Project 4
 * Class Graph - Contains a graph data structure. It stores a collection of vertices and
 * 				 edges. Provides methods to add vertices and edges to the graph, check 
 * 				 for connectivity, detect cycles, perform depth-first search (DFS), perform 
 * 				 breadth-first search (BFS), and retrieve information about vertices and 
 * 				 edges.
 * @author Joshua Leal
 * Date: March 2024
 * Java 17 
 */

public class Graph {
	private List<Vertex> vertices = new ArrayList<>();
	private List<List<Vertex>> edges = new ArrayList<>();
	
	public Graph() {
	}
	
	public void addVertex(Vertex vertex) {
		// Add vertex to the list if it is not in the array
		if(!vertices.contains(vertex)) {
			vertices.add(vertex);
		}
	}
	
	public boolean addEdge(String vertex1, String vertex2) {
		Vertex v1 = null;
	    Vertex v2 = null;

	    // Find vertices with the given names
	    for (Vertex v : vertices) {
	        if (v.getName().equals(vertex1)) {
	            v1 = v;
	        }
	        if (v.getName().equals(vertex2)) {
	            v2 = v;
	        }
	    }

	    // Check if both vertices exist
	    if (v1 != null && v2 != null) {
	        // Create an edge between the vertices
	        List<Vertex> edge = new ArrayList<>();
	        edge.add(v1);
	        edge.add(v2);
	        edges.add(edge);
	        return true; // Indicates edge added
	    } else {
	        return false; // Indicates one or both vertices do not exist
	    }
	}
	
	public boolean hasCycles() {
		// Uses depth-first search to detect cycles
        boolean[] visited = new boolean[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            if (!visited[i]) 
                if (hasCycles(i, -1, visited))
                    return true;
        }
        return false;
	}
	
	private boolean hasCycles(int v, int parent, boolean[] visited) {
		// Recursively visits each vertex 
        visited[v] = true;
        for (Vertex vertex : getAdjacentVertices(vertices.get(v))) {
            int index = vertices.indexOf(vertex);
            if (!visited[index]) {
                if (hasCycles(index, v, visited)) {
                    return true;
                }
            } else if (index != parent) {
                return true;
            }
        }
        return false;
    }
	
	private List<Vertex> getAdjacentVertices(Vertex vertex) {
		// Stores adjacent vertices into a list and returns it
        List<Vertex> adjacentVertices = new ArrayList<>();
        for (List<Vertex> edge : edges) {
            if (edge.contains(vertex)) {
                adjacentVertices.add(edge.get(0).equals(vertex) ? edge.get(1) : edge.get(0));
            }
        }
        return adjacentVertices;
    }
	
	public boolean isConnected() {
		// Use depth-first search to check if all vertices are reachable from any one vertex
        boolean[] visited = new boolean[vertices.size()];
        dfs(0, visited);
        for (boolean visit : visited) {
            if (!visit) {
                return false;
            }
        }
        return true;
	}
	
	public String dfs() {
		// Starting Depth First Search method. Returns String of list
		List<Vertex> dfsResult = new ArrayList<>();
        boolean[] visited = new boolean[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            if (!visited[i]) {
                dfs(i, visited, dfsResult);
            }
        }
        
        String stringResult = formatArrayList(dfsResult);
        return stringResult;
	}
	
	private void dfs(int v, boolean[] visited) {
		// Recursively visits each vertex
        visited[v] = true;
        for (Vertex vertex : getAdjacentVertices(vertices.get(v))) {
            int index = vertices.indexOf(vertex);
            if (!visited[index]) {
                dfs(index, visited);
            }
        }
    }
	
	private void dfs(int v, boolean[] visited, List<Vertex> result) {
		// Recursively visits each vertex and stores visited vertex into result list
        visited[v] = true;
        result.add(vertices.get(v));
        for (Vertex vertex : getAdjacentVertices(vertices.get(v))) {
            int index = vertices.indexOf(vertex);
            if (!visited[index]) {
                dfs(index, visited, result);
            }
        }
    }
	
	public String bfs() {
		// Breadth First Search method. Returns String of list
		List<Vertex> bfsResult = new ArrayList<>();
	    boolean[] visited = new boolean[vertices.size()];
	    Queue<Vertex> queue = new LinkedList<>();
	    for (Vertex vertex : vertices) {
	        int index = vertices.indexOf(vertex);
	        if (!visited[index]) {
	            queue.add(vertex);
	            visited[index] = true;
	            while (!queue.isEmpty()) {
	                Vertex v = queue.poll();
	                bfsResult.add(v);
	                for (Vertex adjacentVertex : getAdjacentVertices(v)) {
	                    int adjIndex = vertices.indexOf(adjacentVertex);
	                    if (!visited[adjIndex]) {
	                        visited[adjIndex] = true;
	                        queue.add(adjacentVertex);
	                    }
	                }
	            }
	        }
	    }
	    String stringResult = formatArrayList(bfsResult);
	    return stringResult;
	}
	
	public static String formatArrayList(List<Vertex> results) {
		// Creates a string with result list and returns it
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < results.size(); i++) {
            if (i > 0) {
                builder.append(", "); // Add a comma separator for all elements except the first one
            }
            builder.append(results.get(i).getName());
        } 
        builder.append("]");
        return builder.toString();
    }
	
	public List<Vertex> getVertices(){
		return vertices;
	}
	
	public List<List<Vertex>> getEdges(){
		return edges;
	}
}
