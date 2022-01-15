package com.example.wimuuvapplication.UI.Org;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import eu.livotov.labs.android.camview.ScannerLiveView;
import eu.livotov.labs.android.camview.scanner.decoder.zxing.ZXDecoder;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.VIBRATE;

import com.example.wimuuvapplication.Login.MainActivity;
import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.UI.Student.HistoricoDeEventosActivity;
import com.example.wimuuvapplication.UI.Student.MainActivity2;
import com.example.wimuuvapplication.downloaders.GetPersons;
import com.example.wimuuvapplication.downloaders.PostData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ReadQRCodeActivity extends AppCompatActivity {
    private ScannerLiveView camera;
    private TextView scanned;
    private JSONArray studentEvent = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_qrcode);
        Intent i = getIntent();
        String eventId = i.getStringExtra("eventId");

        Log.e("EVENT ID CHECK", eventId);



        if (checkPermission()) {
            Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

        scanned = findViewById(R.id.dadosScanner);
        camera = (ScannerLiveView) findViewById(R.id.camview);

        camera.setScannerViewEventListener(new ScannerLiveView.ScannerViewEventListener() {
            @Override
            public void onScannerStarted(ScannerLiveView scanner) {
                Toast.makeText(ReadQRCodeActivity.this, "A Iniciar Scan", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScannerStopped(ScannerLiveView scanner) {
                Toast.makeText(ReadQRCodeActivity.this, "A parar Scan", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScannerError(Throwable err) {
                Toast.makeText(ReadQRCodeActivity.this, "Erro de Scan: " + err.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeScanned(String data) {
                GetPersons getId = new GetPersons();
                Intent i2 = new Intent(getApplicationContext(), MainActivity2.class);
                scanned.setText(data);
                Log.e("Valor data", "" + data);
                try {
                    studentEvent = getId.execute("https://wimuuv.herokuapp.com/api/student_event").get();
                    JSONObject aux = new JSONObject(studentEvent.get(0).toString());
                    {
                        Map<String, String> postData = new HashMap<>();
                        postData.put("evId",eventId);
                        postData.put("entryId",data);

                        PostData task = new PostData(postData);
                        task.execute("https://wimuuv.herokuapp.com/api/student_event/add");

                        Toast.makeText(getApplicationContext(), "QRCode Checked", Toast.LENGTH_SHORT).show();

                        Log.e("Check QRCode", "" + postData.toString());
                        startActivity(i2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    studentEvent = null;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        ZXDecoder decoder = new ZXDecoder();
        // 0.5 is the area where we have
        // to place red marker for scanning.
        decoder.setScanAreaPercent(0.8);
        camera.setDecoder(decoder);
        camera.startScanner();
    }

    @Override
    protected void onPause() {
        camera.stopScanner();
        super.onPause();
    }

    private boolean checkPermission() {
        int camera_permission = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int vibrate_permission = ContextCompat.checkSelfPermission(getApplicationContext(), VIBRATE);
        return camera_permission == PackageManager.PERMISSION_GRANTED && vibrate_permission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        int PERMISSION_REQUEST_CODE = 200;
        ActivityCompat.requestPermissions(this, new String[]{CAMERA, VIBRATE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            boolean cameraaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            boolean vibrateaccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
            if (cameraaccepted && vibrateaccepted) {
                Toast.makeText(this, "Permission granted..", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denined \n You cannot use app without providing permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}