import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v1.DbxEntry;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadErrorException;
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

        DbxEntry.File md = null;

        DecimalFormat numberFormat = new DecimalFormat("#.00");
        Random random = new Random();
        double randomHours = 2 + (8-2)* random.nextDouble();
        Double hoursForXML = Double.valueOf(numberFormat.format(randomHours));

        System.out.println("Random hours:"+ randomHours);
        File file = fileHandler.addHours("Peter_Rosenberg_"+System.currentTimeMillis(), hoursForXML , new Date(2018, 04, 10), "Treco", "Solitare");

        String dropboxDirectory = System.getProperty("user.home")+File.separator+"Dropbox";
        String timeManagerDirectory = dropboxDirectory + File.separator+"TimeManager"+File.separator;
        System.out.println(timeManagerDirectory);


        File[] dropboxFiles = new File(dropboxDirectory).listFiles();
        //showFiles(dropboxFiles);


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

    public static void showFiles(File[] files){
        for (File f : files){
            if (f.isDirectory())
            {
                System.out.println("Directory: " + f.getName());
                showFiles(f.listFiles());
            }
            else
            {
                System.out.println("File: "+f.getName());
            }

        }
    }




}
