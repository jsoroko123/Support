package com.support.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.support.main.MainActivity;
import com.example.appolissupport.R;

import java.util.Random;

public class StatsFragment extends Fragment {
	BarGraphSeries<DataPoint> series;
	
	public StatsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		MainActivity.FragPageTitle = "Statistics";
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);

		GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
		series = new BarGraphSeries<>(generateData());
		graph.addSeries(series);
		series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
			@Override
			public int get(DataPoint data) {
				return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
			}
		});

		series.setSpacing(50);
		series.setDrawValuesOnTop(true);
		series.setValuesOnTopColor(Color.RED);

		StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
		staticLabelsFormatter.setHorizontalLabels(new String[]{"J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D"});
		graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

		getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getActivity().getActionBar().setDisplayShowTitleEnabled(true);
        getActivity().getActionBar().setTitle(MainActivity.FragPageTitle);
		return rootView;
	}


	private DataPoint[] generateData() {
		int count = 12;
		DataPoint[] values = new DataPoint[count];
		for (int i=0; i<count; i++) {
			double x = i;
			double f = mRand.nextDouble()*0.15+0.3;
			double y = Math.sin(i*f+2) + mRand.nextDouble()*0.3;
			DataPoint v = new DataPoint(x, y);
			values[i] = v;
		}
		return values;
	}

	double mLastRandom = 2;
	Random mRand = new Random();
	private double getRandom() {
		return mLastRandom += mRand.nextDouble()*0.5 - 0.25;
	}

}