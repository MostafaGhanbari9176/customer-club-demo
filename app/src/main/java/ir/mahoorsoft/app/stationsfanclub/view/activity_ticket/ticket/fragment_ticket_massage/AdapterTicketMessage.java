package ir.mahoorsoft.app.stationsfanclub.view.activity_ticket.ticket.fragment_ticket_massage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StMessage;

/**
 * Created by M-gh on 01-Aug-18.
 */

public class AdapterTicketMessage extends RecyclerView.Adapter<AdapterTicketMessage.Holder> {

    private Context context;
    private ArrayList<StMessage> source = new ArrayList<>();
    private String lastDate = "";
    private int lastPosition;

    public AdapterTicketMessage(Context context, ArrayList<StMessage> source) {
        this.context = context;
        this.source = source;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ticket_message, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        StMessage item = source.get(position);
        if (item.answerText.length() == 0) {
            holder.txtAnswer.setVisibility(View.GONE);
            holder.txtAnswerDate.setVisibility(View.GONE);
        } else {
            if (item.answerDate.equals(item.qustionDate))
                holder.txtAnswerDate.setVisibility(View.GONE);
            else
                holder.txtAnswerDate.setVisibility(View.VISIBLE);
            holder.txtAnswer.setVisibility(View.VISIBLE);
        }
        if (item.qustionText.length() == 0) {
            holder.txtQuestion.setVisibility(View.GONE);
            holder.txtQuestionDate.setVisibility(View.GONE);
        } else {
            if (item.qustionDate.equals(lastDate))
                holder.txtQuestionDate.setVisibility(View.GONE);
            else
                holder.txtQuestionDate.setVisibility(View.VISIBLE);
            holder.txtQuestion.setVisibility(View.VISIBLE);
        }

        lastDate = item.qustionDate;

        holder.txtQuestion.setText(item.qustionText);
        holder.txtAnswer.setText(item.answerText);
        holder.txtAnswerDate.setText(item.answerDate);
        holder.txtQuestionDate.setText(item.qustionDate);

        lastPosition = G.setListItemsAnimation(new View[]{holder.txtAnswer, holder.txtQuestion}, null, position, lastPosition);

    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView txtQuestion;
        TextView txtAnswer;
        TextView txtQuestionDate;
        TextView txtAnswerDate;

        public Holder(View itemView) {
            super(itemView);

            txtQuestion = (TextView) itemView.findViewById(R.id.txtQuestion);
            txtAnswer = (TextView) itemView.findViewById(R.id.txtAnswer);
            txtQuestionDate = (TextView) itemView.findViewById(R.id.txtQuestionDate);
            txtAnswerDate = (TextView) itemView.findViewById(R.id.txtAnswerDate);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            G.activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            width = (4 * width) / 5;
            txtAnswer.setMaxWidth(width);
            txtQuestion.setMaxWidth(width);
        }
    }
}
