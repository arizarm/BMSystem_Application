package com.example.aprilwang.bookshop;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by April Wang on 21/12/2017.
 */

public class StackTrace {
    public static String trace(Exception ex) {
        StringWriter outStream = new StringWriter();
        ex.printStackTrace(new PrintWriter(outStream));
        return outStream.toString();
    }
}
