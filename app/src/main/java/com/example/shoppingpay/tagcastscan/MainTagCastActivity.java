package com.example.shoppingpay.tagcastscan;

import static com.example.shoppingpay.R.drawable.scanfail;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import com.example.shoppingpay.R;
import com.example.shoppingpay.views.activity.choosetable.UsingTableActivity;

import java.util.List;
import java.util.Map;

import jp.tagcast.bleservice.TGCErrorCode;
import jp.tagcast.bleservice.TGCScanListener;
import jp.tagcast.bleservice.TGCState;
import jp.tagcast.bleservice.TGCType;
import jp.tagcast.bleservice.TagCast;
import jp.tagcast.helper.TGCAdapter;

public class MainTagCastActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    public TGCAdapter tgcAdapter;
    private boolean flgBeacon = false;
    private String serial, pickserial, tablenumber;
    private Map<String,String> map;
    public int mErrorDialogType = ErrorFragment.TYPE_NO;
    ImageButton btn_scan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_tagcast_main);

        Bundle bundle = getIntent().getExtras();
        pickserial = bundle.getString("seri");
        tablenumber = bundle.getString("table");

        TagCastScan();
        anhxa();
    }

    private void anhxa() {
        btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(this::OnClick);
    }

    private void OnClick(View view) {
        switch (view.getId()){
            case R.id.btn_scan: scanTagCastPaper();
        }
    }

    /**
     * ScanTagCast
     */
    private void scanTagCastPaper() {
        final Context context = getApplicationContext();
        final LoadingDialog loadingDialog = new LoadingDialog(MainTagCastActivity.this);
        btn_scan.setEnabled(false);

        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
                if(flgBeacon){
                    if(pickserial.equals(serial)){
                        Intent intent = new Intent(context, UsingTableActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("seri",serial);
                        bundle.putString("table",tablenumber);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else {
                        Toast.makeText(context, "Vui lòng nhận đúng bàn đã chọn", Toast.LENGTH_SHORT).show();
                        btn_scan.setEnabled(true);
                    }
                }else{
                    btn_scan.setBackground(getDrawable(scanfail));
                    btn_scan.setEnabled(true);
                    Toast.makeText(context, "Scan fail, please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        },10000);
    }

    private void TagCastScan() {
        final Context context = getApplicationContext();
        tgcAdapter = TGCAdapter.getInstance(context);
        tgcAdapter.prepare();
        final TGCScanListener mTGCScanListener = new TGCScanListener() {
            @Override
            public void changeState(TGCState tgcState) {

            }

            @Override
            public void didDiscoverdTagcast(TagCast tagCast) {
                if (tagCast.getTgcType() == TGCType.TGCTypePaperBeacon) {
                    flgBeacon = true;
                    map = tagCast.getDetails();
                    serial = map.get("serial_id");
                    Log.d("here", map.toString());
                }
            }

            @Override
            public void didScannedTagcasts(List<TagCast> list) {

            }

            @Override
            public void didScannedStrengthOrderTagcasts(List<TagCast> list) {

            }

            @Override
            public void didFailWithError(TGCErrorCode tgcErrorCode) {
                final FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager == null) {
                    return;
                }
                String title = null;
                String message = null;
                switch (tgcErrorCode) {
                    case TGCErrorCodeUnknown:
                        mErrorDialogType = ErrorFragment.TYPE_RETRY;
                        title = getString(R.string.unknownErrorTitle);
                        message = getString(R.string.unknownErrorMessage);
                        break;
                    case TGCErrorCodeDatabase:
                        mErrorDialogType = ErrorFragment.TYPE_RETRY;
                        title = getString(R.string.databaseErrorTitle);
                        message = getString(R.string.databaseErrorMessage);
                        break;
                    case TGCErrorCodeNetwork:
                        mErrorDialogType = ErrorFragment.TYPE_RETRY;
                        title = getString(R.string.networkErrorTitle);
                        message = getString(R.string.networkErrorMessage);
                        break;
                    case TGCErrorCodeBluetooth:
                        if (mErrorDialogType == ErrorFragment.TYPE_RETRY) {
                            return;
                        }
                        mErrorDialogType = ErrorFragment.TYPE_RETRY;
                        title = getString(R.string.bluetoothErrorTitle);
                        message = getString(R.string.bluetoothErrorMessage);
                        break;
                    case TGCErrorCodeDebugDataInvalid:
                        mErrorDialogType = ErrorFragment.TYPE_OK;
                        title = getString(R.string.databaseErrorTitle);
                        message = getString(R.string.databaseErrorMessage);
                        break;
                    case TGCErrorCodeAPIKeyNotRegistered:
                        mErrorDialogType = ErrorFragment.TYPE_OK;
                        title = getString(R.string.apiKeyNotRegisteredErrorTitle);
                        message = getString(R.string.apiKeyNotRegisteredErrorMessage);
                        break;
                    case TGCErrorCodeInvalidScanInterval:
                        mErrorDialogType = ErrorFragment.TYPE_NO;
                        break;
                    case TGCErrorCodePermissionDenied:
                        mErrorDialogType = ErrorFragment.TYPE_OK;
                        title = getString(R.string.permissionDeniedErrorTitle);
                        message = getString(R.string.permissionDeniedErrorMessage);
                        break;
                    case TGCErrorCodeMasterDataFailedUpdate:
                        mErrorDialogType = ErrorFragment.TYPE_UPDATE;
                        title = getString(R.string.networkErrorTitle);
                        message = getString(R.string.failedUpdateErrorMessage);
                        break;
                    case TGCErrorCodeLocationAccess:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (mErrorDialogType == ErrorFragment.TYPE_RETRY) {
                                return;
                            }
                            mErrorDialogType = ErrorFragment.TYPE_RETRY;
                            title = getString(R.string.localeAccessErrorTitle);
                            message = getString(R.string.localeAccessErrorMessage);
                        } else {
                            return;
                        }
                        break;
                    default:
                        break;
                }
                if (mErrorDialogType != ErrorFragment.TYPE_NO) {
                    ErrorFragment errorDialog = new ErrorFragment();
                    Bundle arguments = new Bundle();
                    arguments.putString(ErrorFragment.KEY_TITLE, title);
                    arguments.putString(ErrorFragment.KEY_MESSAGE, message);
                    arguments.putInt(ErrorFragment.KEY_TYPE, mErrorDialogType);
                    errorDialog.setArguments(arguments);
                    ErrorFragment.showDialogFragment(fragmentManager, ErrorFragment.class.getName(), errorDialog);
                }
            }
        };
        tgcAdapter.setTGCScanListener(mTGCScanListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        tgcAdapter.stopScan();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (checkPermission()) {
            tgcAdapter.setScanInterval(10000);
            tgcAdapter.startScan();;
        }
    }

    /**
     * Permission Request
     */
    private boolean checkPermission() {
        List<String> permissions = ((AppInfo) getApplication()).checkPermission();
        if (permissions.size() == 0) {
            return true;
        } else {
            try {
                String[] array = new String[permissions.size()];
                permissions.toArray(array);
                ActivityCompat.requestPermissions(MainTagCastActivity.this, array, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (permissions.length == 0 || grantResults.length == 0) {
                return;
            }
            boolean isGranted = true;
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults.length <= i || grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    isGranted = false;
                }
            }
            if (isGranted) {
                tgcAdapter.prepare();
                tgcAdapter.setScanInterval(10000);
                tgcAdapter.startScan();
            } else {
                finish();
            }
        }
    }
}
