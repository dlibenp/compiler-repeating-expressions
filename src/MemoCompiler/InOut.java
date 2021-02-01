package MemoCompiler;

public class InOut 
{
    private static MemoConsole console = new DefaultMemoConsole();

    public static void Write(String format, Object[] args)
    {
        console.Write(format, args);
    }

    public static String Read()
    {
        return console.Read();
    }

    public static void SetConsole(MemoConsole newConsole) 
    {
        console = newConsole;
    }
}
