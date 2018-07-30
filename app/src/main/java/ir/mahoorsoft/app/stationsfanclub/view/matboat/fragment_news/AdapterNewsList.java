package ir.mahoorsoft.app.stationsfanclub.view.matboat.fragment_news;

import android.content.Context;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;

import ir.mahoorsoft.app.stationsfanclub.model.struct.StNews;

/**
 * Created by M-gh on 30-Jul-18.
 */

public class AdapterNewsList extends RecyclerView.Adapter<AdapterNewsList.Holder> {

    private Context context;
    private ArrayList<StNews> source = new ArrayList<>();

    public AdapterNewsList(Context context, ArrayList<StNews> source) {
        this.context = context;
        this.source = source;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        StNews stLottery = source.get(position);
        holder.txtSubject.setText(stLottery.nSubject);
        holder.txtDate.setText(stLottery.nDate);
        holder.txtHours.setText(stLottery.nHourse);
        holder.txtNews.setText(stLottery.nText);
        Glide.with(context)
                .load("https://static2.borna.news/thumbnail/5KMtLPIam7ls/zKlnR2CgDMts54GkxMYEj8fvMMUiCETIT3Il7Z8QJ7YCZYBL8OGb1tkXRmfG0ZrOzG4dCpLzLSmmrKvCe4wUTpsw1L7Dn2vC/20795.jpg")
                .centerCrop()
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView txtSubject;
        TextView txtDate;
        TextView txtHours;
        TextView txtNews;
        ImageView img;
        LinearLayout llImg;

        public Holder(View itemView) {
            super(itemView);
            txtSubject = (TextView) itemView.findViewById(R.id.txtSubjectItemNews);
            txtDate = (TextView) itemView.findViewById(R.id.txtDateItemNews);
            txtHours = (TextView) itemView.findViewById(R.id.txtHourseItemNews);
            txtNews = (TextView) itemView.findViewById(R.id.txtNewsItemNews);
            img = (ImageView) itemView.findViewById(R.id.imgNews);
            llImg = (LinearLayout) itemView.findViewById(R.id.LLimgNews);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            G.activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            img.getLayoutParams().height = (int) (width / 2);
        }
    }
}
