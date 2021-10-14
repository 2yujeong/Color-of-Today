package project.coloroftoday.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Color {
    private String colorid;
    private String colorname;
    private String colornumber;

    protected Color() {}

    public Color(String id, String name, String num) {
        colorid = id;
        colorname = name;
        colornumber = num;
    }
}
