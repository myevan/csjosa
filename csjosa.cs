using System;
using System.Text;
using System.Text.RegularExpressions;
using System.Collections.Generic;

namespace Myevan
{
    public class Korean
    {
        public class Josa
        {
            class JosaPair
            {
                public JosaPair(string josa1, string josa2)
                {
                    this.josa1 = josa1;
                    this.josa2 = josa2;
                }

                public string josa1
                { get; private set; }

                public string josa2
                { get; private set; }
            }

            public string Replace(string src)
            {
                var strBuilder = new StringBuilder(src.Length);
                var josaMatches = _josaRegex.Matches(src);
                var lastHeadIndex = 0;
                foreach (Match josaMatch in josaMatches)
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
                strBuilder.Append(src, lastHeadIndex, src.Length - lastHeadIndex);
                return strBuilder.ToString();
            }

            static bool HasJong(char inChar)
            {
                if (inChar >= 0xAC00 && inChar <= 0xD7A3) // 가 ~ 힣
                {
                    int localCode = inChar - 0xAC00; // 가~ 이후 로컬 코드 
                    int jongCode = localCode % 28;
                    if (jongCode > 0)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }

            static bool HasJongExceptRieul(char inChar)
            {
                if (inChar >= 0xAC00 && inChar <= 0xD7A3)
                {
                    int localCode = inChar - 0xAC00;
                    int jongCode = localCode % 28;
                    if (jongCode == 8 || jongCode == 0)
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
                else
                {
                    return false;
                }
            }

            Regex _josaRegex = new Regex(@"\(이\)가|\(와\)과|\(을\)를|\(은\)는|\(아\)야|\(이\)여|\(으\)로|\(이\)라");

            Dictionary<string, JosaPair> _josaPatternPaird = new Dictionary<string, JosaPair>
            {
                { "(이)가", new JosaPair("이", "가") },
                { "(와)과", new JosaPair("과", "와") },
                { "(을)를", new JosaPair("을", "를") },
                { "(은)는", new JosaPair("은", "는") },
                { "(아)야", new JosaPair("아", "야") },
                { "(이)여", new JosaPair("이여", "여") },
                { "(으)로", new JosaPair("으로", "로") },
                { "(이)라", new JosaPair("이라", "라") },
            };

        }

        public static string ReplaceJosa(string src)
        {
            return _josa.Replace(src); 
        }

        static Josa _josa = new Josa();
    }
}
