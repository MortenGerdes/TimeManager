import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by jackzet on 10/04/2018.
 */
public class FileHandler {

    private DbxClientV2 client;

    public FileHandler(DbxClientV2 client) {
        this.client = client;
    }

    public void printDirectories() {

        ListFolderResult result = null;
        // Get files and folder metadata from Dropbox root directory
        try {
            result = client.files().listFolder("");

        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }
            try {
                result = client.files().listFolderContinue(result.getCursor());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void deleteFile(String path){
        try{
            Metadata metaData = client.files().delete(path);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void uploadFile(File file) throws DbxException, IOException {
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

    public File addHours(String id, double hours, Date date, String customer, String project){
        XMLBuilder builder = new XMLBuilder();
        return builder.buildXML(id, hours, date, customer, project);
    }

}
