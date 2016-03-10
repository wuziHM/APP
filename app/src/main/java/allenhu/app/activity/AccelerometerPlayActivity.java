
package allenhu.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

import allenhu.app.view.SimulationView;

/**
 * This is an example of using the accelerometer to integrate the device's
 * acceleration to a position using the Verlet method. This is illustrated with
 * a very simple particle system comprised of a few iron balls freely moving on
 * an inclined wooden table. The inclination of the virtual table is controlled
 * by the device's accelerometer.
 * 
 * @see SensorManager
 * @see SensorEvent
 * @see Sensor
 */

/**
 * 参考资料 weakLock机制浅析：http://blog.sina.com.cn/s/blog_4ad7c2540101n2k2.html
 * SensorManager:http://www.cnblogs.com/androidez/archive/2013/02/06/2901295.
 * html
 *
 * 传感器的坐标系：http://www.cnblogs.com/mengdd/archive/2013/03/12/2954947.html
 * @author LIUBO
 *
 */

public class AccelerometerPlayActivity extends Activity {
	private SimulationView mSimulationView;
	/** 电源管理者 */
	private PowerManager mPowerManager;
	/** 唤醒锁 */
	private WakeLock mWakeLock;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 获取一个电池管理者的实例
		mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
		// SCREEN_BRIGHT_WAKE_LOCK：保持CPU 运转，保持屏幕高亮显示，允许关闭键盘灯
		// 保持屏幕的高亮
		mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, getClass().getName());
		mSimulationView = new SimulationView(this);
		setContentView(mSimulationView);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mWakeLock.acquire();
		//注册加速度传感器
		mSimulationView.startSimulation();
	}

	@Override
	protected void onPause() {
		super.onPause();
		//解除加速度传感器的注册
		mSimulationView.stopSimulation();
		mWakeLock.release();
	}
}
