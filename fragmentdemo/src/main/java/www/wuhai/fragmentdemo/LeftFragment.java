package www.wuhai.fragmentdemo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.wuhai.common.utils.L;

public class LeftFragment extends Fragment {

	public static String TAG = "LeftFragment";

//	@Override
//	public void onAttach(Context context) {
//		super.onAttach((Activity)context);
//		L.v(TAG, "onAttach");
//	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		L.v(TAG, "onCreate");
	}

	//创建Fragment视图
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		L.v(TAG, "onCreateView");
		View view = inflater.inflate(R.layout.fragment_left, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		L.v(TAG, "onActivityCreated");
	}

	@Override
	public void onStart() {
		super.onStart();
		L.v(TAG, "onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		L.v(TAG, "onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		L.v(TAG, "onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		L.v(TAG, "onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		L.v(TAG, "onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		L.v(TAG, "onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		L.v(TAG, "onDetach");
	}
}
