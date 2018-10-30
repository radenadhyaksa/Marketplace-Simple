package com.androidtutorialshub.loginregister.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidtutorialshub.loginregister.R;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    EditText mTextNama;
    EditText mTextAlamat;
    EditText mTextNote;
    TextView mTextHarga, mTextQty;
    Button mButtonOrder, mButtonPlus, mButtonMin;
    RadioGroup mRadioGroup;
    RadioButton mRadioItem1, mRadioItem2, mRadioItem3;
    String item = null;
    Context mContext;
    Spinner mSpinnerMenu;
    List<String> mListMenu = new ArrayList<>();
    int harga = 0;
    int qty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sms);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext = getApplicationContext();
        mTextNama = (EditText) findViewById(R.id.mTextNama);
        mTextAlamat = (EditText) findViewById(R.id.mTextAlamat);
        mTextNote = (EditText) findViewById(R.id.mTextNote);
        mTextHarga = (TextView) findViewById(R.id.mTextHarga);
        mButtonOrder = (Button) findViewById(R.id.mButtonOrder);
        mTextQty = (TextView) findViewById(R.id.mTextQty);
        mButtonPlus = (Button) findViewById(R.id.mButtonPlus);
        mButtonMin = (Button) findViewById(R.id.mButtonMin);
        // radio
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadioItem1 = (RadioButton) findViewById(R.id.mRadioItem1);
        mRadioItem2 = (RadioButton) findViewById(R.id.mRadioItem2);
        mRadioItem3 = (RadioButton) findViewById(R.id.mRadioItem3);
        // spinner
        mSpinnerMenu = (Spinner) findViewById(R.id.mSpinnerMenu);
        mListMenu.add("Beef Burger");
        mListMenu.add("Chicken Sandwich");
        mListMenu.add("Ice Cappucino Blend");
        mListMenu.add("French Fries");
        mListMenu.add("Crispy Sausage");
        mListMenu.add("Wedang Jahe Mantap");
        mListMenu.add("RotBak With Cheese");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mListMenu);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerMenu.setAdapter(dataAdapter);

    }

    public void onClickOrder(View view) {
        switch (mRadioGroup.getCheckedRadioButtonId()) {
            case R.id.mRadioItem1:
                item = mRadioItem1.getText().toString();
                break;
            case R.id.mRadioItem2:
                item = mRadioItem2.getText().toString();
                break;
            case R.id.mRadioItem3:
                item = mRadioItem3.getText().toString();
                break;
        }

        String to = "anwarnur.f@gmail.com";
        String subject = mTextNama.getText().toString();
        String message = "Atas Nama : "
                +mTextNama.getText()
                + " | "
                +mTextAlamat.getText()
                + " | "
                +mTextNote.getText()
                + " | "
                + mSpinnerMenu.getSelectedItem()
                + " sebanyak "
                + mTextQty.getText()
                + " box, seharga "
                + mTextHarga.getText()
                + ", dengan metode pengiriman: "
                + item;

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Kirim email dengan"));
    }

    public void onClickPlus(View view) {
        harga = harga + 5000;
        qty = qty + 1;
        mTextQty.setText(qty + "");
        mTextHarga.setText("Rp" + harga);
    }

    public void onClickMin(View view) {
        if (harga != 0) {
            harga = harga - 5000;
            qty = qty - 1;
            mTextQty.setText(qty + "");
            mTextHarga.setText("Rp" + harga);
        } else {
            harga = 0;
            qty = 0;
            mTextQty.setText(qty + "");
            mTextHarga.setText("Rp" + harga);
        }
    }

    public void onClickReset(View v) {
        harga = 0;
        qty = 0;
        mTextNama.setText("");
        mTextAlamat.setText("");
        mTextNote.setText("");
        mTextHarga.setText("Rp" + harga);
        mTextQty.setText(qty + "");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
