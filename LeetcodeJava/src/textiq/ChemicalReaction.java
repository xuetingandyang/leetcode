package textiq;

// check whether the given chemical reaction is balanced
// "2H2 + O2 = 2H2O" => true
// "1000H2O = Au + Ag" => wrong
// "Hello + LaLa + HH = HH + LaLa + Hello" => true
// input: String s: a chemical reaction
// Notice: 's' is a chemical reaction,
// ("X + = Y" , "X = Y -"is not, "X = X", "X + Y = Z" is chemical reaction form)
// Elements: start from Capital letters, eg: "H2O", "Au2Fe", But not "ssU"

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChemicalReaction {
    private void countNumPerMolecule(String s, Map<String, Integer> elements) {
        s = s.trim();
        String patternStr = "([A-Z][a-z]*)([0-9]*)";
        Matcher matcher = Pattern.compile(patternStr).matcher(s);
        int multiplier = 1, end = 0;
        while (matcher.find()) {
            if (end == 0) {
                String mStr = s.substring(0, matcher.start()).trim();
                if (! mStr.isEmpty()) multiplier = Integer.parseInt(mStr);
            }

            int curNum = multiplier;
            if (! matcher.group(2).isEmpty()) {
                curNum *= Integer.parseInt(matcher.group(2));
            }
            // System.out.println(matcher.group(0));
            String eleName = matcher.group(1);
            elements.put(eleName, elements.getOrDefault(eleName, 0) + curNum);

            end = matcher.end();
        }
    }

    private Map<String, Integer> countElementNum(String s) {
        String[] molecules = s.split("\\+");
        Map<String, Integer> elements = new HashMap<>();

        for (String m : molecules) {
            countNumPerMolecule(m, elements);
        }
        return elements;
    }


    boolean isReactionBalanced(String s) {
        Map<String, Integer> left = countElementNum(s.split("=")[0]);
        Map<String, Integer> right = countElementNum(s.split("=")[1]);

        return left.equals(right);
    }

    public static void main(String[] args) {
        String s = "2H2 + O2 = 2H2O";
        System.out.println(new ChemicalReaction().isReactionBalanced(s));
    }
}

