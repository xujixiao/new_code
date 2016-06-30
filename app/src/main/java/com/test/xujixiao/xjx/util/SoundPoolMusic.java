package com.test.xujixiao.xjx.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPoolMusic {

	private SoundPool soundPool;
	private Context context;

	private Integer voiceInteger;

	public SoundPoolMusic(Context context) {
		this.context = context;
		this.soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		this.voiceInteger = soundPool
				.load(context, ResourceUtils.getIdByName(context,
						"rev_user_mes_sound", "raw"), 1);
	}

	/**
	 * 播放声音
	 * 
	 * @param number
	 *            0不循环，-1永远循环
	 */
	public void playSound(int number) {
		if (null != context && null != soundPool) {
			AudioManager manager = (AudioManager) context
					.getSystemService(Context.AUDIO_SERVICE);
			if (null != manager) {
				final float maxVolumn = manager
						.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				final float currentVolumn = manager
						.getStreamVolume(AudioManager.STREAM_MUSIC);
				final float volumnRatio = currentVolumn / maxVolumn;

				soundPool.play(voiceInteger, volumnRatio, volumnRatio, 1,
						number, 1);
			}
		}
	}

}
