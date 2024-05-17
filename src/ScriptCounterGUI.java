import javax.swing.*;
import java.awt.*;

public class ScriptCounterGUI extends JFrame {
    public ScriptCounterGUI(){

        super("Script Counter");
//        GUIが閉じられたら終了するように設定
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        ウィンドウのサイズを設定
        setSize(400, 300);
//        ウィンドウを中央に表示
        setLocationRelativeTo(null);
        addGuiComponents();
    }

    private void addGuiComponents(){
//        scriptを入力するためのテキストエリア
        JTextArea scriptArea = new JTextArea();
//        文字のフォントとサイズ変更
        scriptArea.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(new JScrollPane(scriptArea));


    }
}
