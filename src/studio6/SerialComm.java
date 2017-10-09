package studio6;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private static boolean debug;  // Indicator of "debugging mode"
	static byte bite = 0;
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String COM4) throws SerialPortException {
		port = new SerialPort(COM4);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method to write data to serial port
	public void writeByte() {
		try {
			port.writeByte(bite);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(debug)
			System.out.println(bite);
	}
	public boolean available() {
		try {
			if(port.getInputBufferBytesCount() >= 1){
				return true;
			}
			else {
				return false;
			}
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	public byte readByte() {
		try {
			return port.readBytes(1)[0];
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static void main(String[] args) {
		SerialComm comm;
		try {
			comm = new SerialComm("COM4");
			while(true) {
				if(comm.available()) {
					if(debug) {
						System.out.println(String.format("%02x", comm.readByte()));
					}
					else {
						char c = (char)comm.readByte();
						System.out.print(c);
					}
					
				}
			}
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
