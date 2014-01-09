package CetaCiemat.Tempo;

import java.util.Date;

/**
 *
 * @author csuarez
 */
public class WorkLog {
    private String issueKey;
    private Float hours;
    private Date workDate;
    private String username;
    private String workDescription;
    private Integer dotProjectTaskId;

    /**
     * @return the issueKey
     */
    public String getIssueKey() {
        return issueKey;
    }

    /**
     * @param issueKey the issueKey to set
     */
    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    /**
     * @return the hours
     */
    public Float getHours() {
        return hours;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(Float hours) {
        this.hours = hours;
    }

    /**
     * @return the workDate
     */
    public Date getWorkDate() {
        return workDate;
    }

    /**
     * @param workDate the workDate to set
     */
    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the workDescription
     */
    public String getWorkDescription() {
        return workDescription;
    }

    /**
     * @param workDescription the workDescription to set
     */
    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    /**
     * @return the dotProjectUrl
     */
    public Integer getDotProjectTaskId() {
        return dotProjectTaskId;
    }

    /**
     * @param dotProjectUrl the dotProjectUrl to set
     */
    public void setDotProjectTaskId(Integer dotProjectTaskId) {
        this.dotProjectTaskId = dotProjectTaskId;
    }
}
