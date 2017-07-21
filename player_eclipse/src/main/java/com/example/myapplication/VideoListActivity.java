package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.widget.MediaHelp;
import com.example.myapplication.widget.VideoSuperPlayer;

/**
 * 视频列表
 * 
 * @author chenzf
 * @e-mail seven2729@126.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年3月21日15:05:26
 *
 */
public class VideoListActivity extends BaseActivity {
	private String url = "http://7xl1ve.com5.z0.glb.qiniucdn.com/2017/04/13/07/19/b712aff78e6b4d6699268c8168756569.mp4";
	private List<VideoBean> mList;
	private ListView mListView;
	private boolean isPlaying;
	private int indexPostion = -1;
	private MAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.list);
		mList = new ArrayList<VideoBean>();
		for (int i = 0; i < 10; i++) {
			mList.add(new VideoBean(url));
		}
		mAdapter = new MAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if ((indexPostion < mListView.getFirstVisiblePosition() || indexPostion > mListView
						.getLastVisiblePosition()) && isPlaying) {
					indexPostion = -1;
					isPlaying = false;
					mAdapter.notifyDataSetChanged();
					MediaHelp.release();
				}
			}
		});
	}

	class MAdapter extends BaseAdapter {
		private Context context;
		LayoutInflater inflater;

		public MAdapter(Context context) {
			this.context = context;
			inflater = LayoutInflater.from(context);
		}

		@Override
		public VideoBean getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public View getView(int position, View v, ViewGroup parent) {
			GameVideoViewHolder holder = null;
			if (v == null) {
				holder = new GameVideoViewHolder();
				v = inflater.inflate(R.layout.list_video_item, parent, false);
				holder.mVideoViewLayout = (VideoSuperPlayer) v
						.findViewById(R.id.video);
				holder.mPlayBtnView = (ImageView) v.findViewById(R.id.play_btn);
				holder.icon = (ImageView) v.findViewById(R.id.icon);
				holder.info_title = (TextView) v.findViewById(R.id.info_title);
				holder.info_title_two = (TextView) v.findViewById(R.id.info_title_two);
				v.setTag(holder);
			} else {
				holder = (GameVideoViewHolder) v.getTag();
			}
			holder.mPlayBtnView.setOnClickListener(new MyOnclick(
					holder.mPlayBtnView, holder.mVideoViewLayout, position));
			if(position%2 == 0)
			{
				holder.info_title.setVisibility(View.VISIBLE);
				holder.info_title_two.setVisibility(View.GONE);
				holder.info_title.setText("心情不好的时候，请别忘了微笑哦！");
			}
			else
			{
				holder.info_title_two.setVisibility(View.VISIBLE);
				holder.info_title.setVisibility(View.GONE);
				holder.info_title_two.setText( "这是一个悲伤的故事" + position);
			}
			
			if(position == 0)
			{
				holder.icon.setImageResource(R.drawable.img_a);
			}
			else if(position == 1)
			{
				holder.icon.setImageResource(R.drawable.img_two);
			}
			else if(position == 2)
			{
				holder.icon.setImageResource(R.drawable.img_three);
			}
			else
			{
				if(position%2 == 0)
				{
					holder.icon.setImageResource(R.drawable.img_a);
				}
				else
				{
					holder.icon.setImageResource(R.drawable.img_video);
				}
			}
			
			if (indexPostion == position) {
				holder.mVideoViewLayout.setVisibility(View.VISIBLE);
			} else {
				holder.mVideoViewLayout.setVisibility(View.GONE);
				holder.mVideoViewLayout.close();
			}
			return v;
		}

		class MyOnclick implements OnClickListener {
			VideoSuperPlayer mSuperVideoPlayer;
			ImageView mPlayBtnView;
			int position;

			public MyOnclick(ImageView mPlayBtnView,
					VideoSuperPlayer mSuperVideoPlayer, int position) {
				this.position = position;
				this.mSuperVideoPlayer = mSuperVideoPlayer;
				this.mPlayBtnView = mPlayBtnView;
			}

			@Override
			public void onClick(View v) {
				MediaHelp.release();
				indexPostion = position;
				isPlaying = true;
				mSuperVideoPlayer.setVisibility(View.VISIBLE);
				mSuperVideoPlayer.loadAndPlay(MediaHelp.getInstance(), mList
						.get(position).getUrl(), 0, false);
				mSuperVideoPlayer.setVideoPlayCallback(new MyVideoPlayCallback(
						mPlayBtnView, mSuperVideoPlayer, mList.get(position)));
				notifyDataSetChanged();
			}
		}

		class MyVideoPlayCallback implements VideoSuperPlayer.VideoPlayCallbackImpl {
			ImageView mPlayBtnView;
			VideoSuperPlayer mSuperVideoPlayer;
			VideoBean info;

			public MyVideoPlayCallback(ImageView mPlayBtnView,
					VideoSuperPlayer mSuperVideoPlayer, VideoBean info) {
				this.mPlayBtnView = mPlayBtnView;
				this.info = info;
				this.mSuperVideoPlayer = mSuperVideoPlayer;
			}

			@Override
			public void onCloseVideo() {
				closeVideo();
			}

			@Override
			public void onSwitchPageType() {
				if (((Activity) context).getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
					Intent intent = new Intent(new Intent(context,
							FullVideoActivity.class));
					intent.putExtra("video", info);
					intent.putExtra("position",
							mSuperVideoPlayer.getCurrentPosition());
					((Activity) context).startActivityForResult(intent, 1);
				}
			}

			@Override
			public void onPlayFinish() {
				closeVideo();
			}

			private void closeVideo() {
				isPlaying = false;
				indexPostion = -1;
				mSuperVideoPlayer.close();
				MediaHelp.release();
				mPlayBtnView.setVisibility(View.VISIBLE);
				mSuperVideoPlayer.setVisibility(View.GONE);
			}

		}

		class GameVideoViewHolder {
			private VideoSuperPlayer mVideoViewLayout;
			private ImageView mPlayBtnView, icon;
			private TextView info_title, info_title_two;
		}

	}
	
	/**
	 * 监听生命周期
	 */
	@Override
	protected void onDestroy() {
		MediaHelp.release();
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		MediaHelp.resume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		MediaHelp.pause();
		super.onPause();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		MediaHelp.getInstance().seekTo(data.getIntExtra("position", 0));
	}


}
