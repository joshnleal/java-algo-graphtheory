package wk8_Assignment;

import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * UMGC CMSC 315
 * Programming Project 4
 * Class GraphPane - Extends Pane class in order to visualize the graph. It displays vertices 
 * 					 as circles and edges as lines connecting them. Handles mouse events to 
 * 					 allow users to add vertices to the graph by clicking on the canvas. It 
 * 					 also redraws the graph whenever changes are made to the underlying graph 
 * 					 data structure, ensuring that the visualization stays up to date with the 
 * 					 graph's state.  
 * @author Joshua Leal
 * Date: March 2024
 * Java 17 
 */

public class GraphPane extends Pane {
	private final Graph graph;
	
	public GraphPane(Graph graph) {
		this.graph = graph; // Initialize graph
		setOnMousePressed(e -> { // Event handler for creating and adding vertices to the graph
			Vertex vertex = null;
			if(e.isPrimaryButtonDown()) {
				vertex = new Vertex(e.getX(), e.getY());
				graph.addVertex(vertex);
				redraw();
			}
		});
	}
	
	private void drawVertices() {
		// Draws vertices as black circles on the pane
		getChildren().clear();
		if (graph != null) {
			for(Vertex v : graph.getVertices()) {
				Circle circle = new Circle(v.getX(), v.getY(), 5);
				Label nameLabel = new Label(v.getName());
				nameLabel.setLayoutX(v.getX());
				nameLabel.setLayoutY(v.getY() - 30);
				circle.setFill(Color.BLACK);
				getChildren().addAll(circle, nameLabel);
			}
		}
	}
	
	private void drawEdges() {
		// Draws edges as black lines from vertex to vertex on the pane
		if (graph != null) {
            for (List<Vertex> edge : graph.getEdges()) {
                if (edge.size() == 2) {
                    Vertex v1 = edge.get(0);
                    Vertex v2 = edge.get(1);
                    Line line = new Line(v1.getX(), v1.getY(), v2.getX(), v2.getY());
                    line.setStroke(Color.BLACK);
                    getChildren().add(line);
                }
            }
        }
	}
	
	private void redraw() {
		// Draws both vertices and edges on the pane
		drawVertices();
		drawEdges();
	}
	
	public void addEdge() {
		// Draws edge on the pane
		drawEdges();
	}
	
}
