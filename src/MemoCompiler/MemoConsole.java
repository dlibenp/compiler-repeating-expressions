package MemoCompiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public abstract class MemoConsole
{
    public abstract void Write(String format, Object[] args);
    public abstract String Read();
}

class  DefaultMemoConsole extends MemoConsole
{
    public void Write(String format, Object[] args)
    {        
        System.out.println(String.format(format, args));
        //.WriteLine(format, args);
    }

    public String Read()
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //return Console.ReadLine();
        return "";
    }
}




