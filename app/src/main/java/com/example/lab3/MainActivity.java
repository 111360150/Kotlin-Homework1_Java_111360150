package com.example.lab3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText edName; // 輸入玩家姓名
    private TextView tvText, tvName, tvWinner, tvMyMora, tvTargetMora; // 顯示文字
    private Button btnMora; // 猜拳按鈕

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 設置布局文件
        // 連結xml的元件
        initViews();
        // 設定按鈕點擊事件，當按下按鈕時觸發handleMoraGame()
        btnMora.setOnClickListener(v -> handleMoraGame());
    }

    // 初始化元件
    private void initViews() {
        edName = findViewById(R.id.edName); // 連結玩家姓名輸入框
        tvText = findViewById(R.id.tvText); // 連結顯示結果的文字框
        tvName = findViewById(R.id.tvName); // 連結顯示玩家姓名的文字框
        tvWinner = findViewById(R.id.tvWinner); // 連結顯示勝利者的文字框
        tvMyMora = findViewById(R.id.tvMyMora); // 連結顯示玩家出拳的文字框
        tvTargetMora = findViewById(R.id.tvTargetMora); // 連結顯示電腦出拳的文字框
        btnMora = findViewById(R.id.btnMora); // 連結猜拳按鈕
    }

    // 處理猜拳遊戲
    private void handleMoraGame() {
        // 獲取玩家姓名
        String playerName = edName.getText().toString();
        // 檢查玩家是否輸入姓名，若沒有，顯示提示訊息並返回
        if (playerName.isEmpty()) {
            tvText.setText("請輸入玩家姓名");
            return;
        }

        // 獲取玩家的出拳選擇
        int myMora = getPlayerMora();
        // 產生隨機的電腦出拳，範圍為0~2
        int targetMora = (int) (Math.random() * 3);

        // 更新遊戲界面的顯示
        updateUI(playerName, myMora, targetMora);
        // 根據結果顯示勝負
        displayGameResult(playerName, myMora, targetMora);
    }

    // 獲取玩家選擇的出拳，並返回對應的數值
    private int getPlayerMora() {
        // 連結xml(剪刀、石頭、布的按鈕)
        RadioButton btn_scissor = findViewById(R.id.btnScissor);
        RadioButton btn_stone = findViewById(R.id.btnStone);
        RadioButton btn_paper = findViewById(R.id.btnPaper);

        // 判斷哪一個按鈕被選中，返回相應的出拳
        if (btn_scissor.isChecked()) {
            return 0; // 剪刀
        } else if (btn_stone.isChecked()) {
            return 1; // 石頭
        } else if (btn_paper.isChecked()) {
            return 2; // 布
        } else {
            return -1; // 沒有選擇
        }
    }

    // 更新遊戲界面的文字顯示
    private void updateUI(String playerName, int myMora, int targetMora) {
        // 顯示玩家姓名
        tvName.setText("名字\n" + playerName);
        // 顯示玩家的出拳
        tvMyMora.setText("我方出拳\n" + getMoraString(myMora));
        // 顯示電腦的出拳
        tvTargetMora.setText("電腦出拳\n" + getMoraString(targetMora));
    }

    // 根據玩家與電腦的出拳顯示遊戲結果
    private void displayGameResult(String playerName, int myMora, int targetMora) {
        // 如果雙方出拳一樣，則為平手
        if (myMora == targetMora) {
            tvWinner.setText("勝利者\n平手");
            tvText.setText("平局，請再試一次！");
        }
        // 判斷玩家是否勝利
        else if (isPlayerWin(myMora, targetMora)) {
            tvWinner.setText("勝利者\n" + playerName);
            tvText.setText("恭喜你獲勝了！！！");
        }
        // 若玩家沒有勝利，則電腦獲勝
        else {
            tvWinner.setText("勝利者\n電腦");
            tvText.setText("可惜，電腦獲勝了！");
        }
    }

    // 判斷玩家是否勝利，根據剪刀、石頭、布的規則
    private boolean isPlayerWin(int myMora, int targetMora) {
        return (myMora == 0 && targetMora == 2) ||
                (myMora == 1 && targetMora == 0) ||
                (myMora == 2 && targetMora == 1);
    }

    // 根據出拳數字返回對應的文字描述
    private String getMoraString(int mora) {
        switch (mora) {
            case 0:
                return "剪刀";
            case 1:
                return "石頭";
            case 2:
                return "布";
            default:
                return "未知"; // 若未選擇任何出拳，返回"未知"
        }
    }
}
