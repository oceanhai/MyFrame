package step.model;

/**
 * 寻医问药  计步器用
 *
 * @author wuhai
 *         create at 2016/7/19 15:04
 */
public class StepDataModel {

    private String date;
    private int stepCount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    @Override
    public String toString() {
        return "StepDataModel{" +
                "date='" + date + '\'' +
                ", stepCount=" + stepCount +
                '}';
    }
}
