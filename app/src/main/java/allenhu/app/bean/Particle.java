package allenhu.app.bean;

/**
 * 作者：燕青 $ on 16/3/10 09:45
 * 邮箱：359222347@qq.com
 * <p/>
 * use to...
 */
public class Particle {
    //小球和桌子以及空气的摩擦参数
    private static final float sFriction = 0.1f;

    //当前位置
    float mPosX;
    float mPosY;

    //加速度
    private float mAccelX;
    private float mAccelY;

    //上次的位置
    private float mLastPosX;
    private float mLastPosY;
    private float mOneMinusFriction;

    public Particle() {
        //模拟使得每个小球有不同的摩擦系数
        final float r = ((float) Math.random() - 0.5f) * 0.2f;
        mOneMinusFriction = 1.0f - sFriction + r;
    }

    public void computePhysics(float sx, float sy, float dT, float dTC) {
        final float m = 1000.0f;    //虚拟物品的质量
        final float gx = -sx * m;   //重力在x轴上的分力
        final float gy = -sy * m;

        final float invm = 10.f / m;
        final float ax = gx * invm;
        final float ay = gy * invm;
        final float dTdT = dT * dT;

        //最新位置的坐标
        final float x = mPosX + mOneMinusFriction * dTC * (mPosX - mLastPosX) + mAccelX * dTdT * 0.5f;
        final float y = mPosY + mOneMinusFriction * dTC * (mPosY - mLastPosY) + mAccelY * dTdT * 0.5f;
        mLastPosY = mPosY;
        mLastPosX = mPosX;
        mPosX = x;
        mPosY = y;
        mAccelX = ax;
        mAccelY = ay;
    }

    /*
     * 小球粒子不和墙壁相交
     */
    public void resolveCollisionWithBounds(float mHorizontalBound, float mVerticalBound) {
        final float xmax = mHorizontalBound;
        final float ymax = mVerticalBound;
        final float x = mPosX;
        final float y = mPosY;
        if (x > xmax) {
            mPosX = xmax;
        } else if (x < -xmax) {
            mPosX = -xmax;
        }
        if (y > ymax) {
            mPosY = ymax;
        } else if (y < -ymax) {
            mPosY = -ymax;
        }
    }
}
