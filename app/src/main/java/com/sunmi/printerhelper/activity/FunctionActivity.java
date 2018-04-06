package com.sunmi.printerhelper.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sunmi.printerhelper.R;
import com.sunmi.printerhelper.threadhelp.ThreadPoolManager;
import com.sunmi.printerhelper.utils.AidlUtil;
import com.sunmi.printerhelper.utils.BytesUtil;

import sunmi.sunmiui.dialog.DialogCreater;
import sunmi.sunmiui.dialog.HintOneBtnDialog;
import sunmi.sunmiui.dialog.TextHintDialog;

public class FunctionActivity extends AppCompatActivity {
    HintOneBtnDialog mHintOneBtnDialog;
    boolean run;
    /**
     * 在这里增加打印示例
     */
    private final DemoDetails[] demos = {
            new DemoDetails(R.string.function_barcode, R.drawable.function_barcode,
                    BarCodeActivity.class),
            new DemoDetails(R.string.function_pic, R.drawable.function_pic,
                    BitmapActivity.class),
            new DemoDetails(R.string.function_multi, R.drawable.function_multi,
                    null),
            new DemoDetails(R.string.function_threeline, R.drawable.function_threeline,
                    null),
    };

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        final GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        mRecyclerView = (RecyclerView)findViewById(R.id.worklist);
        mRecyclerView.setLayoutManager(layoutManage);
        mRecyclerView.setAdapter(new WorkTogetherAdapter());
    }

    class WorkTogetherAdapter extends RecyclerView.Adapter<WorkTogetherAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_item, parent, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.demoDetails = demos[position];
            holder.tv.setText(demos[position].titleId);
            holder.tv.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(demos[position].iconResID), null, null);
        }

        @Override
        public int getItemCount() {
            return demos.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;
            DemoDetails demoDetails;

            public MyViewHolder(View v) {
                super(v);
                tv =(TextView) v.findViewById(R.id.worktext);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(demoDetails != null && demoDetails.activityClass != null)
                        {
                            if(demoDetails.titleId == R.string.function_buffer &&
                                    !(Build.MODEL.contains("P1") || Build.MODEL.contains("p1") || Build.MODEL.contains("V1s") || Build.MODEL.contains("v1s") || Build.MODEL.contains("P2") || Build.MODEL.contains("p2"))){
                                Toast.makeText(FunctionActivity.this, "This function can only be applied to the specified model!", Toast.LENGTH_LONG).show();
                            }else{
                                startActivity(new Intent(FunctionActivity.this, demoDetails.activityClass));
                            }
                            return;
                        }
                        if(demoDetails.titleId == R.string.function_threeline){
                            AidlUtil.getInstance().print3Line();
                        }
                        if(demoDetails.titleId == R.string.function_multi){
                            if(mHintOneBtnDialog  == null){
                                mHintOneBtnDialog = DialogCreater.createHintOneBtnDialog(FunctionActivity.this, null, getResources().getString(R.string.multithread), getResources().getString(R.string.multithread_stop), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        run =false;
                                        mHintOneBtnDialog.cancel();
                                    }
                                });
                            }
                            mHintOneBtnDialog.show();
                            run = true;
                            ThreadPoolManager.getInstance().executeTask(new Runnable() {
                                @Override
                                public void run() {
                                    while(run){
                                        AidlUtil.getInstance().sendRawData(BytesUtil.getBaiduTestBytes());
                                        try {
                                            Thread.sleep(4000);
                                        } catch (InterruptedException e) {
                                            break;
                                        }
                                    }


                                }
                            });

                        }
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.function, menu);
        return true;
    }





    private class DemoDetails {
        @StringRes
        private final int titleId;
        @DrawableRes
        private final int iconResID;
        private final Class<? extends Activity> activityClass;

        public DemoDetails(@StringRes int titleId, @DrawableRes int descriptionId,
                           Class<? extends Activity> activityClass) {
            super();
            this.titleId = titleId;
            this.iconResID = descriptionId;
            this.activityClass = activityClass;
        }
    }
}
