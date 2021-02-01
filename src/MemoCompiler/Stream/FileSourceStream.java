/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MemoCompiler.Stream;

import java.io.*;
/**
 *
 * @author trujillo
 */
public class FileSourceStream extends SourceStream {
    
    public FileSourceStream(String fileName) throws FileNotFoundException
    {
        super();
        FileInputStream in = new FileInputStream(fileName);
        this.textReader = new InputStreamReader(in);
    }
}
