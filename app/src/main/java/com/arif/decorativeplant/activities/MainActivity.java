package com.arif.decorativeplant.activities;

import static com.arif.decorativeplant.activities.DetailActivity.setWindowFlag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import com.arif.decorativeplant.R;
import com.arif.decorativeplant.adapter.MainAdapter;
import com.arif.decorativeplant.model.ModelMain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //    NIM : 10120211
    //    Nama : Arif Rachmat Darmawan
    //    Kelas : IF-6

    // Deklarasi variabel yang akan digunakan dalam kelas ini.
    List<ModelMain> modelMain = new ArrayList<>();
    MainAdapter mainAdapter;
    RecyclerView rvListTanaman;
    SearchView searchTanaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        rvListTanaman = findViewById(R.id.rvListTanaman);
        searchTanaman = findViewById(R.id.searchTanaman);

        // Set tampilan latar belakang SearchView menjadi transparan.
        int searchPlateId = searchTanaman.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = searchTanaman.findViewById(searchPlateId);
        if (searchPlate != null) {
            searchPlate.setBackgroundColor(Color.TRANSPARENT);
        }

        // Set aksi tombol enter pada keyboard ketika SearchView diklik.
        searchTanaman.setImeOptions(EditorInfo.IME_ACTION_DONE);

        // Menangani perubahan teks pada SearchView untuk melakukan filtering pada RecyclerView.
        searchTanaman.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mainAdapter.getFilter().filter(newText);
                return true;
            }
        });

        // Konfigurasi RecyclerView.
        rvListTanaman.setLayoutManager(new LinearLayoutManager(this));
        rvListTanaman.setHasFixedSize(true);

        // Memuat data dari JSON dan menampilkannya pada RecyclerView.
        getNamaTanaman();

    }

    // Fungsi untuk memuat data tanaman dari file JSON di direktori "assets".
    private void getNamaTanaman() {
        try {
            InputStream stream = getAssets().open("tanaman_hias.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String strContent = new String(buffer, StandardCharsets.UTF_8);
            try {
                JSONObject jsonObject = new JSONObject(strContent);
                JSONArray jsonArray = jsonObject.getJSONArray("daftar_tanaman");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    ModelMain dataApi = new ModelMain();
                    dataApi.setNama(object.getString("nama"));
                    dataApi.setDeskripsi(object.getString("deskripsi"));
                    dataApi.setImage(object.getString("image_url"));
                    modelMain.add(dataApi);
                }
                mainAdapter = new MainAdapter(this, modelMain);
                rvListTanaman.setAdapter(mainAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ignored) {
            Toast.makeText(MainActivity.this, "Data tidak terbaca. " +
                    "Coba Ulangi Lagi Nanti.", Toast.LENGTH_SHORT).show();
        }
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