package com.wanli.webviewdemo;
/************************WebView:WebViewButtonActivity********************************/
/************************制定时间：2020/3/13 0013********************************/
/**
 *功能：实现网页的播放
 *
 *流程：
 *    1.加载网页资源
 *    2.设置JS交互
 *    3.其他交互设置
 *注意：这里仅仅只是实现了部分功能
 */
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.HashMap;


/**
 * Function:
 * Create date on 15/11/26.
 *
 * @author Conquer
 * @version 1.0
 */
public class WebViewButtonActivity  extends AppCompatActivity {

    private WebView mWebView;

    public class TestJSEvent{
        //告诉如何让js调用
        @JavascriptInterface
        public void showToast(String toast){
            Toast.makeText(WebViewButtonActivity.this, toast, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_button);
        mWebView = (WebView) findViewById(R.id.web_view);
        //功能五：开启调试状态，需要版本4.4
        WebView.setWebContentsDebuggingEnabled(true);
        //
        mWebView.loadUrl("http://www.baidu.com");
        HashMap<String,String> map = new HashMap<>();
        map.put("token","xxxxxx");
        map.put("my_header","header");

        //功能一：加载网上页面
        mWebView.loadUrl("http://www.baidu.com", map);
        //功能二：加载本地URL
        //mWebView.loadUrl("file:///android_asset/test.html");
        //功能三：和JS进行交互
        // 设置和js交互的能力
        mWebView.getSettings().setJavaScriptEnabled(true);
        // JS调用原生App
        mWebView.addJavascriptInterface(new TestJSEvent(), "TestApp");
        // 原生App调用JS，使得js代码被执行
        mWebView.loadUrl("javascript:javaCallJS('')");

        //功能六：设置cookie
        CookieManager cookieManager = CookieManager.getInstance();
        //设置允许cookie
        cookieManager.setAcceptCookie(true);
        //清除cookie
        cookieManager.setCookie("domain", "cookie");

        mWebView.setWebViewClient(new WebViewClient(){
            @Override//拦截界面，根据url信息进行逻辑处理
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 404页面
                if(url.contains("404")){
                    view.loadUrl("http://www.zhihu.com");
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override//页面刚刚开始加载的时候
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // 页面开始：显示loading动画
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // 页面开始：隐藏loading动画
                super.onPageFinished(view, url);
            }

            @Override//页面正在加载
            public void onLoadResource(WebView view, String url) {
                // url 替换，可以选择替换图片或者不加载图片
                if(url.contains("logo.img")){

                }
                super.onLoadResource(view, url);
            }

            @Override//拦截网页请求
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                // request        hybrid 离线网页
                return super.shouldInterceptRequest(view, request);
            }
        });

        mWebView.setWebChromeClient(new TestWebChromeClient());
    }

    public class TestWebChromeClient extends WebChromeClient {
        public TestWebChromeClient() {
            super();
        }

        @Override//功能七：显示进度
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
            super.onShowCustomView(view, requestedOrientation, callback);
        }
    }

    @Override//功能三：历史记录
    //原先的功能是Activity返回时的动作，原本就是直接销毁当前Activity
    public void onBackPressed() {
        if(mWebView != null && mWebView.canGoBack()){
            //获取历史记录
//            WebBackForwardList webBackForwardList = mWebView.copyBackForwardList();
//            WebHistoryItem historyItem = webBackForwardList.getItemAtIndex(0);
//            String historyUrl = historyItem.getUrl();
            //下面分别是网页的后退、前进、设置后退前进步数、刷新
            mWebView.goBack();
            mWebView.goForward();
            mWebView.goBackOrForward(+1);
            mWebView.reload();
        } else {
            super.onBackPressed();
        }
    }
}
/**----------------------------END：WebView:WebViewButtonActivity------------------------------------**/
