package com.example.zhizihua.wanandroid;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;

import butterknife.BindView;

/**
 * Created by zhizihua on 2019/3/25.
 */

public class WebActivity extends BaseActivity {
    AgentWeb mAgentWeb;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.title)
    TextView tvtitle;
    String url;

    @Override
    protected void init() {
        url = getIntent().getStringExtra("url");
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(ll, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator().setWebChromeClient(new WebChromeClient() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        if (title != null) {
                            if (title.contains("404")) {

                            } else {
                                tvtitle.setText(title);
                                tvtitle.setSelected(true);
                            }
                        }
                        super.onReceivedTitle(view, title);
                    }
                })
                .createAgentWeb()
                .ready()
                .go(url);
        mAgentWeb.getWebCreator().getWebView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    //按返回键操作并且能回退网页
                    if (keyCode == KeyEvent.KEYCODE_BACK && mAgentWeb.back()) {
                        //后退
                        mAgentWeb.getWebCreator().getWebView().goBack();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.webactivity;
    }
}
