# ScriptCounterの詳細解説

## `AppLauncher`クラス

`AppLauncher`クラスは、アプリケーションのエントリーポイントとなるクラスです。`ScriptCounterGUI`のインスタンスを生成し、GUIを表示します。

### クラス定義
```java
public class AppLauncher {
    public static void main(String[] args) {
        ScriptCounterGUI gui = new ScriptCounterGUI();
        gui.setVisible(true);
    }
}
```

- `main`メソッド: Javaアプリケーションのエントリーポイントです。`ScriptCounterGUI`クラスのインスタンスを生成し、GUIを表示します。

## `ScriptCounter`クラス

`ScriptCounter`クラスは、文字数や単語数、改行数をカウントするためのメソッドを提供します。

### クラス定義
```java
public class ScriptCounter {
    // ここで文字数を取得
    // 引数にスクリプトを取ってそこから文字数をカウントする
    public static int count(String script) {
        return script.length();
    }

    // 改行なしの文字数を取得
    public static int countWithoutNewLine(String script) {
        return script.replace("\n", "").length();
    }

    // 改行の数を取得
    public static int countNewLine(String script) {
        return script.split("\n").length;
    }

    // 単語数を取得
    public static int countWord(String script) {
        return script.split(" ").length;
    }
}
```

- `count`メソッド: 引数として渡されたスクリプトの文字数をカウントします。
- `countWithoutNewLine`メソッド: 改行を除いた文字数をカウントします。
- `countNewLine`メソッド: 改行の数をカウントします。
- `countWord`メソッド: スクリプト内の単語数をカウントします。

## `ScriptCounterGUI`クラス

`ScriptCounterGUI`クラスは、ユーザーがスクリプトを入力し、文字数や単語数、改行数をカウントするためのGUIを提供します。

### クラス定義
```java
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
```

- コンストラクタ: GUIの基本設定を行います。
  - `super("Script Counter");`: ウィンドウのタイトルを設定します。
  - `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);`: ウィンドウを閉じたときにアプリケーションを終了します。
  - `setSize(600, 450);`: ウィンドウのサイズを設定します。
  - `setLocationRelativeTo(null);`: ウィンドウを画面の中央に配置します。
  - `setLayout(new BorderLayout());`: BorderLayoutを使用してコンポーネントを配置します。
  - `addGuiComponents();`: GUIコンポーネントを追加します。

### `addGuiComponents`メソッド
GUIコンポーネントを追加します。
```java
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
```

- `JTextArea`を使用してスクリプトの入力エリアを作成します。
- `JButton`を使用してカウントボタンとリセットボタンを作成し、それぞれのボタンにアクションリスナーを追加します。
- `JLabel`を使用して文字数、改行なし文字数、改行数、単語数を表示するラベルを作成します。
- `JCheckBox`を使用してリアルタイムで文字数をカウントするオプション

を提供し、チェックボックスの状態に応じてドキュメントリスナーを追加または削除します。

### `updateCount`メソッド
スクリプトのカウント結果を更新します。
```java
    private void updateCount(JTextArea scriptArea, JLabel countLabel, JLabel countWithoutNewLineLabel, JLabel countNewLineLabel, JLabel countWordLabel) {
        String script = scriptArea.getText();
        countLabel.setText("<html><b>文字数：<b> " + ScriptCounter.count(script) + "<html>");
        countWithoutNewLineLabel.setText("<html><b>改行なし文字数：<b> " + ScriptCounter.countWithoutNewLine(script) + "<html>");
        countNewLineLabel.setText("<html><b>改行数：<b> " + ScriptCounter.countNewLine(script) + "<html>");
        countWordLabel.setText("<html><b>単語数：<b> " + ScriptCounter.countWord(script) + "<html>");
    }
```

- `scriptArea`のテキストを取得し、各ラベルに表示するカウント結果を更新します。

### `wrapInPanel`メソッド
ラベルをパネルでラップします。
```java
    private JPanel wrapInPanel(JLabel label) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        return panel;
    }
}
```

- ラベルを左揃えで配置するためのパネルを作成し、ラベルをパネルに追加します。

---

このコードは、ユーザーがスクリプトを入力し、文字数、改行なし文字数、改行数、単語数をカウントして表示するためのアプリケーションを実装しています。`AppLauncher`クラスがGUIのエントリーポイントとなり、`ScriptCounter`クラスがカウントロジックを提供し、`ScriptCounterGUI`クラスがデータを表示します。

### 作った理由
自分がよくwebアプリでの使用が多々あったため、オフラインでも使えるようにと作成した。
