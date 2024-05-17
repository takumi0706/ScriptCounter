import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ScriptCounterGUI extends JFrame {
    private JLabel countLabel;
    private JLabel countWithoutNewLineLabel;
    private JLabel countNewLineLabel;
    private JLabel countWordLabel;

    public ScriptCounterGUI() {
        super("Script Counter");
        // GUIが閉じられたら終了するように設定
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ウィンドウのサイズを設定
        setSize(600, 450);
        // ウィンドウを中央に表示
        setLocationRelativeTo(null);

        // BorderLayoutを使って配置
        setLayout(new BorderLayout());
        addGuiComponents();
    }

    private void addGuiComponents() {
        // scriptを入力するためのテキストエリア
        JTextArea scriptArea = new JTextArea();
        // 文字のフォントとサイズ変更
        scriptArea.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(new JScrollPane(scriptArea), BorderLayout.CENTER);

        // ラベルを格納するパネルを作成
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        // ボタンを格納するパネルを作成
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 中央揃え

        // カウントボタンを作成
        JButton countButton = new JButton("カウント");
        countButton.setFont(new Font("Dialog", Font.PLAIN, 24));
        buttonPanel.add(countButton);
        countButton.addActionListener(e -> {
            if (scriptArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "スクリプトが入力されていません", "エラー", JOptionPane.ERROR_MESSAGE);
            } else {
                updateCount(scriptArea, countLabel, countWithoutNewLineLabel, countNewLineLabel, countWordLabel);
            }
        });

        // リセットボタンを作成
        JButton resetScriptButton = new JButton("リセット");
        resetScriptButton.setFont(new Font("Dialog", Font.PLAIN, 24));
        buttonPanel.add(resetScriptButton);
        resetScriptButton.addActionListener(e -> {
            scriptArea.setText("");
            countLabel.setText("<html><b>文字数：<b> <html>");
            countWithoutNewLineLabel.setText("<html><b>改行なし文字数：<b> <html>");
            countNewLineLabel.setText("<html><b>改行数：<b> <html>");
            countWordLabel.setText("<html><b>単語数：<b> <html>");
        });

        labelPanel.add(buttonPanel);

        // 文字数表示用のラベル
        countLabel = new JLabel("<html><b>文字数：<b> <html>");
        countLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
        countLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // 左揃え
        labelPanel.add(wrapInPanel(countLabel));

        // 改行なしの文字数表示用のラベル
        countWithoutNewLineLabel = new JLabel("<html><b>改行なし文字数：<b> <html>");
        countWithoutNewLineLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
        countWithoutNewLineLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // 左揃え
        labelPanel.add(wrapInPanel(countWithoutNewLineLabel));

        // 改行数表示用のラベル
        countNewLineLabel = new JLabel("<html><b>改行数：<b> <html>");
        countNewLineLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
        countNewLineLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // 左揃え
        labelPanel.add(wrapInPanel(countNewLineLabel));

        // 単語数表示用のラベル
        countWordLabel = new JLabel("<html><b>単語数：<b> <html>");
        countWordLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
        countWordLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // 左揃え
        labelPanel.add(wrapInPanel(countWordLabel));

        add(labelPanel, BorderLayout.SOUTH);

        // リアルタイムで文字数をカウントするチェックボックス
        JCheckBox realtimeCountCheckBox = new JCheckBox("リアルタイムで文字数をカウント");
        realtimeCountCheckBox.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(realtimeCountCheckBox, BorderLayout.NORTH);

        // DocumentListenerの定義
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCount(scriptArea, countLabel, countWithoutNewLineLabel, countNewLineLabel, countWordLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCount(scriptArea, countLabel, countWithoutNewLineLabel, countNewLineLabel, countWordLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCount(scriptArea, countLabel, countWithoutNewLineLabel, countNewLineLabel, countWordLabel);
            }
        };

        // チェックボックスのアクションリスナー
        realtimeCountCheckBox.addActionListener(e -> {
            if (realtimeCountCheckBox.isSelected()) {
                scriptArea.getDocument().addDocumentListener(documentListener);
            } else {
                scriptArea.getDocument().removeDocumentListener(documentListener);
            }
        });
    }

    private void updateCount(JTextArea scriptArea, JLabel countLabel, JLabel countWithoutNewLineLabel, JLabel countNewLineLabel, JLabel countWordLabel) {
        String script = scriptArea.getText();
        countLabel.setText("<html><b>文字数：<b> " + ScriptCounter.count(script) + "<html>");
        countWithoutNewLineLabel.setText("<html><b>改行なし文字数：<b> " + ScriptCounter.countWithoutNewLine(script) + "<html>");
        countNewLineLabel.setText("<html><b>改行数：<b> " + ScriptCounter.countNewLine(script) + "<html>");
        countWordLabel.setText("<html><b>単語数：<b> " + ScriptCounter.countWord(script) + "<html>");
    }

    private JPanel wrapInPanel(JLabel label) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        return panel;
    }
}

