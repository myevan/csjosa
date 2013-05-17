using System;

namespace Myevan
{
    class JosaConsole
    {
        public static void Main()
        {
            string src = "아노아(은)는 자루(와)과 오리(을)를 칭송하고 절(으)로 들어갔습니다.";
            Console.WriteLine(Korean.ReplaceJosa(src));
        }
    }
}
