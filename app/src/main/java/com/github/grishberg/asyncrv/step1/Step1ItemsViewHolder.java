package com.github.grishberg.asyncrv.step1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.grishberg.asyncrv.R;

class Step1ItemsViewHolder extends ItemsViewHolder {
    private final TextView textView;
    private final ImageView imageView;

    public Step1ItemsViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        textView = itemView.findViewById(R.id.text);
    }

    @Override
    public void bind(Item i) {
        textView.setText(i.text);
        imageView.setImageDrawable(itemView.getContext().getResources().getDrawable(i.iconRes));
    }
}
