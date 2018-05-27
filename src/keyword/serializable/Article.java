package keyword.serializable;
import java.io.Serializable;
import java.util.Date;
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;  //文章标题
    private String content;  // 文章内容
    private String faceIcon;//表情图标
    private Date postTime; //文章发表的时间
    private String ipAddr;  //用户的ip

    private User author;  //回复的用户

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getFaceIcon() {
        return faceIcon;
    }
    public void setFaceIcon(String faceIcon) {
        this.faceIcon = faceIcon;
    }
    public Date getPostTime() {
        return postTime;
    }
    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
    public String getIpAddr() {
        return ipAddr;
    }
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
}