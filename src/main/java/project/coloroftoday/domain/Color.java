package project.coloroftoday.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Color {
    private String colorid;
    private String colorname;

    protected Color() {}

    public Color(String id, String name) {
        colorid = id;
        colorname = name;
    }
}
