import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CellGUI extends Application
{
	private final String[] arrayOfDays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

	private int rows = 10;
	private GridPane mainGrid, weekDayGrid, dropdownGrid, sumGridRight, sumGridButtom, weekDayLabels;
	private ObservableList<String> projects = FXCollections.observableArrayList(
		"Treco",
		"ThisNameIsDefinitelyTooLong",
		"NanoPark",
		"Frokost",
		"Kopper",
		"Yolo",
		"Swag"
		);
	private Button sumButton;

	public void start(Stage primaryStage)
	{
		mainGrid = new GridPane();
		weekDayGrid = generateGrid();
		dropdownGrid = generateDropDowns();
		sumGridRight = generateRightSumFields();
		sumGridButtom = generateButtomSumFields();
		sumButton = generateSumButton();
		weekDayLabels = generateWeekLabelGrid();

		mainGrid.add(weekDayGrid, 1, 0);
		mainGrid.add(weekDayLabels, 1, 0);
		mainGrid.add(dropdownGrid, 0, 0);
		mainGrid.add(sumGridRight, 2, 0);
		mainGrid.add(sumGridButtom, 1, 2);
		mainGrid.add(sumButton, 2,1);

		primaryStage.setTitle("Time Manager");
		primaryStage.setScene(new Scene(mainGrid, 500, 380));
		primaryStage.show();
	}

	private Button generateSumButton()
	{
		Button sumButton = new Button("Sum hours");
		sumButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				List list = weekDayGrid.getChildren();
				List list2 = sumGridRight.getChildren();
				int week1 = 0;
				int[] weeks = new int[list2.size()];
				int[] days = new int[list2.size()];
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) instanceof TextField && i >= 6 && i <= 13) {
						if (((TextField) list.get(i)).getText() != null && !(((TextField) list.get(i)).getText().isEmpty())){
							week1 += Double.parseDouble(((TextField) list.get(i)).getText().toString());
						}

					}
				}

				weeks[0] = week1;

				for(int i = 0; i <= list2.size(); i++){

					if(list2.get(i) instanceof TextField){
						((TextField) list2.get(i)).setText(""+weeks[i]);
					}
				}

			}
		});
		return sumButton;
	}

	private GridPane generateGrid()
	{
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(8, 0, 0, 0));
		grid.setVgap(10);
		grid.setHgap(2);

		for(int i = 0; i < arrayOfDays.length; i++) //Rows
		{
			//grid.add(new Label(arrayOfDays[i]), i , 0);
			for(int j = 1; j < rows; j++) // Col
			{
				grid.add(new TextField(), i, j);
			}
		}

		return grid;
	}

	private GridPane generateWeekLabelGrid(){
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(8, 0, 0, 0));
		grid.setVgap(10);
		grid.setHgap(2);

		for(int i = 0; i < arrayOfDays.length; i++){
			grid.add(new Label(arrayOfDays[i]), i, 0);
		}

		return grid;
	}

	private GridPane generateDropDowns()
	{
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(2);
		grid.setPadding(new Insets(15, 8, 0, 0));
		for(int i = 0; i < rows-1; i++)
		{
			ComboBox cb = new ComboBox(projects);
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
}
