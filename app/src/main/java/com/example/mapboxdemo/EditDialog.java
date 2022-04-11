package com.example.mapboxdemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class EditDialog extends Dialog {
    private TextView titleTextView;
    private TextView messageTextView;
    private EditText editText;
    private Button downloadButton;
    private Button cancelButton;

    /**
     * 都是内容数据
     */
    private String message;
    private String title;
    private String hint;
    private String download,cancel ;
    private String edit;

    private View columnLineView;
    public EditDialog(Context context)
    {
        super(context,R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_edit_dialog);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面数据
        refreshView();
        //初始化界面控件的事件
        initEvent();
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onClickBottomListener!= null) {
                    onClickBottomListener.onPositiveClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onClickBottomListener!= null) {
                    onClickBottomListener.onNegativeClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void refreshView() {
        //如果用户自定了title和message
        if (!TextUtils.isEmpty(title)) {
            titleTextView.setText(title);
            titleTextView.setVisibility(View.VISIBLE);
        }else {
            titleTextView.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(message)) {
            messageTextView.setText(message);
        }
        //如果设置按钮的文字
        if (!TextUtils.isEmpty(download)) {
            downloadButton.setText(download);
        }else {
            downloadButton.setText("下载");
        }
        if (!TextUtils.isEmpty(cancel)) {
            cancelButton.setText(cancel);
        }else {
            cancelButton.setText("取消");
        }
        if (!TextUtils.isEmpty(hint)) {
            editText.setHint(hint);
        }else {
            editText.setHint("输入名称");
        }
    }

    @Override
    public void show() {
        super.show();
        refreshView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        cancelButton = (Button) findViewById(R.id.negative);
        downloadButton = (Button) findViewById(R.id.positive);
        titleTextView = (TextView) findViewById(R.id.title);
        messageTextView = (TextView) findViewById(R.id.message);
        editText = (EditText) findViewById(R.id.name);
        columnLineView = findViewById(R.id.column_line);
    }

    /**
     * 设置确定取消按钮的回调
     */
    public OnClickBottomListener onClickBottomListener;
    public EditDialog setOnClickBottomListener(OnClickBottomListener onClickBottomListener) {
        this.onClickBottomListener = onClickBottomListener;
        return this;
    }
    public interface OnClickBottomListener{
        /**
         * 点击确定按钮事件
         */
        public void onPositiveClick();
        /**
         * 点击取消按钮事件
         */
        public void onNegativeClick();
    }

    public String getMessage() {
        return message;
    }

    public EditDialog setMessage(String message) {
        this.message = message;
        return this ;
    }

    public String getTitle() {
        return title;
    }

    public EditDialog setTitle(String title) {
        this.title = title;
        return this ;
    }

    public String getDownload() {
        return download;
    }

    public EditDialog setHint(String Hint){
        this.hint=Hint;
        return this;
    }

    public String getHint()
    {
        return hint;
    }

    public EditDialog setEditText(String edit)
    {
        editText.setText(edit);
        return this;
    }

    public String getEditText()
    {
        return editText.getText().toString();
    }

    public EditDialog setPositive(String download) {
        this.download = download;
        return this ;
    }

    public String getNegative() {
        return cancel;
    }

    public EditDialog setNegative(String cancel) {
        this.cancel = cancel;
        return this ;
    }

}
