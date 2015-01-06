package jiang.clocktimer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	Button buttonUpTime = null;
	Button buttonDownTime = null;
	Button buttonStopUp = null;
	Button buttonStopDown = null;
	TextView textUpTime = null;
	TextView textDownTime = null;
	TimePicker tp = null;
	SeekBar volumeBarUp = null;
	SeekBar volumeBarDown = null;
	AudioManager mAudioManager = null;
	public static int volumeValueUp = 0;
	public static int volumeValueDown = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.textUpTime = (TextView) findViewById(R.id.textUpTime);
		this.textDownTime = (TextView) findViewById(R.id.textDownTime);

		this.buttonUpTime = (Button) findViewById(R.id.buttonUTime);
		this.buttonDownTime = (Button) findViewById(R.id.buttonDownTime);

		this.buttonStopUp = (Button) findViewById(R.id.buttonStopUp);
		this.buttonStopDown = (Button) findViewById(R.id.buttonStopDown);

		this.tp = (TimePicker) findViewById(R.id.timePicker);
		this.tp.setIs24HourView(Boolean.TRUE);

		// Calendar c = Calendar.getInstance();
		// this.tp.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
		// this.tp.setCurrentMinute(c.get(Calendar.MINUTE));
		this.volumeBarUp = (SeekBar) findViewById(R.id.volumeBarUp);
		this.volumeBarDown = (SeekBar) findViewById(R.id.volumeBarDown);

		this.buttonUpTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String time = tp.getCurrentHour() + ":" + tp.getCurrentMinute();
				textUpTime.setText(time);
				AlarmHandler.setAlarmTimeUp(getApplication(),
						getTimeInMillis(tp));

			}
		});

		this.buttonDownTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String time = tp.getCurrentHour() + ":" + tp.getCurrentMinute();
				textDownTime.setText(time);
				AlarmHandler.setAlarmTimeDown(getApplication(),
						getTimeInMillis(tp));

			}
		});

		this.buttonStopUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlarmHandler.stopUp(getApplication());
			}
		});

		this.buttonStopDown.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlarmHandler.stopDown(getApplication());
			}
		});
		this.mAudioManager = (AudioManager) this
				.getSystemService(Context.AUDIO_SERVICE);
		int mVolume = this.mAudioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL); // 获取当前音乐音量
		int maxVolume = this.mAudioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
		this.volumeBarUp.setMax(maxVolume); // SEEKBAR设置为音量的最大阶数
		this.volumeBarUp.setProgress(mVolume); // 设置seekbar为当前音量进度
		this.volumeBarUp
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						volumeValueUp = progress;
						mAudioManager.setStreamVolume(
								AudioManager.STREAM_VOICE_CALL, progress,
								AudioManager.FLAG_SHOW_UI);
						// //拖动seekbar时改变音量
					}
				});

		this.volumeBarDown.setMax(maxVolume); // SEEKBAR设置为音量的最大阶数
		this.volumeBarDown.setProgress(mVolume); // 设置seekbar为当前音量进度
		this.volumeBarDown
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						volumeValueDown = progress;
						mAudioManager.setStreamVolume(
								AudioManager.STREAM_VOICE_CALL, progress,
								AudioManager.FLAG_SHOW_UI);
						// //拖动seekbar时改变音量
					}
				});
	}

	public long getTimeInMillis(TimePicker tp) {

		Calendar c = Calendar.getInstance();
		String time = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1)
				+ "-" + c.get(Calendar.DAY_OF_MONTH);
		time = time + "-" + tp.getCurrentHour() + ":" + tp.getCurrentMinute();

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd-hh:mm",
				Locale.getDefault());
		Date d;

		try {
			d = f.parse(time);
			long milliseconds = d.getTime();
			return milliseconds;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
