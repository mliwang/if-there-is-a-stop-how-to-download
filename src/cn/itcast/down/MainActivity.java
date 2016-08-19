package cn.itcast.down;

import java.io.File;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

    private EditText et_url;
	private ProgressBar progressbar;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_url = (EditText) findViewById(R.id.et_url);
        progressbar = (ProgressBar) findViewById(R.id.pb);
     

        
    }
   public void click(View v){
	   String path = et_url.getText().toString().trim();
	   HttpUtils utils =new HttpUtils();
	   //实现断点续传，target下载文件的路径，autoresume代表是否支持断点续传
	   utils.download(path, "/mnt/sdcard/haha.mp3", true, new RequestCallBack<File>() {
		
		@Override
		public void onSuccess(ResponseInfo<File> responseInfo) {
			Toast.makeText(getApplicationContext(), "下载成功", 1).show();
			
		}
		@Override
		public void onLoading(long total, long current, boolean isUploading) {
			// Total代表总进度，current代表当前进度
			progressbar.setMax((int) total);
			progressbar.setProgress((int) current);
			
			
			
		}
		
		@Override
		public void onFailure(HttpException error, String msg) {
			Toast.makeText(getApplicationContext(), "下载失败", 1).show();
			
		}
	});
	   
   }

}
