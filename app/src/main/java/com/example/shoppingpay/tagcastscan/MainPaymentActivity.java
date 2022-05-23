package com.example.shoppingpay.tagcastscan;

import static com.example.shoppingpay.R.drawable.scan;
import static com.example.shoppingpay.R.drawable.scanfail;
import static com.example.shoppingpay.R.drawable.scanpayment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import com.example.shoppingpay.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import jp.tagcast.bleservice.TGCErrorCode;
import jp.tagcast.bleservice.TGCScanListener;
import jp.tagcast.bleservice.TGCState;
import jp.tagcast.bleservice.TGCType;
import jp.tagcast.bleservice.TagCast;
import jp.tagcast.helper.TGCAdapter;

public class MainPaymentActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    ImageButton btn_scan;
    TextView txt_payment_status;
    ProgressBar bar;
    ImageView img_checkin_load;
    public TGCAdapter tgcAdapter;
    private boolean flgBeacon = true;
    private String serial,serial2, tablenumber, timeout, timein;
    private Map<String,String> map;
    int bill_id;
    public int mErrorDialogType = ErrorFragmentPayment.TYPE_NO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_payment_main);


        Bundle bundle = getIntent().getExtras();
        serial = bundle.getString("seri");
        tablenumber = bundle.getString("table");
        timein = bundle.getString("timein");
        bill_id = bundle.getInt("bill_id");

        anhxa();
        TagCastScan();

    }

    private void anhxa() {
        bar = findViewById(R.id.progressBar2);
        img_checkin_load = findViewById(R.id.img_checkin_load2);
        btn_scan = findViewById(R.id.btn_scan2);
        txt_payment_status = findViewById(R.id.txt_payment_status);
        btn_scan.setOnClickListener(this::OnClick);
    }

    private void OnClick(View view) {
        switch (view.getId()){
            case R.id.btn_scan2:scanTagCastPaper();
        }
    }

    /**
     * ScanTagCast
     */
    private void scanTagCastPaper() {
        final Context context = getApplicationContext();

        Animation alpha = AnimationUtils.loadAnimation(context,R.anim.alpha);
        btn_scan.setEnabled(false);
        btn_scan.setBackground(getDrawable(scanpayment));
        bar.setVisibility(View.VISIBLE);

        tgcAdapter.setScanInterval(10000);
        tgcAdapter.startScan();

        img_checkin_load.startAnimation(alpha);
        img_checkin_load.setVisibility(View.VISIBLE);
        txt_payment_status.setVisibility(View.VISIBLE);
        txt_payment_status.setText("Infomation check...");
        txt_payment_status.startAnimation(alpha);

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
                    if(serial.equals(serial)){
                        timeout();
                        Intent payment = new Intent(context,PaymentAcceptAnimation.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("table",tablenumber);
                        bundle.putString("timeout",timeout);
                        bundle.putString("timein",timein);
                        bundle.putInt("bill_id",bill_id);
                        payment.putExtras(bundle);
                        startActivity(payment);
                    }else{
                        btn_scan.setEnabled(true);
                        btn_scan.setBackground(getDrawable(scan));
                        txt_payment_status.setVisibility(View.VISIBLE);
                        txt_payment_status.setText("Vui lòng kiểm tra vị trí bàn thanh toán và thử lại");
                    }
                }else{
                    btn_scan.setBackground(getDrawable(scanfail));
                    btn_scan.setEnabled(true);
                    txt_payment_status.setVisibility(View.VISIBLE);
                    txt_payment_status.setText("Scan fail, please try again!");
                }
            }
        }.start();
    }

    private void timeout() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = Calendar.getInstance().getTime();
        timeout = formatter.format(date);
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
                    serial2 = map.get("serial_id");
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
                        mErrorDialogType = ErrorFragmentPayment.TYPE_RETRY;
                        title = getString(R.string.unknownErrorTitle);
                        message = getString(R.string.unknownErrorMessage);
                        break;
                    case TGCErrorCodeDatabase:
                        mErrorDialogType = ErrorFragmentPayment.TYPE_RETRY;
                        title = getString(R.string.databaseErrorTitle);
                        message = getString(R.string.databaseErrorMessage);
                        break;
                    case TGCErrorCodeNetwork:
                        mErrorDialogType = ErrorFragmentPayment.TYPE_RETRY;
                        title = getString(R.string.networkErrorTitle);
                        message = getString(R.string.networkErrorMessage);
                        break;
                    case TGCErrorCodeBluetooth:
                        if (mErrorDialogType == ErrorFragmentPayment.TYPE_RETRY) {
                            return;
                        }
                        mErrorDialogType = ErrorFragmentPayment.TYPE_RETRY;
                        title = getString(R.string.bluetoothErrorTitle);
                        message = getString(R.string.bluetoothErrorMessage);
                        break;
                    case TGCErrorCodeDebugDataInvalid:
                        mErrorDialogType = ErrorFragmentPayment.TYPE_OK;
                        title = getString(R.string.databaseErrorTitle);
                        message = getString(R.string.databaseErrorMessage);
                        break;
                    case TGCErrorCodeAPIKeyNotRegistered:
                        mErrorDialogType = ErrorFragmentPayment.TYPE_OK;
                        title = getString(R.string.apiKeyNotRegisteredErrorTitle);
                        message = getString(R.string.apiKeyNotRegisteredErrorMessage);
                        break;
                    case TGCErrorCodeInvalidScanInterval:
                        mErrorDialogType = ErrorFragmentPayment.TYPE_NO;
                        break;
                    case TGCErrorCodePermissionDenied:
                        mErrorDialogType = ErrorFragmentPayment.TYPE_OK;
                        title = getString(R.string.permissionDeniedErrorTitle);
                        message = getString(R.string.permissionDeniedErrorMessage);
                        break;
                    case TGCErrorCodeMasterDataFailedUpdate:
                        mErrorDialogType = ErrorFragmentPayment.TYPE_UPDATE;
                        title = getString(R.string.networkErrorTitle);
                        message = getString(R.string.failedUpdateErrorMessage);
                        break;
                    case TGCErrorCodeLocationAccess:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (mErrorDialogType == ErrorFragmentPayment.TYPE_RETRY) {
                                return;
                            }
                            mErrorDialogType = ErrorFragmentPayment.TYPE_RETRY;
                            title = getString(R.string.localeAccessErrorTitle);
                            message = getString(R.string.localeAccessErrorMessage);
                        } else {
                            return;
                        }
                        break;
                    default:
                        break;
                }
                if (mErrorDialogType != ErrorFragmentPayment.TYPE_NO) {
                    ErrorFragmentPayment errorDialog = new ErrorFragmentPayment();
                    Bundle arguments = new Bundle();
                    arguments.putString(ErrorFragmentPayment.KEY_TITLE, title);
                    arguments.putString(ErrorFragmentPayment.KEY_MESSAGE, message);
                    arguments.putInt(ErrorFragmentPayment.KEY_TYPE, mErrorDialogType);
                    errorDialog.setArguments(arguments);
                    ErrorFragmentPayment.showDialogFragment(fragmentManager, ErrorFragmentPayment.class.getName(), errorDialog);
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
        tgcAdapter.prepare();
    }
}