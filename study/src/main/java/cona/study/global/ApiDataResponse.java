package cona.study.global;

public class ApiDataResponse<T> {

    private T data;

    private ApiDataResponse(T data) {
        this.data = data;
    }

    public static <T> ApiDataResponse<T> of(T data) {
        return new ApiDataResponse<>(data);
    }

}
