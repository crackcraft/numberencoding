package net.crackcraft.numberencoding;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;

/**
 * Created by crackcraft on 11.09.2014.
 */

public class Main {

    public static void main(String[] args) throws IOException {
        Dictionary dict = new Dictionary(Files.readAllLines(FileSystems.getDefault().getPath(args[0]), Charset.forName("ASCII")));
        for(String number: Files.readAllLines(FileSystems.getDefault().getPath(args[1]), Charset.forName("ASCII"))) {
            dict.encode(number);
        }
    }
}
