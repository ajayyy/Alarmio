package james.alarmio.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.afollestad.aesthetic.Aesthetic;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ProgressLineView extends BaseSubscriptionView {

    private Paint backgroundPaint;
    private Paint linePaint;

    private float progress;
    private float drawnProgress;

    private Disposable colorAccentSubscription;
    private Disposable textColorPrimarySubscription;

    public ProgressLineView(Context context) {
        this(context, null);
    }

    public ProgressLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(Color.LTGRAY);

        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(Color.DKGRAY);

        subscribe();
    }

    @Override
    public void subscribe() {
        colorAccentSubscription = Aesthetic.get()
                .colorAccent()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        linePaint.setColor(integer);
                        postInvalidate();
                    }
                });

        textColorPrimarySubscription = Aesthetic.get()
                .textColorPrimary()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        backgroundPaint.setColor(integer);
                        backgroundPaint.setAlpha(50);
                        postInvalidate();
                    }
                });
    }

    @Override
    public void unsubscribe() {
        colorAccentSubscription.dispose();
        textColorPrimarySubscription.dispose();
    }

    public void update(float progress) {
        Log.d("THIS", "updated: " + progress + ", " + drawnProgress);
        this.progress = progress;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drawnProgress != progress)
            drawnProgress = ((drawnProgress * 4) + progress) / 5;

        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawRect(0, 0, canvas.getWidth() * drawnProgress, canvas.getHeight(), linePaint);

        if ((drawnProgress - progress) * canvas.getWidth() != 0)
            postInvalidate();
    }
}