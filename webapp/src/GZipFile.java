import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

public class GZipFile {
    private static final String INPUT_GZIP_FILE = "/home/toli/German_adverbs_4.txt.gz";
    private static final String OUTPUT_FILE = INPUT_GZIP_FILE .replace(".gz","");
    
    public static void main( String[] args )
    {
    	GZipFile gZip = new GZipFile();
    	gZip.gunzipIt();
    }

    /**
     * GunZip it
     */
    public void gunzipIt(){

     byte[] buffer = new byte[1024];

     try{

    	 GZIPInputStream gzis =
    		new GZIPInputStream(new FileInputStream(INPUT_GZIP_FILE));

    	 FileOutputStream out =
            new FileOutputStream(OUTPUT_FILE);

        int len;
        while ((len = gzis.read(buffer)) > 0) {
        	out.write(buffer, 0, len);
        }

        gzis.close();
    	out.close();

    	try {
    	    Files.delete(Paths.get(INPUT_GZIP_FILE));
    	} catch (NoSuchFileException x) {
    	    System.err.format("%s: no such" + " file or directory%n", INPUT_GZIP_FILE);
    	}    	
    	//Just like with gunzip, remove the old .gz file

    	System.out.println("Done");


    }catch(IOException ex){
       ex.printStackTrace();
    }
   }
}