import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v1.DbxEntry;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.users.FullAccount;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

public class Main
{

    private static final String ACCESS_TOKEN = "IBsJShmqBzAAAAAAAAABQC0h07HMaPJPR36NyHfndpYJNG9lngYUKL92sGGhc1Qs";
    private static ListFolderResult result;
    private static DbxClientV2 client;
    private String id;
    private double hours;
    private Date date;
    private String customer;
    private String project;

    public static void main(String args[]) throws DbxException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        client = new DbxClientV2(config, ACCESS_TOKEN);

        FullAccount account = client.users().getCurrentAccount();

        System.out.println(account.getName().getDisplayName());
        FileHandler fileHandler = new FileHandler(client);

        DecimalFormat numberFormat = new DecimalFormat("#.00"); //decimal format so we dont get 10000 decimals on the double

        Random random = new Random(); //a Random for the random double number
        double randomHours = 2 + (8-2)* random.nextDouble();//Creates a random double in interval [2, 8]
        double hoursForXML = Double.valueOf(numberFormat.format(randomHours));
        File file = fileHandler.addHours("Peter_Rosenberg_"+System.currentTimeMillis(), hoursForXML , new Date(2018, 04, 10), "Treco", "Solitare");

        String dropboxDirectory = System.getProperty("user.home")+File.separator+"Dropbox";//Gets the default dropbox folder on the system
        String timeManagerDirectory = dropboxDirectory + File.separator+"TimeManager"+File.separator; //Changes the Path String to the timeManager directory path
        System.out.println(timeManagerDirectory);


        File[] timeManagerFiles = new File(timeManagerDirectory).listFiles(); //Get a list of files from directory, in this case timeManagerDirectory
        computeTotalHours(timeManagerFiles); //takes a list of files


        //GUI gui = new GUI();

        /*
        try{
            deleteFile("/test.xml");
        }catch (Exception e){
            e.printStackTrace();
        }*/

        try{
            fileHandler.uploadFile(file);
            fileHandler.printDirectories();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void computeTotalHours(File[] files){
        double totalHours = 0;
        for (File f : files){
            if (f.isDirectory())
            {
                System.out.println("Directory: " + f.getName());
                computeTotalHours(f.listFiles());
            }
            else
            {
                BufferedReader reader = null;
                try{
                    reader = new BufferedReader(new FileReader(f));
                    String line = reader.readLine();
                    while(line != null){
                        if ((line.contains("<hours>")))
                        {
                            String temp = line.replace("<hours>","");
                            String temp2 = temp.replace("</hours>","");
                            totalHours += Double.valueOf(temp2);
                        }
                        line = reader.readLine();
                    }
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
        if(totalHours != 0.0) System.out.println("TOTAL HOURS: " + totalHours);
    }




}
