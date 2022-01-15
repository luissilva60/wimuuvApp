package com.example.wimuuvapplication.UI.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wimuuvapplication.Login.MainActivity;
import com.example.wimuuvapplication.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCodeEventActivity extends AppCompatActivity {
    String text;

    ImageView QRcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_event);


        text = MainActivity.USER_ID;

        QRcode = findViewById(R.id.imageQR);

        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar2.setTitle("QRCode");
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Obter o valor através do edit text
        String sText = text.trim();
        //Inicializar a multi format writer
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            //Inicializar a bit matrix
            BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE, 400, 400);
            //inicializar a barcode encoder
            BarcodeEncoder encoder = new BarcodeEncoder();
            //Inicializar bitmap
            Bitmap bitmap = encoder.createBitmap(matrix);
            //Colocar o bitmap numa imageview
            QRcode.setImageBitmap(bitmap);
            //Inicializar o input manager
            InputMethodManager manager = (InputMethodManager)  getSystemService(
                    Context.INPUT_METHOD_SERVICE
            );
        } catch (WriterException e) {
            e.printStackTrace();
        }



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}