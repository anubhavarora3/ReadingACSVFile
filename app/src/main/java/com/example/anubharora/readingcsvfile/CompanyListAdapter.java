package com.example.anubharora.readingcsvfile;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by anubharora on 1/31/17.
 */

public class CompanyListAdapter extends BaseAdapter {
    private Activity mActivity;
    private List<String[]> companyDetails;
    private List<String[]> companyFilterDetails;

    public CompanyListAdapter(Activity mActivity, List<String[]> companyDetails) {
        this.mActivity = mActivity;
        this.companyDetails = companyDetails;
        companyFilterDetails = new ArrayList<String[]>();
        this.companyFilterDetails.addAll(companyDetails);
    }

    @Override
    public int getCount() {
        return companyDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return companyDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mActivity).inflate(R.layout.stock_list_layout, null);
        }
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView symbol = (TextView) view.findViewById(R.id.symbol);

        String Name = trimString(companyDetails.get(position)[1]);
        String Symbol = trimString(companyDetails.get(position)[0]);
        name.setText(Name);
        symbol.setText(Symbol);

        return view;
    }

    private String trimString(String s) {
        s = s.replace("\"", "");
        return s;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        companyDetails.clear();
        if (charText.length() == 0) {
            companyDetails.addAll(companyFilterDetails);
        } else {
            for (String[] wp : companyFilterDetails) {
                if (wp[1].toLowerCase(Locale.getDefault()).contains(charText)) {
                    companyDetails.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}

