package com.test.xujixiao.xjx.util.update.friend.util;
import android.animation.TypeEvaluator;

public class KickBackAnimator implements TypeEvaluator<Float> {
	float mDuration = 0f;

	public void setDuration(float duration) {
		mDuration = duration;
	}

	public Float evaluate(float fraction, Float startValue, Float endValue) {
		float t = mDuration * fraction;
		float b = startValue.floatValue();
		float c = endValue.floatValue() - startValue.floatValue();
		float d = mDuration;
		return calculate(t, b, c, d);
	}

	public Float calculate(float t, float b, float c, float d) {
		float s = 1.70158f;
		return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
	}
}
