package com.tips.batch.entity;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FineDust implements Serializable {
//    //private String 계약월;
//    private String 거래금액;
//    private String 건축년도;
//    private String 년;
//    private String 법정동;
//    private String 아파트;
//    private String 월;
//    private String 일;
//    private String 전용면적;
//    private String 지번;
//    private String 지역코드;
//    private String 층;

    private String returnType; //": "json",
    private String coGrade    ; //": "1",
    private String coValue    ; //": "0.8",
    private String dataTerm   ; //": "",
    private String dataTime   ; //": "2020-01-15 10:00",
    private String khaiGrade  ; //": "2",
    private String khaiValue  ; //": "82",
    private String mangName   ; //": "도시대기",
    private String no2Grade   ; //": "2",
    private String no2Value   ; //": "0.036",
    private String numOfRows  ; //": "10",
    private String o3Grade    ; //": "1",
    private String o3Value    ; //": "0.007",
    private String pageNo     ; //": "1",
    private String pm10Grade  ; //": "2",
    private String pm10Grade1h; //": "2",
    private String pm10Value  ; //": "53",
    private String pm10Value24; //": "42",
    private String pm25Grade  ; //": "2",
    private String pm25Grade1h; //": "3",
    private String pm25Value  ; //": "37",
    private String pm25Value24; //": "28",
    private String resultCode ; //": "",
    private String resultMsg  ; //": "",
    private String rnum       ; //": 0,
    private String serviceKey ; //": "",
    private String sidoName   ; //": "",
    private String so2Grade   ; //": "1",
    private String so2Value   ; //": "0.005",
    private String stationCode; //": "",
    private String stationName; //": "중구",
    private String totalCount ; //": "",
    private String ver        ; //": ""
        
    // public String get계약월() {
    //     return this.계약월;
    // }

    // public void set계약월(String 계약월) {
    //     this.계약월 = 계약월;
    // }

    // public AptTradeDTO 계약월(String 계약월) {
    //     this.계약월 = 계약월;
    //     return this;
    // }

//    public String get거래금액() {
//        return this.거래금액;
//    }
//
//    public void set거래금액(String 거래금액) {
//        this.거래금액 = 거래금액;
//    }
//
//    public FineDust 거래금액(String 거래금액) {
//        this.거래금액 = 거래금액;
//        return this;
//    }
//
//    public String get건축년도() {
//        return this.건축년도;
//    }
//
//    public void set건축년도(String 건축년도) {
//        this.건축년도 = 건축년도;
//    }
//
//    public FineDust 건축년도(String 건축년도) {
//        this.건축년도 = 건축년도;
//        return this;
//    }
//
//    public String get년() {
//        return this.년;
//    }
//
//    public void set년(String 년) {
//        this.년 = 년;
//    }
//
//    public FineDust 년(String 년) {
//        this.년 = 년;
//        return this;
//    }
//
//    public String get법정동() {
//        return this.법정동;
//    }
//
//    public void set법정동(String 법정동) {
//        this.법정동 = 법정동;
//    }
//
//    public FineDust 법정동(String 법정동) {
//        this.법정동 = 법정동;
//        return this;
//    }
//
//    public String get아파트() {
//        return this.아파트;
//    }
//
//    public void set아파트(String 아파트) {
//        this.아파트 = 아파트;
//    }
//
//    public FineDust 아파트(String 아파트) {
//        this.아파트 = 아파트;
//        return this;
//    }
//
//    public String get월() {
//        return this.월;
//    }
//
//    public void set월(String 월) {
//        this.월 = 월;
//    }
//
//    public FineDust 월(String 월) {
//        this.월 = 월;
//        return this;
//    }
//
//    public String get일() {
//        return this.일;
//    }
//
//    public void set일(String 일) {
//        this.일 = 일;
//    }
//
//    public FineDust 일(String 일) {
//        this.일 = 일;
//        return this;
//    }
//
//    public String get전용면적() {
//        return this.전용면적;
//    }
//
//    public void set전용면적(String 전용면적) {
//        this.전용면적 = 전용면적;
//    }
//
//    public FineDust 전용면적(String 전용면적) {
//        this.전용면적 = 전용면적;
//        return this;
//    }
//
//    public String get지번() {
//        return this.지번;
//    }
//
//    public void set지번(String 지번) {
//        this.지번 = 지번;
//    }
//
//    public FineDust 지번(String 지번) {
//        this.지번 = 지번;
//        return this;
//    }
//
//    public String get지역코드() {
//        return this.지역코드;
//    }
//
//    public void set지역코드(String 지역코드) {
//        this.지역코드 = 지역코드;
//    }
//
//    public FineDust 지역코드(String 지역코드) {
//        this.지역코드 = 지역코드;
//        return this;
//    }
//
//    public String get층() {
//        return this.층;
//    }
//
//    public void set층(String 층) {
//        this.층 = 층;
//    }
//
//    public FineDust 층(String 층) {
//        this.층 = 층;
//        return this;
//    }
}