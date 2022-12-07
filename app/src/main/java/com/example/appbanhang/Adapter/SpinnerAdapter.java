package com.example.appbanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbanhang.R;
import com.example.appbanhang.model.ContryCodes;

import java.util.List;


public class SpinnerAdapter extends ArrayAdapter<ContryCodes> {

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<ContryCodes> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected, parent, false);
        TextView tvnumcontries = convertView.findViewById(R.id.tv_NumContries);
        ImageView tvflags = convertView.findViewById(R.id.tv_flags);
        ContryCodes contryCodes = this.getItem(position);
        tvnumcontries.setText(contryCodes.getNumber());
        tvflags.setImageResource(contryCodes.getImage());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item, parent, false);
        TextView numcontries = convertView.findViewById(R.id.NumContries);
        ImageView flags = convertView.findViewById(R.id.flags);
        ContryCodes contryCodes = this.getItem(position);
        numcontries.setText(contryCodes.getNumber());
        flags.setImageResource(contryCodes.getImage());
        return convertView;
    }
}
