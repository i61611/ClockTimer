package jiang.clocktimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmHandler {

	public static PendingIntent senderUp = null;
	public static PendingIntent senderDown = null;

	public static void setAlarmTimeUp(Context context, long timeInMillis) {
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent("android.alarm.clocktimer.action.up");
		intent.putExtra("volumeValueUp", MainActivity.volumeValueUp);
		senderUp = PendingIntent.getBroadcast(context, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		int interval = 24 * 60 * 60 * 1000;// 闹铃间隔，
											// 这里设为1分钟闹一次，在第2步我们将每隔1分钟收到一次广播
		am.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, interval,
				senderUp);
	}

	public static void setAlarmTimeDown(Context context, long timeInMillis) {
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent("android.alarm.clocktimer.action.down");
		intent.putExtra("volumeValueDown", MainActivity.volumeValueDown);
		senderDown = PendingIntent.getBroadcast(context, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		int interval = 24 * 60 * 60 * 1000;// 闹铃间隔，
											// 这里设为1分钟闹一次，在第2步我们将每隔1分钟收到一次广播
		am.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, interval,
				senderDown);
	}

	public static void stopUp(Context context) {
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		am.cancel(senderUp);
	}

	public static void stopDown(Context context) {
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		am.cancel(senderDown);
	}
}
