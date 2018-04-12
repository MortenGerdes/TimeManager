import java.io.*;

/**
 * Created by Peter on 10-Apr-18.
 */
public class TimeComputer
{
    private double totalHoursInPath = 0.0;

    private String dropboxDirectory = System.getProperty("user.home")+File.separator+"Dropbox";//Gets the default dropbox folder on the system

    public double computeTotalHoursInDir(String path){

        String pathDirectory = dropboxDirectory + File.separator+"TimeManager"+File.separator+path+File.separator; //Changes the Path String to the timeManager directory path
        File[] pathFiles = new File(pathDirectory).listFiles(); //Get a list of files from directory, in this case timeManagerDirectory

        if (pathFiles == null) System.out.println("Path: \""+pathDirectory+"\" does not contain any Files or Folders. It might not exist.");
        else recursiveDirectoryHourSum(pathFiles);

        return totalHoursInPath;
        }

    private void recursiveDirectoryHourSum(File[] files){//Helper method that does the recursion for the totalHoursInDir
        double totalHours = 0;
        for (File f : files){
            if (f.isDirectory())
            {
                recursiveDirectoryHourSum(f.listFiles());
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
                            totalHoursInPath += Double.valueOf(temp2);
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
