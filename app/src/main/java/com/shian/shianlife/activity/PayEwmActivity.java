package com.shian.shianlife.activity;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.InjectViews;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;

public class PayEwmActivity extends BaseActivity {
	@InjectView(R.id.iv_ewm)
	ImageView ivEwm;
	@InjectViews({ R.id.tv_ewm_0, R.id.tv_ewm_1 })
	List<TextView> tvList;
	private String ewm;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_pay_ewm);
		setTitle("支付定金");
		ewm = getIntent().getStringExtra("ewm");
		initData();

	}

	private void initData() {
		tvList.get(0).setText(getIntent().getStringExtra("orderCode"));
		tvList.get(1).setText("￥" + getIntent().getStringExtra("payAmount"));
		ivEwm.setImageBitmap(generateQRCode(ewm));
	}

	private Bitmap bitMatrix2Bitmap(BitMatrix matrix) {
		int w = matrix.getWidth();
		int h = matrix.getHeight();
		int[] rawData = new int[w * h];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				int color = Color.WHITE;
				if (matrix.get(i, j)) {
					color = Color.BLACK;
				}
				rawData[i + (j * w)] = color;
			}
		}

		Bitmap bitmap = Bitmap.createBitmap(w, h, Config.RGB_565);
		bitmap.setPixels(rawData, 0, w, 0, 0, w, h);
		return bitmap;
	}

	private Bitmap generateQRCode(String content) {
		if(content==null)
			ToastUtils.show(this,"没有二维码编码");
		try {
			QRCodeWriter writer = new QRCodeWriter();
			BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE,
					500, 500);
			return bitMatrix2Bitmap(matrix);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return null;
	}
}
