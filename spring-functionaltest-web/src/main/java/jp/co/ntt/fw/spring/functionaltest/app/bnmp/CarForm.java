/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.bnmp;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class CarForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String carId;

    private String carName;

    private String carColor;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private DateTime carReleaseDate;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public DateTime getCarReleaseDate() {
        return carReleaseDate;
    }

    public void setCarReleaseDate(DateTime carReleaseDate) {
        this.carReleaseDate = carReleaseDate;
    }
}
