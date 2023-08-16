package com.arif.decorativeplant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
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

}