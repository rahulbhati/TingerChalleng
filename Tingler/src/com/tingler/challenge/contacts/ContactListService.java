package com.tingler.challenge.contacts;

import java.util.ArrayList;

import com.tingler.challenge.util.ContactItem;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class ContactListService extends IntentService {
	ArrayList<ContactItem> contctArrayList = new ArrayList<ContactItem>();
	public ContactListService() {
		super("ContactListService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		ContentResolver cr = getContentResolver();
		String[] selectionArgs = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
		String[] projection    = new String[] { ContactsContract.Contacts._ID,
                Phone.DISPLAY_NAME,
                Phone.NUMBER};
		//String sql="select "+projection+" from "+ContactsContract.Contacts.CONTENT_URI+" where "+Phone.DISPLAY_NAME+" is not null and "+Phone.NUMBER+" is not null";
	
		Cursor namecursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
				null, ContactsContract.Contacts.DISPLAY_NAME +" is not null and "+ContactsContract.Contacts.HAS_PHONE_NUMBER+" is not null and "+ContactsContract.Contacts.HAS_PHONE_NUMBER+" != 0", null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");
		
	/*	Cursor namecursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
				projection, null, null, Phone.DISPLAY_NAME + " ASC");*/
		while (namecursor.moveToNext()) {
			String contactName = null;
			contactName = namecursor.getString(namecursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
             
			
			if (contactName != null ) {
				ContactItem items = new ContactItem();
				items.setName(contactName);
				items.setCheckboxstatus(false);
				contctArrayList.add(items);
			}
		}
       ManageContacts.setPrimaryContactArrayList(contctArrayList);
       ManageContacts.setChallengeeContactArrayList(contctArrayList);
       ManageContacts.setWitnessContactArrayList(contctArrayList);
       
	}

}
