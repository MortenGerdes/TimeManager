import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CellGUI extends Application
{
	private final String[] arrayOfDays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

	private int rows = 10;
	private int columns = 7;
	private GridPane mainGrid, weekDayGrid, dropdownGrid, sumGridRight, sumGridButtom;
	//private ObservableList<String> projects = FXCollections.observableArrayList();
	private ObservableList<String> projects = FXCollections.observableArrayList(
			"Treco",
			"ThisNameIsDefinitelyTooLong",
			"NanoPark",
			"Frokost",
			"Kopper",
			"Yolo",
			"Swag"
	);
	private TimeComputer timeComputer = new TimeComputer();
	private TableView table;

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
		table = generateTableView();

		//mainGrid.add(weekDayGrid, 1, 0);
		mainGrid.add(dropdownGrid, 0, 0);
		mainGrid.add(sumGridRight, 2, 0);
		mainGrid.add(sumGridButtom, 1, 2);
		mainGrid.add(table, 1, 0);

		primaryStage.setTitle("Time Manager");
		primaryStage.setScene(new Scene(mainGrid, 600, 450));
		// primaryStage.setScene(new Scene(mainGrid));
		primaryStage.show();
	}

	private TableView generateTableView(){
		TableView<Integer> table = new TableView();
		List<Integer> intValues = Arrays.asList(0, 0, 0, 0, 0);
		List<TableColumn<Integer, Number>> columns = new ArrayList<>();

		table.setEditable(true);

		TableColumn<Integer, Number> mondayColumn = new TableColumn("Mon");
		TableColumn<Integer, Number> tuesdayColumn = new TableColumn("Tue");
		TableColumn<Integer, Number> wednesdayColumn = new TableColumn("Wed");
		TableColumn<Integer, Number> thursdayColumn = new TableColumn("Thu");
		TableColumn<Integer, Number> fridayColumn = new TableColumn("Fri");
		TableColumn<Integer, Number> saturdayColumn = new TableColumn("Sat");
		TableColumn<Integer, Number> sundayColumn = new TableColumn("Sun");

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		/*mondayColumn.setCellValueFactory(TextFieldTableCell.forTableColumn());
		mondayColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Integer, Number>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Integer, Number> event) {
				event.getTableView().getItems().get(event.getTablePosition().getRow());
			}
		});*/

		columns.add(mondayColumn);
		columns.add(tuesdayColumn);
		columns.add(wednesdayColumn);
		columns.add(thursdayColumn);
		columns.add(fridayColumn);
		columns.add(saturdayColumn);
		columns.add(sundayColumn);

		for (int i = 0; i < intValues.size(); i++) {
			table.getItems().add(i);
		}
		table.getColumns().addAll(columns);

		for(TableColumn<Integer, Number> tc: columns)
		{
			tc.setCellValueFactory(cellData -> {
				Integer rowIndex = cellData.getValue();
				return new ReadOnlyIntegerWrapper(intValues.get(rowIndex));
			});
		}
		return table;
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
				tf.setOnMouseReleased(event ->
				{
					calculateRightSums();
					calculateButtomSums();
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
			ComboBox cb = new ComboBox(projects.sorted());
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
				String input = "0";
				Node node = weekDayGrid.getChildren().get(i);
				TextField tf;
				if(!(node instanceof TextField))
				{
					continue;
				}
				tf = (TextField) node;

				if(i % rows == k && !tf.getCharacters().toString().isEmpty())
				{
					input = tf.getCharacters().toString();
					sum += Integer.parseInt(input);
				}
				((TextField)(sumGridRight.getChildren().get(k))).setPromptText("" + sum);
			}
		}
	}

	private void calculateButtomSums(){


		for (int j = 0; j < sumGridButtom.getChildren().size(); j++){

			int sum = 0;

			for(int i = 0; i < weekDayGrid.getChildren().size(); i++){
				Node node = weekDayGrid.getChildren().get(i);
				TextField tf;
				if(!(node instanceof TextField))
				{
					continue;
				}
				tf = (TextField) node;

				if (Math.floor(i/rows) == j && !tf.getCharacters().toString().isEmpty()){
					sum += Integer.parseInt(tf.getCharacters().toString());
				}
				((TextField)(sumGridButtom.getChildren().get(j))).setPromptText("" + sum);
			}
		}


	}
}