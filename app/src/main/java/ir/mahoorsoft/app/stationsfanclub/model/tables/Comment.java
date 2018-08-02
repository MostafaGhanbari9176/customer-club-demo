package ir.mahoorsoft.app.stationsfanclub.model.tables;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StComment;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StCustomer;

/**
 * Created by RCC1 on 7/31/2018.
 */

public class Comment {

    public interface OnCommentResponseListener {
        void onReceiveFlag(ArrayList<RFServer> res);

        void onReceiveComment(ArrayList<StComment> comments);

        void sendMessage(String message);
    }

    private OnCommentResponseListener onCommentResponseListener;

    public Comment(OnCommentResponseListener onCommentResponseListener) {
        this.onCommentResponseListener = onCommentResponseListener;
    }

    public void getComments() {

        ArrayList<StComment> stComments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StComment stComment = new StComment();
            stComment.comText = "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافی." + i;
            stComment.comDate = "1379-04-0" + i;
            stComment.cuName = "Mostafa" + i;
            stComment.rat = i;
            stComments.add(stComment);
        }

        onCommentResponseListener.onReceiveComment(stComments);
    }
}
