package com.tingler.challenge.fragment.createchallenge;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tingler.challenge.R;
import com.tingler.challenge.adapter.WitnessAdapter;
import com.tingler.challenge.contacts.ManageContacts;
import com.tingler.challenge.util.ContactItem;

public class Witness extends Fragment implements OnClickListener {
	Button btn_next;
	LinearLayout ly_add_witness;
	ListView lv_witness;
	ArrayList<ContactItem> tempArrayList;
	public WitnessAdapter witnessAdapter;
	int witnessCount = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View challengeeView = inflater.inflate(
				R.layout.fragment__createchallenge_witness, container, false);
		init(challengeeView);
		return challengeeView;
	}

	public void init(View view) {

		btn_next = (Button) view.findViewById(R.id.btn_next);

		lv_witness = (ListView) view.findViewById(R.id.lv_witness);

		btn_next.setOnClickListener(this);
		View footerView = ((LayoutInflater) getActivity().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.footer_witness, null, false);
		ly_add_witness = (LinearLayout) footerView
				.findViewById(R.id.ly_add_witness);
		ly_add_witness.setOnClickListener(this);
		lv_witness.addFooterView(footerView);
		tempArrayList = new ArrayList<ContactItem>();
		tempArrayList = SetterGetter.getWitnessTempArrayList();
		witnessAdapter = new WitnessAdapter(getActivity(), tempArrayList);
		lv_witness.setAdapter(witnessAdapter);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_next) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new Price()).commit();
		} else if (v.getId() == R.id.ly_add_witness) {
			DialogContacts contacts = new DialogContacts(getActivity());
			contacts.contactList("Choose Witness");
		}
	}

	public class DialogContacts implements OnClickListener {
		// EditText etxt_search;
		ListView lv_contacts;
		Button btn_select, btn_cancel, btn_ok;
		Context context;
		ContactsAdapter contactsAdapter;
		ArrayList<ContactItem> contactsArrayList;;
		Dialog dialog;

		public DialogContacts(Context context) {
			this.context = context;
		}

		public void contactList(String title) {
			dialog = new Dialog(context);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.dialog_contact);
			init(dialog);
			dialog.show();

		}

		public void init(Dialog dialog) {
			contactsArrayList = new ArrayList<ContactItem>();
			lv_contacts = (ListView) dialog.findViewById(R.id.lv_contacts);
			contactsAdapter = new ContactsAdapter(context,
					ManageContacts.getWitnessContactArrayList());
			lv_contacts.setAdapter(contactsAdapter);
			dialog.show();
			btn_ok = (Button) dialog.findViewById(R.id.btn_ok);
			btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
			btn_ok.setOnClickListener(this);
			btn_cancel.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v.getId() == R.id.btn_ok) {

				tempArrayList = new ArrayList<ContactItem>();
				tempArrayList = SetterGetter.getWitnessTempArrayList();
				witnessAdapter = new WitnessAdapter(getActivity(),
						tempArrayList);
				lv_witness.setAdapter(witnessAdapter);
				dialog.dismiss();
			} else if (v.getId() == R.id.btn_cancel) {
				dialog.dismiss();
			}
		}
	}

	public class ContactsAdapter extends BaseAdapter {
        Context context;
		LayoutInflater inflater;
		private ArrayList<ContactItem> contactsArrayList = null;
		private ArrayList<ContactItem> arraylist;
		public ArrayList<Boolean> checkBoxStatus;
        public ContactsAdapter(Context context,
				ArrayList<ContactItem> contactItem) {
			this.context = context;
			this.contactsArrayList = ManageContacts
					.getWitnessContactArrayList();
			checkBoxStatus = new ArrayList<Boolean>();
			for (int i = 0; contactsArrayList.size() > i; i++) {
				checkBoxStatus.add(i, contactsArrayList.get(i)
						.isCheckboxstatus());
			}
		}
       public class ViewHolder {
			TextView name;
			TextView mobile;
			ImageView flag;
		}

		@Override
		public int getCount() {
			return contactsArrayList.size();
		}

		@Override
		public ContactItem getItem(int position) {
			return contactsArrayList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView1,
				ViewGroup parent) {
			// final ViewHolder holder;

			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			View convertView = mInflater
					.inflate(R.layout.row_dialog_item, null);
			TextView name = (TextView) convertView.findViewById(R.id.txt_name);
			TextView mobile = (TextView) convertView
					.findViewById(R.id.txt_mobile);
			CheckBox checkBox = (CheckBox) convertView
					.findViewById(R.id.checkBox);
			name.setText(contactsArrayList.get(position).getName());
			mobile.setText(contactsArrayList.get(position).getMobile());
			checkBox.setChecked(checkBoxStatus.get(position));
			checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
				 
					if (isChecked) {
						
						
						checkBoxStatus.set(position, isChecked);
						ManageContacts.getWitnessContactArrayList()
								.get(position).setCheckboxstatus(isChecked);

					} else {
						checkBoxStatus.set(position, false);
						ManageContacts.getWitnessContactArrayList()
								.get(position).setCheckboxstatus(isChecked);

					}
					ArrayList<ContactItem> tempArrayList = new ArrayList<ContactItem>();
					for (int i = 0; i < checkBoxStatus.size(); i++) {

						if (checkBoxStatus.get(i) == true) {
							ContactItem contactItem = new ContactItem(
									contactsArrayList.get(i).getName(),
									contactsArrayList.get(i).getMobile(), "1",
									position);
							tempArrayList.add(contactItem);
						}
					}
					SetterGetter.setWitnessTempArrayList(tempArrayList);

				}
			});

			return convertView;
		}

	}

}
