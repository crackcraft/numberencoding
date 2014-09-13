package net.crackcraft.numberencoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by crackcraft on 11.09.2014.
 */

public class Main {

    private static List<String> readAllLines(String name) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Main.class.getClassLoader().getResourceAsStream(name), "ASCII"));
        List<String> result = new ArrayList<String>();
        for(;;) {
            String line = reader.readLine();
            if(line == null) {
                return result;
            }
            result.add(line);
        }
    }

    public static void main(String[] args) throws IOException {
        Dictionary dict = new Dictionary(readAllLines("dictionary.txt"));
        for (String number: readAllLines("input.txt")) {
            dict.encode(number);
        }
    }
}
