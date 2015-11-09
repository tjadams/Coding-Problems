import java.io.*;

public class FileInputOutput {
    public static void main (String[] args) {
        File f = new File("example.txt");

        // Note: Don't forget to catch exceptions
        try {
            // Setup File Output
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);

            pw.println("Line 1:");
            pw.print("Not appended to Line 1, but rather prepended to Line 2. ");
            pw.println("Line 2: I wonder if someone will ever read this repository");

            // Note: It seems that bw prints after these printlns...
            // In order to do something directly after a write, I may want to put that stuff into a finally
            /*
            System.out.println("Now testing with System.out");
            System.out.print(" appended to Line 1");
            System.out.println("Line 2: I wonder if someone will ever read this repository");
            */

            // Note: Don't forget to close the writers you have set up.
            pw.close();
            fw.close();

            // Setup File Input
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            // Read all lines one line at a time
            // Note: could check if br.readLine() isn't null but then you can't access the first line of the file in memory.
            // By keeping track of the line you have read and then re-reading that line each time, you can access each line.
            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }

            //Note: Don't forget to close the readers you have set up
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
