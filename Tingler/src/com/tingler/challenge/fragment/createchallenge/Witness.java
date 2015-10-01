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
import android.widget.Toast;
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
	
	 private static int witnesCount=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View witnessView = inflater.inflate(
				R.layout.fragment__createchallenge_witness, container, false);
		init(witnessView);
		return witnessView;
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
			contacts.contactList("Choose witness");
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
			dialogContactsAdapter = new DialogContactsAdapter(context,ManageContacts.getWitnessContactArrayList());
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
				if(witnesCount<6){
				
             new LoadWitness().execute();
				dialog.dismiss();
				}else{
					Toast.makeText(getActivity(), "Sorry ! you can add up to 5 witness", Toast.LENGTH_LONG).show();
				}

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
			checkBox.setChecked(ManageContacts.getWitnessContactArrayList()
					.get(position).isCheckboxstatus());
			checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					if (isChecked) {
						ManageContacts.getWitnessContactArrayList()
								.get(position).setCheckboxstatus(isChecked);
						witnesCount=witnesCount+1;
					} else {
						ManageContacts.getWitnessContactArrayList()
								.get(position).setCheckboxstatus(isChecked);
						witnesCount=witnesCount-1;
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

    public class LoadWitness extends AsyncTask<String, String, String> {
		ProgressDialog progressDialog;
		ArrayList<ContactItem> witnessArrayList = new ArrayList<ContactItem>();
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progressDialog = ProgressDialog.show(getActivity(), "", "loading...");
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
		   for(int i=0;i<ManageContacts.getCheckedWitness().size();i++){
			   String name=ManageContacts.getCheckedWitness().get(i).getName();
			   String mobile=getNumberByName(name);
			   if(mobile!=null){
				   ContactItem object=new ContactItem();
				   object.setName(name);
				   object.setMobile(mobile);
				   witnessArrayList.add(object) ;
			   }
		   }
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			SetterGetter.setWitnessTempArrayList(witnessArrayList);
			
			witnessAdapter = new WitnessAdapter(getActivity(),witnessArrayList);
			lv_witness.setAdapter(witnessAdapter);
		    progressDialog.dismiss();
		
		}

	}
}
