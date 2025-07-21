package wk8_Assignment;

/**
 * UMGC CMSC 315
 * Programming Project 4
 * Class Vertex - Represents a vertex/node in a graph. Stores the coordinates (x, y)
 * 				  of the vertex and provides methods for retrieving the x, y, and name
 * 				  of the vertex.
 * @author Joshua Leal
 * Date: March 2024
 * Java 17 
 */

public class Vertex {
	private final double x; // x-coordinate of the vertex
	private final double y; // y-coordinate of the vertex
	private static char nextVertexName = 'A';
	private final String name;
	
	public Vertex(double x, double y) {
		this.x = x;
		this.y = y;
		this.name = "" + nextVertexName;
		nextVertexName++; // Increment the next vertex name for future vertices
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String getName() {
		return name;
	}
}
