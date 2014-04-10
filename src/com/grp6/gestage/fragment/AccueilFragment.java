package com.grp6.gestage.fragment;

import java.util.HashMap;

import com.grp6.gestage.R;
import com.grp6.gestage.library.DatabaseHandler;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class AccueilFragment extends Fragment {

	private TextView bienvenue;
	private ImageView imageView2;

	public AccueilFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_accueil, container,
				false);
		bienvenue = (TextView) rootView.findViewById(R.id.twBienvenue);
		DatabaseHandler db = new DatabaseHandler(getActivity());
		HashMap<String, String> user = db.getUserDetails();
		bienvenue.setText("Bienvenue " + user.get("prenom") + " "
				+ user.get("nom"));

		return rootView;
	}

}