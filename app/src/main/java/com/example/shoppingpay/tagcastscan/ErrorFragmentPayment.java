package com.example.shoppingpay.tagcastscan;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shoppingpay.R;

public class ErrorFragmentPayment extends DialogFragment {
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE 	= "message";
    public static final String KEY_TYPE = "type";

    public static final int TYPE_NO = -1;
    public static final int TYPE_OK	 = 1;
    public static final int TYPE_RETRY = 2;
    public static final int TYPE_UPDATE = 3;

    public static void showDialogFragment(FragmentManager fragmentManager, String tag, Fragment fragment) {
        if (fragmentManager == null) {
            return;
        }
        FragmentTransaction t = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag(tag);
        if (prev != null) {
            t.remove(prev);
        }
        t.add(fragment, tag);
        t.commitAllowingStateLoss();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int dialogFlag = getArguments().getInt(KEY_TYPE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getArguments().getString(KEY_TITLE));
        builder.setMessage(getArguments().getString(KEY_MESSAGE));
        switch (dialogFlag) {
            case TYPE_OK:
                builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onDismiss(dialog);
                    }
                });
                break;
            case TYPE_RETRY:
                builder.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Activity activity = getActivity();
                        if (activity == null) {
                            return;
                        }
                        MainPaymentActivity mainActivity = (MainPaymentActivity) activity;
                        mainActivity.mErrorDialogType = TYPE_NO;
                        // Bắt đầu quét
                        mainActivity.tgcAdapter.startScan();
                        onDismiss(dialog);
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Activity activity = getActivity();
                        if (activity == null) {
                            return;
                        }
                        MainPaymentActivity mainActivity = (MainPaymentActivity) activity;
                        mainActivity.mErrorDialogType = TYPE_NO;
                        onDismiss(dialog);
                    }
                });
                break;
            case TYPE_UPDATE:
                builder.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Activity activity = getActivity();
                        if (activity == null) {
                            return;
                        }
                        MainPaymentActivity mainActivity = (MainPaymentActivity) activity;
                        mainActivity.mErrorDialogType = TYPE_NO;
                        mainActivity.tgcAdapter.prepare();
                        onDismiss(dialog);
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Activity activity = getActivity();
                        if (activity == null) {
                            return;
                        }
                        MainPaymentActivity mainActivity = (MainPaymentActivity) activity;
                        mainActivity.mErrorDialogType = TYPE_NO;
                        onDismiss(dialog);
                    }
                });
                break;
            default:
                break;
        }
        return builder.create();
    }
}
