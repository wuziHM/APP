package allenhu.app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

import allenhu.app.R;
import allenhu.app.bean.ParticleSystem;

public class SimulationView extends View implements SensorEventListener {
	// diameter of the balls in meters（以米为单位：小球的直径）
	public static final float sBallDiameter = 0.004f;
	/** 传感器管理者 */
	private SensorManager mSensorManager;
	/** 窗口管理者 */
	private WindowManager mWindowManager;
	private Display mDisplay;
	private Sensor mAccelerometer;// 重力传感器
	private float mXDpi;// x轴每英寸有多少像素
	private float mYDpi;// y轴每英寸有多少像素
	private float mMetersToPixelsX;// x轴每米有多少像素
	private float mMetersToPixelsY;// y轴每米有多少像素
	private Bitmap mBitmap;// 小球的图片
	private Bitmap mWood;// 背景图片
	private float mXOrigin;
	private float mYOrigin;
	private float mSensorX;// x轴的加速度
	private float mSensorY;// y轴的加速度
	private long mSensorTimeStamp;
	private long mCpuTimeStamp;
	private float mHorizontalBound;
	private float mVerticalBound;
	private final ParticleSystem mParticleSystem = new ParticleSystem();

	public SimulationView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		// 获取屏幕的尺寸
		mDisplay = mWindowManager.getDefaultDisplay();
		// 获取重力传感器
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		// 获取屏幕尺寸参数
		DisplayMetrics metrics = new DisplayMetrics();
		mDisplay.getMetrics(metrics);
		mXDpi = metrics.xdpi;
		mYDpi = metrics.ydpi;
		// 1.0英寸等于0.0254米
		mMetersToPixelsX = mXDpi / 0.0254f;
		mMetersToPixelsY = mYDpi / 0.0254f;

		Bitmap ball = BitmapFactory.decodeResource(getResources(), R.mipmap.ball);
		final int dstWidth = (int) (sBallDiameter * mMetersToPixelsX + 0.5f);
		final int dstHeight = (int) (sBallDiameter * mMetersToPixelsY + 0.5f);
		mBitmap = Bitmap.createScaledBitmap(ball, dstWidth, dstHeight, true);

		Options opts = new Options();
		opts.inDither = true;
		opts.inPreferredConfig = Bitmap.Config.RGB_565;
		mWood = BitmapFactory.decodeResource(getResources(), R.mipmap.wood, opts);
	}

	public void startSimulation() {
		// 传感器的注册
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
	}

	public void stopSimulation() {
		// 传感器解除注册
		mSensorManager.unregisterListener(this);
	}

	/**
	 * 布局改变时更改坐标系的中心和最大活动范围
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// 设置原点的坐标（小球粒子活动的最大范围的中心）
		mXOrigin = (w - mBitmap.getWidth()) * 0.5f;
		mYOrigin = (h - mBitmap.getHeight()) * 0.5f;
		// 小球粒子活动的距离原点的最大x轴和y轴的最大距离
		mHorizontalBound = ((w / mMetersToPixelsX - sBallDiameter) * 0.5f);
		mVerticalBound = ((h / mMetersToPixelsY - sBallDiameter) * 0.5f);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		// 传感器的类型不是加速度传感器的话就抛掉
		if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
			return;
		/**
		 * 这里的坐标点（mSensorX,mSensorY）是在以水平向右为X轴的正方向， 垂直向上为Y轴的正方向的自定义的坐标系中
		 * 重力传感器的坐标系是始终不变的（不要被自定义的坐标系迷惑，这里是两套坐标系）
		 * 屏幕的旋转会改变自定义坐标系所以坐标点（mSensorX,mSensorY）在屏幕旋转时值会发生变化 （这里的屏幕旋转和手机旋转要区分开）
		 */
		switch (mDisplay.getRotation()) {// 判断屏幕的旋转角度
		case Surface.ROTATION_0:// 正常的x-y坐标
			mSensorX = event.values[0];
			mSensorY = event.values[1];
			break;
		case Surface.ROTATION_90:// 旋转90度
			mSensorX = -event.values[1];
			mSensorY = event.values[0];
			break;
		case Surface.ROTATION_180:// 旋转180度
			mSensorX = -event.values[0];
			mSensorY = -event.values[1];
			break;
		case Surface.ROTATION_270:// 旋转270度
			mSensorX = event.values[1];
			mSensorY = -event.values[0];
			break;
		}
		// 传感器发生改变的时间(单位：纳秒)
		mSensorTimeStamp = event.timestamp;
		// 当前的系统时间(单位：纳秒)
		mCpuTimeStamp = System.nanoTime();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 画背景
		canvas.drawBitmap(mWood, 0, 0, null);

		// 小球粒子运动的总时间
		final long now = mSensorTimeStamp + (System.nanoTime() - mCpuTimeStamp);
		/*
		 * 基于重力加速度传感器的数据和当前时间重新计算小球粒子的位置
		 */
		mParticleSystem.update(mSensorX, mSensorY, now, mHorizontalBound, mVerticalBound);
		final Bitmap bitmap = mBitmap;
		final int count = mParticleSystem.getParticleCount();
		for (int i = 0; i < count; i++) {
			/*
			 (改造画布的坐标系统（单位像素）使之适应重力传感器的坐标系统（单位米）)
			 */
			// 这里的x,y的单位是像素
			final float x = mXOrigin + mParticleSystem.getPosX(i) * mMetersToPixelsX;
			final float y = mYOrigin - mParticleSystem.getPosY(i) * mMetersToPixelsY;
			canvas.drawBitmap(bitmap, x, y, null);
		}
		invalidate();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

}
