package com.example.codechallenge.provider.model.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationStatus {

    ANTIFRAUD_DECLINED("Declined by antifraud");

    private final String description;
}
