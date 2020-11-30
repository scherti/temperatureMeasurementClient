package org.schertenleib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class Sender {
  private static final String URL = "http://schertenleib.org:8090";

  GsonBuilder gsonBuilder = new GsonBuilder();
  Gson gson = gsonBuilder.create();

  public void testGet() throws IOException {
    try {
      ClientRequest request = new ClientRequest(
//          "http://schertenleib.org:8090/temperatureData?startDate=1&endDate=2&sensorId=def2");
          URL + "/temperatureData?startDate=1&endDate=2");
      request.accept("application/json");
      ClientResponse<String> response = request.get(String.class);

      if (response.getStatus() != 200) {
        throw new RuntimeException("Failed : HTTP error code : "
            + response.getStatus());
      }

      BufferedReader br = new BufferedReader(new InputStreamReader(
          new ByteArrayInputStream(response.getEntity().getBytes())));

      String output;
      System.out.println("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public void send(MeasurementValues measurementValues) {
    try {
      ClientRequest request = new ClientRequest(URL + "/temperatureData");
      request.accept("application/json");

      String json = gson.toJson(
          measurementValues
      );
      request.body("application/json", json);

      ClientResponse<String> response = request.post(String.class);

      if (response.getStatus() != 200) {
        throw new RuntimeException("Failed : HTTP error code : "
            + response.getStatus());
      }

      BufferedReader br = new BufferedReader(new InputStreamReader(
          new ByteArrayInputStream(response.getEntity().getBytes())));

      String output;
      System.out.println("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
