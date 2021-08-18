package StudentPortal.src.model;

public class Fee {
    private double tuitionFee;
    private double examinationFee;
    private double totalFee;
    private boolean feePaid;
    private double feeDue;

    public Fee() {}

    public Fee(double tuitionFee, double examinationFee) {
        this.tuitionFee = tuitionFee;
        this.examinationFee = examinationFee;
        totalFee = tuitionFee + examinationFee;
    }

    public boolean getFeeClear() {
        return feePaid;
    }

    public double getFeeDue() {
        return feeDue;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public double getExaminationFee() {
        return examinationFee;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTuitionFee(double fee) {
        tuitionFee = fee;
    }

    public void setExaminationFee(double fee) {
        examinationFee = fee;
    }

    public void setFeePaid(boolean x) {
        feePaid = x;
    }
}
