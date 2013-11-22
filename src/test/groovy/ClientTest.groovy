import groovy.mock.interceptor.StubFor
import groovyx.net.http.RESTClient
import net.sf.json.JSONObject
import org.joda.time.format.ISODateTimeFormat
import org.junit.Before
import org.junit.Test

class ClientTest {

  def expectedResponse
  def subject
  def mockHttp

  @Before
  void setup() {
    mockHttp = new StubFor(RESTClient.class)
    subject = new Client()
  }

  @Test
  void load_provider_json() {
    expectedResponse = [:]
    Map mockResponse = [success: true]
    mockHttp.demand.get() { opts, closure ->
      closure.call(mockResponse, expectedResponse)
    }
    mockHttp.use {
      def result = subject.load_provider_json()
      assert result == expectedResponse
    }
  }

  @Test
  void process_data() {
    def response = [
      test: "NO",
      valid_date: "2013-08-16T15:31:20+10:00",
      count: 1000
    ]
    Map mockResponse = [success: true]
    mockHttp.demand.get() { opts, closure ->
      closure.call(mockResponse, response as JSONObject)
    }
    mockHttp.use {
      def result = subject.process_data()
      def fmt = ISODateTimeFormat.dateTimeNoMillis()
      assert result == [10, fmt.parseDateTime("2013-08-16T15:31:20+10:00")]
    }
  }

}
