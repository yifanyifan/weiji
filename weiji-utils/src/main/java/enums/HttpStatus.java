package enums;

public enum HttpStatus {
    SUCCESS(200, "成功"),
    ERROR(400, "失败");

    private Integer code;
    private String name;

    private HttpStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getName() {
        return name;
    }
}
