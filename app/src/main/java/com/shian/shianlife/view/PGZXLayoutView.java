package com.shian.shianlife.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.PgzxActivity;
import com.shian.shianlife.activity.sendorders.SendOrderActivity;

/**
 * Created by Administrator on 2016/12/20.
 */

public class PGZXLayoutView extends LinearLayout {
    ImageView mIVState1;
    ImageView mIVState2;
    ImageView mIVState3;

    Button mBTState1;
    Button mBTState2;
    Button mBTState3;

    TextView mTVTitle;

    String[] listString1 = {"处理临终现场", "治丧第一\n天现场服务", "结束治丧\n初期服务"};
    String[] listString2 = {"开始服务", "出殡前服务", "结束出殡前服务"};
    String[] listString3 = {"开始服务", "出殡当天\n现场服务", "殡仪馆服务"};

    int type = 0;
    int string = 0;

    long orderId;
    long consultId;

    public PGZXLayoutView(Context context) {
        super(context);
    }

    public PGZXLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void setID(long orderId, long consultId) {
        this.consultId = consultId;
        this.orderId = orderId;
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_pgzx_view, null);
        mBTState1 = (Button) view.findViewById(R.id.bt_state_1);
        mBTState2 = (Button) view.findViewById(R.id.bt_state_2);
        mBTState3 = (Button) view.findViewById(R.id.bt_state_3);

        mIVState1 = (ImageView) view.findViewById(R.id.iv_state_1);
        mIVState2 = (ImageView) view.findViewById(R.id.iv_state_2);
        mIVState3 = (ImageView) view.findViewById(R.id.iv_state_3);

        mTVTitle = (TextView) view.findViewById(R.id.tv_title);

        mBTState1.setOnClickListener(onClickListener);
        mBTState2.setOnClickListener(onClickListener);
        mBTState3.setOnClickListener(onClickListener);


        addView(view, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getContext(), SendOrderActivity.class);
            intent.putExtra("consultId", consultId);
            intent.putExtra("orderId", orderId);
            String TITILENAME = "TitleName";
            int step = 0;
            if (view == mBTState1) {
                if (string == 0) {
                    intent.putExtra(TITILENAME, "处理临终现场");
                    step = 0;
                } else if (string == 1) {
                    step = 2;
                    intent.putExtra(TITILENAME, "开始服务");
                } else if (string == 2) {
                    step = 4;
                    intent.putExtra(TITILENAME, "开始服务");
                }
            } else if (view == mBTState2) {
                if (string == 0) {
                    step = 1;
                    intent.putExtra(TITILENAME, "治丧第一天现场服务");
                } else if (string == 1) {
                    step = 3;
                    intent.putExtra(TITILENAME, "出殡前服务");
                } else if (string == 2) {
                    step = 5;
                    intent.putExtra(TITILENAME, "出殡当天现场服务");
                }
            } else if (view == mBTState3) {
                if (string == 0) {
                    step = 2;
                    intent.putExtra(TITILENAME, "结束治丧初期服务");
                } else if (string == 1) {
                    step = 4;
                    intent.putExtra(TITILENAME, "结束出殡前服务");
                } else if (string == 2) {
                    step = 6;
                    intent.putExtra(TITILENAME, "殡仪馆服务");
                }
            }
            intent.putExtra("step", step);
            getContext().startActivity(intent);
        }
    };

    /**
     * 初始化
     *
     * @param type
     * @param state
     * @param string
     */
    public void setType(int type, int state, int string) {
        this.type = type;
        this.string = string;
        setState(state);

        if (string == 0) {
            mTVTitle.setText("治丧初期");
            mBTState1.setText(listString1[0]);
            mBTState2.setText(listString1[1]);
            mBTState3.setText(listString1[2]);
        } else if (string == 1) {
            mTVTitle.setText("出殡前");
            mBTState1.setText(listString2[0]);
            mBTState2.setText(listString2[1]);
            mBTState3.setText(listString2[2]);
        } else if (string == 2) {
            mTVTitle.setText("出殡当天");
            mBTState1.setText(listString3[0]);
            mBTState2.setText(listString3[1]);
            mBTState3.setText(listString3[2]);
        }
    }

    /**
     * 设置状态
     *
     * @param state
     */
    public void setState(int state) {

        if (type == 0) {
            if (state != 3) {
                mBTState3.setTextColor(Color.parseColor("#999999"));
            } else {
                mBTState3.setTextColor(Color.parseColor("#333333"));
            }
            mBTState3.setBackgroundResource(R.drawable.pgzx_select_3);
            mBTState3.setClickable(false);
        } else {
            switch (state) {
                case 0:
                    mBTState3.setBackgroundResource(R.drawable.pgzx_select_0);
                    mBTState3.setClickable(false);
                    break;
                case 1:
                    mBTState3.setBackgroundResource(R.drawable.pgzx_select_0);
                    mBTState3.setClickable(false);
                    break;
                case 2:
                    mBTState3.setBackgroundResource(R.drawable.pgzx_select_0);
                    mBTState3.setClickable(false);
                    break;
                case 3:
                    mBTState3.setBackgroundResource(R.drawable.pgzx_select_1);
                    mBTState3.setClickable(true);
                    break;
            }
        }

        switch (state) {
            case 0:
                //无任何状态
                mBTState1.setBackgroundResource(R.drawable.pgzx_select_0);
                mBTState2.setBackgroundResource(R.drawable.pgzx_select_0);
                mBTState1.setClickable(false);
                mBTState2.setClickable(false);

                mIVState1.setImageResource(R.drawable.pgzx_state_1_0);
                mIVState2.setImageResource(R.drawable.pgzx_state_2_0);
                mIVState3.setImageResource(R.drawable.pgzx_state_3_0);
                break;
            case 1:
                //可以执行状态1
                mBTState1.setBackgroundResource(R.drawable.pgzx_select_1);
                mBTState2.setBackgroundResource(R.drawable.pgzx_select_0);
                mBTState1.setClickable(true);
                mBTState2.setClickable(false);

                mIVState1.setImageResource(R.drawable.pgzx_state_1_1);
                mIVState2.setImageResource(R.drawable.pgzx_state_2_0);
                mIVState3.setImageResource(R.drawable.pgzx_state_3_0);
                break;
            case 2:
                //可以执行状态2
                mBTState1.setBackgroundResource(R.drawable.pgzx_select_1);
                mBTState2.setBackgroundResource(R.drawable.pgzx_select_1);
                mBTState1.setClickable(true);
                mBTState2.setClickable(true);

                mIVState1.setImageResource(R.drawable.pgzx_state_1_1);
                mIVState2.setImageResource(R.drawable.pgzx_state_2_1);
                mIVState3.setImageResource(R.drawable.pgzx_state_3_0);
                break;
            case 3:
                //状态完成
                mBTState1.setBackgroundResource(R.drawable.pgzx_select_1);
                mBTState2.setBackgroundResource(R.drawable.pgzx_select_1);
                mBTState1.setClickable(true);
                mBTState2.setClickable(true);

                mIVState1.setImageResource(R.drawable.pgzx_state_1_1);
                mIVState2.setImageResource(R.drawable.pgzx_state_2_1);
                mIVState3.setImageResource(R.drawable.pgzx_state_3_1);
                break;
        }
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        super.onLayout(b, i, i1, i2, i2);
    }
}
