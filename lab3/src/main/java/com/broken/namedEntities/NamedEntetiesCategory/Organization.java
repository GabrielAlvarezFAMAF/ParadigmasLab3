package com.broken.namedEntities.NamedEntetiesCategory;
import com.broken.namedEntities.NamedEntity;
import java.util.Date;
import java.util.List;
public class Organization extends NamedEntity {
    private Date fundationDate;
    private String website;
    public Organization(List<String> topics, String name) {
        super("Organization", topics, name);
    }
    public Organization(List<String> topics, String name, Date fundationDate, String website) {
        super("Organization", topics, name);
        this.fundationDate = fundationDate;
        this.website = website;
    }
    public Date getFundationDate(){
        return fundationDate;
    }
    public String getWebsite(){
        return website;
    }
}
