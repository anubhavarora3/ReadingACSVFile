package com.example.anubharora.readingcsvfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private List<String[]> companyDetails;
    private CompanyListAdapter companyListAdapter;
    private ListView companyList;
    private EditText searchCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        companyList = (ListView) findViewById(R.id.list);
        searchCompany = (EditText) findViewById(R.id.edt);

        InputStream inputStream = getResources().openRawResource(R.raw.companylist);
        CSVFile csvFile = new CSVFile(inputStream);
        companyDetails = csvFile.read();

        companyListAdapter = new CompanyListAdapter(this, companyDetails);
        companyList.setAdapter(companyListAdapter);
        companyListAdapter.notifyDataSetChanged();

        searchCompany.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = searchCompany.getText().toString().toLowerCase(Locale.getDefault());
                companyListAdapter.filter(text);

            }
        });
    }
}
