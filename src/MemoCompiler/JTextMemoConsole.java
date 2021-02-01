/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler;

/**
 *
 * @author frodo
 */
public class  JTextMemoConsole extends MemoConsole
{
    javax.swing.JTextArea textOuput;

    public JTextMemoConsole(javax.swing.JTextArea textOuput)
    {
        this.textOuput = textOuput;
    }

    public void Write(String format, Object[] args)
    {
         textOuput.append(format);
         textOuput.append("\n");
    }

    public String Read()
    {
        return "";
    }
}
