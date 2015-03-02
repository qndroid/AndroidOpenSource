package view;

import java.util.ArrayList;

import module.Flake;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

import com.example.listviewanimationdemo.R;

public class FlakeView extends View {

	Bitmap droid;
	int numFlakes = 0;
	ArrayList<Flake> flakes = new ArrayList<Flake>();
	ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
	long startTime, prevTime;
	int frames = 0;
	Paint textPaint;
	float fps = 0;
	Matrix m = new Matrix();
	String fpsString = "";
	String numFlakesString = "";

	public FlakeView(Context context) {
		super(context);
		droid = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setColor(Color.BLACK);
		textPaint.setTextSize(24);

		animator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				/**
				 * 根据每一帧,更新动画状态
				 */
				long nowTime = System.currentTimeMillis();
				float secs = (float) (nowTime - prevTime) / 1000f; // 每帧动画时长
				for (int i = 0; i < numFlakes; i++) {
					Flake flake = flakes.get(i);
					if (flake != null) {
						flake.y += flake.speed * secs;
						if (flake.y > getHeight()) {
							flake.y = 0 - flake.height;
						}
						flake.rotation = flake.rotation
								+ (flake.rotationSpeed * secs);
					}
				}
				prevTime = nowTime;
				invalidate(); // 重绘新状态
			}
		});
		animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setDuration(3000);
	}

	public int getNumFlakes() {
		return numFlakes;
	}

	private void setNumFlakes(int quantity) {
		numFlakes = quantity;
		numFlakesString = "numFlakes: " + numFlakes;
	}

	/*
	 * 初始化
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		flakes.clear();
		numFlakes = 0;
		addFlakes(8);
		animator.cancel();
		startTime = System.currentTimeMillis();
		prevTime = startTime;
		frames = 0;
		animator.start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		for (int i = 0; i < numFlakes; i++) {
			Flake flake = flakes.get(i);
			m.setTranslate(flake.width / 2, flake.height / 2);
			m.postRotate(flake.rotation);
			m.postTranslate(flake.width / 2 + flake.x, flake.height / 2
					+ flake.y);
			canvas.drawBitmap(flake.bitmap, m, null);
		}

		++frames;
		long nowTime = System.currentTimeMillis();
		long deltaTime = nowTime - startTime;
		if (deltaTime > 1000) {
			float secs = (float) deltaTime / 1000f;
			fps = (float) frames / secs;
			fpsString = "fps: " + fps;
			startTime = nowTime;
			frames = 0;
		}
		canvas.drawText(numFlakesString, getWidth() - 200, getHeight() - 50,
				textPaint);
		canvas.drawText(fpsString, getWidth() - 200, getHeight() - 80,
				textPaint);
	}

	/**
	 * Add the specified number of droidflakes.
	 */
	public void addFlakes(int quantity) {
		for (int i = 0; i < quantity; ++i) {
			flakes.add(Flake.createFlake(getWidth(), droid));
		}
		setNumFlakes(numFlakes + quantity);
	}

	public void subtractFlakes(int quantity) {
		for (int i = 0; i < quantity; ++i) {
			int index = numFlakes - i - 1;
			flakes.remove(index);
		}
		setNumFlakes(numFlakes - quantity);
	}

	public void pause() {
		animator.pause();
	}

	public void resume() {
		animator.resume();
	}
}
