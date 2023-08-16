package com.arif.decorativeplant.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.arif.decorativeplant.R;
import com.arif.decorativeplant.activities.MainActivity;

public class OnboardingActivity extends AppCompatActivity {
    //    NIM : 10120211
    //    Nama : Arif Rachmat Darmawan
    //    Kelas : IF-6

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private SaveState saveState;
    private Button mulai;

    // Mengatur layar menjadi fullscreen.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // Inisialisasi elemen UI.
        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.ll_dotsLayout);
        mulai = findViewById(R.id.btn_mulai);

        // Listener untuk tombol "Mulai" yang akan mengarahkan ke MainActivity.
        mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveState.setState(1);
                Intent intent = new Intent(OnboardingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Listener untuk tombol "Skip" yang akan langsung menggeser ViewPager ke halaman terakhir.
        TextView skip = findViewById(R.id.tv_skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3, true);
            }
        });


        // Mengecek apakah pengguna sudah melewati onboarding sebelumnya.
        saveState = new SaveState(this, "ob");
        if (saveState.getState() == 1) {
            startActivity(new Intent(OnboardingActivity.this, MainActivity.class));
            finish();
        }

        // Inisialisasi adapter dan ViewPager.
        ObAdapter adapter = new ObAdapter(this);
        viewPager.setAdapter(adapter);

        // Listener untuk perubahan halaman pada ViewPager.
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            // Mengatur tampilan tombol "Mulai" dan "Skip" serta indikator titik-titik.
            @Override
            public void onPageSelected(int position) {
                dotsFunction(position);
                if (position < 2) {
                    mulai.setVisibility(View.INVISIBLE);
                } else {
                    mulai.setVisibility(View.VISIBLE);
                }

                if (position > 0) {
                    skip.setVisibility(View.INVISIBLE);
                } else {
                    skip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Menampilkan titik-titik indikator pada halaman pertama.
        dotsFunction(0);
    }

    // Fungsi untuk mengatur tampilan titik-titik indikator berdasarkan halaman saat ini.
    private void dotsFunction(int pos) {
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&bull"));
            dots[i].setTextColor(getColor(R.color.white));  // Warna titik-titik yang tidak dipilih.
            dots[i].setTextSize(40);
            dots[i].setPadding(10, 0, 10, 0);

            dotsLayout.addView(dots[i]);

        }

        if (dots.length > 0) {
            dots[pos].setTextColor(getColor(R.color.bg_pink));   // Warna titik-titik yang dipilih.
            dots[pos].setTextSize(40);  // Ukuran titik-titik yang dipilih.
        }
    }
}