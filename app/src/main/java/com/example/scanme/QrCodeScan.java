package com.example.scanme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class QrCodeScan extends Fragment implements ZXingScannerView.ResultHandler {
    private static final String TAG = "HI";
    private ZXingScannerView mScannerView;
    private static final int REQUEST_CAMERA =1;

    public QrCodeScan() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(getContext());
        getActivity().setContentView(mScannerView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                Toast.makeText(getContext(), "Permission is granted", Toast.LENGTH_SHORT).show();
            }
            else {
                requestPermission();
            }
        }
    }

    private void mScannerView(FragmentActivity activity) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.qr_code_scan, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getActivity(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(getActivity(), new String[]{CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermissionsResult(int requestCode, String permission[], int grantResults[]){
        switch (requestCode){
            case REQUEST_CAMERA:
                if(grantResults.length>0){
                    boolean cameraAccepted =grantResults[0] ==PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted){
                        Toast.makeText(getActivity(), "Permission Granted ", Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(getActivity(), "Permission Denied ", Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                            if(shouldShowRequestPermissionRationale(CAMERA)){
                                displayAlertMessage("You need to allow access for both permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                            }
                                        });
                                return;

                            }
                        }
                    }
                }
                break;
        }

    }

    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener){
        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton("OK", listener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void handleResult(Result result) {
        final String scanResult = result.getText();
        AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());
        builder.setTitle("Scan Result is in the following");
        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setMessage(scanResult);
        AlertDialog alert = builder.create();
        alert.show();

    }}