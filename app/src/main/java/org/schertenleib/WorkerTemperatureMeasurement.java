package org.schertenleib;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.component.temperature.impl.TmpDS18B20DeviceType;
import com.pi4j.io.w1.W1Device;
import com.pi4j.io.w1.W1Master;
import java.util.List;

public class WorkerTemperatureMeasurement {
  final static int probeIntervalSeconds = 5;

  public void test() {
    try {
      Sender sender = new Sender();
//      Set<Sensor> sensors = Sensors.getSensors();
      W1Master master = new W1Master();
      while (true) {
//        Map<String, W1DeviceType> devices0 = master.getDeviceTypeMap();
//        List w1Devices1 = master.getDevices();
//        List w1Devices2 = master.getDevices(TmpDS18S20DeviceType.FAMILY_CODE);
        List<W1Device> w1Devices = master.getDevices(TmpDS18B20DeviceType.FAMILY_CODE);
//        List<MeasurementValue> allMeasurementValues = new ArrayList<>();
        MeasurementValues measurementValues = new MeasurementValues();
        for (W1Device device : w1Devices) {
          try {
            System.out.println("Device ID: " + device.getId());
            double temperature = ((TemperatureSensor) device).getTemperature();
            System.out
                .println("Device temperature: " + temperature);
            MeasurementValue measurementValue = new MeasurementValue(System.currentTimeMillis(),
                ((TemperatureSensor) device).getTemperature(), device.getId());
            measurementValues.addValue(measurementValue);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        sender.send(measurementValues);
        Thread.sleep(probeIntervalSeconds * 1000);
      }
//            w1Devices.clear();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
