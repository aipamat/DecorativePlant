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

    public ObAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    private int titles[] = {
            R.string.title1,
            R.string.title2,
            R.string.title3
    };

    private int descriptions[] = {
            R.string.description1,
            R.string.description2,
            R.string.description3
    };

    private int images[] = {
            R.drawable.ob1,
            R.drawable.ob2,
            R.drawable.ob3
    };

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView image = v.findViewById(R.id.iv_onboarding);
        TextView title, description;
        title = v.findViewById(R.id.tv_judul);
        description = v.findViewById(R.id.tv_deskripsi);

        image.setImageResource(images[position]);
        title.setText(titles[position]);
        description.setText(descriptions[position]);

        container.addView(v);
        return v;
    }
}
