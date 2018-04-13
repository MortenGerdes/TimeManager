import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CellGUI extends Application
{
	private final String[] arrayOfDays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

	private int rows = 10;
	private GridPane mainGrid, weekDayGrid, dropdownGrid, sumGridRight, sumGridButtom;
	private ObservableList<String> projects = FXCollections.observableArrayList();
	private TimeComputer timeComputer = new TimeComputer();
	public void start(Stage primaryStage)
	{
		ArrayList<String> listOfProjects = timeComputer.recursiveDirectoryNameList("Clients");
		if (listOfProjects.isEmpty()){
			System.out.println("List of projects is empty! Look for error in the path, while making list of projects.");
		}
		projects.addAll(listOfProjects);

		mainGrid = new GridPane();

		weekDayGrid = generateGrid();
		dropdownGrid = generateDropDowns();
		sumGridRight = generateRightSumFields();
		sumGridButtom = generateButtomSumFields();

		mainGrid.add(weekDayGrid, 1, 0);
		mainGrid.add(dropdownGrid, 0, 0);
		mainGrid.add(sumGridRight, 2, 0);
		mainGrid.add(sumGridButtom, 1, 2);

		primaryStage.setTitle("Time Manager");
		primaryStage.setScene(new Scene(mainGrid, 500, 380));
		primaryStage.show();
	}

	private GridPane generateGrid()
	{
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(2);
		grid.setPadding(new Insets(8, 0, 0, 0));
		for(int i = 0; i < arrayOfDays.length; i++) //Rows
		{
			grid.add(new Label(arrayOfDays[i]), i , 0);
			for(int j = 1; j < rows; j++) // Col
			{
				TextField tf = new TextField();
				tf.setOnMouseReleased(new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent event)
					{
						calculateRightSums();
					}
				});

				grid.add(tf, i, j);
			}
		}

		return grid;
	}

	private GridPane generateDropDowns()
	{
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(2);
		grid.setPadding(new Insets(15, 8, 0, 10));
		for(int i = 0; i < rows-1; i++)
		{
			ComboBox<String> cb = new ComboBox<String>(projects);
			cb.setMinWidth(100);
			grid.add(cb, 0, i+2);
		}
		return grid;
	}

	private GridPane generateRightSumFields()
	{
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(2);
		grid.setPadding(new Insets(-2, 12, 0, 12));
		grid.add(new Label("Sums"), 0, 1);
		for(int i = 0; i < rows-1; i++)
		{
			TextField tf = new TextField();
			tf.setDisable(true);
			tf.setMinWidth(40);
			grid.add(tf, 0, i+2);
		}
		return grid;
	}

	private GridPane generateButtomSumFields()
	{
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(2);
		grid.setPadding(new Insets(12, 0, 0, 0));

		for(int i = 0; i < arrayOfDays.length; i++)
		{
			TextField tf = new TextField();
			tf.setDisable(true);
			tf.setMinWidth(40);
			grid.add(tf, i, 0);
		}

		return grid;
	}

	private void calculateRightSums()
	{
		for(int k = 1; k < sumGridRight.getChildren().size(); k++)
		{
			int sum = 0;

			for(int i = 0; i < weekDayGrid.getChildren().size(); i++)
			{
				Node node = weekDayGrid.getChildren().get(i);
				TextField tf;
				if(!(node instanceof TextField))
				{
					continue;
				}
				tf = (TextField) node;

				if(i % rows == k)
				{
					if(tf.getCharacters().length() != 0)
					{
						sum += Integer.parseInt(tf.getCharacters().toString());
						((TextField)(sumGridRight.getChildren().get(k))).setPromptText("" + sum);
					}
				}
			}
		}
	}
}
