package entity;
// Generated Aug 1, 2020 2:29:25 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * FeedbackCatalogs generated by hbm2java
 */
public class FeedbackCatalogs implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeedbackCatalogId")
    private int feedbackCatalogId;
    @Column(name = "FeedbackCatalogName")
    private String feedbackCatalogName;
    @Column(name = "FeedbackCatalogStatus")
    private int feedbackCatalogStatus;
    private Set feedbackses = new HashSet(0);

    public FeedbackCatalogs() {
    }

    public FeedbackCatalogs(int feedbackCatalogId, String feedbackCatalogName, int feedbackCatalogStatus) {
        this.feedbackCatalogId = feedbackCatalogId;
        this.feedbackCatalogName = feedbackCatalogName;
        this.feedbackCatalogStatus = feedbackCatalogStatus;
    }

    public FeedbackCatalogs(int feedbackCatalogId, String feedbackCatalogName, int feedbackCatalogStatus, Set feedbackses) {
        this.feedbackCatalogId = feedbackCatalogId;
        this.feedbackCatalogName = feedbackCatalogName;
        this.feedbackCatalogStatus = feedbackCatalogStatus;
        this.feedbackses = feedbackses;
    }

    public int getFeedbackCatalogId() {
        return this.feedbackCatalogId;
    }

    public void setFeedbackCatalogId(int feedbackCatalogId) {
        this.feedbackCatalogId = feedbackCatalogId;
    }

    public String getFeedbackCatalogName() {
        return this.feedbackCatalogName;
    }

    public void setFeedbackCatalogName(String feedbackCatalogName) {
        this.feedbackCatalogName = feedbackCatalogName;
    }

    public int getFeedbackCatalogStatus() {
        return this.feedbackCatalogStatus;
    }

    public void setFeedbackCatalogStatus(int feedbackCatalogStatus) {
        this.feedbackCatalogStatus = feedbackCatalogStatus;
    }

    public Set getFeedbackses() {
        return this.feedbackses;
    }

    public void setFeedbackses(Set feedbackses) {
        this.feedbackses = feedbackses;
    }

}