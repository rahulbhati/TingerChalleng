package com.tingler.challenge.fragment.createchallenge;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.tingler.challenge.adapter.ChallengeeAdapter;
import com.tingler.challenge.contacts.ManageContacts;
import com.tingler.challenge.util.ContactItem;

public class Challengee extends Fragment implements OnClickListener {
	Button btn_next;
	LinearLayout ly_add_challengee;
	ListView lv_challengee;
	ArrayList<ContactItem> tempArrayList;
	public static ChallengeeAdapter challengeeAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View challengeeView = inflater
				.inflate(R.layout.fragment__createchallenge_challengee,
						container, false);
		init(challengeeView);
		return challengeeView;
	}

	public void init(View view) {

		btn_next = (Button) view.findViewById(R.id.btn_next);
		lv_challengee = (ListView) view.findViewById(R.id.lv_challengee);
		btn_next.setOnClickListener(this);
		View footerView = ((LayoutInflater) getActivity().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.footer_challengee, null, false);
		ly_add_challengee = (LinearLayout) footerView
				.findViewById(R.id.ly_add_challengee);
		ly_add_challengee.setOnClickListener(this);
		lv_challengee.addFooterView(footerView);
		tempArrayList = new ArrayList<ContactItem>();
		tempArrayList = SetterGetter.getChallengeeTempArrayList();
		challengeeAdapter = new ChallengeeAdapter(getActivity(), tempArrayList);
		lv_challengee.setAdapter(challengeeAdapter);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_next) {

			ArrayList<ContactItem> arrayList = new ArrayList<ContactItem>();
			for (int i = 0; i < ManageContacts.getChallengeeContactArrayList()
					.size(); i++) {
				ContactItem object = new ContactItem();
				object = ManageContacts.getChallengeeContactArrayList().get(i);
				if (!object.isCheckboxstatus()) {
					arrayList.add(object);
				}
			}
			ManageContacts.setWitnessContactArrayList(arrayList);

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new Witness()).commit();

		} else if (v.getId() == R.id.ly_add_challengee) {
			DialogContacts contacts = new DialogContacts(getActivity());
			contacts.contactList("Choose Challengee");
		}
	}

	public class DialogContacts implements OnClickListener {

		ListView lv_contacts;
		Button btn_select, btn_cancel, btn_ok;
		Context context;
		DialogContactsAdapter dialogContactsAdapter;
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
			lv_contacts = (ListView) dialog.findViewById(R.id.lv_contacts);
			dialogContactsAdapter = new DialogContactsAdapter(context,ManageContacts.getChallengeeContactArrayList());
			lv_contacts.setAdapter(dialogContactsAdapter);
			btn_ok = (Button) dialog.findViewById(R.id.btn_ok);
			btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
			btn_ok.setOnClickListener(this);
			btn_cancel.setOnClickListener(this);
		}
        @Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v.getId() == R.id.btn_ok) {
             
				new LoadChallengee().execute();
				dialog.dismiss();

			} else if (v.getId() == R.id.btn_cancel) {
				dialog.dismiss();
			}
		}
	}

	public class DialogContactsAdapter extends BaseAdapter {

		// Declare Variables
		Context context;
		LayoutInflater inflater;
		private ArrayList<ContactItem> contactsArrayList = null;
		
		//public ArrayList<Boolean> checkBoxStatus;

		public DialogContactsAdapter(Context context,
				ArrayList<ContactItem> contactItem) {
			this.context = context;
			this.contactsArrayList = contactItem;
			/*checkBoxStatus = new ArrayList<Boolean>();
			for (int i = 0; contactsArrayList.size() > i; i++) {
				checkBoxStatus.add(i, contactsArrayList.get(i)
						.isCheckboxstatus());
			}*/
		}

		public class ViewHolder {
			TextView name;
			//TextView mobile;
			// TextView level;
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
			
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			View convertView = mInflater
					.inflate(R.layout.row_dialog_item, null);
			TextView name = (TextView) convertView.findViewById(R.id.txt_name);
			
			CheckBox checkBox = (CheckBox) convertView
					.findViewById(R.id.checkBox);
			name.setText(contactsArrayList.get(position).getName());
			checkBox.setChecked(ManageContacts.getChallengeeContactArrayList()
					.get(position).isCheckboxstatus());
			checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					if (isChecked) {
					
						ManageContacts.getChallengeeContactArrayList()
								.get(position).setCheckboxstatus(isChecked);
					} else {
					
						ManageContacts.getChallengeeContactArrayList()
								.get(position).setCheckboxstatus(isChecked);
					}
					
				}
			});

			return convertView;
		}

	}

	public String getNumberByName(String name) {

		ContentResolver cr = getActivity().getContentResolver();
		Cursor c = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
						+ "='" + name + "'", null, null);
		String number = null;
		if (c.moveToNext()) {
			number = c
					.getString(c
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
		}

		c.close();
		return number;
	}

    public class LoadChallengee extends AsyncTask<String, String, String> {
		ProgressDialog progressDialog;
		ArrayList<ContactItem> challengeeArrayList = new ArrayList<ContactItem>();
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progressDialog = ProgressDialog.show(getActivity(), "", "loading...");
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
		   for(int i=0;i<ManageContacts.getCheckedChallengee().size();i++){
			   String name=ManageContacts.getCheckedChallengee().get(i).getName();
			   String mobile=getNumberByName(name);
			   if(mobile!=null){
				   ContactItem object=new ContactItem();
				   object.setName(name);
				   object.setMobile(mobile);
				   challengeeArrayList.add(object) ;
			   }
		   }
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			SetterGetter.setChallengeeTempArrayList(challengeeArrayList);
			
			challengeeAdapter = new ChallengeeAdapter(getActivity(),challengeeArrayList);
			lv_challengee.setAdapter(challengeeAdapter);
		    progressDialog.dismiss();
		
		}

	}
}
