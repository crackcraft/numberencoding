package net.crackcraft.numberencoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by crackcraft on 11.09.2014.
 */

public class Dictionary {
    final Map<String, List<String>> dict = new HashMap<String, List<String>>();

    Dictionary(List<String> words) {
        for(String word: words) {
            String nums = str2nums(word);
            if(nums.length()>0) {
                List<String> page = dict.get(nums);
                if(page == null) {
                    page = new ArrayList<String>();
                    dict.put(nums, page);
                }
                page.add(word);
            }
        }
    }

    private static String str2nums(String str) {
        StringBuilder nums = new StringBuilder(str.length());
        for(int i=0; i<str.length(); i++) {
            switch (str.charAt(i)) {
                case 'E':
                case 'e':
                    nums.append('0');
                    break;

                case 'J':
                case 'N':
                case 'Q':
                case 'j':
                case 'n':
                case 'q':
                    nums.append('1');
                    break;

                case 'R':
                case 'W':
                case 'X':
                case 'r':
                case 'w':
                case 'x':
                    nums.append('2');
                    break;

                case 'D':
                case 'S':
                case 'Y':
                case 'd':
                case 's':
                case 'y':
                    nums.append('3');
                    break;

                case 'F':
                case 'T':
                case 'f':
                case 't':
                    nums.append('4');
                    break;

                case 'A':
                case 'M':
                case 'a':
                case 'm':
                    nums.append('5');
                    break;

                case 'C':
                case 'I':
                case 'V':
                case 'c':
                case 'i':
                case 'v':
                    nums.append('6');
                    break;

                case 'B':
                case 'K':
                case 'U':
                case 'b':
                case 'k':
                case 'u':
                    nums.append('7');
                    break;

                case 'L':
                case 'O':
                case 'P':
                case 'l':
                case 'o':
                case 'p':
                    nums.append('8');
                    break;

                case 'G':
                case 'H':
                case 'Z':
                case 'g':
                case 'h':
                case 'z':
                    nums.append('9');
                    break;
            }
        }
        return nums.toString();
    }


    private String number2nums(String number) {
        StringBuilder nums = new StringBuilder(number.length());
        for(int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            if(c>='0' && c<='9') {
                nums.append(c);
            }
        }
        return nums.toString();
    }


    private void print(String number, String nums, List<String> words) {
        System.out.print(number);
        System.out.print(":");
        for(int i=0; i<words.size(); i++) {
            if(i==0 || words.get(i-1).length()>1 || words.get(i).length()>1) {
                System.out.print(' ');
            }
            System.out.print(words.get(i));
        }
        System.out.println();
    }
    private void advance(String number, String nums, int pos, List<String> words) {
        if(pos == nums.length()) {
            if(words.size() != pos) {
                print(number, nums, words);
            }
            return;
        }
        boolean pass = false;
        for(int end=nums.length(); end>pos; end--) {
            String num = nums.substring(pos, end);
            if(dict.containsKey(num)) {
                pass = true;
                for(String word: dict.get(num)) {
                    words.add(word);
                    advance(number, nums, end, words);
                    words.remove(words.size() - 1);
                }
            }
        }
        if(!pass) {
            words.add(nums.substring(pos, pos+1));
            advance(number, nums, pos+1, words);
            words.remove(words.size()-1);
        }

    }

    public void encode(String number) {
        String nums = number2nums(number);
        advance(number, nums, 0, new ArrayList<String>());
    }
}
