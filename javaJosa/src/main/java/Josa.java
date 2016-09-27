import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * translated by dotkebi@gmail.com on 2016-08-16.
 */
public class Josa {

    private Map<String, JosaPair> josaPatternPaird = new HashMap<>();

    public Josa() {
        josaPatternPaird.put("(이)가", new JosaPair("이", "가"));
        josaPatternPaird.put("(와)과", new JosaPair("과", "와"));
        josaPatternPaird.put("(을)를", new JosaPair("을", "를"));
        josaPatternPaird.put("(은)는", new JosaPair("은", "는"));
        josaPatternPaird.put("(아)야", new JosaPair("아", "야"));
        josaPatternPaird.put("(이)여", new JosaPair("이여", "여"));
        josaPatternPaird.put("(으)로", new JosaPair("으로", "로"));
        josaPatternPaird.put("(이)라", new JosaPair("이라", "라"));
    }

    public String replace(String src) {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile("\\(이\\)가|\\(와\\)과|\\(을\\)를|\\(은\\)는|\\(아\\)야|\\(이\\)여|\\(으\\)로|\\(이\\)라");
        Matcher matcher = pattern.matcher(src);
        int lastHeadIndex = 0;

        while (matcher.find()) {
            String group = matcher.group();
            JosaPair josaPair = josaPatternPaird.get(group);
            int endIndexOfBefore = matcher.end();
            String before = src.substring(lastHeadIndex, endIndexOfBefore);

            stringBuilder.append(before.replace(group, ""));

            String josa = josaPair.getJosa1();
            if (matcher.start() > 0) {
                char prevChar = src.substring(matcher.start() - 1, endIndexOfBefore).charAt(0);
                if (!((hasJong(prevChar) && !group.equals("(으)로")) ||
                        (hasJongExceptRieul(prevChar) && group.equals("(으)로")))) {
                    josa = josaPair.getJosa2();
                }
            }
            stringBuilder.append(josa);
            lastHeadIndex = endIndexOfBefore;
        }
        stringBuilder.append(src.substring(lastHeadIndex, src.length()));
        return stringBuilder.toString();
    }

    private boolean hasJong(char inChar) {
        // 가 ~ 힣
        if (inChar >= 0xAC00 && inChar <= 0xD7A3) {
            int localCode = inChar - 0xAC00; // 가~ 이후 로컬 코드
            int jongCode = localCode % 28;
            return jongCode > 0;
        }
        return false;
    }

    private boolean hasJongExceptRieul(char inChar) {
        if (inChar >= 0xAC00 && inChar <= 0xD7A3) {
            int localCode = inChar - 0xAC00;
            int jongCode = localCode % 28;
            return !(jongCode == 8 || jongCode == 0);
        }
        return false;
    }

}
