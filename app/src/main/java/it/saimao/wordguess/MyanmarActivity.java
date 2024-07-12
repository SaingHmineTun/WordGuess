package it.saimao.wordguess;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyanmarActivity extends AppCompatActivity {

    private String[] guessWords = {
            "မုံရွာ", "ပြင်ဦးလွင်", "ရန်ကုန်", "စစ်ကိုင်း", "ဟားခါး", "တောင်ကြီး", "လားရှိုး", "မူဆယ်"
    };

    private TextView tv;
    private String randomWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv_word_guess);
        setRandomWordToTextView();

        EditText et = findViewById(R.id.et_enter_your_guess);

        Button bt = findViewById(R.id.bt_guess);
        bt.setOnClickListener(v -> {
            String myGuess = et.getText().toString();
            if (myGuess.equals(randomWord)) {
                Toast.makeText(this, "You guess right", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
            }
            setRandomWordToTextView();
            et.getText().clear();
        });
    }

    /*
    DRY - Don't Repeat Yourself
     */
    private void setRandomWordToTextView() {
        randomWord = getRandomWord();
        tv.setText(shuffleWord(randomWord));
    }

    private String shuffleWord(String randomWord) {
        // String to List of string
        SyllableSegmentation seg = new SyllableSegmentation();
        List<String> wordList = seg.segment(randomWord);
        Collections.shuffle(wordList);

        // List to String
        String value = "";
        for (String word : wordList) {
            value += word;
        }
        return value;
    }

    private String getRandomWord() {
        Random random = new Random();
        int index = random.nextInt(guessWords.length);
        return guessWords[index];
    }

}