package com.aloha.simplechat.domain;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Alias("Sample")                        // 별칭 (Mybatis package 생략용 - Mapper에서 사용)
public class Sample {
    
    private Long no;                    // PK
    private String id;                  // ID
    private String name;                // 이름
    private int value;                  // 값
    private Date createdAt;             // 생성일
    private Date updatedAt;             // 수정일

    public Sample() {
        this.id = UUID.randomUUID().toString();         // UUID로 ID 생성
    }
}
