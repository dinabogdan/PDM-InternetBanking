package org.pdm.ib.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileUtils {

    public static String readToString(InputStream input) throws IOException {
        char[] buffer = new char[1024];
        Reader in = new InputStreamReader(input, "UTF-8");

        StringBuilder out = new StringBuilder();

        int count;
        while ((count = in.read(buffer, 0, buffer.length)) >= 0) {
            out.append(buffer, 0, count);
        }

        return out.toString();
    }
}
