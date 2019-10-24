package ac.id.uts.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ac.id.uts.R;
import ac.id.uts.models.transaksi;

public class CobaActivity extends AppCompatActivity {

    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btSubmit;
    private EditText etnama;
    private EditText etharga;
    private EditText etuang;
    private EditText etkembalian;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba);

        // inisialisasi fields EditText dan Button
        etnama = (EditText) findViewById(R.id.et_nama);
        etharga = (EditText) findViewById(R.id.et_harga);
        etuang = (EditText) findViewById(R.id.et_uang);
        etkembalian = (EditText) findViewById(R.id.et_kemablian);
        btSubmit = (Button) findViewById(R.id.bt_submit);

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        // kode yang dipanggil ketika tombol Submit diklik
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(etnama.getText().toString()) && !isEmpty(etharga.getText().toString()) && !isEmpty(etuang.getText().toString())&& !isEmpty(etkembalian.getText().toString()))
                    submitTransaksi(new transaksi(etnama.getText().toString(), etharga.getText().toString(), etuang.getText().toString(), etkembalian.getText().toString()));
                else
                    Snackbar.make(findViewById(R.id.bt_submit), "Data barang tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        etnama.getWindowToken(), 0);
            }
        });

    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void updateBarang(transaksi transaksi) {
        // kodingan untuk next tutorial ya :p
    }

    private void submitTransaksi(transaksi transaksi) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("transaksi").push().setValue(transaksi).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etnama.setText("");
                etharga.setText("");
                etuang.setText("");
                etuang.setText("");
                Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, CobaActivity.class);
    }
}

