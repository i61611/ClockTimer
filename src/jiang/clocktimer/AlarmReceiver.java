package jiang.clocktimer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public class AlarmReceiver extends BroadcastReceiver {
	// public static int volumeValueUp = 0;
	// public static int volumeValueDown = 0;

	public void onReceive(Context context, Intent intent) {
		if ("android.alarm.clocktimer.action.up".equals(intent.getAction())) {
			// 第1步中设置的闹铃时间到，这里可以弹出闹铃提示并播放响铃
			// 可以继续设置下一次闹铃时间;

			AudioManager mAudioManager = (AudioManager) context
					.getSystemService(Context.AUDIO_SERVICE);
			mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
					intent.getIntExtra("volumeValueUp", 0),
					AudioManager.FLAG_SHOW_UI);
			return;
		}
		if ("android.alarm.clocktimer.action.down".equals(intent.getAction())) {
			// 第1步中设置的闹铃时间到，这里可以弹出闹铃提示并播放响铃
			// 可以继续设置下一次闹铃时间;

			AudioManager mAudioManager = (AudioManager) context
					.getSystemService(Context.AUDIO_SERVICE);
			mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
					intent.getIntExtra("volumeValueDown", 0),
					AudioManager.FLAG_SHOW_UI);
			return;
		}
	}
}