package ir.mahoorsoft.app.stationsfanclub.view.activity_station_feature.fragment_comment;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StComment;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentComment;


/**
 * Created by RCC1 on 4/28/2018.
 */

public class FragmentComment extends Fragment implements PresentComment.OnPresentCommentResponseListener, AdapterComment.OnCommentItemClickListener {
    FloatingActionButton fab;
    View view;
    RecyclerView list;
    ArrayList<StComment> source = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_comment, container, false);
        init();

        return view;
    }

    private void init() {
        pointer();
        getNewsDataFromServer();

    }

    private void pointer() {
        fab = (FloatingActionButton) view.findViewById(R.id.fabFragmentComment);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGetCommentDataDialog();
            }
        });
        list = (RecyclerView) view.findViewById(R.id.RVFragmentComment);
    }

    private void showGetCommentDataDialog() {
        final Dialog dialog = new Dialog(G.context);
        LayoutInflater li = (LayoutInflater) G.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.dialog_comment, null, false);
        final RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratBDialogComment);
        final TextView textView = (TextView) view.findViewById(R.id.txtDialogComment);
        Button btnConfirm = (Button) view.findViewById(R.id.btnConfirmDialogComment);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancelDialogComment);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCommentData(textView, ratingBar)) {
                    //addComment(textView.getText().toString(), ratingBar.getRating());
                    dialog.cancel();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    private boolean checkCommentData(TextView txt, RatingBar ratBar) {

        Animation animation = new TranslateAnimation(0, 500, 0, 0);
        animation.setDuration(10000);
        animation.setFillAfter(true);

        Animation animation2 = new TranslateAnimation(0, 0, 500, 0);
        animation.setDuration(10000);
        animation.setFillAfter(true);

        if (ratBar.getRating() == 0) {
            ratBar.startAnimation(animation);
            ratBar.setVisibility(View.GONE);
            ratBar.startAnimation(animation2);
            ratBar.setVisibility(View.VISIBLE);
//            ratBar.clearAnimation();

        } else if (TextUtils.isEmpty(txt.getText().toString().trim()))
            txt.setError("یه نظر ثبت کنید");
        else
            return true;
        return false;
    }

    private void getNewsDataFromServer() {
        (new PresentComment(this)).getComment();

    }


    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(G.context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void messageFromComment(String message) {

    }

    @Override
    public void flagFromComment(boolean flag) {

    }

    @Override
    public void dataFromComment(ArrayList<StComment> stComments) {
        source.clear();
        source.addAll(stComments);
        AdapterComment adapterLotteryList = new AdapterComment(G.context, stComments, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(G.context, LinearLayout.VERTICAL, false);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapterLotteryList);
        adapterLotteryList.notifyDataSetChanged();
    }

    @Override
    public void commentItemClicked(int position) {
        reportComment();
    }

    private void reportComment() {
        AlertDialog.Builder builder = new AlertDialog.Builder(G.context);
        builder.setTitle("گزارش این نظر");
        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Snackbar snackbar = Snackbar.make(view, "ازبازخورد شما متشکریم", 1000);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(ContextCompat.getColor(G.context, R.color.primaryLightColor));
                snackbar.show();
            }
        });

        builder.show();
    }
}