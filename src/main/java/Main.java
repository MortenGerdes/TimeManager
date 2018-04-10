import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.users.FullAccount;

import java.io.*;
import java.util.Date;

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


        File file = addHours("Peter Rosenberg", 5.7, new Date(2018, 04, 10), "Treco", "Solitare");

        GUI gui = new GUI();

        /*
        try{
            deleteFile("/test.xml");
        }catch (Exception e){
            e.printStackTrace();
        }*/

       /* try{
            uploadFile(file);
            printDirectories();
        }catch (Exception e){
            e.printStackTrace();
        }
        */


    }

    public static File addHours(String id, double hours, Date date, String customer, String project){
        XMLBuilder builder = new XMLBuilder();
        return builder.buildXML(id, hours, date, customer, project);
    }

    public static void printDirectories(){
        // Get files and folder metadata from Dropbox root directory
        try{
            result = client.files().listFolder("");

        }catch (Exception e){
            e.printStackTrace();
        }

        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }
            try{
                result = client.files().listFolderContinue(result.getCursor());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void deleteFile(String path){
        try{
            Metadata metaData = client.files().delete(path);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void uploadFile(File file) throws DbxException, IOException {
        // Upload "test.txt" to Dropbox
        InputStream in = null;
        try {
            System.out.println(file.exists());
            in = new FileInputStream(file);
            FileMetadata metadata = client.files().uploadBuilder("/test.xml")
                    .uploadAndFinish(in);
            System.out.println("The file was uploaded to dropbox");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }

    }
}
