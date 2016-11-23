package com.myframe.www.yxt01;

/**
 * Created by bobby on 16/6/6.
 */
//public class GoodsFragment extends BaseFragment implements XMBaseAdapter.OnLoadMoreListener, XMBaseAdapter.OnItemClickListener {
//    public static final int ITEM_NUM_PER_PAGE = 1;
//    private static final String ARG_TYPE = "arg_type";
//    private static final String ARG_TOP_REGION = "arg_top_region";
//    private static final String ARG_SUB_REGION = "arg_sub_region";
//
//
//    private int mType;
//    private int mTopRegion;
//    private int mSubRegion;
//
//    private RecyclerView recycle;
//    private GoodsAdapter mAdapter;
//
//    private Handler handler = new Handler();
//    private int page = 0;//页
//
//    public static GoodsFragment newInstance(int type, int t_r, int s_r) {
//        Bundle args = new Bundle();
//        args.putInt(ARG_TYPE, type);
//        args.putInt(ARG_TOP_REGION, t_r);
//        args.putInt(ARG_SUB_REGION, s_r);
//
//        GoodsFragment fragment = new GoodsFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Bundle args = getArguments();
//        if (args != null) {
//            mType = args.getInt(ARG_TYPE);
//            mTopRegion = args.getInt(ARG_TOP_REGION);
//            mSubRegion = args.getInt(ARG_SUB_REGION);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_goods_layout, container, false);
//
//        initView(view);
//        initData();
//        return view;
//    }
//
//    private void initData() {
//        Callback<BaseData> callback = new Callback<BaseData>() {
//            public void handleCallback(BaseData result) {
//                Index2Entity entity = (Index2Entity) result.getData();
//                if (entity != null) {
//                    List<Index2Entity.DataBean> bean = entity.getData();
//                    Iterator<Index2Entity.DataBean> it = bean.iterator();
//                    while (it.hasNext()) {
//                        PlasticCategoryDataModel model = new PlasticCategoryDataModel();
//                        Index2Entity.DataBean bean_data = it.next();
//                        model.setDoctor("yy");
//                        model.setHostpital(bean_data.getYm_hospital_name());
//                        model.setTitle(bean_data.getPro_ltitle());
//                        model.setCountNum(bean_data.getOrder_total());
//                        model.setImg(bean_data.getSmall_pic());
//                        model.setMaxMoney(bean_data.getOriginal_price());
//                        model.setMoney(bean_data.getRv_price());
//                        mAdapter.add(model);
//                    }
//                }
//            }
//        };
//        new Index2Interactor(null,
//                null, callback, mTopRegion, mSubRegion, mType, "pro_id", page, GoodsFragment.ITEM_NUM_PER_PAGE).execute();
//    }
//
//    private void initView(View view) {
//        recycle = (RecyclerView) view.findViewById(R.id.recy);
//
//        mAdapter = new GoodsAdapter(_mActivity.get());
//        LinearLayoutManager manager = new LinearLayoutManager(_mActivity.get());
//        recycle.setLayoutManager(manager);
//        recycle.setAdapter(mAdapter);
//        mAdapter.setMore(R.layout.view_listview_more, this);
//        mAdapter.setNoMore(R.layout.view_listview_nomore);
//        mAdapter.setError(R.layout.view_listview_error);
//
//        mAdapter.setOnItemClickListener(this);
//
//        //String json = CommonUtils.getFromAssets("PlasticCategory.json", getActivity().getApplicationContext());
//        //PlasticCategoryDataResult data = GsonUtils.getInstance().toObj(json, PlasticCategoryDataResult.class);
//    }
//
//    @Override
//    public void onLoadMore() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //String json = CommonUtils.getFromAssets("PlasticCategory1.json", getActivity().getApplicationContext());
//                //PlasticCategoryDataResult data = GsonUtils.getInstance().toObj(json, PlasticCategoryDataResult.class);
//                //刷新
//                if (!NetUtils.isConnected(getActivity())) {
//                    mAdapter.pauseMore();
//                    return;
//                }
//                page++;
//                //mAdapter.addAll(data.getData());
//                //if(page >= 3){
//                //    mAdapter.stopMore();
//                //}
//                Callback<BaseData> callback = new Callback<BaseData>() {
//                    public void handleCallback(BaseData result) {
//                        mAdapter.addAll((List<PlasticCategoryDataModel>) null);
//                    }
//                };
//                new Index2Interactor(null,
//                        null, callback, mTopRegion, mSubRegion, mType, "pro_id", page, GoodsFragment.ITEM_NUM_PER_PAGE).execute();
//            }
//        }, 1000);
//    }
//
//    @Override
//    public void onItemClick(int position) {
//        Toast.makeText(getActivity(), "第" + position + "位置被点击", Toast.LENGTH_SHORT).show();
//    }
//}
