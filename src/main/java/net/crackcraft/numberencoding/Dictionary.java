package net.crackcraft.numberencoding;

import java.util.List;

/**
 * Created by crackcraft on 11.09.2014.
 */

public class Dictionary {
    final Node root = new Node();

    Dictionary(List<String> words) {
        for(String word: words) {
            add(root, word, 0);
        }
    }

    private static int num(char c) {
        switch(c) {
            case 'E':
            case 'e':
                return 0;
            case 'J':
            case 'N':
            case 'Q':
            case 'j':
            case 'n':
            case 'q':
                return 1;

            case 'R':
            case 'W':
            case 'X':
            case 'r':
            case 'w':
            case 'x':
                return 2;

            case 'D':
            case 'S':
            case 'Y':
            case 'd':
            case 's':
            case 'y':
                return 3;

            case 'F':
            case 'T':
            case 'f':
            case 't':
                return 4;

            case 'A':
            case 'M':
            case 'a':
            case 'm':
                return 5;

            case 'C':
            case 'I':
            case 'V':
            case 'c':
            case 'i':
            case 'v':
                return 6;

            case 'B':
            case 'K':
            case 'U':
            case 'b':
            case 'k':
            case 'u':
                return 7;

            case 'L':
            case 'O':
            case 'P':
            case 'l':
            case 'o':
            case 'p':
                return 8;

            case 'G':
            case 'H':
            case 'Z':
            case 'g':
            case 'h':
            case 'z':
                return 9;

            default:
                return -1;
        }
    }

    private static void add(final Node node, final String word, int pos) {
        while(pos < word.length()) {
            int num = num(word.charAt(pos++));
            if(num<0) {
                continue;
            }
            Node child = node.children[num];
            if(child == null) {
                child = new Node();
                node.children[num] = child;
            }
            add(child, word, pos);
            return;
        }
        node.values.add(word);
    }

    private void encode(final Node node, final String number, int pos, StringBuilder out, boolean found, boolean special) {
        for (String val : node.values) {
            encode(root, number, pos, new StringBuilder(out).append(' ').append(val), true, false);
        }

        while(pos < number.length()) {
            int num = Character.getNumericValue(number.charAt(pos++));
            if(num<0 || num>9) {
                continue; // leave the char?
            }
            Node child = node.children[num];
            if(child == null) {
                if(special || node == root) {
                    encode(root, number, pos, new StringBuilder(out).append('0' + num), found, true);
                }
            } else {
                encode(child, number, pos, out, found, false);
            }
            return;
        }
        if(found) {
            System.out.println(number + ": "+out.toString());
        }

    }

    public void encode(String number) {
        encode(root, number, 0, new StringBuilder(), false, false);
    }
}
