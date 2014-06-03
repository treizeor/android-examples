package com.example.ch003dialogs;

import java.util.Calendar;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements
			OnClickListener {

		private Button btn1, btn2, btn3, btn4, btn5;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			btn1 = (Button) rootView.findViewById(R.id.btnDialog1);
			btn2 = (Button) rootView.findViewById(R.id.btnDialog2);
			btn3 = (Button) rootView.findViewById(R.id.btnDialog3);
			btn4 = (Button) rootView.findViewById(R.id.btnDialog4);
			btn5 = (Button) rootView.findViewById(R.id.btnDialog5);
			btn1.setOnClickListener(this);
			btn2.setOnClickListener(this);
			btn3.setOnClickListener(this);
			btn4.setOnClickListener(this);
			btn5.setOnClickListener(this);

			return rootView;
		}

		final DialogInterface.OnClickListener closeDialog = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		};

		final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				view.updateDate(year, monthOfYear + 1, dayOfMonth);
			}
		};
		
		final TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				view.setCurrentHour(hourOfDay);
				view.setCurrentMinute(minute);
			}
		};

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnDialog1:
				// Toast.makeText(getActivity(), "hhhha",
				// Toast.LENGTH_SHORT).show();
				AlertDialog.Builder altBuilder = new AlertDialog.Builder(
						getActivity());
				altBuilder.setIcon(R.drawable.ic_launcher);
				altBuilder.setTitle("�ҵı���");
				altBuilder.setMessage("My messages");
				altBuilder.setNegativeButton("��", closeDialog)
						.setPositiveButton("��", closeDialog);
				altBuilder.create();
				altBuilder.show();
				break;
			case R.id.btnDialog2:
				//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				Toast.makeText(getActivity(), "hhhha", Toast.LENGTH_SHORT).show();
				Calendar c = Calendar.getInstance();
				DatePickerDialog datePickerDialog = new DatePickerDialog(
						getActivity(), dateSetListener, c.get(Calendar.YEAR),
						c.get(Calendar.MONTH), c.get(Calendar.DATE));
				datePickerDialog.setTitle("����ѡ����");
				datePickerDialog.setIcon(R.drawable.ic_launcher);
				datePickerDialog.setMessage("ʱ���С͵");
				datePickerDialog.setButton(AlertDialog.BUTTON_POSITIVE, "��",
						closeDialog);
				datePickerDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "��",
						closeDialog);
				datePickerDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ȡ��",
						closeDialog);

				datePickerDialog.show();
				break;
			case R.id.btnDialog3:
				Toast.makeText(getActivity(), "btnDialog3", Toast.LENGTH_SHORT).show();
				Calendar calendar = Calendar.getInstance();
				TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
				timePickerDialog.setTitle("ʱ��ѡ����");
				timePickerDialog.setMessage("ʱ�䰡��������!");
				timePickerDialog.setIcon(R.drawable.ic_launcher);
//				timePickerDialog.setButton("��", timePickerDialog);
				timePickerDialog.setButton(AlertDialog.BUTTON_POSITIVE, "��", closeDialog);
				timePickerDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "��", closeDialog);
				timePickerDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ȡ��", closeDialog);
				timePickerDialog.show();
				break;
			case R.id.btnDialog4:
				Toast.makeText(getActivity(), "btnDialog4", Toast.LENGTH_SHORT).show();
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.setIcon(R.drawable.ic_launcher);
				alertDialog.setTitle("alertDialog��ʽʵ��");
				alertDialog.setMessage("����һֻСë¿���Ҵ���Ҳ����.");
				alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ȡ��", closeDialog);
				alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "��", closeDialog);
				alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "��", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(), "DialogInterface.OnClickListener()����",Toast.LENGTH_SHORT).show();
					}
				});
				alertDialog.show();
				break;
			case R.id.btnDialog5:
				Toast.makeText(getActivity(), "btnDialog5", Toast.LENGTH_SHORT).show();
				ProgressDialog progressDialog = new ProgressDialog(getActivity());
				progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				progressDialog.setMax(100);
				progressDialog.setIndeterminate(false);
				progressDialog.setTitle("�������Ի���");
				progressDialog.setMessage("���ڼ�������...");
				progressDialog.setIcon(R.drawable.ic_launcher);
				progressDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "��", closeDialog);
				progressDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ȡ��", closeDialog);
				progressDialog.setButton(AlertDialog.BUTTON_POSITIVE, "��", closeDialog);
				progressDialog.show();
				//����ProgerssDialog���ȣ����������֮�������Ч
				progressDialog.setProgress(42);
				progressDialog.setSecondaryProgress(13);
				break;

			}
		}

	}

}
