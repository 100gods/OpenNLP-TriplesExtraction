package one.two.one;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.InvalidFormatException;

public class Token {

    /**
     * @param args
     * @throws IOException 
     * @throws InvalidFormatException 
     */
    public static void main(String[] args) throws InvalidFormatException, IOException {
	FileInputStream fileInputStream=new FileInputStream("/home/saurabh/Documents/text-resources/en-sent.bin");
	SentenceModel model=new SentenceModel(fileInputStream);
	SentenceDetectorME detectorME=new SentenceDetectorME(model);
	String[] sent = detectorME.sentDetect("Hi . Hello . Hwru ? . Who is This ? . This is jfk ");
	for (String string : sent) {
	    System.out.println(string);
	}
	fileInputStream.close();

    }

}
