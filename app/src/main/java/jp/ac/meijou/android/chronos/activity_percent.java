package jp.ac.meijou.android.chronos;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge; // EdgeToEdgeを使用する場合
import androidx.appcompat.app.AppCompatActivity;
// WindowInsetsCompat関連のインポートは、実際に使用する場合にのみ残します
// import androidx.core.graphics.Insets;
// import androidx.core.view.ViewCompat;
// import androidx.core.view.WindowInsetsCompat;

public class activity_percent extends AppCompatActivity { // クラス名も ActivityPercent に変更推奨

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // EdgeToEdge を有効にする場合
        setContentView(R.layout.activity_percent); // ★修正点: 正しいレイアウトファイルを指定

        // XMLの各TextViewをIDで取得
        TextView feelingPercentText = findViewById(R.id.feelingPercent);
        TextView fatiguePercentText = findViewById(R.id.fatiguePercent);
        TextView happinessPercentText = findViewById(R.id.happinessPercent);

        // データを設定
        updateCard(feelingPercentText, 85);
        updateCard(fatiguePercentText, 73);
        updateCard(happinessPercentText, 24);

        /* EdgeToEdge を有効にした場合のパディング処理の例
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> { // R.id.main はルートレイアウトのIDに置き換えてください
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        */

    }

    /**
     * パーセンテージ表示用のTextViewを更新するメソッド
     * @param textView 更新したいTextView
     * @param percentage 表示したいパーセンテージ
     */
    private void updateCard(TextView textView, int percentage) {
        // 値が0〜100の範囲に収まるように調整
        if (percentage < 0) {
            percentage = 0;
        } else if (percentage > 100) {
            percentage = 100;
        }

        textView.setText(percentage + "%");
    }
}
