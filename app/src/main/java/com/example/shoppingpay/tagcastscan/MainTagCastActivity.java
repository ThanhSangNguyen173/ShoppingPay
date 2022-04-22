package com.example.shoppingpay.tagcastscan;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import com.example.shoppingpay.R;
import com.example.shoppingpay.application.AppInfo;
import com.example.shoppingpay.views.activity.MainShoppingActivity;
import com.example.shoppingpay.views.activity.choosetable.ChooseTableActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private String serial, pickserial, tablenumber, timein;
    private Map<String,String> map;
    public int mErrorDialogType = ErrorFragment.TYPE_NO;
    DatabaseReference mData;
    Button btn_scan, btn_changeTable, btn_goToMenu;
    TextView txt_tablenumber;
    ImageView img_table;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_tagcast_main);

        mData = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();
        pickserial = bundle.getString("seri");
        tablenumber = bundle.getString("table");

        anhxa();
        TagCastScan();
        tableNumber();
    }

    private void timeIn() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();
        timein = formatter.format(date);
    }

    private void tableNumber() {
        if (tablenumber.equals("table1")) {
            txt_tablenumber.setText("Table 1");
        }
        if (tablenumber.equals("table2")) {
            txt_tablenumber.setText("Table 2");
        }
        if (tablenumber.equals("table3")) {
            txt_tablenumber.setText("Table 3");
        }
        if (tablenumber.equals("table4")) {
            txt_tablenumber.setText("Table 4");
        }
        if (tablenumber.equals("table5")) {
            txt_tablenumber.setText("Table 5");
        }
        if (tablenumber.equals("table6")) {
            txt_tablenumber.setText("Table 6");
        }
        if (tablenumber.equals("table21")) {
            txt_tablenumber.setText("Table 1 Floor 2");
        }
        if (tablenumber.equals("table22")) {
            txt_tablenumber.setText("Table 2 Floor 2");
        }
        if (tablenumber.equals("table23")) {
            txt_tablenumber.setText("Table 3 Floor 2");
        }
        if (tablenumber.equals("table24")) {
            txt_tablenumber.setText("Table 4 Floor 2");
        }
    }

    private void anhxa() {
        btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(this::OnClick);

        btn_changeTable = findViewById(R.id.btn_changetable);
        btn_changeTable.setOnClickListener(this::OnClick);

        btn_goToMenu = findViewById(R.id.btn_gotomenu);
        btn_goToMenu.setOnClickListener(this::OnClick);

        txt_tablenumber = findViewById(R.id.txt_tablenumber);
        img_table = findViewById(R.id.img_table);
    }

    private void OnClick(View view) {
        final Context context = getApplicationContext();
        switch (view.getId()){
            case R.id.btn_scan: scanTagCastPaper();
                break;
            case R.id.btn_changetable:
                changeTable();
                Intent intent2 = new Intent(MainTagCastActivity.this, ChooseTableActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_gotomenu:
                Intent intent = new Intent(context, MainShoppingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("seri",serial);
                bundle.putString("table",tablenumber);
                bundle.putString("timein",timein);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    private void changeTable() {
        switch (tablenumber){
            case "table1":
                mData.child("TB1").setValue("t");
                break;
            case "table2":
                mData.child("TB2").setValue("t");
                break;
            case "table3":
                mData.child("TB3").setValue("t");
                break;
            case "table4":
                mData.child("TB4").setValue("t");
                break;
            case "table5":
                mData.child("TB5").setValue("t");
                break;
            case "table6":
                mData.child("TB6").setValue("t");
                break;
            case "table21":
                mData.child("TB21").setValue("t");
                break;
            case "table22":
                mData.child("TB22").setValue("t");
                break;
            case "table23":
                mData.child("TB23").setValue("t");
                break;
            case "table24":
                mData.child("TB24").setValue("t");
                break;
        }
    }

    /**
     * ScanTagCast
     */
    private void scanTagCastPaper() {
        final Context context = getApplicationContext();
        final LoadingDialog loadingDialog = new LoadingDialog(MainTagCastActivity.this);
        btn_scan.setEnabled(false);

        tgcAdapter.setScanInterval(10000);
        tgcAdapter.startScan();;

        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
                if(flgBeacon){
                    if(pickserial.equals(serial)){

                        timeIn();

                        Animation alpha2 = AnimationUtils.loadAnimation(context,R.anim.alpha2);
                        btn_goToMenu.setVisibility(View.VISIBLE);
                        btn_goToMenu.startAnimation(alpha2);
                        btn_scan.setEnabled(false);
                        btn_scan.setText("Scan Successfully ✓");
                        img_table.setBackground(getDrawable(R.drawable.using_table));
                    }else {
                        Toast.makeText(context, "Vui lòng nhận đúng bàn đã chọn", Toast.LENGTH_SHORT).show();
                        btn_scan.setEnabled(true);
                        btn_scan.setText("Try scan again");
                    }
                }else{
                    btn_scan.setText("Try scan again");
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
            tgcAdapter.prepare();
        }
    }

    @Override
    public void onBackPressed() {
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
