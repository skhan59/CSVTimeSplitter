import java.util.Scanner;  
import java.io.*;  
public class csvSplitter 
{  
    static String csvFile = "ScriptNoDoH.csv"; //change filename accordingly
    public static void main(String args[])  
    {  
    //read the csv and allow us to collect data
        FileInputStream fileInput=null;  
        DataInputStream dataInput=null;
        FileOutputStream fileOutput =null;
        DataOutputStream dataOutput =null;
        
        String line;
        try
        {  
            fileInput = new FileInputStream(csvFile); 
            dataInput = new DataInputStream(fileInput);  
            String header=dataInput.readLine();
            int x=0;
            
            fileOutput = new FileOutputStream("test"+(x+1)+".csv");  
            dataOutput = new DataOutputStream(fileOutput);  
            
            dataOutput.writeBytes(header+"\n");
            while((line=dataInput.readLine())!=null)
            {
                String words[]=line.split(","); //delimiter
                String strTime=words[1].substring(1, words[1].length()-1);
                double time=Double.parseDouble(strTime);
                
                if(time<=50*(x+1))
                {
                    dataOutput.writeBytes(line+"\n");
                }
                else 
                {
                    dataOutput.close();
                    fileOutput.close();
                    x++;
                    fileOutput = new FileOutputStream("outputfile"+(x+1)+".csv");
                    dataOutput = new DataOutputStream(fileOutput); 
                    dataOutput.writeBytes(header+"\n");


                }
            }
            System.out.println("Success! Check your folder."); //cmnd+spacebar "filename"


        }
        catch (FileNotFoundException e)
        {  
            System.err.println("File Not Found Error: " + e.getMessage());  
        } 
        catch (IOException e)          {              
            System.err.println("IO Error: " + e.getMessage());  
        }  
        catch (Exception e)        {
            System.err.println("Error: " + e.getMessage());  
        }
        finally 
        {
            try 
            {
                dataInput.close();
                fileInput.close();
                dataOutput.close();
                fileOutput.close();
            } 
            catch (Exception e)
            {
                    System.err.println("Error"+e.getMessage());  
            } 


        }
    }
}
