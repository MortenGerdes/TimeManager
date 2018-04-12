import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import javafx.application.Application;

import java.util.Date;

public class Main
{
	private static final String ACCESS_TOKEN = "IBsJShmqBzAAAAAAAAABQC0h07HMaPJPR36NyHfndpYJNG9lngYUKL92sGGhc1Qs";

	private static ListFolderResult result;
	private static DbxClientV2 client;
	private static TimeComputer timeComputer;

	private double hours;
	private Date date;
	private String id, customer, project;


	public static void main(String args[]) throws Exception
	{
		// Create Dropbox client
		/*
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
		client = new DbxClientV2(config, ACCESS_TOKEN);
		FullAccount account = client.users().getCurrentAccount();
		System.out.println(account.getName().getDisplayName());
		*/
		Application.launch(CellGUI.class, args);
		/*
		FileHandler fileHandler = new FileHandler(client);
		DecimalFormat numberFormat = new DecimalFormat("#.00"); //decimal format so we dont get 10000 decimals on the double
		Random random = new Random(); //a Random for the random double number
		double randomHours = 2 + (8 - 2) * random.nextDouble();//Creates a random double in interval [2, 8]
		double hoursForXML = Double.valueOf(numberFormat.format(randomHours));
		File file = fileHandler.addHours("Peter_Rosenberg_" + System
				.currentTimeMillis(), hoursForXML, new Date(2018, 04, 10), "Treco", "Solitare");
		timeComputer = new TimeComputer();
		System.out.println("Total hours, main: " + timeComputer.computeTotalHoursInDir("Clients/Treco"));

		GUI gui = new GUI();
        try{
            deleteFile("/test.xml");
        }catch (Exception e){
            e.printStackTrace();
        }


		try
		{
			fileHandler.uploadFile(file);
			//fileHandler.printDirectories();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		*/

	}
}
