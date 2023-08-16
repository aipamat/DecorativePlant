package com.arif.decorativeplant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.arif.decorativeplant.R;
import com.arif.decorativeplant.onboarding.OnboardingActivity;

public class SplashScreenActivity extends AppCompatActivity {
    //    NIM : 10120211
    //    Nama : Arif Rachmat Darmawan
    //    Kelas : IF-6

    // Deklarasi waktu tampilan SplashScreen (dalam milidetik).
    private int waktu_loading = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Menggunakan Handler untuk menunda pindah ke aktivitas berikutnya.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Setelah loading selesai, akan pindah ke OnboardingActivity.
                Intent home = new Intent(SplashScreenActivity.this, OnboardingActivity.class);
                startActivity(home);
                finish(); // Menutup SplashScreenActivity agar tidak bisa kembali.

            }
        }, waktu_loading); // Menjalankan fungsi di atas setelah waktu_loading milidetik.
    }
}