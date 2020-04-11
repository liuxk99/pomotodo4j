package com.sj.pomotodo;

import java.util.ArrayList;

public class Todo {
    private String uuid;
    private String created_at;
    private String updated_at;
    private String description;
    private String notice;
    private boolean pin;
    private boolean completed;
    private String completed_at = null;
    private String repeat_type;
    private String remind_time = null;
    private String estimated_pomo_count = null;
    private float costed_pomo_count;
    ArrayList<String> sub_todos = new ArrayList<>();


    // Getter Methods
    public String getUuid() {
        return uuid;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getDescription() {
        return description;
    }

    public String getNotice() {
        return notice;
    }

    public boolean getPin() {
        return pin;
    }

    public boolean getCompleted() {
        return completed;
    }

    public String getCompleted_at() {
        return completed_at;
    }

    public String getRepeat_type() {
        return repeat_type;
    }

    public String getRemind_time() {
        return remind_time;
    }

    public String getEstimated_pomo_count() {
        return estimated_pomo_count;
    }

    public float getCosted_pomo_count() {
        return costed_pomo_count;
    }

    // Setter Methods

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public void setPin(boolean pin) {
        this.pin = pin;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setCompleted_at(String completed_at) {
        this.completed_at = completed_at;
    }

    public void setRepeat_type(String repeat_type) {
        this.repeat_type = repeat_type;
    }

    public void setRemind_time(String remind_time) {
        this.remind_time = remind_time;
    }

    public void setEstimated_pomo_count(String estimated_pomo_count) {
        this.estimated_pomo_count = estimated_pomo_count;
    }

    public void setCosted_pomo_count(float costed_pomo_count) {
        this.costed_pomo_count = costed_pomo_count;
    }

    @Override
    public String toString() {
        final String LF = "\n";
        StringBuilder sb = new StringBuilder("[" + this.getClass().getName() + "] {").append(LF);
        {
            sb.append("uuid:").append(uuid).append(LF);
            sb.append("created_at:").append(created_at).append(LF);
            sb.append("updated_at:").append(updated_at).append(LF);
            sb.append("description:").append(description).append(LF);
            sb.append("notice:").append(notice).append(LF);
            sb.append("pin:").append(pin).append(LF);
            sb.append("notice:").append(notice).append(LF);
            sb.append("completed:").append(completed).append(LF);
            sb.append("completed_at:").append(completed_at).append(LF);
            sb.append("repeat_type:").append(repeat_type).append(LF);
            sb.append("remind_time:").append(remind_time).append(LF);
            sb.append("estimated_pomo_count:").append(estimated_pomo_count).append(LF);
            sb.append("costed_pomo_count:").append(costed_pomo_count).append(LF);
            sb.append("estimated_pomo_count:").append(estimated_pomo_count).append(LF);
            sb.append("sub_todos:[").append(LF);
            {
                for (String sub_todo : sub_todos) {
                    sb.append("\t").append(sub_todo).append(LF);
                }
            }
            sb.append("]").append(LF);
        }
        sb.append("}").append(LF);

        return sb.toString();
    }
}
