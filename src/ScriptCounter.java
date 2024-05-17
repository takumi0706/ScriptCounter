public class ScriptCounter {
//    ここで文字数を取得
//    引数にスクリプトを取ってそこから文字数をカウントする
    public static int count(String script) {
        return script.length();
    }

//    改行なしの文字数を取得
    public static int countWithoutNewLine(String script) {
        return script.replace("\n", "").length();
    }

//    改行の数を取得
    public static int countNewLine(String script) {
        return script.split("\n").length;
    }

//    単語数を取得
    public static int countWord(String script) {
        return script.split(" ").length;
    }
}
