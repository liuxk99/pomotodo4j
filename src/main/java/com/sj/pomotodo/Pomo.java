package com.sj.pomotodo;

import java.util.Date;
/**
 * Auto-generated: 2020-08-16 18:9:7
 *
 * @author www.jsons.cn
 * @website http://www.jsons.cn/json2java/
 */
public class Pomo {

    private String uuid;

    private Date created_at;

    private Date updated_at;
    private String description;

    private Date started_at;

    private Date ended_at;

    private Date local_started_at;

    private Date local_ended_at;
    private int length;
    private boolean abandoned;
    private boolean manual;
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUuid() {
        return uuid;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public Date getCreated_at() {
        return created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    public Date getUpdated_at() {
        return updated_at;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setStarted_at(Date started_at) {
        this.started_at = started_at;
    }
    public Date getStarted_at() {
        return started_at;
    }

    public void setEnded_at(Date ended_at) {
        this.ended_at = ended_at;
    }
    public Date getEnded_at() {
        return ended_at;
    }

    public void setLocal_started_at(Date local_started_at) {
        this.local_started_at = local_started_at;
    }
    public Date getLocal_started_at() {
        return local_started_at;
    }

    public void setLocal_ended_at(Date local_ended_at) {
        this.local_ended_at = local_ended_at;
    }
    public Date getLocal_ended_at() {
        return local_ended_at;
    }

    public void setLength(int length) {
        this.length = length;
    }
    public int getLength() {
        return length;
    }

    public void setAbandoned(boolean abandoned) {
        this.abandoned = abandoned;
    }
    public boolean getAbandoned() {
        return abandoned;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }
    public boolean getManual() {
        return manual;
    }

    @Override
    public String toString() {
        final String LF = "\n";
        StringBuilder sb = new StringBuilder("[" + this.getClass().getName() + "] {").append(LF);
        {
            sb.append(" uuid:").append(uuid).append(LF);
            sb.append(" created_at:").append(created_at).append(LF);
            sb.append(" updated_at:").append(updated_at).append(LF);
            sb.append(" description:").append(description).append(LF);
            sb.append(" started_at:").append(started_at).append(LF);
            sb.append(" ended_at:").append(ended_at).append(LF);
            sb.append(" local_started_at:").append(local_started_at).append(LF);
            sb.append(" local_ended_at").append(local_ended_at).append(LF);
            sb.append(" length:").append(length).append(LF);
            sb.append(" abandoned:").append(abandoned).append(LF);
            sb.append(" manual:").append(manual).append(LF);
        }
        sb.append("}").append(LF);

        return sb.toString();
    }

    public String toText() {
        final String LF = "\n";
        StringBuilder sb = new StringBuilder();
        {
            sb.append("created time: ").append(getCreated_at()).append(LF);
            sb.append("description: ").append(getDescription()).append(LF);
        }
        return sb.toString();
    }
}