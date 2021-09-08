package com.example.codechallenge.provider.model.shared;

public enum OperationState {

    APPROVED("approved"),
    DECLINED("declined"),
    ERROR("error"),
    PENDING("pending");

    private String value;

    OperationState(String value) {
        this.value = value;
    }

    public String getvalue() {
        return this.value;
    }

    public static OperationState fromString(String value) {
        for (OperationState b : OperationState.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("No mapping for that response");
    }
}
