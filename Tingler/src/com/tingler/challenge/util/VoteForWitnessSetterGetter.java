package com.tingler.challenge.util;

import java.util.ArrayList;

public class VoteForWitnessSetterGetter {
	public static ArrayList<WitnessForVote> voteForWitnessArrayList;

	public static ArrayList<WitnessForVote> getVoteForWitnessArrayList() {
		return voteForWitnessArrayList;
	}

	public static void setVoteForWitnessArrayList(
			ArrayList<WitnessForVote> voteForWitnessArrayList) {
		VoteForWitnessSetterGetter.voteForWitnessArrayList = voteForWitnessArrayList;
	}

}
