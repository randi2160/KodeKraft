package khemlall.sample;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Body1 {
    private String mode;
    private String raw;

    public Body1(){
    }

    public Body1(String mode, String raw){
        this.mode = mode;
        this.raw = raw;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
