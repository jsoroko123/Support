package com.support.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appolissupport.R;
import com.support.adapters.PendingCasesAdapter;
import com.support.custom.CustomProgressBar;
import com.support.http.HttpClient;
import com.support.main.MainActivity;
import com.support.objects.PendingCases;
import com.support.utilities.Constants;
import com.support.utilities.DataParser;
import com.support.utilities.SharedPreferenceManager;
import com.support.utilities.Utilities;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class PendingFragment extends Fragment {

	private ArrayList<PendingCases> listItemInfo = new ArrayList<>();
	private ListView lvPendingCases;
	private PendingCasesAdapter pendingCasesAdapter = null;
	private TextView tvNoCases;
	private SharedPreferenceManager spm;

	public PendingFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		MainActivity.FragPageTitle = "Pending Cases";
		View rootView = inflater.inflate(R.layout.fragment_pending, container,
				false);
		lvPendingCases = (ListView) rootView.findViewById(R.id.list_pending_cases);
		tvNoCases = (TextView) rootView.findViewById(R.id.tvNoPendingCases);
		spm = new SharedPreferenceManager(getActivity());
		getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getActivity().getActionBar().setDisplayShowTitleEnabled(true);
        getActivity().getActionBar().setTitle(MainActivity.FragPageTitle);
		ListCasesAsyncCallWS as = new ListCasesAsyncCallWS(getActivity(), spm.getInt("ClientID",0));
		as.execute();
		return rootView;
	}

	private class ListCasesAsyncCallWS extends AsyncTask<String, Void, Integer> {

		Context context;
		int ClientID;
		String response;

		public ListCasesAsyncCallWS(Context mContext, int mClientID) {
			this.context = mContext;
			this.ClientID = mClientID;
		}

		@Override
		protected void onPreExecute() {
			listItemInfo.clear();
			super.onPreExecute();
			if (!isCancelled()) {
				CustomProgressBar.showProgressBar(context, false);
			}

		}

		@Override
		protected Integer doInBackground(String... params) {
			HttpClient client = new HttpClient();
			try {
				response = client.get(Constants.URL + "/api/PendingCase/Get?ClientID=" + String.valueOf(ClientID));

			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof SocketException
						|| e instanceof UnknownHostException
						|| e instanceof SocketTimeoutException
						|| e instanceof ConnectTimeoutException
						|| e instanceof ClientProtocolException) {
					return 1;
				} else {
					return 2;
				}
			}
			listItemInfo = DataParser.getPendingCases(response);
			return 0;
		}

		@Override
		protected void onPostExecute(Integer result) {
			CustomProgressBar.hideProgressBar();
			if (result == 1) {
				Utilities.ShowDialog("Network Error", Constants.ERROR_CONNECTION, context);
			} else if (result == 2) {
				Utilities.ShowDialog("Error", Constants.DEFAULT_ERROR_MSG, context);
			} else {
				pendingCasesAdapter = new PendingCasesAdapter(context,
						listItemInfo);
				lvPendingCases.setAdapter(pendingCasesAdapter);
				pendingCasesAdapter.notifyDataSetChanged();

				if (listItemInfo.isEmpty()) {
					tvNoCases.setVisibility(View.VISIBLE);
				} else {
					tvNoCases.setVisibility(View.GONE);
				}
			}
		}
	}
	
}
