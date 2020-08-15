package codesignal;

import java.util.ArrayList;
import java.util.List;

//    String[][] lines = {{"hello", "world"}, {"How", "areYou", "doing"}, {"Please look", "and align", "to right", "OK?"}};
//    String[] aligns = {"LEFT", "RIGHT", "RIGHT"};
//    int width = 16;
// Notice: the len of line must <= width
// output:
//    ******************
//    *hello world     *
//    *How areYou doing*
//    *     Please look*
//    *       and align*
//    *    to right OK?*
//    ******************

public class JustifyNewspaperText {

    public static final String STAR = "*";
    public static final String SPACE = " ";
    public static final String[] POS = {"LEFT", "RIGHT"};

    public String[] justifyNewspaperText(String[][] lines, String[] aligns, int width) {
        String bar = STAR.repeat(width + 2);
        List<String> output = new ArrayList<>();
        output.add(bar);
        for (int i = 0; i < lines.length; ++i) {
            String[] line = lines[i];
            List<StringBuilder> sbs = new ArrayList<>();
            sbs.add(new StringBuilder());
            int curSb = 0;
            sbs.get(curSb).append(line[0]);
            for (int j = 1; j < line.length; ++j) {
                String word = line[j];
                if (sbs.get(curSb).length() + word.length() + 1 <= width) {
                    sbs.get(curSb).append(SPACE).append(word);
                } else {
                    sbs.add(new StringBuilder());
                    curSb++;
                    sbs.get(curSb).append(word);
                }
            }
            for (StringBuilder sb : sbs) {
                output.add(getLine(sb, aligns[i], width));
            }
        }
        output.add(bar);
        String[] res = new String[output.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = output.get(i);
        }
        return res;
    }

    public String getLine(StringBuilder sb, String pos, int width) {
        int remainingSpace = width - sb.length();
        String res = STAR;
        if (pos.equals(POS[0])) {
            res += sb.toString() + SPACE.repeat(remainingSpace) + STAR;
        } else {
            res += SPACE.repeat(remainingSpace) + sb.toString() + STAR;
        }
        return res;
    }


    public static void main(String[] args) {
        JustifyNewspaperText test = new JustifyNewspaperText();
        String[][] lines = {{"hello", "world"}, {"How", "areYou", "doing"}, {"Please look", "and align", "to right", "OK?"}};
        String[] aligns = {"LEFT", "RIGHT", "RIGHT"};
        int width = 16;
        String[] res = test.justifyNewspaperText(lines, aligns, width);
        for (String x : res) {
            System.out.println(x);
        }
    }
}

