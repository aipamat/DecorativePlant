package com.arif.decorativeplant.onboarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.arif.decorativeplant.R;

public class ObAdapter extends PagerAdapter {
    //    NIM : 10120211
    //    Nama : Arif Rachmat Darmawan
    //    Kelas : IF-6

    private Context context;
    private LayoutInflater layoutInflater;

    // Konstruktor untuk inisialisasi adapter dengan konteks.
    public ObAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    // Array yang berisi judul dari setiap slide pada onboarding.
    private int titles[] = {
            R.string.title1,
            R.string.title2,
            R.string.title3
    };

    // Array yang berisi deskripsi dari setiap slide pada onboarding.
    private int descriptions[] = {
            R.string.description1,
            R.string.description2,
            R.string.description3
    };

    // Array yang berisi ID gambar untuk setiap slide pada onboarding.
    private int images[] = {
            R.drawable.ob1,
            R.drawable.ob2,
            R.drawable.ob3
    };

    // Mendapatkan jumlah total slide dalam onboarding.
    @Override
    public int getCount() {
        return titles.length;
    }

    // Mengecek apakah view yang di-passed adalah instance dari object yang sesuai.
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    // Menghapus view pada position tertentu dari container.
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }

    // Meng-inflate dan mengatur data untuk view pada position tertentu.
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = layoutInflater.inflate(R.layout.slider_layout, container, false);

        // Menghubungkan elemen UI dengan variabel.
        ImageView image = v.findViewById(R.id.iv_onboarding);
        TextView title, description;
        title = v.findViewById(R.id.tv_judul);
        description = v.findViewById(R.id.tv_deskripsi);

        // Mengatur data dari array ke elemen UI.
        image.setImageResource(images[position]);
        title.setText(titles[position]);
        description.setText(descriptions[position]);

        // Menambahkan view ke container dan mengembalikan view.
        container.addView(v);
        return v;
    }
}
