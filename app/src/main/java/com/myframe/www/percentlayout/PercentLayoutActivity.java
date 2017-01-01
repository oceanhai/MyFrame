package com.myframe.www.percentlayout;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myframe.www.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 百分比布局
 */
public class PercentLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.btn02)
    Button btn02;
    @Bind(R.id.btn03)
    Button btn03;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PercentLayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent_layout);
        ButterKnife.bind(this);

        initLitener();
        init();
    }

    private void init() {

    }

    private void initLitener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                getFragmentManager().beginTransaction().replace(R.id.container,PlaceholderFragment.newInstance(1)).commit();
                break;
            case R.id.btn02:
                getFragmentManager().beginTransaction().replace(R.id.container,PlaceholderFragment.newInstance(2)).commit();
                break;
            case R.id.btn03:
                getFragmentManager().beginTransaction().replace(R.id.container,PlaceholderFragment.newInstance(3)).commit();
                break;
        }
    }

    public static class PlaceholderFragment extends Fragment
    {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber)
        {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment()
        {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            int nb_frag = getArguments().getInt(ARG_SECTION_NUMBER);

            int resID = getActivity().getResources().getIdentifier("view" + String.valueOf(nb_frag), "layout", getActivity().getPackageName());
            View rootView = inflater.inflate(resID, container, false);

            return rootView;
        }

    }
}
