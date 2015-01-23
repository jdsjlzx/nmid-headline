package cn.edu.cqupt.nmid.headline.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.edu.cqupt.nmid.headline.R;
import cn.edu.cqupt.nmid.headline.support.api.headline.bean.Feed;
import cn.edu.cqupt.nmid.headline.support.pref.ThemePref;
import cn.edu.cqupt.nmid.headline.ui.activity.DetailedActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by leon on 1/19/15.
 */
public class SwipeAdapter extends HeaderFooterRecyclerViewAdapter {

  private ArrayList<Feed> mFeeds;
  private Context mContext;

  public SwipeAdapter(Context mContext, ArrayList<Feed> feeds) {
    this.mContext = mContext;
    this.mFeeds = feeds;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override protected int getHeaderItemCount() {
    return 0;
  }

  @Override protected int getFooterItemCount() {
    return 1;
  }

  @Override protected int getContentItemCount() {
    return mFeeds.size();
  }

  @Override protected RecyclerView.ViewHolder onCreateHeaderItemViewHolder(ViewGroup parent,
      int headerViewType) {
    return null;
  }

  @Override protected RecyclerView.ViewHolder onCreateFooterItemViewHolder(ViewGroup parent,
      int footerViewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.include_progressbar, parent, false);
    return new FooterViewHolder(view);
  }

  @Override protected RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent,
      int contentViewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_content, parent, false);
    return new ContentViewHolder(view);
  }

  @Override protected void onBindHeaderItemViewHolder(RecyclerView.ViewHolder headerViewHolder,
      int position) {

  }

  @Override protected void onBindFooterItemViewHolder(RecyclerView.ViewHolder footerViewHolder,
      int position) {

  }

  @Override protected void onBindContentItemViewHolder(RecyclerView.ViewHolder holder,
      int position) {



    ContentViewHolder holder_typed = (ContentViewHolder) holder;

    holder_typed.mCardView.setCardBackgroundColor(mContext.getResources().getColor(
        ThemePref.getItemBackgroundResColor(mContext)));
    final Feed feed = mFeeds.get(position);
    holder_typed.title.setText(feed.getTitle());
    holder_typed.time.setText(feed.getId() +   " ");
    if ("".equals(feed.getImage1())) {
      holder_typed.image1.setVisibility(View.GONE);
      holder_typed.threeImagesLayout.setVisibility(View.GONE);
      holder_typed.excerpt.setText(feed.getSimple_content());
    } else if ("".equals(feed.getImage2())) {
      holder_typed.threeImagesLayout.setVisibility(View.GONE);
      Picasso.with(mContext).load(feed.getImage1()).into(holder_typed.image1);
      holder_typed.excerpt.setText(feed.getSimple_content());
    } else if ("".equals(feed.getImage3())) {
      holder_typed.image1.setVisibility(View.GONE);
      holder_typed.threeImagesLayout.setVisibility(View.GONE);
      holder_typed.excerpt.setText(feed.getSimple_content());
    } else {
      holder_typed.image1.setVisibility(View.GONE);
      holder_typed.excerpt.setVisibility(View.GONE);
      Picasso.with(mContext).load(feed.getImage1()).into(holder_typed.image2);
      Picasso.with(mContext).load(feed.getImage2()).into(holder_typed.image3);
      Picasso.with(mContext).load(feed.getImage3()).into(holder_typed.image4);
    }
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent(mContext, DetailedActivity.class);
        intent.putExtra("id",feed.getId());
        intent.putExtra("category",feed.getCategory());
        mContext.startActivity(intent);
      }
    });
  }

  public static class ContentViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.item_card_holder) CardView mCardView;
    @InjectView(R.id.item_title) TextView title;
    @InjectView(R.id.item_time) TextView time;
    @InjectView(R.id.item_excerpt) TextView excerpt;
    @InjectView(R.id.item_image1) ImageView image1;
    @InjectView(R.id.item_image2) ImageView image2;
    @InjectView(R.id.item_image3) ImageView image3;
    @InjectView(R.id.item_image4) ImageView image4;
    @InjectView(R.id.item_three_images_layout) LinearLayout threeImagesLayout;

    public ContentViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }

  public static class FooterViewHolder extends RecyclerView.ViewHolder {

    public FooterViewHolder(View itemView) {
      super(itemView);
    }
  }
}
