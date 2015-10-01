package com.tingler.challenge.fragment.createchallenge;

import com.tingler.challenge.R;
import com.tingler.challenge.contacts.ContactListService;
import com.tingler.challenge.fragment.EditProfile;
import com.tingler.challenge.util.Profile;
import com.tingler.challenge.util.Validations;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class Details extends Fragment implements OnClickListener {
	EditText etxt_title, etxt_description, etxt_days, etxt_hours, etxt_minutes;
	Button btn_next;

	// NumberPicker np_days,np_hours,np_minutes;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View createChalleneView = inflater.inflate(
				R.layout.fragment__createchallenge_details, container, false);

		Intent getContactIntent = new Intent(getActivity(),
				ContactListService.class);
		getActivity().startService(getContactIntent);

		init(createChalleneView);
		return createChalleneView;
	}

	public void init(View view) {
		etxt_title = (EditText) view.findViewById(R.id.etxt_title);
		etxt_days = (EditText) view.findViewById(R.id.etxt_days);
		etxt_hours = (EditText) view.findViewById(R.id.etxt_hours);
		etxt_minutes = (EditText) view.findViewById(R.id.etxt_minutes);
		etxt_description = (EditText) view.findViewById(R.id.etxt_description);
		btn_next = (Button) view.findViewById(R.id.btn_next);
		btn_next.setOnClickListener(this);
		setValue();
	}

	public void setValue() {
		etxt_title.setText(SetterGetter.getTitle());
		etxt_description.setText(SetterGetter.getDescription());
		etxt_days.setText(SetterGetter.getDays());
		etxt_hours.setText(SetterGetter.getHours());
		etxt_minutes.setText(SetterGetter.getMinutes());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String title = etxt_title.getText().toString().trim();
		String days = etxt_days.getText().toString().trim();
		String hours = etxt_hours.getText().toString().trim();
		String minutes = etxt_minutes.getText().toString().trim();
		String description = etxt_description.getText().toString().trim();
		String regularExpression = "[0-9]";

		if (v.getId() == R.id.btn_next) {

			if (title.length() > 0) {
				Validations.isError(etxt_title, false);
				if (description.length() > 0) {
					Validations.isError(etxt_description, false);
					/*
					 * if (days.matches(regularExpression)) {
					 * Validations.isError(etxt_days, false); if
					 * (hours.matches(regularExpression)) {
					 * Validations.isError(etxt_hours, false); if
					 * (minutes.matches(regularExpression)) {
					 * Validations.isError(etxt_minutes, false);
					 */
					if (days.length() > 0 || hours.length() > 0
							|| minutes.length() > 0) {

						if (timerValidation()) {

							SetterGetter.setTitle(title);
							
							SetterGetter.setDescription(description);
							SetterGetter.setDays(days);
							SetterGetter.setHours(hours);
							SetterGetter.setMinutes(minutes);
							FragmentManager fragmentManager = getFragmentManager();
							fragmentManager
									.beginTransaction()
									.replace(R.id.frame_container,
											new Challengee()).commit();

						} else {
							Toast.makeText(getActivity(), " Invalid Time ",
									Toast.LENGTH_LONG).show();
						}

					} else {
						Toast.makeText(getActivity(),
								"Please enter days or hours or minutes",
								Toast.LENGTH_LONG).show();
					}

					/*
					 * }else{ Validations.isError(etxt_minutes, true); } }else{
					 * Validations.isError(etxt_hours, true); }
					 * 
					 * }else{ Validations.isError(etxt_days, true); }
					 */
				} else {
					Validations.isError(etxt_description, true);
				}
			} else {
				Validations.isError(etxt_title, true);
			}

		}
	}

	public boolean timerValidation() {
		boolean check = false;
		try {
			int hours = 0, minutes = 0;
			
			if(etxt_hours.getText().toString().trim().length()>0){
				hours = Integer.parseInt(etxt_hours.getText().toString().trim());
			}
			if(etxt_minutes.getText().toString().trim().length()>0){
				minutes = Integer.parseInt(etxt_minutes.getText().toString().trim());
			}
			
			if (hours == 0 || hours < 24) {
					if (minutes == 0 || minutes < 60) {
						check = true;
					} else {
						check = false;
					}
				} else {
					check = false;
				
			}
		} catch (NumberFormatException e) {
			check = false;

		}
		return check;

	}
}
