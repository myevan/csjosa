csjosa
======

c# 한글 조사 처리입니다. 현재는 **모노**에서만 빌드 및 실행 테스트되었습니다. 

## 예제

#### 코드

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

#### 빌드

    $ make

#### 실행

    $ mono csjosa.exe 
    아노아는 자루와 오리를 칭송하고 절로 들어갔습니다.
