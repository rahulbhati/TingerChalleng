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
import android.provider.ContactsContract.CommonDataKinds.Phone;
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

public class Challengee extends Fragment implements OnClickListener{
	Button btn_next;
	LinearLayout ly_add_challengee;
    ListView lv_challengee;
    ArrayList<ContactItem> tempArrayList;
    public static ChallengeeAdapter challengeeAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View challengeeView=inflater.inflate(R.layout.fragment__createchallenge_challengee,container,false);
		init(challengeeView);
		return challengeeView;
	}
	public void init(View view)
	{
		
		btn_next=(Button)view.findViewById(R.id.btn_next);
	
		lv_challengee=(ListView)view.findViewById(R.id.lv_challengee);
		
		btn_next.setOnClickListener(this);
		View footerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_challengee, null, false);
		ly_add_challengee=(LinearLayout)footerView.findViewById(R.id.ly_add_challengee);
		ly_add_challengee.setOnClickListener(this);
	    lv_challengee.addFooterView(footerView);
	    tempArrayList=new ArrayList<ContactItem>();
		tempArrayList=SetterGetter.getChallengeeTempArrayList();
		challengeeAdapter=new ChallengeeAdapter(getActivity(), tempArrayList);
		lv_challengee.setAdapter(challengeeAdapter);;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_next){
			
			ArrayList<ContactItem> arrayList=new ArrayList<ContactItem>();
			for(int i=0;i<ManageContacts.getChallengeeContactArrayList().size();i++){
				 ContactItem object=new ContactItem();
				 object=ManageContacts.getChallengeeContactArrayList().get(i);
				if(!object.isCheckboxstatus()){
					arrayList.add(object);
				}
			}
			ManageContacts.setWitnessContactArrayList(arrayList);
			
			
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new Witness()).commit();
			
		}else if(v.getId()==R.id.ly_add_challengee){
			 DialogContacts contacts=new DialogContacts(getActivity());
			  contacts.contactList("Choose Challengee") ;
			}
	}
	
	

public class DialogContacts implements OnClickListener {
//	EditText etxt_search;
	ListView lv_contacts;
	Button btn_select, btn_cancel,btn_ok;
	Context context;
	ContactsAdapter contactsAdapter;
	ArrayList<ContactItem> contactsArrayList;;
	Dialog dialog ;
	public DialogContacts(Context context) {
		this.context = context;
}
	public void contactList(String title) {
	 dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_contact);
	//	dialog.setTitle(title);
		init(dialog);
	}

	public void init(Dialog dialog) {
	lv_contacts = (ListView) dialog.findViewById(R.id.lv_contacts);
		if(ManageContacts.getChallengeeContactArrayList()!=null ){
			contactsAdapter = new ContactsAdapter(context, ManageContacts.getChallengeeContactArrayList());
			lv_contacts.setAdapter(contactsAdapter);
			dialog.show();
		}else
		{
			new ContactsAsy().execute();
		}
		btn_ok=(Button)dialog.findViewById(R.id.btn_ok);
		btn_cancel=(Button)dialog.findViewById(R.id.btn_cancel);
		btn_ok.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
	}

	public class ContactsAsy extends AsyncTask<String, String, String> {
		ProgressDialog progressDialog;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progressDialog = ProgressDialog.show(context, "", "loading...");
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			/*Cursor c = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
			while(c.moveToNext()==true)
			{
				String name =null;
				String phoneNumber=null ;
				name=c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				phoneNumber=c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				
				if(name.length()>0 && phoneNumber.length()>0){
				ContactItem contactItem=new ContactItem(name, phoneNumber, "");
				contactsArrayList.add(contactItem);
				}
				
			}
			c.close();*/
			ArrayList<ContactItem> contctArrayList = new ArrayList<ContactItem>();
			ContentResolver cr = getActivity(). getContentResolver();
			Cursor namecursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
					null, null, null, Phone.DISPLAY_NAME + " ASC");

			while (namecursor.moveToNext()) {
				String contactName=null, contactNumber=null;
				contactName = namecursor.getString(namecursor
						.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				Cursor numberCursor = cr.query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
						ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "='"
								+ contactName + "'", null, null);
				if (numberCursor.moveToNext()) {
					contactNumber = numberCursor
							.getString(numberCursor
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

				} 
				if(contactName!=null && contactNumber!=null){
				ContactItem items = new ContactItem();
				items.setName(contactName);
				items.setMobile(contactNumber);
				items.setCheckboxstatus(false);
				
				contctArrayList.add(items);
				}
				numberCursor.close();
			}
			namecursor.close();
			ManageContacts.setPrimaryContactArrayList(contctArrayList);
			ManageContacts.setChallengeeContactArrayList(contctArrayList);
			
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();
			contactsAdapter = new ContactsAdapter(context, ManageContacts.getChallengeeContactArrayList());
			lv_contacts.setAdapter(contactsAdapter);
			dialog.show();
		}

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_ok){
			
			tempArrayList=new ArrayList<ContactItem>();
			tempArrayList=SetterGetter.getChallengeeTempArrayList();
			challengeeAdapter=new ChallengeeAdapter(getActivity(), SetterGetter.getChallengeeTempArrayList());
			lv_challengee.setAdapter(challengeeAdapter);;
			dialog.dismiss();
		
		}else if(v.getId()==R.id.btn_cancel){
			dialog.dismiss();
		}
	}
}
public class ContactsAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	private ArrayList<ContactItem> contactsArrayList = null;
	private ArrayList<ContactItem> arraylist;
	public ArrayList<Boolean> checkBoxStatus;
	public ContactsAdapter(Context context,
			ArrayList<ContactItem> contactItem) {
		this.context = context;
		this.contactsArrayList = ManageContacts.getChallengeeContactArrayList();
		checkBoxStatus=new ArrayList<Boolean>();
		for(int i=0;contactsArrayList.size()>i;i++){
			checkBoxStatus.add(i, contactsArrayList.get(i).isCheckboxstatus());
		}
	}
   public class ViewHolder {
		TextView name;
		TextView mobile;
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

	public View getView(final int position, View convertView1, ViewGroup parent) {
	//	final ViewHolder holder;
		 LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View convertView = mInflater.inflate(R.layout.row_dialog_item, null);
		TextView name = (TextView) convertView.findViewById(R.id.txt_name);
		TextView mobile = (TextView) convertView.findViewById(R.id.txt_mobile);
        CheckBox checkBox=(CheckBox)convertView.findViewById(R.id.checkBox);
		name.setText(contactsArrayList.get(position).getName());
		mobile.setText(contactsArrayList.get(position).getMobile());
		checkBox.setChecked(checkBoxStatus.get(position));
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					checkBoxStatus.set(position, isChecked);
				    ManageContacts.getChallengeeContactArrayList().get(position).setCheckboxstatus(isChecked);
				}else{
					checkBoxStatus.set(position, false);
					   ManageContacts.getChallengeeContactArrayList().get(position).setCheckboxstatus(isChecked);
				}
				ArrayList<ContactItem> tempArrayList=new ArrayList<ContactItem>();
				for (int i = 0; i < checkBoxStatus.size(); i++) {
					if(checkBoxStatus.get(i)==true){
						ContactItem contactItem=new ContactItem(contactsArrayList.get(i).getName(),contactsArrayList.get(i).getMobile(), "1",position);
						tempArrayList.add(contactItem);
					}
				}
				SetterGetter.setChallengeeTempArrayList(tempArrayList);
			}
		});
		
		return convertView;
	}

}

}
