package com.example.lab3
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.lab3.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //將變數與 XML 元件綁定
        val input_name = findViewById<EditText>(R.id.input_name)
        val text_show = findViewById<TextView>(R.id.text_show)
        val btn_sci = findViewById<RadioButton>(R.id.btn_sci)
        val btn_rock = findViewById<RadioButton>(R.id.btn_rock)
        val btn_pa = findViewById<RadioButton>(R.id.btn_pa)
        val button = findViewById<Button>(R.id.button)
        val text_name = findViewById<TextView>(R.id.text_name)
        val text_win = findViewById<TextView>(R.id.text_win)
        val text_myturn = findViewById<TextView>(R.id.text_myturn)
        val text_comturn = findViewById<TextView>(R.id.text_comturn)
        button.setOnClickListener {
            //判斷 EditText 的字數是否小於一，若成立則無法進行猜拳
            if (input_name.length() < 1) {
                text_show.text = "請輸入玩家姓名"
                return@setOnClickListener
            }
            //取出 EditText 文字作為玩家姓名並用變數儲存
            val playerName = input_name.text
            //亂數產生介於 0~1 之間不含 1 的小數，乘 3 變成 0~2 作為電腦的出拳
            val comMora = (Math.random() * 3).toInt()
            //將玩家出拳結果對應成字串並用變數儲存
            val playerMoraText = when {
                btn_sci.isChecked -> "剪刀"
                btn_rock.isChecked -> "石頭"
                else -> "布"
            }
            //將電腦出拳結果對應成字串並用變數儲存
            val comMoraText = when(comMora) {
                0 -> "剪刀"
                1 -> "石頭"
                else -> "布"
            }
            //顯示玩家姓名與雙方出拳結果
            text_name.text = "名字\n$playerName"
            text_myturn.text = "我方出拳\n$playerMoraText"
            text_comturn.text = "電腦出拳\n$comMoraText"
            //用三個判斷式決定勝負並顯示猜拳結果
            when {
                btn_sci.isChecked && comMora == 2 ||
                        btn_rock.isChecked && comMora == 0 ||
                        btn_pa.isChecked && comMora == 1 -> {
                    text_win.text = "勝利者\n$playerName"
                    text_show.text = "你贏了！！！"
                }
                btn_sci.isChecked && comMora == 1 ||
                        btn_rock.isChecked && comMora == 2 ||
                        btn_pa.isChecked && comMora == 0 -> {
                    text_win.text = "勝利者\n 電腦"
                    text_show.text = "輸惹......"
                }
                else -> {
                    text_win.text = "勝利者\n 平手"
                    text_show.text = "平手~再來一局？"
                }
            }
        }
    }
}
