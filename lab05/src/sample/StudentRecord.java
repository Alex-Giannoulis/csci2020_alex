package sample;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentRecord{
    SimpleStringProperty StudId;
    SimpleFloatProperty midterm;
    SimpleFloatProperty assignment;
    SimpleFloatProperty finalExam;
    SimpleFloatProperty finalMark;
    SimpleStringProperty letterGrade;

    public StudentRecord(String SID, float assign, float mid, float fExam){
        this.StudId = new SimpleStringProperty(SID);
        this.midterm = new SimpleFloatProperty(mid);
        this.assignment = new SimpleFloatProperty(assign);
        this.finalExam = new SimpleFloatProperty(fExam);
        this.finalMark = new SimpleFloatProperty(calculateFMark(getMidterm(), getAssignment(), getFinalExam()));
        this.letterGrade = new SimpleStringProperty(calculateLGrade(getFinalMark()));
    }
    public String getStudId(){
        return StudId.get();
    }
    public void setStudId(String S){
        StudId.set(S);
    }
    public float getMidterm(){
        return midterm.get();
    }
    public void setMidterm(float M){
        midterm.set(M);
    }
    public float getAssignment(){
        return assignment.get();
    }
    public void setAssignment(float A){
        assignment.set(A);
    }
    public float getFinalExam(){
        return finalExam.get();
    }
    public void setFinalExam(float FE){
        finalExam.set(FE);
    }
    public float getFinalMark(){
        return finalMark.get();
    }
    public void setFinalMark(float FM){
        finalMark.set(FM);
    }
    public String getLetterGrade(){
        return letterGrade.get();
    }
    public void setLetterGrade(String LG){
        letterGrade.set(LG);
    }
    protected float calculateFMark(float m, float a, float fE){
        return((0.3f*m)+(0.2f*a)+(0.5f*fE));
    }
    protected String calculateLGrade(float mark){
        if(mark >= 80f){
            return "A";
        }
        else if(mark >= 70f && mark<80f){
            return "B";
        }
        else if(mark >= 60f && mark<70f){
            return "C";
        }
        else if(mark>= 50f && mark<60f){
            return "D";
        }
        else{
            return "F";
        }

    }

};
