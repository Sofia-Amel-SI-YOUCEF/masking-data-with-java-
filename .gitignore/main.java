import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class CSVReader {

	 static char randomChar (Random r, String cs, boolean uppercase) {
	        char c = cs.charAt(r.nextInt(cs.length()));
	        return uppercase ? Character.toUpperCase(c) : c;
	    }

	    static String mask (String str, int seed) {

	        final String cons = "bcdfghjklmnpqrstvwxz";
	        final String vowel = "aeiouy";
	        final String digit = "123456789";

	        Random r = new Random(seed);
	        char data[] = str.toCharArray();

	        for (int n = 0; n < data.length; ++ n) {
	            char ln = Character.toLowerCase(data[n]);
	            if (cons.indexOf(ln) >= 0)
	                data[n] = randomChar(r, cons, ln != data[n]);
	            else if (vowel.indexOf(ln) >= 0)
	                data[n] = randomChar(r, vowel, ln != data[n]);
	            else if (digit.indexOf(ln) >= 0)
	                data[n] = randomChar(r, digit, ln != data[n]);
	        }

	        return new String(data);

	    }

    public static void main(String[] args) throws IOException {

        String csvFile = "C:\\Users\\sasi-youcef\\Documents\\DSO-PRED-PND\\DataPND.test.csv";
        String line = "";
        String cvsSplitBy = ",";
        String currentWord ="";
        String exportedFile = "C:\\Users\\sasi-youcef\\Documents\\DSO-PRED-PND\\MaskedDataPND1.test.csv";
        FileWriter writer = null;
        
		 writer = new FileWriter(exportedFile);

		

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] dataPND = line.split(cvsSplitBy);
                String[] maskedDataPND = dataPND.clone();
                
                for (int i=0; i<dataPND.length; i++)
                	
                {   
                	currentWord = mask(dataPND[i], 0) ;
                	maskedDataPND[i] = currentWord;
                	
                	
                	
                	/*
                	if(i == dataPND.length -1)
                		System.out.println();
                		*/
                	
                }
                CSVUtils.writeLine(writer, Arrays.asList(maskedDataPND), ',');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        writer.flush();
        writer.close();
        
    }

}


