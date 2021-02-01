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
public class StringSourceStream extends SourceStream {
    
    public StringSourceStream(String sourceCode)
    {
        super();
        this.textReader = new StringReader(sourceCode);
    }
}
