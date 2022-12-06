package za.flatrock.assessment.demo.models.enums;

public enum DialingCodeEnum {
    SOUTH_AFRICA("+27");

    private String code;

    DialingCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
