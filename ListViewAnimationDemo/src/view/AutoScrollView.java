package view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import android.widget.ScrollView;

/**********************************************************
 * @文件名称：AutoScrollView.java
 * @文件作者：renzhiqiang
 * @创建时间：2015年2月26日 上午11:25:29
 * @文件描述：利用ScrolView生命周期实现自动上下滑动
 * @修改历史：2015年2月26日创建初始版本
 **********************************************************/
public class AutoScrollView extends ScrollView {
	private Context context;
	private OverScroller scroller;
	/**
	 * 只有前两次要计算偏移值
	 */
	private int flag = 0;
	/**
	 * 自动滑动间距
	 */
	private int deltaY = 100;

	public AutoScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}

	private void initView() {
		Interpolator polator = new AccelerateInterpolator();
		scroller = new OverScroller(context, polator);
	}

	// 推动门的动画
	public void startAnim(int startY, int dY, int duration) {
		flag++;
		scroller.startScroll(0, startY, 0, dY, duration); // 利用Scroller来达到平滑移动的效果
		invalidate();
	}

	/**
	 * 刚生成时播放滑动动画
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		startAnim(this.getScrollY(), deltaY, 500);
	}

	@Override
	public void computeScroll() {
		/**
		 * true,未滑动完
		 */
		if (scroller.computeScrollOffset()) {
			if (flag <= 2) {
				scrollTo(scroller.getCurrX(), scroller.getCurrY());
				// 不要忘记更新界面
				postInvalidate();
			}
		} else {
			startAnim(AutoScrollView.this.getScrollY(), -deltaY, 500);
		}
	}
}
