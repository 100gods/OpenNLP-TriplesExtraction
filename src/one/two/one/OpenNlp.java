package one.two.one;

import java.awt.RenderingHints.Key;
import java.io.FileInputStream;
import java.io.IOException;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.util.InvalidFormatException;

public class OpenNlp {
    public static void IE(Parse parse){
	    String NP="",VP="",obj;
	parse.show();
	if (parse.getType().equals("S")) {
	   // parse.show();
	    Parse[] sent=parse.getChildren();
	    for (Parse child : sent) {
		if (child.getType().equals("NP")) {
		    NP=child.getCoveredText();
		    System.out.print("Sub : ");
		    key(child.getChildren());
		   }else if(child.getType().equals("VP")){
		       VP=child.getCoveredText();
		       System.out.print("\nRelation : ");
		       getRel(child.getChildren());
		       System.out.print("\nObj : ");
		       key(child.getChildren());
		       
		   }
	    }
	}
	System.out.println("\nNP="+ NP+"\tVP="+VP);
    
    }
    public static void getRel(Parse[] parses){
	for (Parse parse : parses) {
	    if(parse.getType().matches("\\w*V\\w*")){
		System.out.print(parse.getCoveredText());
	    }
	}
    }
    public static void key(Parse[] parse){
	for (Parse parse2 : parse) {
	    if(parse2.getType().matches("\\w*NN\\w*")){
		System.out.print(parse2.getCoveredText()+" ");
	    }else if (parse2.getChildCount() == 0) {
		return ;
	    }else{
		key(parse2.getChildren());
	    }
	}
    }

    /**
     * @param args
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static void main(String[] args) throws InvalidFormatException,
	    IOException {
	FileInputStream modelIn = new FileInputStream("/home/saurabh/Documents/text-resources/en-parser-chunking.bin");
	try {
	    ParserModel model = new ParserModel(modelIn);
	    Parser parser = ParserFactory.create(model);
	 //  String sentence = "The quick brown fox jumps over the lazy dog .";
	    String sentence = "United States intelligence sources now believe that whistle-blower Edward Snowden was unable to access the 'crown jewels' NSA programs that secretly monitor telephone and online conversations worldwide.";
	    Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);

	    for (Parse parse : topParses) {IE(parse.getChildren()[0]);}
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (modelIn != null) {
		try {
		    modelIn.close();
		} catch (IOException e) {
		}
	    }
	}
	

    }

}
