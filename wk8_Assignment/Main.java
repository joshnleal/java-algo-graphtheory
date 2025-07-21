package wk8_Assignment;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * UMGC CMSC 315
 * Programming Project 4
 * Class Main - Extends Application class, sets up the GUI interface, including buttons 
 * 				and text fields, and handles user interactions such as adding vertices, 
 * 				adding edges, and performing graph operations like depth first search (DFS) 
 * 				and breadth first search (BFS). 
 * @author Joshua Leal
 * Date: March 2024
 * Java 17 
 */


public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = getPane(); // Gets main pane for GUI
		Scene scene = new Scene(pane, 505, 550);
		primaryStage.setTitle("Project 4");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	protected BorderPane getPane() {
		// GUI components
		Graph graph = new Graph();
		Label vertex1Label = new Label("Vertex 1");
		Label vertex2Label = new Label("Vertex 2");
		TextField vertex1TextField = new TextField();
		TextField vertex2TextField = new TextField();
		TextField statusTextField = new TextField();
		Button addEdgeButton = new Button("Add Edge");
		Button isConnectedButton = new Button("Is Connected?");
		Button hasCyclesButton = new Button("Has Cycles?");
		Button dfsButton = new Button("Depth-First Search");
		Button bfsButton = new Button("Breadth-First Search");
		GraphPane graphPane = new GraphPane(graph);
		
		// Layout for border pane
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(20, 0, 20, 0));

        // Top pane of border pane
        HBox topPane = new HBox(10);
        topPane.setAlignment(Pos.CENTER);
        vertex1TextField.setPrefWidth(30);
        vertex2TextField.setPrefWidth(30); 
        topPane.getChildren().addAll(addEdgeButton, vertex1Label, 
        		vertex1TextField, vertex2Label, vertex2TextField);
        pane.setTop(topPane);
        
        // Center pane of borderpane
        pane.setCenter(graphPane);
        
        // Bottom pane of borderpane
        BorderPane bottomPane = new BorderPane();
        HBox buttonPane = new HBox(10);
        HBox statusPane = new HBox();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(isConnectedButton, hasCyclesButton, 
        		dfsButton, bfsButton);
        bottomPane.setTop(buttonPane);
        BorderPane.setMargin(buttonPane, new Insets(0, 0, 10, 0));
        statusPane.setAlignment(Pos.CENTER);
        statusTextField.setPrefWidth(440);
        statusTextField.setEditable(false);
        statusPane.getChildren().add(statusTextField);
        bottomPane.setBottom(statusPane);
        pane.setBottom(bottomPane);
		
		// Event handlers
		addEdgeButton.setOnAction(e -> {
			String vertex1 = vertex1TextField.getText().toUpperCase();
		    String vertex2 = vertex2TextField.getText().toUpperCase();
		    if (!(vertex1.isEmpty()) && !(vertex2.isEmpty())) {
		        if (graph.addEdge(vertex1, vertex2)) 
		            // Edge added successfully
		            graphPane.addEdge();
		        else 
		            statusTextField.setText("One or both vertices do not exist.");
		    } else {
		        statusTextField.setText("Please enter both vertices.");
		    }
		});
		
		isConnectedButton.setOnAction(e -> {
			if(!graph.isConnected())
				statusTextField.setText("Graph is not connected.");
			else
				statusTextField.setText("Graph is connected.");
		});
		
		hasCyclesButton.setOnAction(e -> {
			if(!graph.hasCycles())
				statusTextField.setText("Graph does not have cycles.");
			else
				statusTextField.setText("Graph has cycles.");
		});
		
		dfsButton.setOnAction(e -> {
			String dfsResult = graph.dfs();
			statusTextField.setText("Depth First Search Result: " + dfsResult);
		});
		
		bfsButton.setOnAction(e -> {
			String bfsResult = graph.bfs();
			statusTextField.setText("Breadth First Search Result: " + bfsResult);
		});
		
		return pane;
	}

}
