package com.example.quranapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView Verse, SuraNum, VerseNum, NumAyat, NumSura;
    public Button btnSearch, gitButton;
    int SurahNumber;
    int AyatNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Verse = findViewById(R.id.Verse);
        btnSearch = findViewById(R.id.btnSearch);
        SuraNum = findViewById(R.id.SuraNum);
        VerseNum = findViewById(R.id.VerseNum);
        NumAyat = findViewById(R.id.NumAyat);
        NumSura = findViewById(R.id.NumSura);
        gitButton = findViewById(R.id.gitButton);

        gitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String url = "https://github.com/umarfaizan-dev/quran-app/commits/main";
                Uri webpage = Uri.parse(url);

                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                SurahNumber = Integer.parseInt(NumSura.getText().toString());
                AyatNumber = Integer.parseInt(NumAyat.getText().toString());

                if (SurahNumber <= 114 && SurahNumber > 0) {
                    VerseData QuranVerse = new VerseData();
                    int NoOfVerse = QuranVerse.getSurahAyatCount(SurahNumber - 1);
                    if (AyatNumber <= NoOfVerse) {
                        int FinalVerseCount = (QuranVerse.getTotalAyatCount(SurahNumber - 1)) + AyatNumber;
                        QuranArabicText FinalVerse = new QuranArabicText();
                        String FinalVerseToShow = FinalVerse.getQuranArabicText(FinalVerseCount - 1);
                        Verse.setText(FinalVerseToShow);
                    } else {
                        Verse.setText("Wrong input of Verses");
                    }

                } else {
                    Verse.setText("Working Input of Surah");
                }
            }

        });
    }
}