package com.example.yikezhong.ui.video_fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.HotVideoBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.ui.activity.videodetail.VideoDetailActivity;
import com.example.yikezhong.ui.base.BaseFragment;
import com.example.yikezhong.ui.video_fragment.adapter.HotVideoAdapter;
import com.example.yikezhong.ui.video_fragment.contract.HotVideoContract;
import com.example.yikezhong.ui.video_fragment.presenter.HotVideoPresenter;

/**
 * Created by lenovo on 2018/6/5.
 * 视频 热点
 */
public class HotVideo_Fragment extends BaseFragment<HotVideoPresenter> implements HotVideoContract.View {
    private RecyclerView rv;

    @Override
    public int getContentLayout() {
          return R.layout.hotvideo_fragment;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);

    }

    @Override
    public void initView(View view) {
        rv=view.findViewById(R.id.Hot_Video_Rv);
        mPresenter.getHotVideo();
    }

    @Override
    public void getHotVideoSuccess(final HotVideoBean adBean) {
        //设置视频列表适配器及布局管理器
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        HotVideoAdapter adapter=new HotVideoAdapter(getActivity(),adBean);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setItemClickListener(new HotVideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(), VideoDetailActivity.class);
                intent.putExtra("wid",adBean.getData().get(position).getWid()+"");
                startActivity(intent);
            }
        });
    }
}
