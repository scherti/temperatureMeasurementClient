package org.schertenleib;

public class MeasurementValue {
  private long measurementTime;
  private double temperatureValue;
  private String sensorId;

  public MeasurementValue(long measurementTime, double temperatureValue, String sensorId) {
    this.measurementTime = measurementTime;
    this.temperatureValue = temperatureValue;
    this.sensorId = sensorId;
  }
}
