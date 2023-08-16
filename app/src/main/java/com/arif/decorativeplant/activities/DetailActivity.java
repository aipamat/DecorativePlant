package com.arif.decorativeplant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.arif.decorativeplant.R;
import com.arif.decorativeplant.model.ModelMain;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    //    NIM : 10120211
    //    Nama : Arif Rachmat Darmawan
    //    Kelas : IF-6

    // Deklarasi variabel konstanta untuk kunci data yang akan dikirim melalui intent.
    public static final String DETAIL_TANAMAN = "DETAIL_TANAMAN";

    // Deklarasi variabel-variabel yang akan digunakan dalam kelas ini.
    String strNamaTanaman, strManfaatTanaman;
    ModelMain modelMain;
    Toolbar toolbar;
    ImageView imageTanaman;
    TextView tvNamaTanaman, tvManfaatTanaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Set status bar menjadi transparan pada Android Marshmallow (API level 23) ke atas.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        // Set status bar transparan pada Android Lollipop (API level 21) ke atas.
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        // Inisialisasi elemen-elemen UI.
        toolbar = findViewById(R.id.toolbar);
        imageTanaman = findViewById(R.id.imageTanaman);
        tvNamaTanaman = findViewById(R.id.tvNamaTanaman);
        tvManfaatTanaman = findViewById(R.id.tvManfaatTanaman);

        // Set toolbar sebagai action bar dan konfigurasikan tampilan action bar.
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Mendapatkan data yang dikirim melalui intent dan menampilkan informasi pada UI.
        modelMain = (ModelMain) getIntent().getSerializableExtra(DETAIL_TANAMAN);
        if (modelMain != null) {
            strNamaTanaman = modelMain.getNama();
            strManfaatTanaman = modelMain.getDeskripsi();

            // Menggunakan Glide untuk memuat gambar dari URL ke ImageView.
            Glide.with(this)
                    .load(modelMain.getImage())
                    .into(imageTanaman);
            // Menampilkan nama tanaman dan manfaatnya pada TextView.
            tvNamaTanaman.setText(strNamaTanaman);
            tvManfaatTanaman.setText(strManfaatTanaman);
        }

    }

    // Fungsi ini akan dipanggil ketika salah satu item di action bar diklik.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Mengakhiri aktivitas saat tombol back di action bar ditekan.
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Fungsi ini digunakan untuk mengatur beberapa flag pada window.
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits; // Mengatur bit flag jika on (true).
        } else {
            layoutParams.flags &= ~bits; // Menghapus bit flag jika off (false).
        }
        window.setAttributes(layoutParams);
    }

}