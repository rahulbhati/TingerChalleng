package com.tingler.challenge.adapter;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import com.tingler.challenge.R;
import com.tingler.challenge.util.ProfileMemberItems;

public class ChallengeAdapter extends BaseAdapter {

	Context context;

	ArrayList<ProfileMemberItems> challengeMemberArrayList = null;

	public ChallengeAdapter(Context context,
			ArrayList<ProfileMemberItems> contactItem) {
		this.context = context;
		this.challengeMemberArrayList = contactItem;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.challengeMemberArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.challengeMemberArrayList.get(position);

	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = mInflater.inflate(R.layout.row_challenge_member, null);

		ProgressBar progressbar_profileLevel = (ProgressBar) view
				.findViewById(R.id.progressbar_profileLevel);
		int level = challengeMemberArrayList.get(position)
				.getProgressBarLevel();
		progressbar_profileLevel.setProgress(level);
		if (level > 50) {
           setProgressBarLineColor(progressbar_profileLevel, R.drawable.style_progresss_line_second);
		}else{
			 setProgressBarLineColor(progressbar_profileLevel, R.drawable.style_progresss_line_first);
		}
		

		return view;
	}

	public void setProgressBarLineColor(ProgressBar view,int style){
		final int sdk = android.os.Build.VERSION.SDK_INT;
		if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			view.setProgressDrawable(context
					.getResources().getDrawable(
							style));
		} else {
			view.setProgressDrawable(context
					.getResources().getDrawable(
							style));

		}
	}
	
}
