package www.wuhai.fragmentdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LeftFragment extends Fragment {

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);

	}

	//创建Fragment视图
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_left, null);
		return view;
	}


}
