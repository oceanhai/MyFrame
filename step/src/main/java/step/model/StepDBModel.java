package step.model;

/**
 * Created by yuwentao on 16/5/23.
 */
public class StepDBModel {


    private String userid;

    private String date;

    private int isupload;

    private int stepCount;

    private int isUploadPoints;

    public String getUserid() {
        return userid;
    }

    public void setUserid(final String userid) {
        this.userid = userid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public int getIsupload() {
        return isupload;
    }

    public void setIsupload(final int isupload) {
        this.isupload = isupload;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(final int stepCount) {
        this.stepCount = stepCount;
    }

    public int getIsUploadPoints() {
        return isUploadPoints;
    }

    public void setIsUploadPoints(int isUploadPoints) {
        this.isUploadPoints = isUploadPoints;
    }
}
