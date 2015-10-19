package com.tingler.challenge.util;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.tingler.challenge.R;

public class EditChallenge implements OnClickListener {
	Context context;
	Dialog dialog;
	EditText etxt_title, etxt_description;
	Button btn_cancel,btn_submit;

	public EditChallenge(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	public void showDialogBox(){
		 dialog =new Dialog(context);
		 dialog.setContentView(R.layout.dialogbox_edit_challenge);
		 etxt_title=(EditText)dialog.findViewById(R.id.etxt_title);
		 etxt_description=(EditText)dialog.findViewById(R.id.etxt_description);
		 
		 GetChallengeDetailsItems getChallengeDetailsItems=new GetChallengeDetailsItems();
		 etxt_title.setText(getChallengeDetailsItems.getTitle());
		 etxt_description.setText(getChallengeDetailsItems.getDescription());
		 btn_cancel=(Button)dialog.findViewById(R.id.btn_cancel);
		 btn_submit=(Button)dialog.findViewById(R.id.btn_submit);
		 btn_cancel.setOnClickListener(this);
		 btn_submit.setOnClickListener(this);
		 
		 dialog.show();
	}
	public void dismissDialogBox(){
		dialog.dismiss();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_cancel){
			dismissDialogBox();
		}
	}
}
