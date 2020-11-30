package org.schertenleib;

public class MeasurementValues {
  private MeasurementValue[] values;

  public MeasurementValues() {
    this.values = new MeasurementValue[0];
  }

  public MeasurementValue[] getValues() {
    return values;
  }

  public void setValues(MeasurementValue[] values) {
    this.values = values;
  }

  public void addValue(MeasurementValue value) {
    this.values = addElementUsingPureJava(this.values, value);
  }

  public MeasurementValue[] addElementUsingPureJava(MeasurementValue[] srcArray,
      MeasurementValue elementToAdd) {
    MeasurementValue[] destArray = new MeasurementValue[srcArray.length + 1];

    for (int i = 0; i < srcArray.length; i++) {
      destArray[i] = srcArray[i];
    }

    destArray[destArray.length - 1] = elementToAdd;
    return destArray;
  }
}
