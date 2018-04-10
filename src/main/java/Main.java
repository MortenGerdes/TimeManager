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
    private static TimeComputer timeComputer;

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


        timeComputer = new TimeComputer();
        timeComputer.computeTotalHoursInDir("Clients/Firma2"); //takes a list of files


        //GUI gui = new GUI();

        /*
        try{
            deleteFile("/test.xml");
        }catch (Exception e){
            e.printStackTrace();
        }*/

        try{
            fileHandler.uploadFile(file);
            //fileHandler.printDirectories();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
