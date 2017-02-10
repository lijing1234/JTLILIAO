package com.app.nuts.app.mvp.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.app.nuts.R;
import com.app.nuts.app.common.AppComponent;
import com.app.nuts.app.common.BaseActivity;
import com.app.nuts.app.di.component.DaggerReadComponent;
import com.app.nuts.app.di.module.ReadModule;
import com.app.nuts.app.mvp.contract.ReadContract;
import com.app.nuts.app.mvp.presenter.ReadPresenter;
import com.app.nuts.utils.RopUtils;
import com.google.common.collect.Maps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadActivity extends BaseActivity<ReadPresenter> implements ReadContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.readStr)
    TextView readStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        ButterKnife.bind(this);
        toolbar.setTitle("豆瓣阅读");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        Map<String, String> form = Maps.newHashMap();
        form.put("method", "rop.productclass.get");
        form.put("appKey", "00001");
        form.put("v", "1.0");
        form.put("format", "json");
        String sing = RopUtils.signString(form, "qwertyuiop");
        form.put("sign", sing);
        mPresenter.getReadInfo(form);
    }


    @Override
    public void showReadInfo(String readInfos) {
        readStr.setText(jsonFormat(readInfos));
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerReadComponent
                .builder()
                .appComponent(appComponent)
                .readModule(new ReadModule(this))
                .build()
                .inject(this);
    }

    /**
     * json 格式化
     * @param bodyString
     * @return
     */
    public static String jsonFormat(String bodyString) {
        String message;
        try {
            if (bodyString.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(bodyString);
                message = jsonObject.toString(4);
            } else if (bodyString.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(bodyString);
                message = jsonArray.toString(4);
            } else {
                message = bodyString;
            }
        } catch (JSONException e) {
            message = bodyString;
        }
        return message;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }
}
