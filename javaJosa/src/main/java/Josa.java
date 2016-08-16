import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * translated by dotkebi@gmail.com on 2016-08-16.
 */
public class Josa {

    String josaRegex = "(이)가|(와)과|(을)를|(은)는|(아)야|(이)여|(으)로|(이)라";

    Map<String, JosaPair> josaPatternPaird = new HashMap<>();

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
        Pattern pattern = Pattern.compile(josaRegex);
        Matcher matcher = pattern.matcher(src);
        int lastHeadIndex = 0;

        for (int i = 1; i < matcher.groupCount(); i++) {
            String group = matcher.group(i);
            JosaPair josaPair = josaPatternPaird.get(group);

            //stringBuilder.append()
        }

        /*var josaMatches = _josaRegex.Matches(src);
        var lastHeadIndex = 0;
        for (Match josaMatch in josaMatches)
        {
            var josaPair = _josaPatternPaird[josaMatch.Value];

            strBuilder.Append(src, lastHeadIndex, josaMatch.Index - lastHeadIndex);
            if (josaMatch.Index > 0)
            {
                var prevChar = src[josaMatch.Index - 1];
                if ((HasJong(prevChar) && josaMatch.Value != "(으)로") ||
                        (HasJongExceptRieul(prevChar) && josaMatch.Value == "(으)로"))
                {
                    strBuilder.Append(josaPair.josa1);
                }
                else
                {
                    strBuilder.Append(josaPair.josa2);
                }
            }
            else
            {
                strBuilder.Append(josaPair.josa1);
            }

            lastHeadIndex = josaMatch.Index + josaMatch.Length;
        }
        strBuilder.Append(src, lastHeadIndex, src.Length - lastHeadIndex);*/
        return stringBuilder.toString();
    }

    public boolean hasJong(char inChar) {
        // 가 ~ 힣
        if (inChar >= 0xAC00 && inChar <= 0xD7A3) {
            int localCode = inChar - 0xAC00; // 가~ 이후 로컬 코드
            int jongCode = localCode % 28;
            if (jongCode > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean HasJongExceptRieul(char inChar) {
        if (inChar >= 0xAC00 && inChar <= 0xD7A3) {
            int localCode = inChar - 0xAC00;
            int jongCode = localCode % 28;
            if (jongCode == 8 || jongCode == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }



}
