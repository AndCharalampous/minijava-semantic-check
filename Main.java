import syntaxtree.*;
import visitor.*;
import java.io.*;

public class Main {
    public static void main (String [] args){
    if(args.length != 1){
        System.err.println("Usage: java Main <inputFile>");
        System.exit(1);
    }
    FileInputStream fis = null;
    try{
        fis = new FileInputStream(args[0]);
        MiniJavaParser parser = new MiniJavaParser(fis);
        
        Goal root = parser.Goal();
        System.err.println("Program parsed successfully.");
        CollectVisitor vis = new CollectVisitor();
        root.accept(vis, null);
    }
    catch(ParseException ex){
        System.out.println(ex.getMessage());
    }
    catch(FileNotFoundException ex){
        System.err.println(ex.getMessage());
    }
    finally{
        try{
        if(fis != null) fis.close();
        }
        catch(IOException ex){
        System.err.println(ex.getMessage());
        }
    }
    }
}