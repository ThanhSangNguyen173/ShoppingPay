package com.example.shoppingpay.tagcastscan;

import static com.example.shoppingpay.R.drawable.scanfail;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import com.example.shoppingpay.R;
import com.example.shoppingpay.views.MainShoppingActivity;

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
    private String serial;
    private Map<String,String> map;
    public int mErrorDialogType = ErrorFragment.TYPE_NO;
    ImageButton btn_scan;
    ProgressBar bar;
    ImageView img_checkin_load;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setContentView(R.layout.activity_tagcast_main);
        btn_scan = findViewById(R.id.btn_scan);
        bar = findViewById(R.id.progressBar);
        img_checkin_load = findViewById(R.id.img_checkin_load);
        Animation alpha = AnimationUtils.loadAnimation(this,R.anim.alpha);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_scan.setEnabled(false);
                bar.setVisibility(View.VISIBLE);
                tgcAdapter.setScanInterval(10000);
                tgcAdapter.startScan();
                img_checkin_load.startAnimation(alpha);
                img_checkin_load.setVisibility(View.VISIBLE);

                int tensec= 10 * 1000;
                new CountDownTimer(tensec, 1000) {

                    public void onTick(long millisUntilFinished) {
                        long finishedSeconds = tensec- millisUntilFinished+2000;
                        int total = (int) ((finishedSeconds / (float)tensec) * 100.0);
                        bar.setProgress(total);
                    }

                    public void onFinish() {
                        img_checkin_load.setVisibility(View.INVISIBLE);
                        bar.setVisibility(View.INVISIBLE);
                        if(flgBeacon){
                            Toast.makeText(context, "Scan Succesfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainShoppingActivity.class);
                    startActivity(intent);

                    Intent intent1 = new Intent(context, MainPaymentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("seri",serial);
                    intent1.putExtras(bundle);

                        }else{
                            btn_scan.setBackground(getDrawable(scanfail));
                            btn_scan.setEnabled(true);
                            Toast.makeText(context, "Scan fail, please try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.start();
            }
        });

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
            tgcAdapter.prepare();
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
