csjosa
======

c# 한글 조사 처리입니다. 모노와 VS2013에서 빌드 및 실행 테스트되었습니다. 

## 예제

#### 코드

    using System;

    namespace Myevan
    {
        class JosaConsole
        {
            public static void Main()
            {
                Console.WriteLine(Korean.ReplaceJosa("네(이)가 잘못했어. 확률(이)가 이상해. 덫(이)가 깔렸어."));
                Console.WriteLine(Korean.ReplaceJosa("너(와)과 함께 할게. 글(와)과 그림. 빛(와)과 어둠."));
                Console.WriteLine(Korean.ReplaceJosa("수녀(을)를 존경했어. 남자들(을)를 입히다. 버튼(을)를 만지지 마."));
                Console.WriteLine(Korean.ReplaceJosa("우리(은)는 끝이야. 쌀(은)는 필요없어. 갑옷(은)는 찢었다."));
                Console.WriteLine(Korean.ReplaceJosa("진우(아)야, 그것도 몰라? 경렬(아)야, 진정해. 상현(아)야, 뭐해?"));
                Console.WriteLine(Korean.ReplaceJosa("진우(이)여. 닥쳐라. 경렬(이)여. 이리 오라. 상현(이)여. 아무 일도 아니다."));
                Console.WriteLine(Korean.ReplaceJosa("부두(으)로 가야 해. 대궐(으)로 가거나. 집(으)로 갈래?"));
                Console.WriteLine(Korean.ReplaceJosa("나(이)라고 어쩔 수 있겠니? 별(이)라고 불러줘. 라면(이)라고 했잖아."));
                Console.ReadKey();
            }
        }
    }

#### 빌드

    $ make

#### 실행

    $ mono csjosa.exe 
    네가 잘못했어. 확률이 이상해. 덫이 깔렸어.
    너와 함께 할게. 글과 그림. 빛과 어둠.
    수녀를 존경했어. 남자들을 입히다. 버튼을 만지지 마.
    우리는 끝이야. 쌀은 필요없어. 갑옷은 찢었다.
    진우야, 그것도 몰라? 경렬아, 진정해. 상현아, 뭐해?
    진우여, 닥쳐라. 경렬이여, 이리 오라. 상현이여, 아무 일도 아니다.
    부두로 가야 해. 대궐로 가거나. 집으로 갈래?
    나라고 어쩔 수 있겠니? 별이라고 불러줘. 라면이라고 했잖아.
