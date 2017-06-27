package com.userdetails.forms.view.scan_barcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;
import com.userdetails.forms.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanBarcodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {

        Toast.makeText(getApplicationContext(), result.getText().toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), result.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }
}
