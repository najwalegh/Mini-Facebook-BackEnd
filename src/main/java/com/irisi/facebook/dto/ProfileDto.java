package com.irisi.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private String id;
    private String status;
    private String citation;
    private String userId;
}
