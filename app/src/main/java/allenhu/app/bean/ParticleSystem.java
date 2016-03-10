package allenhu.app.bean;

import allenhu.app.view.SimulationView;

public class ParticleSystem {

    //小球的外接正方形面积
    private static float sBallDiameter2 = SimulationView.sBallDiameter * SimulationView.sBallDiameter;
    private static final int NUM_PARTICLES = 15;    //小球的数量
    private Particle mBalls[] = new Particle[NUM_PARTICLES];
    private long mLastT;      //小球上次更改位置的时间
    private float mLastDeltaT;  //记录小球相邻两次的位置变化的时间间隔

    public ParticleSystem() {
        //初始化所有的小球
        for (int i = 0; i < NUM_PARTICLES; i++) {
            mBalls[i] = new Particle();
        }
    }


    public void updatePositions(float sx, float sy, long timestamp) {
        final long t = timestamp;
        if (mLastT != 0) {
            //此次位置上的边和与上次位置上的变化的时间间隔（以秒为单位）
            final float dT = (float) (t - mLastT) * (1.0f / 1000000000.0f);
            if (mLastDeltaT != 0) {
                //这一次和上一次的比例系数
                final float dTC = dT / mLastDeltaT;
                final int count = mBalls.length;
                for (int i = 0; i < count; i++) {
                    Particle ball = mBalls[i];
                    ball.computePhysics(sx, sy, dT, dTC);
                }
            }
            mLastDeltaT = dT;
        }
        mLastT = t;
    }

    public void update(float sx, float sy, long now, float mHorizontalBound, float mVerticalBound) {
        updatePositions(sx, sy, now);
        // 设置最大的迭代次数
        final int NUM_MAX_ITERATIONS = 10;
        /*
         * 解决小球冲突，每个粒子被针对每一个其他测试 粒子碰撞。如果检测到冲突的粒子是 使用无限刚度的假想的弹簧弹走。
		 */
        boolean more = true;
        final int count = mBalls.length;
        for (int k = 0; k < NUM_MAX_ITERATIONS && more; k++) {
            more = false;
            for (int i = 0; i < count; i++) {
                Particle curr = mBalls[i];
                for (int j = i + 1; j < count; j++) {
                    Particle ball = mBalls[j];
                    float dx = ball.mPosX - curr.mPosX;
                    float dy = ball.mPosY - curr.mPosY;
                    float dd = dx * dx + dy * dy;
                    // 小球的碰撞检测
                    if (dd <= sBallDiameter2) {
                        /*
                         * add a little bit of entropy, after nothing is perfect
						 * in the universe.
						 */
                        dx += ((float) Math.random() - 0.5f) * 0.0001f;
                        dy += ((float) Math.random() - 0.5f) * 0.0001f;
                        dd = dx * dx + dy * dy;// 两个小球粒子的中心之间的距离的二次方
                        // 两个小球之间的距离
                        final float d = (float) Math.sqrt(dd);
                        final float c = (0.5f * (SimulationView.sBallDiameter - d)) / d;
                        curr.mPosX -= dx * c;
                        curr.mPosY -= dy * c;
                        ball.mPosX += dx * c;
                        ball.mPosY += dy * c;
                        more = true;
                    }
                }
				/*
				 * 小球粒子不和墙壁相交
				 */
                curr.resolveCollisionWithBounds(mHorizontalBound, mVerticalBound);
            }
        }
    }

    // 返回粒子的数量
    public int getParticleCount() {
        return mBalls.length;
    }

    // 返回小球所在的x坐标
    public float getPosX(int i) {
        return mBalls[i].mPosX;
    }

    // 返回小球所在的y坐标
    public float getPosY(int i) {
        return mBalls[i].mPosY;
    }
}