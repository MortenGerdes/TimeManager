import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.util.ArrayList;

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
		TableView<PersonWeekOverview> table = new TableView<>();
		ObservableList<PersonWeekOverview> data = FXCollections.observableArrayList(
				new PersonWeekOverview(1, 4 ,6, 23, 5, 1, 5, 7),
				new PersonWeekOverview(1, 12 ,7, 6, 2, 7, 0, 0),
				new PersonWeekOverview(1, 4 ,6, 23, 5, 1, 5, 7)
				);

		table.setEditable(true);

		TableColumn mondayColumn = new TableColumn("Mon");
		mondayColumn.setCellValueFactory(
				new PropertyValueFactory<PersonWeekOverview, String>("hoursMon")
		);
		mondayColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));

		TableColumn tuesdayColumn = new TableColumn("Tue");
		tuesdayColumn.setCellValueFactory(
				new PropertyValueFactory<PersonWeekOverview, String>("hoursTue")
		);
		tuesdayColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));

		TableColumn wednesdayColumn = new TableColumn("Wed");
		wednesdayColumn.setCellValueFactory(
				new PropertyValueFactory<PersonWeekOverview, String>("hoursWed")
		);
		wednesdayColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));

		TableColumn thursdayColumn = new TableColumn("Thu");
		thursdayColumn.setCellValueFactory(
				new PropertyValueFactory<PersonWeekOverview, String>("hoursThu")
		);
		thursdayColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));

		TableColumn fridayColumn = new TableColumn("Fri");
		fridayColumn.setCellValueFactory(
				new PropertyValueFactory<PersonWeekOverview, String>("hoursFri")
		);
		fridayColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));

		TableColumn saturdayColumn = new TableColumn("Sat");
		saturdayColumn.setCellValueFactory(
				new PropertyValueFactory<PersonWeekOverview, String>("hoursSat")
		);
		saturdayColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));

		TableColumn sundayColumn = new TableColumn("Sun");
		sundayColumn.setCellValueFactory(
				new PropertyValueFactory<PersonWeekOverview, String>("hoursSun")
		);
		sundayColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getSelectionModel().cellSelectionEnabledProperty().set(true);

		table.setItems(data);
		table.getColumns().addAll(mondayColumn, tuesdayColumn, wednesdayColumn, thursdayColumn, fridayColumn, saturdayColumn, sundayColumn);

/*		for(TableColumn<PersonWeekOverview, String> tc: columns)
		{
			tc.setCellFactory(TextFieldTableCell.forTableColumn());
			tc.setOnEditCommit(event ->
			{
				event.getTableView().getItems().get(event.getTablePosition().getRow()).s);
			});
		}*/
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