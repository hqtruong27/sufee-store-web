package entity;
// Generated Aug 1, 2020 2:29:25 PM by Hibernate Tools 4.3.1


import java.io.Serializable;

/**
 * Introductions generated by hbm2java
 */
public class Introductions  implements java.io.Serializable {


     private int introductionId;
     private String introductionContent;
     private int introductionStatus;

    public Introductions() {
    }

    public Introductions(int introductionId, String introductionContent, int introductionStatus) {
       this.introductionId = introductionId;
       this.introductionContent = introductionContent;
       this.introductionStatus = introductionStatus;
    }
   
    public int getIntroductionId() {
        return this.introductionId;
    }
    
    public void setIntroductionId(int introductionId) {
        this.introductionId = introductionId;
    }
    public String getIntroductionContent() {
        return this.introductionContent;
    }
    
    public void setIntroductionContent(String introductionContent) {
        this.introductionContent = introductionContent;
    }
    public int getIntroductionStatus() {
        return this.introductionStatus;
    }
    
    public void setIntroductionStatus(int introductionStatus) {
        this.introductionStatus = introductionStatus;
    }




}

