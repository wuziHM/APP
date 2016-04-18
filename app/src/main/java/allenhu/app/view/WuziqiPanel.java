package allenhu.app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;

/**
 * Author：燕青 $ on 16/4/14 10:35
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class WuziqiPanel extends View {

    private int mPanelWidth;
    private float mLineHeight;
    private static final int MAX_LINE = 12;
    private int MAX_COUNT_IN_LINE = 5;

    private Bitmap mWhitePiece;
    private Bitmap mBlackPiece;
    private boolean mIsGameOver;
    private boolean mIsWhiteWinner;

    private float rationPieceLineHeight = 3 * 1.0f / 4;

    private Paint mPaint = new Paint();

    private ArrayList<Point> mWhiteArray = new ArrayList<>();
    private ArrayList<Point> mBlackArray = new ArrayList<>();

    /**
     * 意味着白棋先手  或者是轮到白棋了
     */
    private boolean mIsWhite = true;

    public WuziqiPanel(Context context) {
        this(context, null);
    }

    public WuziqiPanel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WuziqiPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setBackgroundColor(0x44ff0000);
        init();

    }

    private void init() {
        mPaint.setColor(0x88000000);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mWhitePiece = BitmapFactory.decodeResource(getResources(), R.mipmap.stone_w2);
        mBlackPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.stone_b1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = Math.min(widthSize, heightSize);

        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPanelWidth = w;
        mLineHeight = mPanelWidth * 1.0f / MAX_LINE;

        int pieceWidth = (int) (mLineHeight * rationPieceLineHeight);
        mWhitePiece = Bitmap.createScaledBitmap(mWhitePiece, pieceWidth, pieceWidth, false);
        mBlackPiece = Bitmap.createScaledBitmap(mBlackPiece, pieceWidth, pieceWidth, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);
        drawPieces(canvas);
        checkGameOver();
    }

    private static final String INSTANCE = "instance";
    private static final String INSTANCE_GAME_OVER = "instance_game_over";
    private static final String INSTANCE_WHITE_ARRAY = "instance_white_array";
    private static final String INSTANCE_BLACK_OVER = "instance_black_array";

    @Override
    protected Parcelable onSaveInstanceState() {

        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE, super.onSaveInstanceState());
        bundle.putBoolean(INSTANCE_GAME_OVER, mIsGameOver);
        bundle.putParcelableArrayList(INSTANCE_WHITE_ARRAY, mWhiteArray);
        bundle.putParcelableArrayList(INSTANCE_BLACK_OVER, mBlackArray);
        return bundle;

    }

    /**
     * view 的存储于恢复 需要在xml文件中给控件设置id
     * <p/>
     * 不设置id的话不能起效果
     *
     * @param state
     */
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {

            Bundle bundle = (Bundle) state;
            mIsGameOver = bundle.getBoolean(INSTANCE_BLACK_OVER);
            mWhiteArray = bundle.getParcelableArrayList(INSTANCE_WHITE_ARRAY);
            mBlackArray = bundle.getParcelableArrayList(INSTANCE_BLACK_OVER);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE));
        }

//        super.onRestoreInstanceState(state);
    }

    /**
     * 判断是否游戏结束
     */
    private void checkGameOver() {
        boolean whiteWin = checkFiveInLine(mWhiteArray);
        boolean blackWin = checkFiveInLine(mBlackArray);
        if (whiteWin || blackWin) {
            mIsGameOver = true;
            mIsWhiteWinner = whiteWin;
            String text = mIsWhiteWinner ? "白棋胜利" : "黑棋胜利";
            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkFiveInLine(List<Point> points) {


        for (Point p : points) {
            int x = p.x;
            int y = p.y;
            boolean win = checkHorizontal(x, y, points);
            if (win) return true;
            win = checkVertical(x, y, points);
            if (win) return true;
            win = checkLeftDiagonal(x, y, points);
            if (win) return true;
            win = checkRightDiagonal(x, y, points);
            if (win) return true;

        }

        return false;
    }

    /**
     * 判断x,y位置的棋子是否是横向相连的5个
     *
     * @param x
     * @param y
     * @param points
     * @return
     */
    private boolean checkHorizontal(int x, int y, List<Point> points) {
        int count = 1;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point((x - i), y)))
                count++;
            else
                break;
        }
        if (count == MAX_COUNT_IN_LINE) return true;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point((x + i), y)))
                count++;
            else
                break;
        }
        if (count == MAX_COUNT_IN_LINE) return true;
        return false;
    }

    private boolean checkVertical(int x, int y, List<Point> points) {
        int count = 1;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point(x, (y - i))))
                count++;
            else
                break;
        }
        if (count == MAX_COUNT_IN_LINE) return true;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point(x, (y + i))))
                count++;
            else
                break;
        }
        if (count == MAX_COUNT_IN_LINE) return true;
        return false;
    }

    private boolean checkLeftDiagonal(int x, int y, List<Point> points) {
        int count = 1;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point((x - i), (y - i))))
                count++;
            else
                break;
        }
        if (count == MAX_COUNT_IN_LINE) return true;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point((x + i), (y + i))))
                count++;
            else
                break;
        }
        if (count == MAX_COUNT_IN_LINE) return true;
        return false;
    }

    /**
     * 判断右倾斜是否有5子连线
     *
     * @param x
     * @param y
     * @param points
     * @return
     */
    private boolean checkRightDiagonal(int x, int y, List<Point> points) {
        int count = 1;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point((x - i), (i + y))))
                count++;
            else
                break;
        }
        if (count == MAX_COUNT_IN_LINE) return true;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point((x + i), (y - i))))
                count++;
            else
                break;
        }
        if (count == MAX_COUNT_IN_LINE) return true;
        return false;
    }

    private void drawPieces(Canvas canvas) {
        for (int i = 0, n = mWhiteArray.size(); i < n; i++) {
            Point whitePoint = mWhiteArray.get(i);
            canvas.drawBitmap(mWhitePiece, (whitePoint.x + (1 - rationPieceLineHeight) / 2) * mLineHeight,
                    (whitePoint.y + (1 - rationPieceLineHeight) / 2) * mLineHeight, null);
        }

        for (int i = 0, n = mBlackArray.size(); i < n; i++) {
            Point blackPoint = mBlackArray.get(i);
            canvas.drawBitmap(mBlackPiece, (blackPoint.x + (1 - rationPieceLineHeight) / 2) * mLineHeight,
                    (blackPoint.y + (1 - rationPieceLineHeight) / 2) * mLineHeight, null);
        }
    }

    private void drawBoard(Canvas canvas) {
        int w = mPanelWidth;
        float lineHeight = mLineHeight;
        for (int i = 0; i < MAX_LINE; i++) {
            int startX = (int) (lineHeight / 2);
            int endX = (int) (w - lineHeight / 2);

            int y = (int) ((0.5 + i) * lineHeight);
            canvas.drawLine(startX, y, endX, y, mPaint);
            canvas.drawLine(y, startX, y, endX, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mIsGameOver)
            return false;

        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            Point point = getValidPoint(x, y);
            if (mWhiteArray.contains(point) || mBlackArray.contains(point)) {
                return false;
            }

            if (mIsWhite) {
                mWhiteArray.add(point);
            } else {
                mBlackArray.add(point);
            }
            invalidate();
            mIsWhite = !mIsWhite;
        }
        return true;

    }

    public Point getValidPoint(int x, int y) {

        return new Point((int) (x / mLineHeight), (int) (y / mLineHeight));
    }

    public void reStart() {
        mBlackArray.clear();
        mWhiteArray.clear();
        mIsGameOver = false;
        mIsWhite = true;
        mIsWhiteWinner = false;
        invalidate();
    }

    public boolean ismIsGameOver() {
        return mIsGameOver;
    }
}
