package com.example.mvvm_sample.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mvvm_sample.R;
import com.example.mvvm_sample.databinding.ActivityWebViewBinding;
import com.example.mvvm_sample.di.component.ActivityComponent;
import com.example.mvvm_sample.model.FeatureItem;
import com.example.mvvm_sample.ui.base.BaseActivity;

import java.util.ArrayList;

public class WebviewActivity extends BaseActivity<ActivityWebViewBinding, WebviewViewModel> {
    @Override
    protected int layoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected Class<WebviewViewModel> viewModelClass() {
        return WebviewViewModel.class;
    }

    @Override
    protected void injectComponent(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setVm(viewModel);

        // opening the html file in webview
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setSupportZoom(true);
        binding.webView.addJavascriptInterface(this, "Dialog");
        binding.webView.loadUrl("file:///android_asset/test.html");

        binding.btnChangeColor.setOnClickListener(v -> {
            // change background color
            binding.webView.evaluateJavascript("javascript:changeBackgroundColor('blue')", null);
        });
    }

    @JavascriptInterface
    public void showMsg(String fname, String pswd) {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> {
            if (isFinishing() || isDestroyed()) return;
            new androidx.appcompat.app.AlertDialog.Builder(WebviewActivity.this)
                    .setTitle("Confirmation")
                    .setMessage("UserName:\t" + fname + "\nPassword:\t" + pswd)
                    .setPositiveButton("Ok", (d, i) ->
                            Toast.makeText(WebviewActivity.this, "Data Saved Locally", Toast.LENGTH_SHORT).show()
                    )
                    .show();
        });
    }

}
