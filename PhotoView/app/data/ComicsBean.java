package data;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;


@Table(name = "news")
public class ComicsBean {
    @Column(name = "id", isId = true)
    private int id;
    private int info_type;
    private boolean can_view;
    private int updated_count;
    @Column(name = "image")
    private String cover_image_url;
    private int created_at;
    @Column(name = "title")
    private String title;
    private int updated_at;
    private int selling_kk_currency;
    private int zoomable;
    private int push_flag;
    private int comic_type;
    private boolean is_liked;
    private String label_color;
    private int storyboard_cnt;
    private String url;
    private int likes_count;
    private int comments_count;
    private String label_text;
    private boolean is_free;
    private int shared_count;
    private String label_text_color;
    private int serial_no;
    private String status;

    public int getInfo_type() {
        return info_type;
    }

    public void setInfo_type(int info_type) {
        this.info_type = info_type;
    }

    public boolean isCan_view() {
        return can_view;
    }

    public void setCan_view(boolean can_view) {
        this.can_view = can_view;
    }

    public int getUpdated_count() {
        return updated_count;
    }

    public void setUpdated_count(int updated_count) {
        this.updated_count = updated_count;
    }

    public String getCover_image_url() {
        return cover_image_url;
    }

    public void setCover_image_url(String cover_image_url) {
        this.cover_image_url = cover_image_url;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public int getSelling_kk_currency() {
        return selling_kk_currency;
    }

    public void setSelling_kk_currency(int selling_kk_currency) {
        this.selling_kk_currency = selling_kk_currency;
    }

    public int getZoomable() {
        return zoomable;
    }

    public void setZoomable(int zoomable) {
        this.zoomable = zoomable;
    }

    public int getPush_flag() {
        return push_flag;
    }

    public void setPush_flag(int push_flag) {
        this.push_flag = push_flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComic_type() {
        return comic_type;
    }

    public void setComic_type(int comic_type) {
        this.comic_type = comic_type;
    }

    public boolean isIs_liked() {
        return is_liked;
    }

    public void setIs_liked(boolean is_liked) {
        this.is_liked = is_liked;
    }

    public String getLabel_color() {
        return label_color;
    }

    public void setLabel_color(String label_color) {
        this.label_color = label_color;
    }

    public int getStoryboard_cnt() {
        return storyboard_cnt;
    }

    public void setStoryboard_cnt(int storyboard_cnt) {
        this.storyboard_cnt = storyboard_cnt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public String getLabel_text() {
        return label_text;
    }

    public void setLabel_text(String label_text) {
        this.label_text = label_text;
    }

    public boolean isIs_free() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
        this.is_free = is_free;
    }

    public int getShared_count() {
        return shared_count;
    }

    public void setShared_count(int shared_count) {
        this.shared_count = shared_count;
    }

    public String getLabel_text_color() {
        return label_text_color;
    }

    public void setLabel_text_color(String label_text_color) {
        this.label_text_color = label_text_color;
    }

    public int getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(int serial_no) {
        this.serial_no = serial_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
