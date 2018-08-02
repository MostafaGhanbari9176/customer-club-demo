package ir.mahoorsoft.app.stationsfanclub.view.activity_station_feature.fragment_comment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StComment;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StNews;

/**
 * Created by M-gh on 30-Jul-18.
 */

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.Holder> {

    interface OnCommentItemClickListener {
        public void commentItemClicked(int position);
    }

    private OnCommentItemClickListener onCommentItemClickListener;

    private Context context;
    private ArrayList<StComment> source = new ArrayList<>();
    private int lastPosition;


    public AdapterComment(Context context, ArrayList<StComment> source, OnCommentItemClickListener onCommentItemClickListener) {
        this.context = context;
        this.source = source;
        this.onCommentItemClickListener = onCommentItemClickListener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        StComment stLottery = source.get(position);
        holder.txtComment.setText(stLottery.comText);
        holder.txtDate.setText(stLottery.comDate);
        holder.txtName.setText(stLottery.cuName);
        holder.ratingBar.setRating(stLottery.rat);
        holder.txtReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommentItemClickListener.commentItemClicked(position);
                G.setListItemsAnimation(new View[]{holder.txtReport}, null, 1, 0);
            }
        });
        lastPosition = G.setListItemsAnimation(new View[]{holder.cardView}, null, position, lastPosition);
    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView txtComment;
        TextView txtDate;
        TextView txtName;
        TextView txtReport;
        RatingBar ratingBar;
        CardView cardView;


        public Holder(View itemView) {
            super(itemView);
            txtComment = (TextView) itemView.findViewById(R.id.txtItemComment);
            txtDate = (TextView) itemView.findViewById(R.id.txtDateComment);
            txtName = (TextView) itemView.findViewById(R.id.txtNameItemComment);
            txtReport = (TextView) itemView.findViewById(R.id.txtReportComment);
            cardView = (CardView) itemView.findViewById(R.id.CVItemComment);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBarItemComment);
        }
    }
}
